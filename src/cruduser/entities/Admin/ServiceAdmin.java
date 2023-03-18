/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruduser.entities.Admin;

/**
 *
 * @author user
 */

import cruduser.database.DatabaseHandler;
import cruduser.entities.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Nasr
 */
public class ServiceAdmin implements IService<Admin>{
  
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    public void ajouter(Admin p) {
        String req = "INSERT INTO User(User_FirstName , User_lastName ,User_mail,User_phone, Username, Password,role) VALUES(?, ? ,? ,? ,?,?,?);";
            //Statement st = cnx.createStatement();
            try{
            PreparedStatement pst = cnx.prepareStatement(req);
           
        
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.setString(7, p.getRole());
           
         
         
           
            pst.executeUpdate();
            System.out.println("Admin ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Admin p) {
        String req = "UPDATE  user SET User_FirstName=? , User_lastName=? ,Username=? , Password=?, Role=?, User_mail=? , User_Phone=? where id_User = ?  ";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
         
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
              pst.setString(7, p.getRole());
            pst.setInt(8, p.getId_User());
          
            pst.executeUpdate();
            System.out.println("Admin modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Admin p) {
        String req = "DELETE FROM user WHERE id_User=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_User());
            pst.executeUpdate();
            System.out.println("Admin supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  public List<Admin> afficher() {
        List<Admin> list = new ArrayList<>();
        
        String req = "SELECT * FROM User where role='admin ' ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Admin(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"),result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password")));
            }
            System.out.println("Admin récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    }
