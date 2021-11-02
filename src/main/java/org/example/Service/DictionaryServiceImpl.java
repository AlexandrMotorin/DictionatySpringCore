package org.example.Service;


import org.example.Config.DictionaryType;
import org.example.DAO.DictionaryDAO;
import org.example.Exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    private DictionaryDAO dictionaryDAO;
    private DictionaryType type;

    public void setType(DictionaryType type) {
        this.type = type;
        dictionaryDAO.setPathByType(type);
    }

    private void checkValid(String word){
        switch (type){
            case NUMERIC_DICTIONARY -> {
                if (!word.matches("\\d{5}")) throw new MyException("Введено некорректное значение");
            }
            case ENGLISH_DICTIONARY -> {
                if (!word.toLowerCase().matches("[a-z]{4}")) throw new MyException("Введено не корректное значение");
            }
        }
    }

    @Override
    public Map<String, String> getAllWords() {
        return dictionaryDAO.getAllWords();
    }

    @Override
    public Map<String, String> getWordByKey(String word) {
        checkValid(word);
        return dictionaryDAO.getWordByKey(word);
    }

    @Override
    public void deleteWord(String word) {
        checkValid(word);
        dictionaryDAO.deleteWord(word);
    }

    @Override
    public void addEntry(String word, String translate) {
        checkValid(word);
        dictionaryDAO.addEntry(word,translate);
    }

}
