/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruduser.entities.Admin;


import cruduser.entities.User.User;
import java.util.Objects;
/**
 *
 * @author user
 */
public class Admin extends User{

    public Admin(String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password) {
        super(User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
    }

    public Admin(int Id_User) {
        super(Id_User);
    }

    public Admin(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
    }

    public Admin(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
    }
 public Admin( String User_FirstName, String User_LastName, String User_mail,  String Username, String Password,int User_phone,String role) {
        super( User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
    }

   

   

  
   
  

   
}
