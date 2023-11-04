package net.kender.lt.controlers.config.tabs;

import static net.kender.lt.Main.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.kender.Kjson.Json;
import net.kender.logger.log5k.Logger;
import net.kender.lt.Funtions;
import net.kender.lt.Main;
import net.kender.lt.cons;


public class tabsOcontroler {

    //private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(tabsOcontroler.class);

    @FXML private ColorPicker Col;
    @FXML private ComboBox<String> icons;
    @FXML private ComboBox<String> Ty;
    @FXML private TextField POL;
    public static Color c;
    public static String n;
    public static String key;
    public static String Type;
    public static String dat;

    public static boolean modeADD = false;
    public void cancel(ActionEvent actionEvent) {
        Funtions.stageTabs.close();
    }

    @FXML
    public void delate(){
        Thread run = new Thread(new Runnable() {
            public void run(){
                // Crear un ObjectMapper de Jackson

                JOptionPane.showConfirmDialog(null,"confirm");
                try {
                        Json s = new Json(new File(cons.LTP + "\\config\\config.json"));
                        s.delateNode(s.loadNode("tabs"), key);
                        
                        Funtions.stageConfig.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/pane.fxml"));
                        Scene e = new Scene(fxmlLoader.load(), 913, 525);
                        sta.setScene(e);
                        sta.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        run.start();
        
        
    }

    public void save(ActionEvent actionEvent) throws IOException {
        savE(key);
        Funtions.stageConfig.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/pane.fxml"));
        Scene e = new Scene(fxmlLoader.load(), 913, 525);
        sta.setScene(e);
        sta.show();
    }
    public void initialize(){
        ObservableList<String> options = FXCollections.observableArrayList(
                "web",
                "mods",
                "profiles",
                "quickplay"
        );


        icons.setItems(scanDirectoryForPNGFiles(LTP + "\\Textures\\minecraft\\assets\\minecraft\\textures\\item"));
        Ty.setItems(options);
        Ty.setValue(Type);
        Col.setValue(c);
        if(n != null) {
            icons.setValue(n.replace(".png", "").replace("_", " "));
        }
        POL.setText(dat);
    }

    public void savE(String key) throws IOException {
            if(modeADD != true) {
                ObjectMapper objectMapper = new ObjectMapper();
                File jsonFile = new File(cons.LTP + "\\config\\config.json");
                JsonNode jsonNode = objectMapper.readTree(jsonFile);
                JsonNode tabs = jsonNode.get("tabs");

                JsonNode r = tabs.get(key);
                // Cambiar el valor de "nombre" a "Carlos"
                ((ObjectNode) r).put("Icon", icons.getValue().toString().replace("\"","").replace(" ","_") + ".png");
                ((ObjectNode) r).put("color", Col.getValue().toString());
                ((ObjectNode) r).put("url", POL.getText());
                    //((ObjectNode) r).put("type", Ty.getValue().toString());


                    // Guardar el JSON actualizado de nuevo en el archivo
                    objectMapper.writeValue(jsonFile, jsonNode);
                    Funtions.stageTabs.close();
                }else{
                logger.INFO("neweqwer");
                File jsonFile = new File(LTP + "\\config\\config.json");

                // Crear un ObjectMapper para procesar JSON
                ObjectMapper objectMapper = new ObjectMapper();

                // Leer el JSON desde el archivo en un ObjectNode
                ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(jsonFile);

                ObjectNode tabs = (ObjectNode) jsonNode.get("tabs");
                // Crear un nuevo objeto JSON que deseas agregar
                ObjectNode nuevoObjeto = objectMapper.createObjectNode();
                nuevoObjeto.put("Icon", icons.getValue().toString().replace("\"","").replace(" ","_") + ".png");
                nuevoObjeto.put("color", Col.getValue().toString());
                nuevoObjeto.put("type","");
                nuevoObjeto.put("url",POL.getText());

                // Agregar el nuevo objeto al JSON existente
                int r = tabs.size();
                r = r + 1;
                tabs.set(String.valueOf(r), nuevoObjeto);

                // Escribir el JSON actualizado de nuevo en el archivo
                objectMapper.writeValue(jsonFile, jsonNode);
            }


    }
    public ObservableList<String> scanDirectoryForPNGFiles(String directoryPath) {
        ObservableList<String> pngFiles = FXCollections.observableArrayList();

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            // Verifica si el directorio existe y es un directorio válido
            return pngFiles;
        }

        // Utiliza FileUtils.listFiles para obtener una colección de archivos .png
        Collection<File> pngFilesCollection = FileUtils.listFiles(directory, new String[]{"png"}, false);

        // Recorre la colección de archivos .png
        for (File pngFile : pngFilesCollection) {
            String fileName = pngFile.getName();
            // Quita la extensión .png y agrega el nombre del archivo al ObservableList
            logger.INFO(fileName);
            pngFiles.add(fileName.replace(".png","").replace("_", " "));
        }

        return pngFiles;
    }

}
