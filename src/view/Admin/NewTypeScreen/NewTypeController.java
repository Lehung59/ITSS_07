package com.itss.view.Admin.NewTypeScreen;

import com.itss.controller.BikeTypeController;
import com.itss.model.BikeTypeModel;
import com.itss.object.BikeType;
import com.itss.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewTypeController implements Initializable {

    BikeTypeController bikeTypeController = new BikeTypeController();

    @FXML
    private TableView<BikeType> table;
    @FXML
    private TableColumn<BikeType, Integer> id;
    @FXML
    private TableColumn<BikeType, String> name;
    @FXML
    TextField input_id, input_name;
    @FXML
    private TextField input_description;
    @FXML
    private CheckBox input_type;
    @FXML
    private TableColumn<BikeType, String> description;

    @FXML
    private TableColumn<BikeType, String> electricType;

    ObservableList<BikeType> data = FXCollections.observableArrayList();

    public void addNewType(ActionEvent event) throws SQLException {
        String id = input_id.getText();
        String name = input_name.getText();
        String description = input_description.getText();
        Boolean electricType = input_type.isSelected();
        input_id.clear();
        input_name.clear();
        input_type.setSelected(false);
        input_description.clear();
        bikeTypeController.insertBikeType(Integer.parseInt(id),name,description,electricType);
        getData();
        }
    
    public void deleteType(ActionEvent event) throws SQLException, IOException {
        BikeType selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int typeId = selectedItem.getId();

            bikeTypeController.deleteBikeType(typeId);
            data.clear();
                getData();
        }
    }

    public void getData() {
        try {
            bikeTypeController.getTypesToTable(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        electricType.setCellValueFactory(new PropertyValueFactory<>("electricType"));
        table.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
    }
}
