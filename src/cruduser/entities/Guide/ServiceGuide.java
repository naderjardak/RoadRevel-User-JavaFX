/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruduser.entities.Guide;


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
public class ServiceGuide implements IService<Guide>{
  
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    public void ajouter(Guide p) {
        String req = "INSERT INTO user(User_FirstName,User_lastName,User_mail,User_phone,Username,Password,role ,Lang1 ,Lang2 ,Lang3 ,Cityname) VALUES(?, ? ,? ,? ,? ,? ,? ,?,?,?,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
           pst.setString(1, p.getUser_FirstName());
           
           
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
          
             pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.setString(7, p.getRole());
            pst.setString(8, p.getLang1());
           pst.setString(9, p.getLang2());
            pst.setString(10, p.getLang3());
            pst.setString(11, p.getCityname());
         
            pst.executeUpdate();
            System.out.println("Guide ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Guide p) {
        String req = "UPDATE  user SET User_FirstName =? , User_lastName =? , User_mail =? ,User_phone =?,Username =?  ,Password =?,Lang1=? ,Lang2=? ,Lang3=? ,Cityname=? where id_User= ?";
           try{
               PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getUser_FirstName());
           
           
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
          
             pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.setString(7, p.getRole());
            pst.setString(8, p.getLang1());
           pst.setString(9, p.getLang2());
            pst.setString(10, p.getLang3());
            pst.setString(11, p.getCityname());
            pst.executeUpdate();
            System.out.println("Guide modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Guide p) {
        String req = "DELETE FROM user WHERE id_User=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_User());
            pst.executeUpdate();
            System.out.println("Guide supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Guide> afficher() {
        List<Guide> list = new ArrayList<>();
        
        String req = "SELECT * FROM user";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
           list.add(new Guide(result.getInt("id_User"),result.getString("User_FirstName"), result.getString("User_lastName"),result.getString("User_mail"),result.getInt("User_phone"), result.getString("Username"),result.getString("Password"),result.getString("Lang1"),result.getString("Lang2"),result.getString("Lang3"),result.getString("Cityname")));
            }
            System.out.println("Guide récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}

