package net.kender.Kjson;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import net.kender.logger.log5k.Logger;
import net.kender.lt.Funtions;
import net.kender.lt.Main;

public class Json {
    private File e = new File("");

    private static final Logger logger = new Logger(Json.class);

    public Json(File a) {
        e = a;
    }

    public void registerString(String obj, String dat) throws IOException {

        // Crear un nuevo objeto ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode existingJsonNode = (ObjectNode) objectMapper.readTree(e);

        // Agregar un nuevo campo directamente al objeto JSON base
        existingJsonNode.put(obj, dat);

        // Guardar el JSON actualizado de nuevo en el archivo
        objectMapper.writeValue(e, existingJsonNode);

        logger.INFO( "config json: " + e + " is updated");
        // Hacer algo con boolValue
    }

    public void registerBool(String obj, Boolean dat) throws IOException {

        // Crear un nuevo objeto ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Cargar el JSON existente desde un archivo
        ObjectNode existingJsonNode = (ObjectNode) objectMapper.readTree(e);

        // Agregar un nuevo campo directamente al objeto JSON base
        existingJsonNode.put(obj, dat);

        // Guardar el JSON actualizado de nuevo en el archivo
        objectMapper.writeValue(e, existingJsonNode);
        logger.DEBUG( "config json: " + e + " is updated");
        // Hacer algo con boolValue
    }

    public String loadString(String obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(e);
        JsonNode dat = jsonNode.get(obj);
        // A partir de aquí, puedes acceder y trabajar con el JSON utilizando jsonNode
        if (dat == null) {
            return null;
        } else {
            return dat.asText();
        }
    }

    public int loadInt(String obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(e);
        JsonNode dat = jsonNode.get(obj);
        // A partir de aquí, puedes acceder y trabajar con el JSON utilizando jsonNode

        return dat.asInt();
    }

    public Boolean loadBool(String obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(e);
        JsonNode dat = jsonNode.get(obj);
        if (dat == null) {
            registerBool(obj, false);
            return false;
        } else {
            return dat.asBoolean();
        }
    }
    public JsonNode loadNode(String key) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(e);
        JsonNode dat = jsonNode.get(key);
        return dat;
    }
    public void delateNode(JsonNode er,String key){
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    // Leer el archivo JSON en un objeto JsonNode
                    JsonNode rootNode = objectMapper.readTree(e);
                
                    // Verificar si la clave existe y eliminar el objeto si es necesario
                    if (rootNode.has("tabs")) {
                        ObjectNode tabsNode = (ObjectNode) rootNode.get("tabs");
                        if (er.has(key)) {
                            tabsNode.remove(key);
                        
                            // Guardar los cambios de nuevo en el archivo JSON
                            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
                            objectWriter.writeValue(e, rootNode);
                        
                        } else {
                           
                        }
                    } else {
                        
                    }
                } catch (IOException es) {
                    es.printStackTrace();
                }
    }
    public Iterator<JsonNode> getIterator() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
   
        // Leer el archivo JSON en un objeto JsonNode
        JsonNode rootNode;
        rootNode = objectMapper.readTree(e);
    

        return rootNode.iterator();
    }
    public JsonNode getRootNode() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
   
        return objectMapper.readTree(e);
    }

}
