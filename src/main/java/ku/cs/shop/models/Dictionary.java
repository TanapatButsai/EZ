package ku.cs.shop.models;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private Map<String, Word> words;

    public Dictionary() {
        words = new TreeMap<>();
    }

    public void add(String word, PartsOfSpeech partsOfSpeech){
        words.put(word,new Word(word,partsOfSpeech));
    }


    public void add(String word,Word wordWord){
        words.put(word,wordWord);
    }



    private Word find(String word) {
        Word found = words.get(word);
        if (found == null) {
            throw new RuntimeException(word +"not found in dictionary");}
        return found;
    }

    public void defineWordMeaning(String word,String meaning){
        Word found = find(word);
        found.defineMeaning(meaning);
    }

    public void addWordExample(String word,String example){
        Word found = find(word) ;
        found.addExample(example);
    }
    public Map<String, Word> getAllWord(){return words;}
    public boolean isWord(String string){
        return !words.containsKey(string);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "words=" + words +
                '}';
    }

}
