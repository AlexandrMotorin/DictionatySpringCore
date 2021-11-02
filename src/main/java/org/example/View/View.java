package org.example.View;

import org.example.Config.DictionaryType;
import org.example.Controllers.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("viewBean")
public class View {

    @Autowired
    public Controller controller;
    private boolean menuWork;

    public void start(){
        menuWork = true;
        connectorMenu();
        enterMenu();
    }

    public void connectorMenu(){
        ConsoleHelper.connectorToDictMenu();

        int choice = ConsoleHelper.readIntMenu("[12]");

        switch (choice){
            case 1 : controller.setDictionaryServiceType(DictionaryType.ENGLISH_DICTIONARY); break;
            case 2 : controller.setDictionaryServiceType(DictionaryType.NUMERIC_DICTIONARY); break;
            default:
        }
    }

    public void enterMenu(){
        do{
            ConsoleHelper.consoleServiceMenu();
            int choiceMenu = ConsoleHelper.readIntMenu("[123456]");

            switch (choiceMenu) {
                case 1 -> getAllData();
                case 2 -> getDataByWord(ConsoleHelper.readSearchWord());
                case 3 -> deleteWord(ConsoleHelper.readSearchWord());
                case 4 -> addWord(ConsoleHelper.readSearchWord(), ConsoleHelper.readWordTranslate());
                case 5 -> connectorMenu();
                case 6 -> menuWork = false;
            }
            if(menuWork) enterMenu();

        }while (menuWork);

    }

    public void getAllData(){
        controller.getAllData();
    }

    private void deleteWord(String word) {
        controller.deleteWord(word);
    }

    public void getDataByWord(String word){
        controller.getWordByKey(word);
    }

    private void addWord(String word, String translate) {
        controller.add(word,translate);
    }

    public void returnData(Map<String,String> map){
        ConsoleHelper.resultToConsole(map);
    }

    public void returnMassage(String message) {
        ConsoleHelper.messageToConsole(message);
    }
}
