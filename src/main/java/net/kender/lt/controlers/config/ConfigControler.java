package net.kender.lt.controlers.config;

import static net.kender.lt.Main.*;
import static net.kender.lt.RM.RM.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import net.kender.Kjson.Json;
import net.kender.logger.log5k.Logger;
import net.kender.lt.Funtions;
import net.kender.lt.Main;
import net.kender.lt.RM.RM;

public class ConfigControler {

    //panels
    @FXML
    private Pane panelC;

    @FXML
    private Pane pd;
    // Buttons
    @FXML
    private Button saveB;

    @FXML
    private Button cancelB;

    @FXML
    private Button addB;

    @FXML
    private Button addtabB;
    //etc
    @FXML
    private Slider ramC;

    @FXML
    private ComboBox<String> temeC;

    @FXML
    private ComboBox<String> texturesC;

    @FXML
    private CheckBox textureC;

    @FXML
    private Text ramM;

    @FXML
    private ComboBox<String> stylesC;
    //functions

    //private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(ConfigControler.class);

    private static final Json json = new Json(new File(LTP + "\\config\\config.json"));

    @FXML
    protected void save() throws IOException {
        json.registerBool("terxuresU", textureC.isSelected());
        String h = null;
        if(temeC.getValue() != null){
            h = temeC.getValue().toString();
        }
        
        if(stylesC.getValue() != null){

            scene.getStylesheets().add("file:///C:/User/krist/AppData/Roaming/kender/LT/styles/qwr.css");

            logger.DEBUG(LTP + "\\styles\\" + stylesC.getValue().toString().replace("\"", ""));
        }

        if(textureC.isSelected()){
            String t = texturesC.getValue().toString().replace("\"","");
            json.registerString("Texture",t);
            readTexture(t);
            json.registerString("Texture",texturesC.getValue().toString().replace("\"",""));
        }else{
        }
        if(h != null){
            logger.INFO(h);
        }
        Funtions.stageConfig.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/pane.fxml"));
        scene = new Scene(fxmlLoader.load(), 913, 525);
        sta.setScene(scene);
        sta.show();

    }

    @FXML
    protected void cancel() throws IOException {
        Funtions.stageConfig.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pane.fxml"));
        scene = new Scene(fxmlLoader.load(), 913, 525);
        sta.setScene(scene);
        sta.show();
    }

    @FXML
    protected void addT() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo");

        // Configurar filtros de extensión si es necesario
        // fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));

        // Mostrar el cuadro de diálogo de selección de archivo y obtener la ruta del archivo seleccionado

        FileChooser.ExtensionFilter Filter = new FileChooser.ExtensionFilter("json and zip arch", "*.json", "*.zip");
        fileChooser.getExtensionFilters().addAll(Filter);

        java.io.File selectedFile = fileChooser.showOpenDialog(Funtions.stageConfig);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String archname = selectedFile.getName();
            logger.INFO("Ruta del archivo seleccionado: " + filePath);
            Path source = Path.of(filePath);
            Path dest = Path.of("f");
            if(filePath.contains(".json")){
                dest = Path.of(LTP + "\\Temes\\" + archname);
            }else if(filePath.contains(".zip")){
                dest = Path.of(LTP + "\\Textures\\" + archname);
            }
            Files.move(source,dest);
        } else {
            logger.INFO("Ningún archivo seleccionado.");
        }
    }

    @FXML
    protected void addtabs() throws IOException {
        Funtions.tabs();
    }

    @FXML
    protected void texon(){
        if(textureC.isSelected()){
            temeC.setDisable(true);
            texturesC.setDisable(false);
        }else{
            temeC.setDisable(false);
            texturesC.setDisable(true);
        }
    }
    //inatlizing method
    public void initialize() throws IOException {
        temeC.setVisible(true);
        temeC.setVisible(true);
        texturesC.setDisable(true);
        texturesC.setItems(RM.getLT());
        textureC.setSelected(json.loadBool("terxuresU"));
        texturesC.setDisable(false);
        temeC.setDisable(true);
        texturesC.setValue(json.loadString("Texture").replace("\"",""));
    }

}
