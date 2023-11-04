package net.kender.lt;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.kender.logger.log5k.Logger;


public class Funtions {

    private static final Logger logger = new Logger(Funtions.class);

    public static Stage stageConfig = new Stage();
    public static Stage stageTabs = new Stage();
    public static void play(Text por) throws IOException {

    }
    public static void config() throws IOException {
        Main.sta.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/configPane.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 602, 176);
        stageConfig.setTitle("Launcher Trinity config");
        stageConfig.setScene(scene);
        stageConfig.setResizable(false);
        stageConfig.show();
        logger.INFO("Open Config Pane");
    }
    public static void tabs() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/tabs.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 187, 467);
        stageTabs.setTitle("Launcher Trinity config tabs");
        stageTabs.setScene(scene);
        stageTabs.setResizable(false);
        stageTabs.show();
        logger.INFO("Open Config Pane tabs");
    }

}
