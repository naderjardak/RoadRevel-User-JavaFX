/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruduser.util;

/**
 *
 * @author MSI GF63
 */
public final class SessionManager {
 
    private static SessionManager instance;
 
     private static int id;
    private static String nom;
    private static String prenom;
    private static String email;
    private static int tel;
    private static String role;

    public static boolean getRoles(String admin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SessionManager() {
    }


   
  //SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
    private SessionManager(int id , String nom , String prenom , String email , int tel ,String photo,String role,String domaine ) {
    SessionManager.id=id;
    SessionManager.nom=nom;
    SessionManager.prenom=prenom;
    SessionManager.email=email;
    SessionManager.tel=tel;
    SessionManager.role=role;
    }
   
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */
    public static SessionManager getInstace(int id , String nom , String prenom , String email , int tel ,String photo,String role,String domaine) {
        if(instance == null) {
            instance = new SessionManager( id , nom ,  prenom , email ,tel ,photo, role,domaine);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static int getTel() {
        return tel;
    }

    public static void setTel(int tel) {
        SessionManager.tel = tel;
    }


    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }


   

    public static void cleanUserSession() {
     id=0;
     nom="";
     prenom="";
     email="";
     tel=0;
     role="";
    }
 
    
}