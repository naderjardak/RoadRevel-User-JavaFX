/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cruduser.Admin.AdminList;

import cruduser.Admin.AddAdmin.AddController;
import cruduser.database.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import cruduser.entities.Admin.Admin;
import cruduser.entities.Admin.ServiceAdmin;
import cruduser.util.AlertMaker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Admin_listController implements Initializable {

    ObservableList<Admin> list = FXCollections.observableArrayList();
    private Connection cnx = DatabaseHandler.getInstance().getCnx();
    ServiceAdmin sa = new ServiceAdmin();
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableColumn<Admin, String> fnameCol;
    @FXML
    private TableColumn<Admin, String> lanameCol;
    @FXML
    private TableColumn<Admin, String> mailCol;
    @FXML
    private TableColumn<Admin, String> unameCol;
    @FXML
    private TableColumn<Admin, String> phoneCol;
    @FXML
    private TableView<Admin> tableView;
    @FXML
    private TableColumn<Admin, String> pwdCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();

    }

    private void initCol() {
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("User_FirstName"));
        lanameCol.setCellValueFactory(new PropertyValueFactory<>("User_LastName"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("User_mail"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("User_phone"));
        unameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        pwdCol.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }

    private void loadData() {
        list.clear();
        String req = "SELECT * FROM user where role='Admin'";

        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                list.add(new Admin(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"), result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableView.setItems(list);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();

    }

    @FXML
    private void handlePlaceEdit(ActionEvent event) {
                //Fetch the selected row
        Admin selectedForEdit;
        selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No Place selected", "Please select a Place for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cruduser/Admin/AddAdmin/Add.fxml"));
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
    private void handlePlaceDelete(ActionEvent event) {
                //Fetch the selected row
        Admin selectedForDeletion;
        selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No Admin selected", "Please select a Place for Remove.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Removing Admin ");
        alert.setContentText("Are you sure want to Remove " + selectedForDeletion.getUser_FirstName() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            sa.supprimer(selectedForDeletion);

            list.remove(selectedForDeletion);

        }
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }
}
