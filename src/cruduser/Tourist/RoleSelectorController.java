/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Tourist;

import cruduser.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class RoleSelectorController implements Initializable {

    @FXML
    private Button tourist;
    @FXML
    private Button guide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tourist(ActionEvent event) 
    {                 Util.loadWindow(getClass().getResource("/cruduser/Tourist/AddTourist/Add.fxml"), "Add New Place", null);

    }

    @FXML
    private void guide(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/cruduser/Guide/AddGuide/Add.fxml"), "Add New Place", null);
    }
    
}
