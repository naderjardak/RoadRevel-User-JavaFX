/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Tourist.TouristList;

import cruduser.Tourist.AddTourist.AddController;
import cruduser.database.DatabaseHandler;
import cruduser.entities.Tourist.ServiceTourist;
import cruduser.entities.Tourist.Tourist;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author user
 */
public class TouristListController implements Initializable {
    ObservableList<Tourist> list = FXCollections.observableArrayList();


     private Connection cnx = DatabaseHandler.getInstance().getCnx();
    @FXML
    private TableView<Tourist> tableView;

    ServiceTourist st= new ServiceTourist();
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableColumn<Tourist, String> fnameCol;
    @FXML
    private TableColumn<Tourist, String> lanameCol;
    @FXML
    private TableColumn<Tourist, String> mailCol;
    @FXML
    private TableColumn<Tourist, String> unameCol;
    @FXML
    private TableColumn<Tourist, String> pwdCol;
    @FXML
    private TableColumn<Tourist, String> phoneCol;
    @FXML
    private TableColumn<Tourist, String> langCol;
    @FXML
    private TableColumn<Tourist, String> natCol;

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
initCol();
    }
    private void initCol() {
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("User_FirstName"));
        lanameCol.setCellValueFactory(new PropertyValueFactory<>("User_LastName"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("User_mail"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("User_phone"));
        unameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        pwdCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
        natCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
       langCol.setCellValueFactory(new PropertyValueFactory<>("langue"));
      
    }

   private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void loadData() {
        list.clear();
        String req = "SELECT * FROM user where role='Tourist'";
  
                    PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                Tourist t = new Tourist(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"), result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password"), result.getString("Nationality"), result.getString("Langue"));
                System.out.println(t);
                list.add(t);
            }
    } catch (SQLException ex) {
System.out.println(ex.getMessage()); }       
            
        tableView.setItems(list);}
    
    
    @FXML
    private void handlePlaceDelete(ActionEvent event) {
        //Fetch the selected row
        Tourist selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            JOptionPane.showMessageDialog(null,"No Place selected , Please select a Place for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Place");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getUsername()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            st.supprimer(selectedForDeletion);

                list.remove(selectedForDeletion);

    }}

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handlePlaceEdit(ActionEvent event) {
        //Fetch the selected row
        Tourist selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JOptionPane.showMessageDialog(null,"No Place selected , Please select a Place for deletion.");
            return;
        }
        try {
             //Util.loadWindow(getClass().getResource("/roadrevel/UI/Guide/AddGuide/Add.fxml"), "Add New Place", null);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruduser/Tourist/AddTourist/Add.fxml"));
            Parent parent = loader.load();

            AddController controller = (AddController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(parent));
            stage.show();


            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {
    }

    
    
}
