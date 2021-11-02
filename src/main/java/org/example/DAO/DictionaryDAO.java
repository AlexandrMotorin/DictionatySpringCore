package org.example.DAO;

import org.example.Config.DictionaryType;

import java.util.Map;

public interface DictionaryDAO {
    void setPathByType(DictionaryType type);
    Map<String, String> getAllWords();
    Map<String, String> getWordByKey(String word);
    void deleteWord(String word);
    void addEntry(String word,String translate);
}
