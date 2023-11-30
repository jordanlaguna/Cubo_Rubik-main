/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gatov
 */
public class FXMLCuboControllerContinuar implements Initializable {

    private Map<Integer, Integer> mapeoColores = new HashMap<>();

    @FXML
    private GridPane panelCubo;
    @FXML
    private Button derechaUnoBt;
    @FXML
    private Button abajoTresBt;
    @FXML
    private Button derechaDosBt;
    @FXML
    private Button IzquierdaTresBt;
    @FXML
    private Button derechaTresBt;
    @FXML
    private Button IzquierdaDosBt;
    @FXML
    private Button IzquierdaUnoBt;
    @FXML
    private Button abajoDosBt;
    @FXML
    private Button abajoUnoBt;
    @FXML
    private Button arribaTresBt;
    @FXML
    private Button arribaDosBt;
    @FXML
    private Button arribaUnoBt;
    @FXML
    private Button BtSalir;
    @FXML
    private TextField TfMovimientos;
    @FXML
    private Pane panelCeroCero;
    @FXML
    private Pane panelCeroUno;
    @FXML
    private Pane panelCeroDos;
    @FXML
    private Pane panelUnoCero;
    @FXML
    private Pane panelUnoUno;
    @FXML
    private Pane panelUnoDos;
    @FXML
    private Pane panelDosCero;
    @FXML
    private Pane panelDosUno;
    @FXML
    private Pane panelDosDos;
    @FXML
    private Button btDerechaTotal;
    @FXML
    private Button btIzquierdaTotal;
    @FXML
    private Button btArribaTotal;
    @FXML
    private Button btAbajoTotal;

    /**
     * Initializes the controller class.
     */
    int fila = 3;
    int columna = 3;
    int capa = 6;
    int movimientos = 0;
    int auto = 0;

    String[][][] codigo = new String[fila][columna][capa];
    String[][][] auxCodigo = new String[fila][columna][capa];
    Pane[][][] cubo = new Pane[fila][columna][capa];

    Stack<Integer> pilaMovimientos = new Stack<>();
    Stack<String[][][]> estadoAnterior = new Stack<>();
    Stack<String[][][]> pilaEstados = new Stack<>();
    // private Stack<Integer> pila = new Stack<>();
    @FXML
    private GridPane PanelUno;
    @FXML
    private GridPane PanelSeis;
    @FXML
    private GridPane PanelCinco;
    @FXML
    private GridPane PanelCuatro;
    @FXML
    private GridPane PanelDos;
    @FXML
    private GridPane PanelTres;
    @FXML
    private Button btDesarmar;
    @FXML
    private Button btRendirse;
    @FXML
    private TextField txtnombre;
    @FXML
    private Button btRevisar;
    @FXML
    private Button btGuardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ruta completa del archivo que deseas leer
        String rutaArchivo = "C://Users//julio//OneDrive//Escritorio//CarpetaJava//datos.txt";
         
        try {
            // Crear un objeto FileReader para leer el archivo
            FileReader archivo = new FileReader(rutaArchivo);

            // Crear un objeto BufferedReader para leer líneas del archivo
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            int aux = 0;
            // Leer y mostrar cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                aux++;
                if (aux == 1) {
                    txtnombre.setText(linea);
                } else if (aux == 2) {
                    TfMovimientos.setText(linea);
                }
            }

            // Cerrar el archivo
            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Ruta completa del archivo que deseas leer
        String rutaArchivoDos = "C://Users//julio//OneDrive//Escritorio//CarpetaJava//juego.txt";
        
        try {
            // Crear un objeto FileReader para leer el archivo
            FileReader archivoDos = new FileReader(rutaArchivoDos);

            // Crear un objeto BufferedReader para leer líneas del archivo
            BufferedReader lectorDos = new BufferedReader(archivoDos);

            String lineaDos;
            int auxDos = 0, a = 0, b = 0, c = 0;

            // Leer y mostrar cada línea del archivo
            while ((lineaDos = lectorDos.readLine()) != null) {

                codigo[b][a][c] = lineaDos;     

                System.out.println(codigo[a][b][c]);
                if (a == 2 && b == 2) {
                    c++;
                    a=0;
                    b=0;
                    auxDos = 0;
                } else {
                    auxDos++;  

                    b++; 
                }

                if (auxDos == 3) {
                    a++;
                    b = 0;
                    auxDos = 0;
                } else if (a == 3) {
                    a = 0;
                    b = 0;
                    auxDos = 0;
                }

            }

            // Cerrar el archivo
            lectorDos.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }
            }
        }
        
        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }

                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void colores(int Num) {
        movimientos++;
        TfMovimientos.setText("" + movimientos);

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    auxCodigo[fila][columna][capa] = codigo[fila][columna][capa];
                }
            }
        }

        for (capa = 5; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    switch (Num) {
                        //Derecha Uno
                        case 1:
                            if (fila == 0) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 1];

                                codigo[fila][columna][capa - 1] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 3];

                                codigo[fila][columna][capa - 3] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Derecha Dos
                        case 2:
                            if (fila == 0) {
                                codigo[fila + 1][columna][capa] = codigo[fila + 1][columna][capa - 1];

                                codigo[fila + 1][columna][capa - 1] = codigo[fila + 1][columna][capa - 2];

                                codigo[fila + 1][columna][capa - 2] = codigo[fila + 1][columna][capa - 3];

                                codigo[fila + 1][columna][capa - 3] = auxCodigo[fila + 1][columna][capa];
                            }

                            break;
                        //Derecha Tres
                        case 3:
                            if (fila == 0) {
                                codigo[fila + 2][columna][capa] = codigo[fila + 2][columna][capa - 1];

                                codigo[fila + 2][columna][capa - 1] = codigo[fila + 2][columna][capa - 2];

                                codigo[fila + 2][columna][capa - 2] = codigo[fila + 2][columna][capa - 3];

                                codigo[fila + 2][columna][capa - 3] = auxCodigo[fila + 2][columna][capa];
                            }
                            break;
                        //Abajo Tres
                        case 4:
                            if (columna == 0) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Abajo Dos
                        case 5:
                            if (columna == 1) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Abajo Uno
                        case 6:
                            if (columna == 2) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Izquierda Uno
                        case 7:
                            if (fila == 0) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 3];

                                codigo[fila][columna][capa - 3] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 1];

                                codigo[fila][columna][capa - 1] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Izquierda Dos
                        case 8:
                            if (fila == 1) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 3];

                                codigo[fila][columna][capa - 3] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 1];

                                codigo[fila][columna][capa - 1] = auxCodigo[fila][columna][capa];
                            }

                            break;
                        //Izquierda Tres
                        case 9:
                            if (fila == 2) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 3];

                                codigo[fila][columna][capa - 3] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 1];

                                codigo[fila][columna][capa - 1] = auxCodigo[fila][columna][capa];
                            }

                            break;
                        //Arriba Uno
                        case 10:
                            if (columna == 2) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Arriba Dos
                        case 11:
                            if (columna == 1) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        //Arriba Tres
                        case 12:
                            if (columna == 0) {
                                codigo[fila][columna][capa] = codigo[fila][columna][capa - 5];

                                codigo[fila][columna][capa - 5] = codigo[fila][columna][capa - 2];

                                codigo[fila][columna][capa - 2] = codigo[fila][columna][capa - 4];

                                codigo[fila][columna][capa - 4] = auxCodigo[fila][columna][capa];
                            }
                            break;
                        default:
                            break;
                    }

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }
            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    @FXML
    private void derechaUno(ActionEvent event) {
        int Num = 1;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void abajoTres(ActionEvent event) {
        int Num = 4;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void derechaDos(ActionEvent event) {
        int Num = 2;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void IzquierdaTres(ActionEvent event) {
        int Num = 9;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void derechaTres(ActionEvent event) {
        int Num = 3;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void IzquierdaDos(ActionEvent event) {
        int Num = 8;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void IzquierdaUno(ActionEvent event) {
        int Num = 7;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void abajoDos(ActionEvent event) {
        int Num = 5;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void abajoUno(ActionEvent event) {
        int Num = 6;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void arribaTres(ActionEvent event) {
        int Num = 12;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void arribaDos(ActionEvent event) {
        int Num = 11;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void arribaUno(ActionEvent event) {

        int Num = 10;
        guardarNumeroEnPila(Num);
        colores(Num);
    }

    @FXML
    private void Salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/FXMLMenu.fxml"));

        Parent root = loader.load();

        Stage app_stage;
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Rubik's Systems");

        stage.show();
    }

    //Mover toda la cara--------------------------------------------------------
    @FXML
    private void DerechaTotal(ActionEvent event) {
        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    auxCodigo[fila][columna][capa] = codigo[fila][columna][capa];
                }
            }
        }
        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    switch (capa) {
                        case 2:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][5];
                            break;
                        case 3:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][2];
                            break;
                        case 4:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][3];
                            break;
                        case 5:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][4];
                            break;
                        default:
                            break;
                    }

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }

                    panelCubo.add(cubo[fila][columna][capa], columna, fila);
                }
            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    @FXML
    private void IzquierdaTotal(ActionEvent event) {

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    auxCodigo[fila][columna][capa] = codigo[fila][columna][capa];
                }
            }
        }

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    switch (capa) {
                        case 2:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][3];
                            break;
                        case 3:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][4];
                            break;
                        case 4:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][5];
                            break;
                        case 5:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][2];
                            break;
                        default:
                            break;
                    }

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);
                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }

                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }
            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    @FXML
    private void ArribaTotal(ActionEvent event) {

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    auxCodigo[fila][columna][capa] = codigo[fila][columna][capa];
                }
            }
        }

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    switch (capa) {
                        case 0:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][3];
                            break;
                        case 3:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][1];
                            break;
                        case 1:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][5];
                            break;
                        case 5:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][0];
                            break;
                        default:
                            break;
                    }

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }

                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }

            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }

                }

            }

        }

    }

    @FXML
    private void AbajoTotal(ActionEvent event) {

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    auxCodigo[fila][columna][capa] = codigo[fila][columna][capa];
                }
            }
        }

        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    switch (capa) {
                        case 0:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][5];
                            break;
                        case 3:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][0];
                            break;
                        case 1:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][3];
                            break;
                        case 5:
                            codigo[fila][columna][capa] = auxCodigo[fila][columna][1];
                            break;
                        default:
                            break;
                    }

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }
            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @FXML
    private void Desarmar(ActionEvent event) {
        auto++;

        if (auto == 1) {
            Random random = new Random();
            Random randomAux = new Random();

            int ram = random.nextInt(30 - 20 + 1) + 20;

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

            Runnable task = new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (i < ram) {
                        movimientos--;
                        int Num = randomAux.nextInt(12 - 1 + 1) + 1;
                        Platform.runLater(() -> colores(Num));
                        guardarNumeroEnPila(Num);
                        i++;
                    } else {
                        executorService.shutdown();
                    }
                }
            };

            executorService.scheduleAtFixedRate(task, 0, 80, TimeUnit.MILLISECONDS);
        } else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("Este botón solamente funciona una vez.");
            alerta.setContentText("¡Llevas " + auto + " veces!");

            alerta.showAndWait();
        }

    }

    void record() {
        // Ruta de la carpeta donde deseas guardar el archivo
        String carpeta = "C://Users//gatov//OneDrive//Escritorio//CarpetaJava//";

        // Nombre del archivo que deseas crear
        String nombreArchivo = "record.txt";

        try {
            // Crear un objeto File para la carpeta
            File carpetaDestino = new File(carpeta);

            // Asegurarse de que la carpeta exista; si no, crearla
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs(); // Crea la carpeta y sus padres si no existen
            }

            // Crear un objeto FileWriter para escribir en el archivo
            FileWriter archivo = new FileWriter(carpeta + nombreArchivo);

            // Escribir datos en el archivo
            String nombre = txtnombre.getText();

            archivo.write("" + nombre + "\n");
            archivo.write("" + movimientos + "\n");

            // Cerrar el archivo
            archivo.close();

            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("¡Felicidades!");
            alerta.setHeaderText("¡Hiciste un record!");
            alerta.setContentText("¡Con " + movimientos + " movimientos!");

            alerta.showAndWait();

            System.out.println("Se ha creado el archivo " + carpeta + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
        System.out.println("Hiciste: " + movimientos + " movimientos.");
        System.exit(0);
    }

    @FXML
    private void Rendirse(ActionEvent event) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {

            @Override
            public void run() {
                try {
                    if (!pilaMovimientos.isEmpty()) {
                        int ultimoMovimiento = pilaMovimientos.pop();

                        // Obtén el estado anterior del cubo desde la pila de estados
                        if (!pilaEstados.isEmpty()) {
                            String[][][] estadoPrevio = pilaEstados.pop();
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    for (int k = 0; k < 6; k++) {
                                        codigo[i][j][k] = estadoPrevio[i][j][k];

                                    }
                                }

                            }
                            Platform.runLater(() -> {
                                // Actualiza la interfaz gráfica aquí
                                actualizarInterfazGrafica();
                            });

                        } else {
                            executorService.shutdown();
                        }
                    } else {
                        executorService.shutdown();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                imprimirPila();

            }
        };

        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    }

    private void guardarNumeroEnPila(int numero) {
        pilaMovimientos.push(numero);
        System.out.println("Número guardado en la pila: " + numero);

        // Copia el estado actual del cubo y lo guarda en otra pila
        String[][][] estadoActual = new String[3][3][6];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 6; k++) {
                    estadoActual[i][j][k] = codigo[i][j][k];
                }
            }
        }
        pilaEstados.push(estadoActual);
    }

    private void imprimirPila() {
        System.out.print("Contenido de la pila: ");

        pilaMovimientos.forEach(numero -> {
            System.out.print(numero + " ");
        });
        System.out.println(); // Salto de línea para una mejor presentación.
    }

    private void actualizarInterfazGrafica() {
        panelCubo.getChildren().clear();
        for (capa = 0; capa < codigo[0][0].length; capa++) {
            for (columna = 0; columna < codigo[0].length; columna++) {
                for (fila = 0; fila < codigo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    panelCubo.add(cubo[fila][columna][capa], columna, fila);

                }
            }
        }

        for (capa = 0; capa < cubo[0][0].length; capa++) {
            for (columna = 0; columna < cubo[0].length; columna++) {
                for (fila = 0; fila < cubo.length; fila++) {
                    cubo[fila][columna][capa] = new Pane();
                    cubo[fila][columna][capa].setPrefSize(50, 50);
                    //Ancho y largo de los Pane

                    String letra = codigo[fila][columna][capa];
                    char primeraLetra = letra.charAt(0);

                    switch (primeraLetra) {
                        case 'A':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: white; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'B':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: yellow; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'C':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'D':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: orange; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'E':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        case 'F':
                            cubo[fila][columna][capa].setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                            break;
                        default:
                            break;
                    }
                    switch (capa) {
                        case 0:
                            PanelUno.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 1:
                            PanelDos.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 2:
                            PanelTres.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 3:
                            PanelCuatro.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 4:
                            PanelCinco.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        case 5:
                            PanelSeis.add(cubo[fila][columna][capa], columna, fila);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }

    @FXML
    private void sacarNumero(ActionEvent event) {

        try {
            if (!pilaMovimientos.isEmpty()) {
                int ultimoMovimiento = pilaMovimientos.pop();

                // Obtén el estado anterior del cubo desde la pila de estados
                if (!pilaEstados.isEmpty()) {
                    String[][][] estadoPrevio = pilaEstados.pop();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            for (int k = 0; k < 6; k++) {
                                codigo[i][j][k] = estadoPrevio[i][j][k];

                            }
                        }

                    }
                    actualizarInterfazGrafica();
                } else {

                }
            } else {
                // La pila de movimientos está vacía, no hay movimientos para deshacer
                // Puedes mostrar un mensaje o realizar otras acciones
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        imprimirPila();
    }

    @FXML
    private int Revisar(ActionEvent event) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    String letra = codigo[k][j][i];
                    char primeraLetra = letra.charAt(0);
                    switch (i) {
                        case 0:
                            if (primeraLetra != 'A') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;
                        case 1:
                            if (primeraLetra != 'B') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;
                        case 2:
                            if (primeraLetra != 'C') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;
                        case 3:
                            if (primeraLetra != 'D') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;
                        case 4:
                            if (primeraLetra != 'E') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;
                        case 5:
                            if (primeraLetra != 'F') {
                                Alert alerta = new Alert(AlertType.WARNING);
                                alerta.setTitle("REVISAR");
                                alerta.setHeaderText("El cubo no esta armado.");

                                alerta.showAndWait();
                                return 0;
                            }
                            break;

                        default:

                            break;
                    }

                }
            }
        }

        // Ruta completa del archivo que deseas leer
        String rutaArchivo = "C://Users//gatov//OneDrive//Escritorio//CarpetaJava//record.txt";

        try {
            // Crear un objeto FileReader para leer el archivo
            FileReader archivo = new FileReader(rutaArchivo);

            // Crear un objeto BufferedReader para leer líneas del archivo
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            int aux = 0;
            // Leer y mostrar cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                aux++;

                if (aux == 2) {
                    int N = Integer.parseInt(linea);
                    if (movimientos < N) {
                        lector.close();
                        record();
                    } else {
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Advertencia");
                        alerta.setHeaderText("No hay record.");

                        alerta.showAndWait();
                        return 0;
                    }
                }
            }

            // Cerrar el archivo
            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("REVISAR");
        alerta.setHeaderText("Felicidades, completó el cubo.");

        alerta.showAndWait();
        return 0;
    }

    @FXML
    private void Guardar(ActionEvent event) throws IOException {
        String carpeta = "C://Users//gatov//OneDrive//Escritorio//CarpetaJava//";

        // Nombre del archivo que deseas crear
        String nombreArchivo = "juego.txt";
        // Crear un objeto File para la carpeta
        File carpetaDestino = new File(carpeta);

        // Asegurarse de que la carpeta exista; si no, crearla
        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs(); // Crea la carpeta y sus padres si no existen
        }

        // Crear un objeto FileWriter para escribir en el archivo
        FileWriter archivo = new FileWriter(carpeta + nombreArchivo);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    String letra = codigo[k][j][i];
                    // Ruta de la carpeta donde deseas guardar el archivo

                    try {
                        archivo.write("" + letra + "\n");

                    } catch (IOException e) {
                        System.err.println("Error al crear el archivo: " + e.getMessage());
                    }
                }
            }
        }
        archivo.close();
    }

}
