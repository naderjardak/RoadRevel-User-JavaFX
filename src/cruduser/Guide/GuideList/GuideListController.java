/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Guide.GuideList;

import com.jfoenix.controls.JFXTextField;
import cruduser.Guide.AddGuide.AddController;
import cruduser.database.DatabaseHandler;
import cruduser.entities.Guide.Guide;
import cruduser.entities.Guide.ServiceGuide;
import cruduser.entities.User.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author sarra
 */
public class GuideListController implements Initializable {

    ObservableList<Guide> list = FXCollections.observableArrayList();

    private Connection cnx = DatabaseHandler.getInstance().getCnx();
    @FXML
    private TableView<Guide> tableView;

    ServiceGuide sg = new ServiceGuide();
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableColumn<Guide, String> fnameCol;
    @FXML
    private TableColumn<Guide, String> lanameCol;
    @FXML
    private TableColumn<Guide, String> phoneCol;
    @FXML
    private TableColumn<Guide, String> mailCol;
    @FXML
    private TableColumn<Guide, String> unameCol;
    @FXML
    private TableColumn<Guide, String> pwdCol;

    @FXML
    private TableColumn<Guide, String> cnameCol;
    @FXML
    private TableColumn<Guide, String> langCol;
    @FXML
    private TableColumn<Guide, String> langCol2;
    @FXML
    private TableColumn<Guide, String> langCol3;
    @FXML
    private JFXTextField rech;

    /**
     * Initializes the controller class.
     */
     private final ObservableList<Guide> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
            initCol();
            
            ServiceUser sc=new ServiceUser();
            ObservableList<Guide> list;
   
            FilteredList<Guide> filteredData = new FilteredList<>(dataList, b -> true);
            rech.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(guide -> {
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    String t=""+guide.getUser_FirstName();
                    if(t.toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if (guide.getUser_LastName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (guide.getUser_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (guide.getLang1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (guide.getCityname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else
                        return false; // Does not match.
                });
            });
            SortedList<Guide> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            dataList.addAll(sc.afficherAllGuides());
            System.out.println(dataList);
            fnameCol.setCellValueFactory(new PropertyValueFactory<>("User_FirstName"));
            lanameCol.setCellValueFactory(new PropertyValueFactory<>("User_LastName"));
            mailCol.setCellValueFactory(new PropertyValueFactory<>("User_mail"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("User_phone"));
            unameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
            pwdCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
            langCol.setCellValueFactory(new PropertyValueFactory<>("lang1"));
            langCol2.setCellValueFactory(new PropertyValueFactory<>("lang2"));
            langCol3.setCellValueFactory(new PropertyValueFactory<>("lang3"));
            cnameCol.setCellValueFactory(new PropertyValueFactory<>("cityname"));
            tableView.setItems(sortedData); 
        } catch (SQLException ex) {
            Logger.getLogger(GuideListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("User_FirstName"));
        lanameCol.setCellValueFactory(new PropertyValueFactory<>("User_LastName"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("User_mail"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("User_phone"));
        unameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        pwdCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
        langCol.setCellValueFactory(new PropertyValueFactory<>("lang1"));
        langCol2.setCellValueFactory(new PropertyValueFactory<>("lang2"));
        langCol3.setCellValueFactory(new PropertyValueFactory<>("lang3"));
        cnameCol.setCellValueFactory(new PropertyValueFactory<>("cityname"));

    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void loadData() {
        list.clear();
        String req = "SELECT * FROM user where role='Guide' ";

        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Guide g = new Guide(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"), result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password"), result.getString("Lang1"), result.getString("Lang2"), result.getString("Lang3"), result.getString("CityName"));
                System.out.println(g);
                list.add(g);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableView.setItems(list);
    }

    @FXML
    private void handlePlaceDelete(ActionEvent event) {
        //Fetch the selected row
        Guide selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            JOptionPane.showMessageDialog(null, "No Place selected , Please select a Place for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Place");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getUsername() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            sg.supprimer(selectedForDeletion);

            list.remove(selectedForDeletion);

        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handlePlaceEdit(ActionEvent event) {
        //Fetch the selected row
        Guide selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            JOptionPane.showMessageDialog(null, "No Place selected , Please select a Place for deletion.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruduser/Guide/AddGuide/Add.fxml"));
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
