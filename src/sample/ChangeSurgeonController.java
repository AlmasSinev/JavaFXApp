package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.Surgeon;

import java.io.IOException;

public class ChangeSurgeonController {
    DataAccessor da;
    Surgeon surgeon;

    @FXML
    private TextField fnameEdit;

    @FXML
    private TextField lnameEdit;

    @FXML
    private TextField emailEdit;

    @FXML
    private TextField passwordEdit;

    @FXML
    private Button saveChangesSurgeonBtn;

    @FXML
    private Button fillBtn;

    @FXML
    private Slider expSlider;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();

    }

    public void handleBtnFillAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) saveChangesSurgeonBtn.getScene().getWindow();
        String lName = stage1.getTitle().split("-")[0].split(" ")[0];
        String fName = stage1.getTitle().split("-")[0].split(" ")[1];

        surgeon = da.getSurgeonByName(lName, fName);

        fnameEdit.setText(surgeon.getF_name());
        lnameEdit.setText(surgeon.getL_name());
        emailEdit.setText(surgeon.getEmail());
        passwordEdit.setText(surgeon.getPassword());
        expSlider.setValue(surgeon.getExp());

        fillBtn.setStyle("-fx-text-fill: #3b149e");
    }

    public void handleBtnSaveChangesSurgeonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String fname = fnameEdit.getText();
        String lname = lnameEdit.getText();
        int exp = (int) expSlider.getValue();
        String email = emailEdit.getText();
        String password = passwordEdit.getText();


        da.changeSurgeon(surgeon.getId(), fname, lname, exp, email, password);
        Stage stage1 = (Stage) saveChangesSurgeonBtn.getScene().getWindow();
        stage1.close();
        System.out.println("Изменено!");
    }
}
