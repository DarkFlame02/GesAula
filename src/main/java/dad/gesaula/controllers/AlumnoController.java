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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    // model

    private ListProperty<Alumno> alumnos = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    alumno -> new Observable[] { alumno.nombreProperty(), alumno.apellidosProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );

    private ObjectProperty<Alumno> selectedFriend = new SimpleObjectProperty<>();


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

    }

    public SplitPane getRoot() {
        return root;
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

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
