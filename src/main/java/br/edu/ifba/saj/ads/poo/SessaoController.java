package br.edu.ifba.saj.ads.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SessaoController {

    @FXML
    private TextField txTituloProjeto;

    @FXML
    private TextArea txDescricaoProjeto;

    @FXML
    private ComboBox<String> cbProfessor;

    @FXML
    private ComboBox<String> cbAluno;

    @FXML
    private void initialize() {
        // TODO: Load professors and students from data storage
        cbProfessor.getItems().addAll("Professor 1", "Professor 2");
        cbAluno.getItems().addAll("Aluno 1", "Aluno 2");
    }

    @FXML
    void salvar(ActionEvent event) {
        if (txTituloProjeto.getText().isEmpty() || txDescricaoProjeto.getText().isEmpty()) {
            new Alert(AlertType.ERROR, "Preencha o título e a descrição do projeto!").showAndWait();
            return;
        }
        
        if (cbProfessor.getValue() == null || cbAluno.getValue() == null) {
            new Alert(AlertType.ERROR, "Selecione um professor e um aluno!").showAndWait();
            return;
        }
        
        // TODO: Create project and add to data storage
        new Alert(AlertType.INFORMATION, "Projeto salvo e publicado com sucesso!").showAndWait();
        limparCampos();
    }

    @FXML
    void cancelar(ActionEvent event) {
        limparCampos();
    }

    private void limparCampos() {
        txTituloProjeto.clear();
        txDescricaoProjeto.clear();
        cbProfessor.setValue(null);
        cbAluno.setValue(null);
    }
}
