package br.edu.ifba.saj.ads.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class IndexController {

    @FXML
    private BorderPane pane;

    @FXML
    private TableView<?> tbProjetos;

    @FXML
    private TextField txBusca;

    @FXML
    public void abrirCadastroIntegrantes(ActionEvent event) {
        try {
            pane.setCenter(FXMLLoader.load(getClass().getResource("Ingresso.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirCadastroProjeto(ActionEvent event) {
        try {
            pane.setCenter(FXMLLoader.load(getClass().getResource("Sessao.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
