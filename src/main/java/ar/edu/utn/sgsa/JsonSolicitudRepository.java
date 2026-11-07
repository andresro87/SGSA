
package ar.edu.utn.sgsa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonSolicitudRepository {

    private final Gson gson;
    private final Path archivo;

    public JsonSolicitudRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();

        archivo = Path.of(
                System.getProperty("user.dir"),
                "data",
                "solicitudes.json"
        );
    }

    public void guardar(List<Solicitud> solicitudes) {

        try {
            Files.createDirectories(archivo.getParent());

            try (FileWriter writer = new FileWriter(
                    archivo.toFile(),
                    StandardCharsets.UTF_8
            )) {
                gson.toJson(solicitudes, writer);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudieron guardar las solicitudes.",
                    e
            );
        }
    }

    public List<Solicitud> cargar() {

        if (!Files.exists(archivo)) {
            List<Solicitud> solicitudes = new ArrayList<>();
            guardar(solicitudes);
            return solicitudes;
        }

        try (FileReader reader = new FileReader(
                archivo.toFile(),
                StandardCharsets.UTF_8
        )) {

            Type tipoLista =
                    new TypeToken<ArrayList<Solicitud>>() {}.getType();

            List<Solicitud> solicitudes = gson.fromJson(reader, tipoLista);

            return solicitudes != null
                    ? solicitudes
                    : new ArrayList<>();

        } catch (IOException | JsonParseException e) {
            throw new IllegalStateException(
                    "No se pudieron recuperar las solicitudes. "
                            + "El archivo JSON no será sobrescrito.",
                    e
            );
        }
    }
}

