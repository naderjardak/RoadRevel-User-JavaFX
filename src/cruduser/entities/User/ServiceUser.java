
package cruduser.entities.User;

import cruduser.database.DatabaseHandler;
import cruduser.entities.Guide.Guide;
import cruduser.entities.IService;
import cruduser.entities.Tourist.Tourist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nasr
 */
public class ServiceUser implements IService<User>{
  
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(User p) {
        String req = "INSERT INTO user(User_FirstName , User_lastName ,User_mail,User_phone, Username, Password) VALUES(?, ? ,? ,? ,? ,? ,? ,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.executeUpdate();
            System.out.println("User ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User p) {
        String req = "UPDATE  user SET User_FirstName=?, User_lastName= ? ,User_mail= ? ,User_phone=?,Username= ?, Password= ? ,Cityname=? ,dateBeg=?,dateEnd=? WHERE id_User=?";
        System.out.println("modifier user");
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.setString(7,p.getCityname1());
            pst.setDate(8, (java.sql.Date) p.getDateBegin());
            pst.setDate(9, (java.sql.Date) p.getDateEnd());
            pst.setInt(10, p.getId_User());
            pst.executeUpdate();
            System.out.println("User modifiée !"+req);
        } catch (SQLException ex) {
            System.out.println("Modifier error"+ex.getMessage());
        }
    }

    @Override
    public void supprimer(User p) {
        String req = "DELETE FROM user WHERE id_User=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_User());
            pst.executeUpdate();
            System.out.println("User supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
       public void modifier1(User p) {
        String req = "UPDATE  user SET Cityname=? ,dateBeg=?,dateEnd=? WHERE id_User=?";
        System.out.println("modifier user");
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,p.getCityname1());
            pst.setString(2,p.getDateBegin().toString());
            pst.setString(3, p.getDateEnd().toString());
            pst.setInt(4, p.getId_User());
            pst.executeUpdate();
            System.out.println("User modifiée !"+req);
        } catch (SQLException ex) {
            System.out.println("Modifier error"+ex.getMessage());
        }
    }

   
    
    @Override
    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        
        String req = "SELECT * FROM user";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new User(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"),result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password")));
            }
            System.out.println("Users récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
     public User afficherUser(int id)  { 
        try {
         User us = null;

        String req = "SELECT * FROM user where id_User=? ";
  
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet result = pst.executeQuery();
            
            while(result.next()) {
                us= new User(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"),result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password"));
            }
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }

    public User validate(String Uname , String Upassword) {
         
        String vd = "SELECT * FROM user WHERE Username= ? and Password= ?";
        try {
        PreparedStatement pst = cnx.prepareStatement(vd);
            pst.setString(1,Uname);
            pst.setString(2, Upassword);
            ResultSet resultSet = pst.executeQuery();
            User user=new User();
            if (resultSet.next()) {
                user.setId_User(resultSet.getInt("id_User"));
                user.setUsername(resultSet.getString("Username"));
                user.setUser_mail(resultSet.getString("User_mail"));
                user.setUser_phone(resultSet.getInt("User_phone"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+" Eroor Validation");
        }
        return null ;
    }
    public User existe(String username)
    {
        String vd = "SELECT * FROM user WHERE Username= ? ";
        try {
        PreparedStatement pst = cnx.prepareStatement(vd);
            pst.setString(1,username);
            ResultSet resultSet = pst.executeQuery();
            User user;
            if (resultSet.next()) {         
               user=new User(resultSet.getInt("id_User"), resultSet.getString("User_FirstName"), resultSet.getString("User_lastName"),resultSet.getString("User_mail"), resultSet.getInt("User_phone"), resultSet.getString("Username"), resultSet.getString("Password"),resultSet.getString("role"));
               return user;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+" Eroor Validation");
        }
        return null;
    }
    public List<User> stat_Users() throws SQLException {
    List<User> arr=new ArrayList();
        Statement stm = cnx.createStatement();
        String req="select count(*) nb ,role from user group by role";
        ResultSet r=stm.executeQuery(req);
        
        while (r.next()) {
            User c = new User(r.getInt("nb")
                    , r.getString("role"));
            arr.add(c);
        }
        
        return arr;
   }
    
    public int nbUsers() throws SQLException
    {
        Statement stm = cnx.createStatement();
        String req="select count(*) nb from user";
        ResultSet r=stm.executeQuery(req);
        while(r.next())
        {
        return r.getInt("nb");
        }     
        return 0;
    }
    
    public List<Guide> guideCountOnCity() throws SQLException
    {
        List<Guide> arr=new ArrayList();
        Statement stm = cnx.createStatement();
        String req="select count(*) nb ,Cityname from user where role='Tourist' group by Cityname";
        ResultSet r=stm.executeQuery(req);
        
        while (r.next()) {
            Guide c = new Guide(r.getInt("nb")
                    , r.getString("Cityname"));
            arr.add(c);
        }
        
        return arr;
    }
    
    public List<Guide> reportByType() throws SQLException
    {
        List<Guide> arr=new ArrayList();
        Statement stm = cnx.createStatement();
        String req="select count(*) nb ,Incident_type from reports group by Incident_type";
        ResultSet r=stm.executeQuery(req);
        
        while (r.next()) {
           Guide c = new Guide(r.getInt("nb")
                    , r.getString("Incident_type"));
            arr.add(c);
        }
        
        return arr;
    }
    
    public List<Guide> afficherAllGuides() throws SQLException
    {
        String req = "SELECT * FROM user where role='Guide' ";
        List<Guide> list=new ArrayList<>();
        PreparedStatement pst;
   
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Guide g = new Guide(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"), result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password"), result.getString("Lang1"), result.getString("Lang2"), result.getString("Lang3"), result.getString("CityName"));
                System.out.println(g);
                list.add(g);
            }


        return list;
    }
    
    
        public List<User> findGuides(String cityname,Date dateBeg ,Date dateEnd) throws SQLException
    {
         String vd = "SELECT * FROM user WHERE Cityname=? and dateBeg<=? and dateEnd>=? and disponibility=1 and role='Guide'";
         List<User> userListId=new ArrayList<>();
        try {
        PreparedStatement pst = cnx.prepareStatement(vd);
            pst.setString(1,cityname);
            pst.setDate(2, (java.sql.Date) dateBeg);
            pst.setDate(3, (java.sql.Date) dateEnd);
            ResultSet resultSet = pst.executeQuery();
            User user=new User();
            if (resultSet.next()) {
                user.setId_User(resultSet.getInt("id_User"));
                user.setUser_FirstName(resultSet.getString("User_FirstName"));
                user.setUser_LastName(resultSet.getString("User_LastName"));
                user.setUser_phone(resultSet.getInt("User_phone"));
                user.setUser_mail("User_mail");
                userListId.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+" Eroor Validation");
        }
        return userListId ;
    }
    
    public List<User> findTourist() throws SQLException
    {
         String vd = "SELECT * FROM user WHERE disponibility=1 and role='Tourist'";
         List<User> userListId=new ArrayList<>();
        try {
        PreparedStatement pst = cnx.prepareStatement(vd);
            ResultSet resultSet = pst.executeQuery();
           User user=new User();
            while (resultSet.next()) {
                user.setId_User(resultSet.getInt("id_User"));
                user.setCityname1(resultSet.getString("Cityname"));
                user.setDateBegin(resultSet.getDate("dateBeg"));
                user.setDateEnd(resultSet.getDate("dateEnd"));             
                userListId.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+" Eroor Validation");
        }
        return userListId ;
    }    
        
    public boolean autoMatch(int iduser,int idrelation ) throws SQLException
    {
        String req = "UPDATE  user SET id_relation=? WHERE id_User=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idrelation);
            pst.setInt(2,iduser);
            pst.executeUpdate();
            System.out.println("User modifiée !");
            return true;
    }
    
}

