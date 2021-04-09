package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DataAccessor;

import java.io.IOException;

public class CreateOrderController {

    DataAccessor da;
    ObservableList<String> nodes;
    ObservableList<String> methods;
    ObservableList<String> buyers;

    @FXML
    private Spinner nodeSpinner;

    @FXML
    private Spinner methodSpinner;

    @FXML
    private Spinner buyerSpinner;

    @FXML
    private Label priceText;

    @FXML
    private Button saveOrderBtn;

    @FXML
    private CheckBox checkNewBuyer;

    @FXML
    private TextField lNameEdit;

    @FXML
    private TextField fNameEdit;

    @FXML
    private TextField emailEdit;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();
        nodes = da.getNodesCodesList();
        methods = da.getMethodsNamesList();
        buyers = da.getBuyersNamesList();

        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(nodes);

        SpinnerValueFactory<String> valueFactory2 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(methods);

        SpinnerValueFactory<String> valueFactory3 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(buyers);

        valueFactory.setValue("2M3O24NJ2I");
        valueFactory2.setValue("Cyber_money");
        valueFactory3.setValue("Камаулутдинов Егор");

        nodeSpinner.setValueFactory(valueFactory);
        nodeSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
                priceText.setText(String.valueOf(da.getNodeByCode((String) newValue).getPrice()) + " р."));
        methodSpinner.setValueFactory(valueFactory2);
        buyerSpinner.setValueFactory(valueFactory3);
    }

    public void handleBtnNewOrderAction(javafx.event.ActionEvent actionEvent) throws IOException {

        if (checkNewBuyer.isSelected()){
            Stage stage1 = (Stage) saveOrderBtn.getScene().getWindow();
            String title = stage1.getTitle().split("-")[0];
            int nodeId = da.getNodeByCode((String) nodeSpinner.getValue()).getId();
            int adminId = da.getAdministratorIdByName(title);
            int orderPrice = Integer.valueOf(priceText.getText().split(" ")[0]);
            int methodId = methods.indexOf(methodSpinner.getValue().toString()) + 1;

            da.createNewBuyer(fNameEdit.getText().toString(), lNameEdit.getText().toString(), emailEdit.getText().toString());

            int buyerId = da.getBuyerIdByFullNameAndEmail(fNameEdit.getText().toString(), lNameEdit.getText().toString(), emailEdit.getText().toString());
            da.createNewOrder(nodeId, adminId, buyerId, orderPrice, methodId);
            System.out.println("Успешно!");
        }else{
            Stage stage1 = (Stage) saveOrderBtn.getScene().getWindow();
            String title = stage1.getTitle().split("-")[0];
            int nodeId = da.getNodeByCode((String) nodeSpinner.getValue()).getId();
            int adminId = da.getAdministratorIdByName(title);
            int buyerId = buyers.indexOf(buyerSpinner.getValue().toString()) + 1;
            int orderPrice = Integer.valueOf(priceText.getText().split(" ")[0]);
            int methodId = methods.indexOf(methodSpinner.getValue().toString()) + 1;

            da.createNewOrder(nodeId, adminId, buyerId, orderPrice, methodId);
            System.out.println("Успешно!!");
        }
    }

    public void handleBtnNewBuyerAction(javafx.event.ActionEvent actionEvent) throws IOException {
        if (checkNewBuyer.isSelected()){
            lNameEdit.setOpacity(1.0);
            fNameEdit.setOpacity(1.0);
            emailEdit.setOpacity(1.0);
        } else {
            lNameEdit.setOpacity(0.0);
            fNameEdit.setOpacity(0.0);
            emailEdit.setOpacity(0.0);
        }
    }
}
