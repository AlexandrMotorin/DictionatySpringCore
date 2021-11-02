package org.example.Controllers;

import org.example.Config.DictionaryType;
import org.example.Exceptions.MyException;
import org.example.Service.DictionaryService;
import org.example.View.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("controllerBean")
public class Controller {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private View view;

    public void getAllData(){
        Map<String, String> map = dictionaryService.getAllWords();
        if (map.size() != 0) view.returnData(map);
        else view.returnMassage("Словарь пуст");
    }

    public void getWordByKey(String key) {
        Map<String,String> map;
        try{
            map = dictionaryService.getWordByKey(key);
            if (map.size() != 0) view.returnData(map);
            else view.returnMassage("Такого слова нет в словаре");
        }catch (MyException e){
            view.returnMassage(e.getMessage());
        }
    }

    public void deleteWord(String word) {
        try {
            dictionaryService.deleteWord(word);
            view.returnMassage("запись успешно удалена");
        }catch (MyException e){
            view.returnMassage(e.getMessage());
        }
    }

    public void add(String s, String word) {
        try{
            dictionaryService.addEntry(s, word);
            view.returnMassage("Слово успешно добавлено");
        }catch (MyException e){
            view.returnMassage(e.getMessage());
        }
    }

    public void setDictionaryServiceType(DictionaryType type){
        dictionaryService.setType(type);
    }
}
