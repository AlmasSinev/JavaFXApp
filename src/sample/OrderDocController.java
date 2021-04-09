package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.Order;
import sample.models.OrderNode;
import sample.models.Organ;
import sample.models.Surgeon;

import java.io.IOException;

public class OrderDocController {

    DataAccessor da;

    @FXML
    private Label node;

    @FXML
    private Label date;

    @FXML
    private Label organName;

    @FXML
    private Label organSpecification;

    @FXML
    private Label surgeonName;

    @FXML
    private Label adminName;

    @FXML
    private Label buyerName;

    @FXML
    private Label method;

    @FXML
    private Label price;

    @FXML
    private Button readBtn;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();
        readBtn.graphicProperty().setValue(new ImageView(new Image("sample/images/download_icon.png")));
    }

    public void handleBtnReadAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) readBtn.getScene().getWindow();
        node.setText(stage1.getTitle().split(" ")[1]);

        OrderNode node = da.getNodeByCode(stage1.getTitle().split(" ")[1]);
        Organ organ = da.getOrganById(node.getOrganId());
        Surgeon surgeon = da.getSurgeonById(node.getSurgeonId());

        organName.setText(organ.getName());
        organSpecification.setText(organ.getSpecification());

        surgeonName.setText(surgeon.getL_name() + " " + surgeon.getF_name());

        date.setText(String.valueOf(node.getDate()));

        price.setText(String.valueOf(node.getPrice()) + "р.");

        Order order = da.getOrderByCode(node.getCode());
        buyerName.setText(order.getBuyer());
        adminName.setText(order.getAdmin());
        method.setText(order.getMethod());
    }

}
