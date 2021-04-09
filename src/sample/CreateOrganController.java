package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DataAccessor;
import sample.models.Surgeon;

import java.io.IOException;
import java.util.Date;

public class CreateOrganController {

    ObservableList<String> types;
    ObservableList<String> donors;
    DataAccessor da;

    @FXML
    private Slider priceSlider;

    @FXML
    private Spinner typeSpinner;

    @FXML
    private Button newOrganBtn;

    @FXML
    private Label costText;


    @FXML
    private Spinner donorsSpinner;

    @FXML
    TextField nameEdit;

    @FXML
    TextArea specificationEdit;

    @FXML
    public void initialize(){
        da = DataAccessor.getDataAccessor();
        types = da.getTypesList();
        donors = da.getDonorsList();

        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(types);

        SpinnerValueFactory<String> valueFactory2 = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(donors);

        valueFactory.setValue("Внутренний протез");
        valueFactory2.setValue("Смирнова Вероника");

        typeSpinner.setValueFactory(valueFactory);
        donorsSpinner.setValueFactory(valueFactory2);

        priceSlider.valueProperty().addListener(new ChangeListener<Number>(){

            public void changed(ObservableValue<? extends Number> changed, Number oldValue, Number newValue){

                costText.setText("Стоимость: " + newValue.intValue() + "р.");
            }
        });
    }

    public void handleBtnNewOrganAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String name = nameEdit.getText();

        String type = typeSpinner.getValue().toString();
        int type_id = types.indexOf(type) + 1;

        String donor = donorsSpinner.getValue().toString();
        int donor_id  = donors.indexOf(donor) + 1;

        Stage stage1 = (Stage) newOrganBtn.getScene().getWindow();
        String surgeonName = stage1.getTitle().split("-")[0].split(" ")[0] + " " + stage1.getTitle().split("-")[0].split(" ")[1];
        String surgeon = surgeonName;
        int surgeon_id = da.getSurgeonIdByName(surgeonName);

        String specification = specificationEdit.getText();
        int price = (int) priceSlider.getValue();

        System.out.println(name);
        System.out.println(type + " (" + type_id + ")");
        System.out.println(donor + " (" + donor_id + ")");
        System.out.println(surgeon + " (" + surgeon_id + ")");
        System.out.println(specification);
        System.out.println(price);

        da.createNewOrgan(name, type_id, donor_id, surgeon_id, specification, price);


        String code = generateCode();
        Date date = new Date();
        int surgeonId = da.getSurgeonIdByName(stage1.getTitle().split("-")[0]);
        int organId = da.getOrganByNameAndDonor(name, donors.indexOf(donor) + 1).getId();
        Surgeon surgeon1 = da.getSurgeonById(surgeonId);
        int nodePrice = price + 25000*surgeon1.getExp();
        da.createNewNode(code, date, surgeonId, organId, nodePrice);
        stage1.close();
    }

    public String generateCode(){
        String[] arr = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Q", "W", "E", "R", "T",
                "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z",
                "X", "C", "V", "B", "N", "M"
        };

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 10; i++){
            int index = (int)(Math.random()*arr.length);
            sb.append(arr[index]);
        }

        return sb.toString();
    }
}
