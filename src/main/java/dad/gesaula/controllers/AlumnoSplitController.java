package dad.gesaula.controllers;

import dad.gesaula.model.Alumno;
import dad.gesaula.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoSplitController implements Initializable {

    // model

    private ObjectProperty<Alumno> selectedAlumno = new SimpleObjectProperty<>();
    // view

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField nameText;

    @FXML
    private CheckBox repiteCheck;

    @FXML
    private GridPane root;

    @FXML
    private ComboBox<Sexo> sexCombo;

    @FXML
    private TextField surnameText;

    public AlumnoSplitController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoSplitView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sexCombo.getItems().setAll(Sexo.values());

        selectedAlumno.addListener((o, ov ,nv) -> {
            if (ov != null) {
                nameText.textProperty().unbindBidirectional(ov.nombreProperty());
                surnameText.textProperty().unbindBidirectional(ov.apellidosProperty());
                sexCombo.valueProperty().unbindBidirectional(ov.sexoProperty());
                birthDatePicker.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
                repiteCheck.selectedProperty().unbindBidirectional(ov.repiteProperty());
            }

            if (nv != null) {
                nameText.textProperty().bindBidirectional(nv.nombreProperty());
                surnameText.textProperty().bindBidirectional(nv.apellidosProperty());
                sexCombo.valueProperty().bindBidirectional(nv.sexoProperty());
                birthDatePicker.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
                repiteCheck.selectedProperty().bindBidirectional(nv.repiteProperty());
            }

        });
    }

    public GridPane getRoot() {
        return root;
    }

    public Alumno getSelectedAlumno() {
        return selectedAlumno.get();
    }

    public ObjectProperty<Alumno> selectedAlumnoProperty() {
        return selectedAlumno;
    }

}
