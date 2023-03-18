/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Tourist;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class DashbordAdminController implements Initializable {

    @FXML
    private AnchorPane sideBar;
    @FXML
    private JFXButton btn_workbench;
    @FXML
    private JFXButton btn_workbench1;
    @FXML
    private JFXButton btn_workbench2;
    @FXML
    private JFXButton btn_workbench3;
    @FXML
    private JFXButton btn_workbench21;
    @FXML
    private JFXButton btn_workbench211;
    @FXML
    private JFXTextField txt_search;
    @FXML
    private StackedBarChart<?, ?> chart;
    @FXML
    private PieChart pie;
    @FXML
    private PieChart pieReport;
    @FXML
    private JFXButton showTourist;
    @FXML
    private JFXButton showGuide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // 
 
         try {
            ServiceUser su=new ServiceUser();
            XYChart.Series s = new XYChart.Series<>();
            XYChart.Series s1 = new XYChart.Series<>();
            List<User> l=su.stat_Users();
            s1.getData().add(new XYChart.Data("users", su.nbUsers()));
       
            s1.setName("Nb Users");
            s.setName(" Role");

            for (int i = 0; i < l.size(); i++) {

                s.getData().add(new XYChart.Data(l.get(i).getRole(), l.get(i).getId_User()));

            }
            chart.getData().addAll(s1, s);
            chart.setTitle("Statistiques sur les utilisateur par Role ");

             int nb1=0;
                      
    nb1=su.guideCountOnCity().size();
    for(int j=0;j<nb1;j++)
    {
        pie.getData().add(new PieChart.Data(su.guideCountOnCity().get(j).getCityname(),su.guideCountOnCity().get(j).getId_User()));
    }
    pie.setTitle("Statistiques sur les Quide par Cityname ");        
    
        int nb2=su.reportByType().size();
    for(int K=0;K<nb2;K++)
    {
        pieReport.getData().add(new PieChart.Data(su.reportByType().get(K).getCityname()+" ("+ su.reportByType().get(K).getId_User()+")" ,su.reportByType().get(K).getId_User()));
    }
    pieReport.setTitle("Statistiques sur les Raport par type ");  
                 
             } catch (SQLException ex) {
            Logger.getLogger(DashbordAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @FXML
    private void showTourist(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/cruduser/Tourist/TouristList/tourist_list.fxml"), "Add New Place", null);
    }

    @FXML
    private void showGuide(ActionEvent event) {
         Util.loadWindow(getClass().getResource("/cruduser/Guide/GuideList/Guide_list.fxml"), "Add New Place", null);
    }
     
    
}
