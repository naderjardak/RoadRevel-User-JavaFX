/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Tourist;

import com.jfoenix.controls.JFXButton;
import cruduser.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class InterfaceTouristController implements Initializable {

    @FXML
    private JFXButton btn_workbench211;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private JFXButton btn_workbench212;
    @FXML
    private JFXButton btn_workbench22;
    @FXML
    private JFXButton btn_workbench12;
    @FXML
    private JFXButton btn_workbench112;
    @FXML
    private JFXButton btn_workbench1112;
    @FXML
    private JFXButton btn_workbench11112;
    @FXML
    private JFXButton btn_workbench111111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profile(ActionEvent event) {
         Util.loadWindow(getClass().getResource("/cruduser/Tourist/automa.fxml"), "Add New Place", null);
    }
    
}
