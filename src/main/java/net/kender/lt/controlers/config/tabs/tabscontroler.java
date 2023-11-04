package net.kender.lt.controlers.config.tabs;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.kender.logger.log5k.Logger;
import net.kender.lt.Funtions;
import net.kender.lt.Main;

public class tabscontroler {

    private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(tabscontroler.class);
    @FXML private VBox lisT;

    public void add(ActionEvent actionEvent) throws IOException {
        // Ruta del archivo JSON existente

        tabsOcontroler.modeADD = true;

    


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/net/kender/lt/fxml/tabsO.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 492, 137);
            Funtions.stageTabs.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void cancel(ActionEvent actionEvent) {Funtions.stageTabs.close();}

    public void initialize() throws IOException {
        readTabs();
    }
    public void readTabs() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(LTP + "\\config\\config.json");
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        JsonNode tabs = jsonNode.get("tabs");
        ObjectNode objectNode = (ObjectNode) tabs;

        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();

            JsonNode r = tabs.get(key);

            JsonNode type = r.get("type");
            JsonNode color = r.get("color");
            JsonNode url = r.get("url");

            logger.INFO(SEPARATOR);
            logger.INFO("key: " + key);
            logger.INFO("tab Icon: " + r.get("Icon").toString());
            logger.INFO("type: " + type);
            logger.INFO("color: " + color);
            logger.INFO("url: " + url);
            logger.INFO(SEPARATOR);
            addtab(r.get("Icon").toString().replace("\"",""),color.toString().replace("\"",""), key, type.toString().replace("\"",""),r.get("url").asText());


        }

    }
    private void addtab(String name, String color, String key,String Type,String dat){
        Button boton = new Button("");
        Image image = new Image("file:///" + LTP + "\\Textures\\minecraft\\assets\\minecraft\\textures\\item\\" + name); // Reemplaza con la ruta de tu imagen
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32); // Ajusta el ancho de la imagen según tus necesidades
        imageView.setFitHeight(32); // Ajusta el alto de la imagen según tus necesidades

        boton.setGraphic(imageView); // Establece la imagen como gráfico del botón

        BackgroundFill backgroundFill = new BackgroundFill(Color.web(color), new CornerRadii(5), null);
        Background background = new Background(backgroundFill);


        boton.setBackground(background);

        // Crear un contenedor HBox para organizar la imagen y el botón
        HBox hbox = new HBox(10); // Espacio entre la imagen y el botón
        hbox.setAlignment(Pos.CENTER);

        lisT.getChildren().add(boton);

        boton.setOnAction(e -> {

            Background buttonBackground = boton.getBackground();

            tabsOcontroler.Type = Type;
            tabsOcontroler.n = name;
            tabsOcontroler.key = key;
            tabsOcontroler.dat = dat;


            BackgroundFill fill = buttonBackground.getFills().get(0);
            Color backgroundColor = (Color) fill.getFill();
            logger.INFO("Color de fondo del botón: " + replaceAndRemove(backgroundColor.toString()));
            tabsOcontroler.c = backgroundColor;

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/tabsO.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 492, 137);
                Funtions.stageTabs.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            logger.INFO(name + " clicked");

        });


    }
    public static String replaceAndRemove(String input) {
        if (input.length() < 4) {
            // La cadena es demasiado corta para aplicar la transformación
            return input;
        } else {
            // Reemplazar las dos primeras letras con "#"
            String modifiedString = "#" + input.substring(2);
            // Quitar las dos últimas letras
            modifiedString = modifiedString.substring(0, modifiedString.length() - 2);
            return modifiedString;
        }
    }

}
