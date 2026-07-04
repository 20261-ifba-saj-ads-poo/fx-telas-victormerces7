package br.edu.ifba.saj.ads.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class IngressoController {

    @FXML
    private TextField txNomeProfessor;

    @FXML
    private TextField txMatriculaProfessor;

    @FXML
    private TextField txDepartamento;

    @FXML
    private TextField txNomeAluno;

    @FXML
    private TextField txMatriculaAluno;

    @FXML
    private TextField txCursoSemestre;

    @FXML
    void cadastrarProfessor(ActionEvent event) {
        if (txNomeProfessor.getText().isEmpty() || txMatriculaProfessor.getText().isEmpty() || txDepartamento.getText().isEmpty()) {
            new Alert(AlertType.ERROR, "Preencha todos os campos do professor!").showAndWait();
            return;
        }
        
        // TODO: Add professor to data storage
        new Alert(AlertType.INFORMATION, "Professor cadastrado com sucesso!").showAndWait();
        limparCamposProfessor();
    }

    @FXML
    void cadastrarAluno(ActionEvent event) {
        if (txNomeAluno.getText().isEmpty() || txMatriculaAluno.getText().isEmpty() || txCursoSemestre.getText().isEmpty()) {
            new Alert(AlertType.ERROR, "Preencha todos os campos do aluno!").showAndWait();
            return;
        }
        
        // TODO: Add student to data storage
        new Alert(AlertType.INFORMATION, "Aluno cadastrado com sucesso!").showAndWait();
        limparCamposAluno();
    }

    @FXML
    void voltarParaIndex(ActionEvent event) {
        try {
            FXMLLoader.load(getClass().getResource("Index.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCamposProfessor() {
        txNomeProfessor.clear();
        txMatriculaProfessor.clear();
        txDepartamento.clear();
    }

    private void limparCamposAluno() {
        txNomeAluno.clear();
        txMatriculaAluno.clear();
        txCursoSemestre.clear();
    }
}
