package cruduser.entities.Guide;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import cruduser.entities.User.User;
import java.util.Objects;



/**
 *
 * @author user
 */
public class Guide extends User{
     
    private String lang1;
    private String lang2;
    private String lang3;
    private String cityname;

    public Guide(String lang1, String lang2, String lang3, String cityname, int Id_User) {
        super(Id_User);
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.cityname = cityname;
    }

    public Guide( String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password,String lang1, String lang2, String lang3, String cityname) {
        super(User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.cityname = cityname;
    }

    public Guide( String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role,String lang1, String lang2, String lang3, String cityname) {
        super(User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.cityname = cityname;
    }
    

    public Guide( int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password,String lang1, String lang2, String lang3,String cityname) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.cityname = cityname;
    }
    

    public Guide( int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role,String lang1, String lang2, String lang3, String cityname) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.cityname = cityname;
    }

    public Guide(String Fname, String lname, String uname, String email, String Pass, int tpho, String Role, String cityname, String langue1, String langue2, String langue3) {
        super(Fname, lname,  email, tpho,uname,Pass,  Role);
        this.cityname = cityname;
        this.lang1 = langue1;
        this.lang2 = langue2;
        this.lang3 = langue3;
    }

    public Guide(int Id_User, String cityname) {
        super(Id_User);
       this.cityname = cityname;
    }
   
    

    public String getLang1() {
        return lang1;
    }

    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    public String getLang2() {
        return lang2;
    }

    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    public String getLang3() {
        return lang3;
    }

    public void setLang3(String lang3) {
        this.lang3 = lang3;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.lang1);
        hash = 53 * hash + Objects.hashCode(this.lang2);
        hash = 53 * hash + Objects.hashCode(this.lang3);
        hash = 53 * hash + Objects.hashCode(this.cityname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Guide other = (Guide) obj;
        if (!Objects.equals(this.lang1, other.lang1)) {
            return false;
        }
        if (!Objects.equals(this.lang2, other.lang2)) {
            return false;
        }
        if (!Objects.equals(this.lang3, other.lang3)) {
            return false;
        }
        return Objects.equals(this.cityname, other.cityname);
    }

    @Override
    public String toString() {
        return "Guide{" + "lang1=" + lang1 + ", lang2=" + lang2 + ", lang3=" + lang3 + ", cityname=" + cityname + '}';
    }
    
    
    
    

}
