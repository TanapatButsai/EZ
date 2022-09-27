package ku.cs.shop.services;

import ku.cs.shop.models.Dictionary;
import ku.cs.shop.models.PartsOfSpeech;
import ku.cs.shop.models.Word;

import java.io.*;
import java.util.*;

public class WordListDataSource implements DataSource<Dictionary>{
    private String directoryName;

    private String fileName;

    public WordListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }


    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if (! file.exists()){
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (! file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Dictionary readData() {
        Dictionary dictionary = new Dictionary();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;
        int kkk = 0;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            Word word = null;
            while ( ( line = buffer.readLine() ) != null ){
                String[] data = line.split(",");

                    String key = data[0].trim();
                    String partsOfSpeech = data[1].trim();
                    String meaning = data[2].trim();
                    String example = data[3].trim();
                    String[] exampleArr = example.split(":");
                    ArrayList<String> exampleArrList = new ArrayList<>(Arrays.asList(exampleArr));
                    PartsOfSpeech x = findPartsOfSpeech(partsOfSpeech);
                    word = new Word(key,x);
                    word.defineMeaning(meaning);
                    word.setExamples(exampleArrList);
                    dictionary.add(word.getWord(),word);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return dictionary;
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

    @Override
    public void writeData(Dictionary dictionary) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            StringBuilder line;
            for (Map.Entry<String, Word> entry : dictionary.getAllWord().entrySet()){
                String example = "";
                for(String string:entry.getValue().getExamples()){
                    example = example +string+ ":";
                }
                line = new StringBuilder(entry.getKey() +","+ entry.getValue().getPartsOfSpeech().toString()+","+
                         entry.getValue().getMeaning() +","+ example);
                buffer.append(line);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}