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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.Order;
import sample.models.Organ;
import sample.models.Surgeon;

import java.io.IOException;

public class AdminController {

    DataAccessor da;

    @FXML
    private TableView<Surgeon> surgeonsTable;

    @FXML
    private TableColumn surgeonId;

    @FXML
    private TableColumn surgeonFName;

    @FXML
    private TableColumn surgeonLName;

    @FXML
    private TableColumn surgeonExp;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn orderId;

    @FXML
    private TableColumn order_node;

    @FXML
    private TableColumn buyer;

    @FXML
    private TableColumn admin;

    @FXML
    private TableColumn cost;

    @FXML
    private TableColumn method;

    @FXML
    private Button deleteSurgeonBtn;

    @FXML
    private Button createSurgeonBtn;

    @FXML
    private Button updateSurgeonsBtn;

    @FXML
    private Button changeSurgeonBtn;

    @FXML
    private Button openDocBtn;

    @FXML
    private Button newOrderBtn;

    @FXML
    private Button updateOrdersBtn;

    @FXML
    public void initialize(){

        createSurgeonBtn.graphicProperty().setValue(new ImageView(new Image("sample/images/create_icon2.png")));
        changeSurgeonBtn.graphicProperty().setValue(new ImageView(new Image("sample/images/change_icon2.png")));
        updateSurgeonsBtn.graphicProperty().setValue(new ImageView(new Image("sample/images/update_icon2.png")));
        deleteSurgeonBtn.graphicProperty().setValue(new ImageView(new Image("sample/images/delete_icon2.png")));

        da = DataAccessor.getDataAccessor();

        ObservableList<Surgeon> surgeons = da.getSurgeonsList();

        surgeonsTable.getItems().clear();
        surgeonId.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        surgeonLName.setCellValueFactory(new PropertyValueFactory<Organ, String>("l_name"));
        surgeonFName.setCellValueFactory(new PropertyValueFactory<Organ, String>("f_name"));
        surgeonExp.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("exp"));

        surgeonsTable.setItems(surgeons);

        ObservableList<Order> orders = da.getOrdersList();

        ordersTable.getItems().clear();
        orderId.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        order_node.setCellValueFactory(new PropertyValueFactory<Organ, String>("node"));
        buyer.setCellValueFactory(new PropertyValueFactory<Organ, String>("buyer"));
        admin.setCellValueFactory(new PropertyValueFactory<Organ, String>("admin"));
        cost.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("price"));
        method.setCellValueFactory(new PropertyValueFactory<Organ, String>("method"));

        ordersTable.setItems(orders);

    }

    public void handleBtnCreateSurgeonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) createSurgeonBtn.getScene().getWindow();
        String title = stage1.getTitle() + " - " + "Добавить хирурга";

        Parent root = FXMLLoader.load(getClass().getResource("layouts/create_surgeon_layout.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnCreateOrderAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) createSurgeonBtn.getScene().getWindow();
        String title = stage1.getTitle() + " - " + "Новый заказ";

        Parent root = FXMLLoader.load(getClass().getResource("layouts/create_order_layout.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnOpenDocAction(javafx.event.ActionEvent actionEvent) throws IOException {

        String title = "Заказ " + ordersTable.getSelectionModel().getSelectedItem().getNode();

        Parent root = FXMLLoader.load(getClass().getResource("layouts/order_doc_layout.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnDeleteSurgeonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        int id = surgeonsTable.getSelectionModel().getSelectedItem().getId();
        da.deleteSurgeonById(id);
        System.out.println("Удалено!");
        surgeonsTable.getItems().remove(surgeonsTable.getSelectionModel().getSelectedItem());
    }

    public void handleBtnUpdateSurgeonsAction(javafx.event.ActionEvent actionEvent) throws IOException {
        ObservableList<Surgeon> surgeons = da.getSurgeonsList();

        surgeonsTable.getItems().clear();
        surgeonId.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        surgeonLName.setCellValueFactory(new PropertyValueFactory<Organ, String>("l_name"));
        surgeonFName.setCellValueFactory(new PropertyValueFactory<Organ, String>("f_name"));
        surgeonExp.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("exp"));

        surgeonsTable.setItems(surgeons);
    }

    public void handleBtnChangeSurgeonsAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) changeSurgeonBtn.getScene().getWindow();

        String lName = surgeonsTable.getSelectionModel().getSelectedItem().getL_name();
        String fName = surgeonsTable.getSelectionModel().getSelectedItem().getF_name();


        String title = lName + " " + fName + " - " + "Редактирование";

        Parent root = FXMLLoader.load(getClass().getResource("layouts/change_surgeon_layout.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnUpdateOrdersAction(javafx.event.ActionEvent actionEvent) throws IOException {
        ObservableList<Order> orders = da.getOrdersList();

        ordersTable.getItems().clear();
        orderId.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("id"));
        order_node.setCellValueFactory(new PropertyValueFactory<Organ, String>("node"));
        buyer.setCellValueFactory(new PropertyValueFactory<Organ, String>("buyer"));
        admin.setCellValueFactory(new PropertyValueFactory<Organ, String>("admin"));
        cost.setCellValueFactory(new PropertyValueFactory<Organ, Integer>("price"));
        method.setCellValueFactory(new PropertyValueFactory<Organ, String>("method"));

        ordersTable.setItems(orders);
    }
}
