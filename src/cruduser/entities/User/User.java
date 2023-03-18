
package cruduser.entities.User;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Nasr
 */
public class User {
    private int Id_User ;
    private String User_FirstName ;
    private String User_LastName ; 
    private String User_mail ;
    private int User_phone ;
    private String Username ;
    private String Password ;
    private String role ;
    private String cityname1;
    private Date dateBegin;
    private Date dateEnd;
    private Boolean disponibility;

    public User() {
    }

    
    
    
    public String getCityname1() {
        return cityname1;
    }

    public void setCityname1(String cityname) {
        this.cityname1 = cityname;
    }

    public User(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role, String cityname1, Date dateBegin, Date dateEnd, Boolean disponibility) {
        this.Id_User = Id_User;
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
        this.cityname1 = cityname1;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.disponibility = disponibility;
    }

    public User(int Id_User, Date dateBegin, Date dateEnd, Boolean disponibility) {
        this.Id_User = Id_User;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.disponibility = disponibility;
    }

    public User(int Id_User, String cityname1, Date dateBegin, Date dateEnd) {
        this.Id_User = Id_User;
        this.cityname1 = cityname1;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Boolean disponibility) {
        this.disponibility = disponibility;
    }

    
    
    
    public User(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role, Date dateBegin, Date dateEnd, Boolean disponibility) {
        this.Id_User = Id_User;
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.disponibility = disponibility;
    }
    

    public User(String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password) {
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
    }

    public User(String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role) {
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
    }
     
     

    public User(int Id_User) {
        this.Id_User = Id_User;
    }

    public User(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password) {
        this.Id_User = Id_User;
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
    }

    public User(int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role) {
        this.Id_User = Id_User;
        this.User_FirstName = User_FirstName;
        this.User_LastName = User_LastName;
        this.User_mail = User_mail;
        this.User_phone = User_phone;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
    }


    public User(int Id_User,String role) {
        this.Id_User = Id_User;
        this.role=role;
    }


    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public String getUser_FirstName() {
        return User_FirstName;
    }

    public void setUser_FirstName(String User_FirstName) {
        this.User_FirstName = User_FirstName;
    }

    public String getUser_LastName() {
        return User_LastName;
    }

    public void setUser_LastName(String User_LastName) {
        this.User_LastName = User_LastName;
    }

    public String getUser_mail() {
        return User_mail;
    }

    public void setUser_mail(String User_mail) {
        this.User_mail = User_mail;
    }

    public int getUser_phone() {
        return User_phone;
    }

    public void setUser_phone(int User_phone) {
        this.User_phone = User_phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.Id_User;
        hash = 97 * hash + Objects.hashCode(this.User_FirstName);
        hash = 97 * hash + Objects.hashCode(this.User_LastName);
        hash = 97 * hash + Objects.hashCode(this.User_mail);
        hash = 97 * hash + this.User_phone;
        hash = 97 * hash + Objects.hashCode(this.Username);
        hash = 97 * hash + Objects.hashCode(this.Password);
        hash = 97 * hash + Objects.hashCode(this.role);
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
        final User other = (User) obj;
        if (this.Id_User != other.Id_User) {
            return false;
        }
        if (this.User_phone != other.User_phone) {
            return false;
        }
        if (!Objects.equals(this.User_FirstName, other.User_FirstName)) {
            return false;
        }
        if (!Objects.equals(this.User_LastName, other.User_LastName)) {
            return false;
        }
        if (!Objects.equals(this.User_mail, other.User_mail)) {
            return false;
        }
        if (!Objects.equals(this.Username, other.Username)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        return Objects.equals(this.role, other.role);
    }

    @Override
    public String toString() {
        return "User{" + "Id_User=" + Id_User + ", User_FirstName=" + User_FirstName + ", User_LastName=" + User_LastName + ", User_mail=" + User_mail + ", User_phone=" + User_phone + ", Username=" + Username + ", Password=" + Password + ", role=" + role + '}';
    }

  
   
}