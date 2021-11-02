package org.example.DAO;
import org.example.Config.DictionaryType;
import org.example.Exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component("daoBean")
public class DictionaryDAOImpl implements DictionaryDAO{

    private Path dictionaryPath;
    @Autowired
    private PathDistributor pathDistributor;

    public void setPathByType(DictionaryType type){
        String path = pathDistributor.getPathByType(type);
        dictionaryPath = Paths.get(path);
    }

    @Override
    public Map<String, String> getAllWords() {

        Map<String,String> result = null;

        try {
            result = Files.lines(dictionaryPath)
                    .collect(Collectors.toMap(
                            s -> s.split(" - ")[0],
                            s -> s.split(" - ")[1],
                            (a,b) -> a,
                            TreeMap<String,String>::new
                    ));
        } catch (IOException e) {
            throw new MyException("Ошибка при чтении файла");
        }

        return result;
    }

    @Override
    public Map<String, String> getWordByKey(String word) {
        if(!wordIsPresent(word,getAllWords()))
            throw new MyException("Такого слова нет в словаре");

        return getAllWords().entrySet().stream()
                .filter(e -> e.getKey().equals(word))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Override
    public void deleteWord(String word) {
        Map<String,String> allWords = getAllWords();
        if(!wordIsPresent(word,allWords)) throw new MyException("Такого слова нет с словаре");
        else {
            try (Writer writer = new FileWriter(dictionaryPath.toFile())){
                allWords.remove(word);
                writeIntoFile(writer, allWords);
            } catch (IOException e) {
                throw new MyException("Произошла ошибка при удалении");
            }
        }
    }

    @Override
    public void addEntry(String word, String translate) {
        Map<String,String> allWords = getAllWords();
        if(wordIsPresent(word,allWords)) throw new MyException("Такое слово уже есть в словаре");
        else {
            allWords.put(word,translate);
            try(Writer writer = new FileWriter(dictionaryPath.toFile())){
                writeIntoFile(writer,allWords);
            }catch (IOException e){
                throw new MyException("Произошла ошибка при добавлении слова");
            }
        }
    }

    public boolean wordIsPresent(String word,Map<String,String> map){
        return map.entrySet().stream().anyMatch(e -> e.getKey().equals(word));
    }

    public void writeIntoFile(Writer writer, Map<String, String> resultMap) throws IOException {
        for(Map.Entry<String,String> entry : resultMap.entrySet()){
            writer.write(String.format("%s - %s\n",entry.getKey(),entry.getValue()));
            writer.flush();
        }
    }
}
