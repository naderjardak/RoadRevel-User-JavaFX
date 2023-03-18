/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theme.resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 *
 * @author Nasr
 */
public class Util {
        public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return controller;
    }
    public String ImgPicker (){ 
        Stage secondStage = new Stage();
    ImageView imageView = new ImageView();
    FileChooser fc =new FileChooser() ;
     fc.getExtensionFilters().add(new ExtensionFilter("Images ","*.png"));
    fc.getExtensionFilters().add(new ExtensionFilter("Images ","*.jpg"));
    fc.getExtensionFilters().add(new ExtensionFilter("Images ","*.jpeg"));
    File f = fc.showOpenDialog(null);
    Image image = new Image(f.toURI().toString());
               FileChooser fileChooser = new FileChooser();
               fileChooser.getExtensionFilters().add(new ExtensionFilter("Images ","*.png"));
                fileChooser.setTitle("Save Image");
                
                File file = fileChooser.showSaveDialog(secondStage);
                
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image,null), "png", file);
                    } catch (IOException ex) {
                        ex.getMessage() ;
                    }

                    }

         
    if (f != null ){
         
        System.out.println(f.getAbsolutePath());
                System.out.println("the file will be uploaded here"+ file.getAbsolutePath());

    }
    
String str = file.getAbsolutePath();
		System.out.println(" we are going to take the relative from here  "+ str);
		// find word in String
		String find = "\\roadrevel\\resources";
		int i = str.indexOf(find);
                
		if(i>0){
                    System.out.println(str.substring(i, str.length()));
                    
			return str.substring(i, str.length());}
		else {
                    System.out.println("string not found");
			return "string not found";
                }
    }
    
}
