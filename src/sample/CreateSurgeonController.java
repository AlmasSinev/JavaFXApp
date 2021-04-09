package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataAccessor;

import java.io.IOException;

public class CreateSurgeonController {

    DataAccessor da;

    @FXML
    private TextField fnameEdit;

    @FXML
    private TextField lnameEdit;

    @FXML
    private TextField emailEdit;

    @FXML
    private PasswordField passwordEdit;

    @FXML
    private Button newSurgeonBtn;

    @FXML
    private Slider expSlider;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();
    }

    public void handleBtnNewSurgeonAction(javafx.event.ActionEvent actionEvent) throws IOException{
        String fname = fnameEdit.getText();
        String lname = lnameEdit.getText();
        int exp = (int) expSlider.getValue();
        String email = emailEdit.getText();
        String password = passwordEdit.getText();

        da.createNewSurgeon(fname, lname, exp, email, password);
        Stage stage1 = (Stage) newSurgeonBtn.getScene().getWindow();
        stage1.close();
        System.out.println("Добавлено!");
    }
}
