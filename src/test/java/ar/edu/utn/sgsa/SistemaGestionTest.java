
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

    @Test
    void debeRegistrarSolicitudCorrectamente() {

        sistema.registrarSolicitud(
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

        sistema.registrarSolicitud(
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

        sistema.registrarSolicitud(
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

        sistema.registrarSolicitud(
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

        sistema.registrarSolicitud(
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

        sistema.registrarSolicitud(
                "Juan Perez",
                "12345",
                "juan@email.com",
                "CERTIFICADO",
                2,
                "Solicito certificado"
        );

        sistema.registrarSolicitud(
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
        assertEquals("Maria Gomez",
                resultados.get(0).getEstudiante().getNombre());
    }

    @Test
    void debeGuardarYRecuperarSolicitudesDesdeJson() {

        sistema.registrarSolicitud(
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
}
