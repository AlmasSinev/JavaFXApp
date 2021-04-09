package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.Administrator;
import sample.models.Surgeon;


import java.io.IOException;

public class LoginController {

    private String email;
    private String password;

    public PasswordField passwordEdit;
    @FXML
    private Button loginBtn;

    @FXML
    private Label exceptionLabel;

    @FXML
    private TextField emailEdit;

    public void handleBtnLoginAction(javafx.event.ActionEvent actionEvent) throws IOException {
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();

        DataAccessor da = DataAccessor.getDataAccessor();
        Administrator admin = da.getAdministratorByPassword(email, password);
        Surgeon surgeon = da.getSurgeonByPassword(email, password);
        System.out.println(surgeon.getId() + " " + surgeon.getF_name());

        if (admin.getId() != -1){
            String title = admin.getL_name() + " " + admin.getF_name() + " (Администратор)";
            Parent root = FXMLLoader.load(getClass().getResource("layouts/admin_layout.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) loginBtn.getScene().getWindow();
            stage1.close();
        } else if (surgeon.getId() != -1){
            String title = surgeon.getL_name() + " " + surgeon.getF_name() + " (Хирург)";

            Parent root = FXMLLoader.load(getClass().getResource("layouts/sample.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) loginBtn.getScene().getWindow();
            stage1.close();
        } else
            System.out.println("Sorry");
            exceptionLabel.setOpacity(0.85);



    }
}
