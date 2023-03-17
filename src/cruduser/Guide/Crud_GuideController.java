/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Guide;

import com.jfoenix.controls.JFXButton;
import cruduser.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class Crud_GuideController implements Initializable {

    private Button btnread;
    private Button AddGuide;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane one;
    @FXML
    private JFXButton Aadmin;
    @FXML
    private JFXButton Sadmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void addguide(ActionEvent event) {
     Util.loadWindow(getClass().getResource("/cruduser/Guide/AddGuide/Add.fxml"), "Add New Place", null);

    }

    private void shguide(ActionEvent event) {
             Util.loadWindow(getClass().getResource("/cruduser/Guide/GuideList/Guide_list.fxml"), "Add New Place", null);

    }

   
}
