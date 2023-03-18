package cruduser.Tourist.AddTourist;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.Tourist.Tourist;
import cruduser.entities.Tourist.ServiceTourist;
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
 * @author user
 */
public class AddController implements Initializable {

    @FXML
    private TextField phone;

    ServiceTourist sg = new ServiceTourist();
    Boolean isInEditMode = Boolean.FALSE;
    String Role = "Tourist";
    int idTourist;
    //private TextField password;
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
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextField nat;
    @FXML
    private JFXTextField lang;
    @FXML
    private JFXButton Cancel;
    private int idtourist;

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
        Tourist d = new Tourist(idtourist, fname.getText(), Lname.getText(), mail.getText(), Integer.parseInt(phone.getText()), Uname.getText(), pass.getText(), nat.getText(), lang.getText());
        sg.modifier(d);
        JOptionPane.showMessageDialog(null, " Tourist has been modified ");
    }

    public void infalteUI(Tourist place) {
        fname.setText(place.getUser_FirstName());
        Lname.setText(place.getUser_LastName());
        Uname.setText(place.getUsername());
        mail.setText(place.getUser_mail());
        pass.setText(place.getPassword());
        phone.setText(String.valueOf(place.getUser_phone()));
        nat.setText(place.getNationality());
        lang.setText(place.getLangue());
        idtourist = place.getId_User();

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
        String natio = nat.getText();
        String langue = lang.getText();

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
            System.out.println(Pass + " / " + cpass);
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Wrong Data", "Make sure you write the same Password.");
            return;
        }
        if (natio.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter password.");
            return;
        }
        if (langue.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please re-enter password.");
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            return;
        }
        Pass=BCrypt.hashpw(Pass, BCrypt.gensalt());
        Tourist pv = new Tourist(Fname, lname, uname, email, Pass, tpho, Role, natio, langue);

        sg.ajouter(pv);
        getStage().close();

    }

    @FXML
    private void handleCancel(ActionEvent event) {
        getStage().close();

    }
}
