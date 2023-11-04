package net.kender.lt;

import static net.kender.core.UtilsFiles.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.kender.logger.log5k.Logger;
import net.kender.logger.log5k.conf.log5kConf;
import net.kender.lt.RM.utilsRM;

import static net.kender.logger.log5k.Logger.config;

public class Main extends Application {
    public static Stage sta;
    public static Scene scene;

    private static Logger logger = new Logger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {
        sta = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/pane.fxml"));
        scene = new Scene(fxmlLoader.load(), 913, 525);
        sta.setTitle("Launcher Trinity");
        sta.setScene(scene);
        sta.setResizable(false);
        sta.show();
    }

    public static void main(String[] args) throws IOException {
        //READ r = new READ();

        //Image r = new Image("file:///C://Users//krist//AppData//Roaming//kender//LT//Textures//minecraft//assets//minecraft//textures//block//acacia_door_bottom.png");
        log5kConf logConfig = new log5kConf(Main.class.getResourceAsStream("/net/kender/lt/config/logger.properties"));
        config = logConfig;

        utilsRM.getAssets("https://bitbucket.org/ltbase/files/downloads/assets.zip");

        String c = "{\n" +
                "    \"ram\":4,\n" +
                "    \"Texture\":\"\",\n" +
                "    \"teme\":\"\",\n" +
                "    \"tabs\":{\n" +
                "        \"one\":{\n" +
                "            \"type\":\"web\",\n" +
                "            \"url\":\"kenderdev.github.io\"\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}";
        File h = new File(cons.LTP + "\\Textures\\");

        if (h.exists()) {
            logger.DEBUG( h + " :exist");
        } else {
            FileUtils.forceMkdir(h);
        }

        File s = new File(cons.LTP + "\\styles\\");

        if (s.exists()) {
            logger.DEBUG( s + " :exist");
        } else {
            FileUtils.forceMkdir(s);
        }

        File t = new File(LTP + "\\config\\config.json");

        if (t.exists()) {
            logger.DEBUG( h + " :exist");
        } else {
            createFile(cons.LTP, "json", "\\config\\config", c);
        }
        launch();
    }

}