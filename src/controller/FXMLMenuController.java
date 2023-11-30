/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gatov
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button btIniciar;
    @FXML
    private Button btContinuar;
    @FXML
    private Button btRecord;
    @FXML
    private Button btSalirMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void IniciarJuego(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/FXMLCubo.fxml"));

        Parent root = loader.load();

        Stage app_stage;
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Rubik's Systems");

        stage.show();
    }

    @FXML
    private void Continuar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/FXMLCuboContinuar.fxml"));

        Parent root = loader.load();

        Stage app_stage;
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Rubik's Systems");

        stage.show();
    }

    @FXML
    private void RecordVer(ActionEvent event) {
        // Ruta completa del archivo que deseas leer
        String rutaArchivo = "C://Users//julio//OneDrive//Escritorio//CarpetaJava//record.txt";

        try {
            // Crear un objeto FileReader para leer el archivo
            FileReader archivo = new FileReader(rutaArchivo);

            // Crear un objeto BufferedReader para leer líneas del archivo
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            int aux = 0;
            String NoteUno = "", NoteDos = "";

            // Leer y mostrar cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                aux++;
                if (aux == 1) {
                    NoteUno = linea;
                } else if (aux == 2) {
                    NoteDos = linea;
                }
            }
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("RECORD");
            alerta.setHeaderText("Información");
            
            alerta.setContentText("El record es de "+NoteUno+",\n"+"con "+NoteDos+" movimientos.");
            
            alerta.showAndWait();
           
            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    @FXML
    private void SalirMenu(ActionEvent event) {
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Rubik's Systems");
        alerta.setHeaderText("SALIÓ");
            
        alerta.setContentText("¡Has salido con éxito!");
            
        alerta.showAndWait();
        System.exit(0);
    }

}
