/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Guide.AddGuide;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.Guide.Guide;
import cruduser.entities.Guide.ServiceGuide;
import cruduser.entities.Tourist.Tourist;
import cruduser.util.AlertMaker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class AddController implements Initializable {

    @FXML
    private TextField phone;

    private TextField username;
    @FXML
    private TextField lang1;
    @FXML
    private TextField lang2;
    @FXML
    private TextField lang3;

    ServiceGuide sg = new ServiceGuide();
    Boolean isInEditMode = Boolean.FALSE;
    String Role = "Guide";
    int idGuide;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField Lname;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField Uname;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXPasswordField Cpass;
    @FXML
    private JFXTextField cname;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton Cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void cancel(ActionEvent event) {
        getStage().close();
    }

    public Stage getStage() {
        return (Stage) fname.getScene().getWindow();
    }

    public void handleEditOperation() {
        Guide d = new Guide(idGuide, fname.getText(), Lname.getText(), mail.getText(), Integer.parseInt(phone.getText()), Uname.getText(), pass.getText(), cname.getText(), lang1.getText(), lang2.getText(), lang3.getText());
        sg.modifier(d);
        JOptionPane.showMessageDialog(null, " Tourist has been modified ");
    }

    public void infalteUI(Guide place) {
        fname.setText(place.getUser_FirstName());
        Lname.setText(place.getUser_LastName());
        Uname.setText(place.getUsername());
        mail.setText(place.getUser_mail());
        pass.setText(place.getPassword());
        phone.setText(String.valueOf(place.getUser_phone()));
        cname.setText(place.getCityname());
        lang1.setText(place.getLang1());
        lang2.setText(place.getLang2());
        lang3.setText(place.getLang3());
        idGuide = place.getId_User();

        isInEditMode = Boolean.TRUE;
    }

    private void LoadCancel(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void handleAddOperation(ActionEvent event) {
        int tpho = 0;
        String Fname = fname.getText();
        String lname = Lname.getText();
        String uname = Uname.getText();
        String email = mail.getText();
        String Pass = pass.getText();
        String cpass = Cpass.getText();
        String cityname = cname.getText();
        String langue1 = lang1.getText();
        String langue2 = lang2.getText();
        String langue3 = lang3.getText();

        if (Fname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Name.");
            return;
        }
        if (lname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Last Name.");
            return;
        }
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Mail.");
            return;
        }
        if (phone.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Phone Number");
            return;
        } else if (!(phone.getText().matches("[0-9]+"))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data", "Phone Must Be a Number");
            return;
        } else if (Integer.parseInt(phone.getText()) < 10000000 || Integer.parseInt(phone.getText()) > 100000000) {

            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data", "Phone Must Be a Valid Number");
            return;

        } else {
            tpho = Integer.parseInt(phone.getText());

        }
        if (uname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your UserName.");
            return;
        }

        if (Pass.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter password.");
            return;
        }
        if (cpass.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please re-enter password.");
            return;
        }
        if (!(Pass.equalsIgnoreCase(cpass))) {
            System.out.println(Pass + " / " + cpass);
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data", "Make sure you write the same Password.");
            return;
        }
        if (cityname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter The city you want to work in.");
            return;
        }
        if (langue1.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your 1st lanquage .");
            return;
        }
        if (langue2.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your 2nd lanquage .");
            return;
        }
        if (langue3.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your 3rd lanquage .");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }
        Pass=BCrypt.hashpw(Pass, BCrypt.gensalt());
        Guide pv = new Guide(Fname, lname, uname, email, Pass, tpho, Role, cityname, langue1, langue2, langue3);

        sg.ajouter(pv);
        getStage().close();

    }

    @FXML
    private void handleCancel(ActionEvent event) {
        getStage().close();

    }
}
