package ku.cs.shop.models;

import java.util.ArrayList;
import java.util.Collection;

public class Word {

    private String word;
    private PartsOfSpeech partsOfSpeech;
    private String meaning;
    private ArrayList<String> examples;


    public Word(String word, PartsOfSpeech partsOfSpeech) {
        this.word = word;
        this.partsOfSpeech = partsOfSpeech;
        examples = new ArrayList<>();
    }

    public void defineMeaning (String meaning){
        this.meaning = meaning;
    }

    public void addExample(String example){
        examples.add(example);
    }
    public String getWord() {
        return word;
    }

    public PartsOfSpeech getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public String getMeaning() {
        return meaning;
    }

    public ArrayList<String> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<String> examples) {
        this.examples = examples;
    }

    @Override
    public String toString() {
        return word;
    }
}