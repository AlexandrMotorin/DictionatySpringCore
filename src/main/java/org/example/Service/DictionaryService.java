package org.example.Service;

import org.example.Config.DictionaryType;

import java.util.Map;

public interface DictionaryService {
    void setType(DictionaryType type);
    Map<String, String> getAllWords();
    Map<String, String> getWordByKey(String word);
    void deleteWord(String word);
    void addEntry(String word,String translate);
}
