/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Admin.AddAdmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.Admin.Admin;
import cruduser.entities.Admin.ServiceAdmin;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import cruduser.util.AlertMaker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddController implements Initializable {

    @FXML
    private TextField phone;

    ServiceAdmin sa = new ServiceAdmin();
    Boolean isInEditMode = Boolean.FALSE;
    String Role = "Admin";
    int idAdmin;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField Lname;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField Uname;

    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton Cancel;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXPasswordField Cpass;

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
        Admin d = new Admin(idAdmin, fname.getText(), Lname.getText(), mail.getText(), Integer.parseInt(phone.getText()), Uname.getText(), pass.getText());
        sa.modifier(d);
     AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "sucess", "Ajouter avec sucées");
    }

    public void infalteUI(Admin place) {
        fname.setText(place.getUser_FirstName());
        Lname.setText(place.getUser_LastName());
        Uname.setText(place.getUsername());
        mail.setText(place.getUser_mail());
        pass.setText(place.getPassword());
        phone.setText(String.valueOf(place.getUser_phone()));
        idAdmin = place.getId_User();

        isInEditMode = Boolean.TRUE;
    }

    private void LoadCancel(ActionEvent event) {
        getStage().close();
    }

    private void loadAddAdmin(ActionEvent event) {
        String Fname = fname.getText();
        String lname = Lname.getText();
        String uname = Uname.getText();
        String email = mail.getText();
        String Pass = pass.getText();
        int Tpho = Integer.parseInt(phone.getText());

        if (Fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || email.isEmpty() || Pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, " please fill the Blank ");
            return;

        }
        if (isInEditMode) {
            handleEditOperation();
            return;
        }
        Admin d = new Admin(Fname, lname, uname, email, Pass, Tpho, Role);
        sa.ajouter(d);
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

        if (Fname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Name.");
            return;
        }
        if (lname.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter your Last Name.");
            return;
        }
        if (email.isEmpty()) {
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
            System.out.println(Pass +" / "+cpass);
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data", "Make sure you write the same Password.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }
        Admin pv = new Admin(Fname, lname, uname, email, Pass, tpho, Role);
 
        sa.ajouter(pv);

    }

    @FXML
    private void handleCancel(ActionEvent event) {
        getStage().close();

    }
}
