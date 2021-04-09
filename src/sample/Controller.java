package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.OrderNode;
import sample.models.Organ;

import java.io.IOException;

public class Controller {

    DataAccessor da;

    @FXML
    private TableView<Organ> organsTable;

    @FXML
    private Button organBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn type;

    @FXML
    private TableColumn donor;

    @FXML
    private TableColumn surgeon;

    @FXML
    private TableColumn price;

    @FXML
    private TableColumn conditions;

    @FXML
    private TableView<OrderNode> nodeTable;

    @FXML
    private TableColumn nodeCode;

    @FXML
    private TableColumn nodeOrgan;

    @FXML
    private TableColumn nodeDate;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();

        ObservableList<Organ> organs = da.getOrgansList();

        organsTable.getItems().clear();
        id.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Organ, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Organ, String>("type"));
        donor.setCellValueFactory(new PropertyValueFactory<Organ, String>("donor"));
        surgeon.setCellValueFactory(new PropertyValueFactory<Organ, String>("surgeon"));
        conditions.setCellValueFactory(new PropertyValueFactory<Organ, String>("specification"));
        price.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("price"));

        organsTable.setItems(organs);
        
    }

    public void handleBtnOrganAction(javafx.event.ActionEvent actionEvent) throws IOException {

        Stage stage1 = (Stage) organBtn.getScene().getWindow();
        String title = stage1.getTitle() + " - " + "Новый орган";

        Parent root = FXMLLoader.load(getClass().getResource("layouts/create_organ_layout.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnUpdateAction(javafx.event.ActionEvent actionEvent) throws IOException {
        ObservableList<Organ> organs = da.getOrgansList();

        organsTable.getItems().clear();
        id.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Organ, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Organ, String>("type"));
        donor.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("donor"));
        surgeon.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("surgeon"));
        conditions.setCellValueFactory(new PropertyValueFactory<Organ, String>("specification"));
        price.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("price"));

        organsTable.setItems(organs);
        System.out.println("Обновлено!");
    }

    public void handleBtnDeleteAction(javafx.event.ActionEvent actionEvent) throws IOException {
        int id = organsTable.getSelectionModel().getSelectedItem().getId();
        da.deleteOrganById(id);
        da.deleteNodeByOrganId(id);
        System.out.println("Удалено!");
        organsTable.getItems().remove(organsTable.getSelectionModel().getSelectedItem());

    }


}
