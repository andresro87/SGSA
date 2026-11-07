

package ar.edu.utn.sgsa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SistemaGestionTest {

    private SistemaGestion sistema;
    private final Path archivo = Path.of("data/solicitudes.json");

    @BeforeEach
    void preparar() throws Exception {
        Files.deleteIfExists(archivo);
        sistema = new SistemaGestion();
    }

    @AfterEach
    void limpiar() throws Exception {
        Files.deleteIfExists(archivo);
    }

    private void registrar(
            String nombre,
            String legajo,
            String email,
            String tipo,
            int prioridad,
            String descripcion
    ) {
        sistema.registrarSolicitud(
                new DatosSolicitud(
                        nombre,
                        legajo,
                        email,
                        tipo,
                        prioridad,
                        descripcion
                )
        );
    }

    @Test
    void debeRegistrarSolicitudCorrectamente() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado de alumno regular"
        );

        List<Solicitud> solicitudes = sistema.obtenerSolicitudes();

        assertEquals(1, solicitudes.size());
        assertEquals(1, solicitudes.get(0).getId());
        assertEquals("NUEVA", solicitudes.get(0).getEstado());
        assertEquals("CERTIFICADO", solicitudes.get(0).getTipo());
    }

    @Test
    void noDebeRegistrarSolicitudConNombreVacio() {

        registrar(
                "",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        assertTrue(sistema.obtenerSolicitudes().isEmpty());
    }

    @Test
    void debeCambiarEstadoDeNuevaAEnProceso() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        sistema.cambiarEstado(1, "EN_PROCESO");

        assertEquals(
                "EN_PROCESO",
                sistema.obtenerSolicitudes().get(0).getEstado()
        );
    }

    @Test
    void noDebePermitirSaltoDeEstadoInvalido() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        sistema.cambiarEstado(1, "RESUELTA");

        assertEquals(
                "NUEVA",
                sistema.obtenerSolicitudes().get(0).getEstado()
        );
    }

    @Test
    void debeAsignarResponsableCorrectamente() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        sistema.asignarResponsable(
                1,
                "Laura Fernandez",
                "Alumnado"
        );

        Responsable responsable =
                sistema.obtenerSolicitudes().get(0).getResponsable();

        assertNotNull(responsable);
        assertEquals("Laura Fernandez", responsable.getNombre());
        assertEquals("Alumnado", responsable.getArea());
    }

    @Test
    void debeFiltrarSolicitudesPorTipoYEstado() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        registrar(
                "Maria Gomez",
                "23456",
                "maria@email.com",
                "EQUIVALENCIA",
                1,
                "Solicito equivalencia"
        );

        sistema.cambiarEstado(1, "EN_PROCESO");

        List<Solicitud> resultados =
                sistema.buscarSolicitudes("NUEVA", "EQUIVALENCIA", null);

        assertEquals(1, resultados.size());
        assertEquals(
                "Maria Gomez",
                resultados.get(0).getEstudiante().getNombre()
        );
    }

    @Test
    void debeGuardarYRecuperarSolicitudesDesdeJson() {

        registrar(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        SistemaGestion sistemaRecuperado = new SistemaGestion();

        assertEquals(1, sistemaRecuperado.obtenerSolicitudes().size());
        assertEquals(
                "Juan Perez",
                sistemaRecuperado.obtenerSolicitudes()
                        .get(0).getEstudiante().getNombre()
        );
    }
    @Test
    void noDebeRegistrarSolicitudConLegajoVacio() {
        DatosSolicitud datos = new DatosSolicitud(
                "Juan Pérez",
                "",
                "juan@email.com",
                "CERTIFICADO",
                3,
                "Solicito certificado de alumno regular"
        );

        assertThrows(IllegalArgumentException.class,
                () -> sistema.registrarSolicitud(datos));
    }

    @Test
    void noDebeRegistrarSolicitudConPrioridadInvalida() {
        DatosSolicitud prioridadBaja = new DatosSolicitud(
                "Juan Pérez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                0,
                "Solicito certificado"
        );

        DatosSolicitud prioridadAlta = new DatosSolicitud(
                "Juan Pérez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                6,
                "Solicito certificado"
        );

        assertThrows(IllegalArgumentException.class,
                () -> sistema.registrarSolicitud(prioridadBaja));

        assertThrows(IllegalArgumentException.class,
                () -> sistema.registrarSolicitud(prioridadAlta));
    }

    @Test
    void noDebeAsignarResponsableASolicitudResuelta() {
        DatosSolicitud datos = new DatosSolicitud(
                "Juan Pérez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                3,
                "Solicito certificado"
        );

        Solicitud solicitud = sistema.registrarSolicitud(datos);

        sistema.cambiarEstado(solicitud.getId(), "EN_PROCESO");
        sistema.cambiarEstado(solicitud.getId(), "RESUELTA");

        assertThrows(IllegalStateException.class,
                () -> sistema.asignarResponsable(
                        solicitud.getId(),
                        "María Gómez",
                        "Secretaría"
                ));
    }

    @Test
    void noDebeCambiarEstadoDeSolicitudInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> sistema.cambiarEstado(999, "EN_PROCESO"));
    }

    @Test
    void noDebePermitirModificarListaInternaDeSolicitudes() {
        assertThrows(UnsupportedOperationException.class,
                () -> sistema.obtenerSolicitudes().clear());
    }
}
