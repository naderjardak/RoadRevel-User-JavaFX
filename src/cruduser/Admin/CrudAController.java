/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruduser.admin;

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
 * @author Nasr
 */
public class CrudAController implements Initializable {

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

    @FXML
    private void addadmin(ActionEvent event) {
     Util.loadWindow(getClass().getResource("/cruduser/Admin/AddAdmin/Add.fxml"), "Add New Place", null);

    }

    @FXML
    private void shadmins(ActionEvent event) {
             Util.loadWindow(getClass().getResource("/cruduser/Admin/AdminList/Admin_list.fxml"), "Add New Place", null);

    }
    
}
