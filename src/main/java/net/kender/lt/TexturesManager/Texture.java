package net.kender.lt.TexturesManager;

import static net.kender.lt.controlers.Controler.*;

import java.io.File;
import java.net.URI;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;



public class Texture {
    
    public Texture(Image img,double widht,double heigt,boolean complete,String index){
            BackgroundImage background = null;
    
            if (widht == 0) {
                widht = 15.0;
            }
            if (heigt == 0) {
                heigt = 15.0;
            }
    
            if (complete) {
                background = new BackgroundImage(
                        img,
                        BackgroundRepeat.NO_REPEAT, // No repetir la imagen en ninguna dirección
                        BackgroundRepeat.NO_REPEAT, // No repetir la imagen en ninguna dirección
                        BackgroundPosition.CENTER,   // Centrar la imagen en el contenedor
                        new BackgroundSize(widht, heigt, true, true, false, false)  // Estirar la imagen para que ocupe todo el espacio
                );
            } else {
                background = new BackgroundImage(
                        img,
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(widht, heigt, false, false, false, false)
                );
            }
            // Establecer la imagen de fondo en el nodo
            Background Texture = new Background(background);
            switch (index) {
                case "cpb":
                    cpb = Texture;
                    break;
                case "centralPb":
                    centralPb = Texture;
                    break;
                case "tabsb":
                    tabsb = Texture;
                    break;
                case "profilesb":
                    profilesb = Texture;
                    break;
                case "downb":
                    downb = Texture;
                    break;
                case "playBb":
                    playBb = Texture;
                    break;
                case "changeNBb":
                    changeNBb = Texture;
                    break;
                case "configBb":
                    configBb = Texture;
                    break;
                case "plusPb":
                    plusPb = Texture;
                    break;
                case "naPb":
                    naPb = Texture;
                    break;
                case "imPb":
                    imPb = Texture;
                    break;
        }
    }

    public Texture(Color color,String index){
    
            BackgroundFill fill = new BackgroundFill(color, null, null);
            
            // Establecer la imagen de fondo en el nodo
            Background Texture = new Background(fill);
            switch (index) {
                case "cpb":
                    cpb = Texture;
                    break;
                case "centralPb":
                    centralPb = Texture;
                    break;
                case "tabsb":
                    tabsb = Texture;
                    break;
                case "profilesb":
                    profilesb = Texture;
                    break;
                case "downb":
                    downb = Texture;
                    break;
                case "playBb":
                    playBb = Texture;
                    break;
                case "changeNBb":
                    changeNBb = Texture;
                    break;
                case "configBb":
                    configBb = Texture;
                    break;
                case "plusPb":
                    plusPb = Texture;
                    break;
                case "naPb":
                    naPb = Texture;
                    break;
                case "imPb":
                    imPb = Texture;
                    break;
        }
    }
    
}
