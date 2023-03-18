/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Guide;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AutomatchGuideController implements Initializable {

    @FXML
    private JFXTextField cityname;
    @FXML
    private Button add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void Add(ActionEvent event) {

    }

    @FXML
    private void addAuto(ActionEvent event) {
        ServiceUser sc=new ServiceUser();
        User us=new User();
        String cityn=cityname.getText();
       // LocalDate localDate = enddate.getValue();
        //ZoneId defaultZoneId = ZoneId.systemDefault();
       // Date dateEnd = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
       
       //LocalDate localDate1 = begindate.getValue();
       // ZoneId defaultZoneId1 = ZoneId.systemDefault();
       // Date begdate = Date.from(localDate1.atStartOfDay(defaultZoneId1).toInstant());
       us.setId_User(22);
       us.setCityname1(cityn);

       sc.modifier1(us);
    }
    
}
