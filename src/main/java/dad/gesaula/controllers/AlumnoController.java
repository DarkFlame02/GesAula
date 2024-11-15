package dad.gesaula.controllers;

import dad.gesaula.model.Alumno;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    // controllers

    AlumnoSplitController alumnoSplitController = new AlumnoSplitController();

    // model

    private ListProperty<Alumno> alumnos = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    alumno -> new Observable[] { alumno.nombreProperty(), alumno.apellidosProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );

    private ObjectProperty<Alumno> selectedAlumno = new SimpleObjectProperty<>();

    // view

    @FXML
    private TableView<Alumno> alumTable;

    @FXML
    private BorderPane alumnPane;

    @FXML
    private TableColumn<Alumno, LocalDate> birthDateColumn;

    @FXML
    private TableColumn<Alumno, String> nameColumn;

    @FXML
    private SplitPane root;

    @FXML
    private TableColumn<Alumno, String> surnameColumn;

    public AlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alumTable.setItems(alumnos);

        nameColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
        surnameColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
        birthDateColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());

        selectedAlumno.bind(alumTable.getSelectionModel().selectedItemProperty());

        selectedAlumno.addListener((o, ov, nv) -> {
            if (nv != null) {
                alumnoSplitController.selectedAlumnoProperty().set(nv);
                alumnPane.setCenter(alumnoSplitController.getRoot());
            }
        });

    }

    public SplitPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar alumno");
        alert.setHeaderText("Se va a eliminar al alumno '" + selectedAlumno.get().getNombre() + " " + selectedAlumno.get().getApellidos() + "'");
        alert.setContentText("¿Está seguro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            alumnos.remove(selectedAlumno.get());
        }

    }

    @FXML
    void onNuevoAction(ActionEvent event) {
        Alumno alumno = new Alumno();
        alumno.setNombre("Sin nombre");
        alumno.setApellidos("Sin apellidos");
        alumnos.add(alumno);
    }

    public ObservableList<Alumno> getAlumnos() {
        return alumnos.get();
    }

    public ListProperty<Alumno> alumnosProperty() {
        return alumnos;
    }
}
