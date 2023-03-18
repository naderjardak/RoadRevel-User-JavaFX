/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Guide;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import cruduser.util.SessionManager;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class AutomaController implements Initializable {

    @FXML
    private JFXTextField cityname;
    @FXML
    private Button ajoutbtn;
    @FXML
    private JFXDatePicker enddate;
    @FXML
    private JFXDatePicker begdate;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void add(ActionEvent event) {
        ServiceUser sc=new ServiceUser();
        String cityn=cityname.getText();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(begdate.getValue());
        java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(enddate.getValue());
        User us=new User(SessionManager.getId(),cityn,gettedDatePickerDate,gettedDatePickerDate1);
        sc.modifier1(us);
}
    
}
