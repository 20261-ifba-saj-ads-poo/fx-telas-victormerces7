package br.edu.ifba.saj.ads.poo;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.function.UnaryOperator;

import br.edu.ifba.saj.ads.poo.data.Cinema;
import br.edu.ifba.saj.ads.poo.model.Filme;
import br.edu.ifba.saj.ads.poo.model.Sessao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class SessaoController {

    @FXML
    private DatePicker dtHorarioDia;
    @FXML
    private Spinner<Integer> dtHorarioHora;
    @FXML
    private Spinner<Integer> dtHorarioMinuto;

    @FXML
    private ChoiceBox<Filme> slFilme;

    @FXML
    private TextField txQuantidade;

    @FXML
    private TextField txValor;

    private Filme filmeSelecionado;

    @FXML
    private void initialize() {
        slFilme.getItems().addAll(Cinema.filmes);
        slFilme.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filmeSelecionado = newValue;
            }
        });

        slFilme.setConverter(new StringConverter<Filme>() {
            @Override
            public String toString(Filme filme) {
                return filme == null ? "" : filme.getNome();
            }

            @Override
            public Filme fromString(String string) {
                return null;
            }
        });

        dtHorarioHora.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        dtHorarioMinuto.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        txValor.setStyle("-fx-alignment: CENTER-RIGHT;");

        txValor.setTextFormatter(new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }

            String textoApenasNumeros = change.getControlNewText().replaceAll("[^0-9]", "");

            if (textoApenasNumeros.isEmpty()) {
                change.setText("0.00");
                change.setCaretPosition(4);
                change.setAnchor(4);
                return change;
            }

            long valorLong = Long.parseLong(textoApenasNumeros);
            String novoTextoFormatado = String.format("%.2f", valorLong / 100.0);

            int tamanhoTexto = novoTextoFormatado.length();
            change.setRange(0, change.getControlText().length());
            change.setText(novoTextoFormatado);
            change.setCaretPosition(tamanhoTexto);
            change.setAnchor(tamanhoTexto);

            return change;
        }));

        txValor.setText("0.00");

        txValor.focusedProperty().addListener((obs, antigo, novoFoco) -> {
            if (novoFoco) {
                Platform.runLater(txValor::end);
            }
        });

        UnaryOperator<TextFormatter.Change> filtroInteiro = change -> {
            String novoTexto = change.getControlNewText();
            if (novoTexto.matches("\\d*")) {
                return change;
            }
            return null;
        };

        txQuantidade.setTextFormatter(new TextFormatter<>(filtroInteiro));
    }

    private LocalDateTime getHorarioSelecionado() {
        LocalDate date = dtHorarioDia.getValue();
        if (date == null) {
            return null;
        }

        int hour = dtHorarioHora.getValue();
        int minute = dtHorarioMinuto.getValue();

        return LocalDateTime.of(date, LocalTime.of(hour, minute));
    }

    @FXML
    void salvar(ActionEvent event) throws NumberFormatException, ParseException {
        getFilmeSelecionado().addSessao(new Sessao(getHorarioSelecionado(), filmeSelecionado, Integer.valueOf(txQuantidade.getText()), NumberFormat.getNumberInstance(Locale.of("pt", "BR")).parse(txValor.getText()).floatValue()));

        new Alert(AlertType.INFORMATION,
                String.format("Horario filme %1$td/%1$tm/%1$tY %1$tH:%1$tM ", getHorarioSelecionado())).showAndWait();
        //new Alert(AlertType.INFORMATION, String.format("Nome filme %s ", getFilmeSelecionado().getNome()))
        //        .showAndWait();
        //new Alert(AlertType.INFORMATION, String.format("Sessoes filme %s  %s", getFilmeSelecionado().getNome(), getFilmeSelecionado().getSessoes().toString()))
        //        .showAndWait();

    }

    public Filme getFilmeSelecionado() {
        return filmeSelecionado;
    }

}
