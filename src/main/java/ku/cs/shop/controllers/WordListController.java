package ku.cs.shop.controllers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import ku.cs.shop.models.MemberCard;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.shop.models.Dictionary;
import ku.cs.shop.models.PartsOfSpeech;
import ku.cs.shop.models.Word;
import ku.cs.shop.services.DataSource;
import ku.cs.shop.services.WordListDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class WordListController {
    @FXML private ListView<Word> wordListView;
    @FXML private ListView<ArrayList> exampleListView;
    @FXML private Label wordLabel;
    @FXML private Label partsOfSpeechLabel;
    @FXML private Label meaningLabel;
    @FXML private TextArea exampleTextArea;
    private DataSource<Dictionary> dataSource;
    private Dictionary dictionary;
    @FXML
    public void initialize() {
            dataSource = new WordListDataSource("data","dictionary.csv");
            dictionary = dataSource.readData();
            System.out.println(dictionary.toString());
            showListView();
            clearAllLabel();
            handleSelectedListView();
    }
    private void clearAllLabel() {
            wordLabel.setText("");
            partsOfSpeechLabel.setText("");
            meaningLabel.setText("");
    }


    @FXML public void handleBackToHome(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showListView() {
        wordListView.getItems().addAll(dictionary.getAllWord().values());
        wordListView.refresh();
    }

    private void handleSelectedListView() {
        wordListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Word>() {
                    @Override
                    public void changed(ObservableValue<? extends Word> observableValue, Word word, Word t1) {
                        System.out.println(t1 + " is selected");
                        showSelectedWord(t1);
                    }
                });

    }
    @FXML private void showExample(Word word){

        String line = "";
        for (String string:word.getExamples()) {
            line = line + string + "\n";
        }
        exampleTextArea.setText(line);
    }
    private void showSelectedWord(Word word) {
        wordLabel.setText(word.getWord());
        partsOfSpeechLabel.setText(word.getPartsOfSpeech().toString());
        meaningLabel.setText(word.getMeaning());
        showExample(word);
    }
    public PartsOfSpeech findPartsOfSpeech(String partsOfSpeech){
        return switch (partsOfSpeech) {
            case "NOUN" -> PartsOfSpeech.NOUN;
            case "PRONOUN" -> PartsOfSpeech.PRONOUN;
            case "ADJECTIVE" -> PartsOfSpeech.ADJECTIVE;
            case "VERB" -> PartsOfSpeech.VERB;
            case "ADVERB" -> PartsOfSpeech.ADVERB;
            case "PREPOSITION" -> PartsOfSpeech.PREPOSITION;
            case "CONJUNCTION" -> PartsOfSpeech.CONJUNCTION;
            case "ARTICLE" -> PartsOfSpeech.ARTICLE;
            default -> null;
        };
    }
}
