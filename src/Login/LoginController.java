/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.taskrouter.v1.workspace.Task;
import com.twilio.type.PhoneNumber;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import cruduser.util.SessionManager;
import cruduser.util.Util;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {

    @FXML
    private Button login_btn;
    @FXML
    private Button signUp;
    @FXML
    private JFXTextField user_name;
    @FXML
    private JFXTextField user_pass;
    @FXML
    private Text error_password;
    @FXML
    private Button forg_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

}    

    @FXML
    private void login(ActionEvent event) throws SQLException {
       String login = user_name.getText();
       String pass = user_pass.getText();
            if(login.equals("Admin") && pass.equals("Admin"))
           {
                Util.loadWindow(getClass().getResource("/cruduser/Tourist/DashbordAdmin.FXML"), "Admin Interface", null);   
           }    
           else
            {
       ServiceUser su = new ServiceUser();
       User user=su.existe(login);
       boolean etat=BCrypt.checkpw(pass,user.getPassword());
       System.out.println("pass hash =" +etat);
       
        if (login.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter your UserName");
            return;
        }
        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter a password");
            return;
        } 
        

 
                
        if(user==null)
       {
           error_password.setText("Account Not Found");
       }
       else if(etat==true)
       {
                    // String sendList[] = jTextFieldTo.getText().split(",");
           List<User> touristList=su.findTourist();
            for(User u:touristList)
            {
            System.out.println("id : "+u.getId_User()) ;
                List<User> guideList=su.findGuides(u.getCityname1(),u.getDateBegin(),u.getDateEnd());
                if(!guideList.isEmpty())
                {
                    System.out.println("id : "+u.getId_User()+" id : "+guideList.get(0).getId_User() );
                    su.autoMatch(u.getId_User(),guideList.get(0).getId_User());
                    
                   /* Twilio.init("ACbcc7f8d34a41d899234eaee96ba4df31","1fb1c8f3093f0ab01a579ba0e900f87b");
                    Message message =   Message.creator(new PhoneNumber("+21654449381"),
                    new PhoneNumber("+15674093244"), "\n The Guide accorded to u is "+guideList.get(0).getUser_FirstName()+" "+guideList.get(0).getUser_LastName()+ "\n 📞"+guideList.get(0).getUser_phone()+"").create();
                */
                }
           } 
            SessionManager.setId(user.getId_User());
            SessionManager.setEmail(user.getUser_mail());
            SessionManager.setRole(user.getRole());
            SessionManager.setTel(user.getUser_phone());
           
           if(user.getRole().equals("Tourist"))
           {
               System.out.println(etat);
                Util.loadWindow(getClass().getResource("/cruduser/Tourist/InterfaceTourist.fxml"), "Tourist Interface", null);
           }
           else
           {
            Util.loadWindow(getClass().getResource("/cruduser/Tourist/InterfaceGuide.fxml"), "Guide Interface", null);           }
     
       }
            }
    }

    @FXML
    private void signUp(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/cruduser/Tourist/RoleSelector.fxml"), "Code Mail", null);
    }


 
       private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static String validEmail;
    @FXML
    private void Email_forget_Code(ActionEvent event) {
          ServiceUser su = new ServiceUser();
          validEmail = user_name.getText();
         if (validEmail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter your UserName");
            return;
        }else if(su.existe(validEmail)!=null)
        {
            Util.loadWindow(getClass().getResource("/cruduser/Tourist/ForgetPassword.fxml"), "Code Mail", null);
        }    
         else
        {showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Account does not exist");
         return;
        }
    }


}
