package net.kender.version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class READ {
    private Version V = null;

    public READ(File e) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(e);
            JsonNode versionNode = root.get("version");
            String version = versionNode.asText();
            Version v = new Version(version);
            V = v;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void Compare(Runnable exe, URI database) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            // Realizar una solicitud HTTP GET a la URL
            HttpURLConnection conn = (HttpURLConnection) database.toURL().openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonNode rootN = objectMapper.readTree(response.toString());
            JsonNode root = rootN.get("record");
            JsonNode versionNode = root.get("version");
            String version = versionNode.asText();
            Version v = new Version(version);
            if (v.getMajor() != V.getMajor()) {
                exe.run();
            } else {
                if (v.getMinor() != V.getMinor()) {
                    exe.run();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
