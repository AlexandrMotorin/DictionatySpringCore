package org.example.DAO;

import org.example.Config.DictionaryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathDistributor {

    @Value("${english_dictionary}")
    private String enDictPath;

    @Value("${numeric_dictionary}")
    private String numDictPath;

    public String getPathByType(DictionaryType type){
        if(type == DictionaryType.ENGLISH_DICTIONARY) return enDictPath;
        if(type == DictionaryType.NUMERIC_DICTIONARY) return numDictPath;
        else return null;
    }
}
