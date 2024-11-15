package dad.gesaula.controllers;

import dad.gesaula.model.Grupo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // controllers

    private GroupController groupController = new GroupController();
    private AlumnoController alumnoController = new AlumnoController();
    private AlumnoSplitController alumnoSplitController = new AlumnoSplitController();

    //model

    private StringProperty fileName = new SimpleStringProperty();

    // view

    @FXML
    private Tab alumTab;

    @FXML
    private Tab groupTab;

    @FXML
    private TextField nameText;

    @FXML
    private BorderPane root;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupTab.setContent(groupController.getRoot());
        alumTab.setContent(alumnoController.getRoot());
        fileName.bind(nameText.textProperty().concat(".xml"));
        groupController.setGrupo(new Grupo());
        groupController.getGrupo().setAlumnos(alumnoController.getAlumnos());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewAction(ActionEvent event) {
        groupController.setGrupo(new Grupo());
        alumnoController.getAlumnos().setAll();
    }

    @FXML
    void onSaveAction(ActionEvent event) {
        try {
            groupController.getGrupo().setAlumnos(alumnoController.getAlumnos());
            groupController.getGrupo().save(new File(fileName.get()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
