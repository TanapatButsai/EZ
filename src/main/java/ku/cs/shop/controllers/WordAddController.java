package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Dictionary;
import ku.cs.shop.models.PartsOfSpeech;
import ku.cs.shop.models.Word;
import ku.cs.shop.services.DataSource;
import ku.cs.shop.services.WordListDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class WordAddController {
    @FXML private ImageView imageNaja;
    @FXML private TextField newWordTextField;
    @FXML private TextField newPartsOfSpeechTextField;
    @FXML private TextField newMeaningTextField;
    @FXML private TextField wordAddExampleTextField;
    @FXML private TextField AddExampleTextField;
    @FXML private TextField exampleTextField1;
    private DataSource<Dictionary> dataSource = new WordListDataSource("data","dictionary.csv");

    private Dictionary dictionary;



    @FXML public void initialize(){
        String url = getClass().getResource("/ku/cs/images/cat.jpg").toExternalForm();
        imageNaja.setImage(new Image(url));
        dictionary = dataSource.readData();

    }
    @FXML public void handleBackToHome(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void handleAddWord(ActionEvent actionEvent){
        if (exampleTextField1.getText().equals("") || newPartsOfSpeechTextField.getText().equals("")
                || newMeaningTextField.getText().equals("")|| newWordTextField.getText().equals("")){
            newMeaningTextField.clear();
            newWordTextField.clear();
            exampleTextField1.clear();
            newPartsOfSpeechTextField.clear();
        }else {
            String newWord = newWordTextField.getText();
            String partsOfSpeechString = newPartsOfSpeechTextField.getText();
            String meaning = newMeaningTextField.getText();
            String example = exampleTextField1.getText();
            PartsOfSpeech partsOfSpeech = findPartsOfSpeech(partsOfSpeechString);
            Word word = new Word(newWord,partsOfSpeech);
            word.defineMeaning(meaning);
            dictionary.add(newWord,word);
            dictionary.addWordExample(newWord,exampleTextField1.getText());
            dataSource.writeData(dictionary);
        }

    }

    @FXML public void handleAddExample(ActionEvent actionEvent){
        if (wordAddExampleTextField.getText().equals("")||AddExampleTextField.getText().equals("")){
            wordAddExampleTextField.setText("");
            AddExampleTextField.setText("");

        } else if (dictionary.isWord(wordAddExampleTextField.getText())) {
            wordAddExampleTextField.setText("");
            AddExampleTextField.setText("");
        } else {
            String wordExample = wordAddExampleTextField.getText();
            String example = AddExampleTextField.getText();
            dictionary.addWordExample(wordExample,example);
            dataSource.writeData(dictionary);
            wordAddExampleTextField.clear();
            AddExampleTextField.clear();
        }

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
