package dad.gesaula.controllers;

import dad.gesaula.model.Grupo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {

    // model

    private final SimpleObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

    // view

    @FXML
    private Label actiLabel;

    @FXML
    private Slider actiSlider;

    @FXML
    private TextField denomText;

    @FXML
    private Label examLabel;

    @FXML
    private Slider examSlider;

    @FXML
    private DatePicker finDate;

    @FXML
    private DatePicker incioDate;

    @FXML
    private Label practLabel;

    @FXML
    private Slider practSlider;

    @FXML
    private GridPane root;

    public GroupController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GroupView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        examLabel.textProperty().bind(examSlider.valueProperty().asString("%.0f").concat("%"));
        practLabel.textProperty().bind(practSlider.valueProperty().asString("%.0f").concat("%"));
        actiLabel.textProperty().bind(actiSlider.valueProperty().asString("%.0f").concat("%"));

        grupo.addListener(this::onAlumChanged);
    }

    private void onAlumChanged(ObservableValue<? extends Grupo> o, Grupo oldValue, Grupo newValue) {
        if(oldValue != null) {
            denomText.textProperty().unbindBidirectional(oldValue.denominacionProperty());
            incioDate.valueProperty().unbindBidirectional(oldValue.iniCursoProperty());
            finDate.valueProperty().unbindBidirectional(oldValue.finCursoProperty());
            examSlider.valueProperty().unbindBidirectional(oldValue.getPesos().examenesProperty());
            practSlider.valueProperty().unbindBidirectional(oldValue.getPesos().practicasProperty());
            actiSlider.valueProperty().unbindBidirectional(oldValue.getPesos().actitudProperty());

        }

        if (newValue !=null) {
            denomText.textProperty().bindBidirectional(newValue.denominacionProperty());
            incioDate.valueProperty().bindBidirectional(newValue.iniCursoProperty());
            finDate.valueProperty().bindBidirectional(newValue.finCursoProperty());
            examSlider.valueProperty().bindBidirectional(newValue.getPesos().examenesProperty());
            practSlider.valueProperty().bindBidirectional(newValue.getPesos().practicasProperty());
            actiSlider.valueProperty().bindBidirectional(newValue.getPesos().actitudProperty());

        }
    }

    public GridPane getRoot() {
        return root;
    }

    public Grupo getGrupo() {
        return grupo.get();
    }

    public SimpleObjectProperty<Grupo> grupoProperty() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo.set(grupo);
    }
}
