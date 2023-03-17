/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Tourist;

import Api.Mail;
import Login.LoginController;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private JFXTextField codeText;
    @FXML
    private Button verifierCode;

    /**
     * Initializes the controller class.
     */
    int code=0;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXTextField cpass;
    @FXML
    private Button NewPasse_btn;
    @FXML
    private Text msg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Random random = new Random();
        code=random.nextInt(89999) + 10000;
        String mailCode=""+code;
        Mail mail=new Mail();
        mail.envoyerCode(mailCode,LoginController.validEmail /*"sarra.bachhamba@esprit.tn"*/);
    }    

    @FXML
    private void VerifierCode(ActionEvent event) {

        if(Integer.parseInt(codeText.getText())==code)
        {
            codeText.setVisible(false);
            verifierCode.setVisible(false);
            msg.setVisible(false);
            
            pass.setVisible(true);
            cpass.setVisible(true);
            NewPasse_btn.setVisible(true);
            
        }
        else
            System.out.println(" try  again " );
    }

    @FXML
    private void ConfirmerNewPasse(ActionEvent event) {
        if(pass.getText().equals(cpass.getText()) && !pass.getText().isEmpty())
        {
            ServiceUser su = new ServiceUser();
            User user= su.existe(LoginController.validEmail);
            String Pass=BCrypt.hashpw(pass.getText(), BCrypt.gensalt());
            user.setPassword(Pass);
            su.modifier(user);
        }
    }
    
}
