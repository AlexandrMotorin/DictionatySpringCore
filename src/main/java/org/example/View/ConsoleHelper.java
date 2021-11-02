package org.example.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static void consoleServiceMenu(){
        System.out.println("""
                -------------------\s
                Меню:\s
                1. Просмотреть содержимое\s
                2. Поиск слова\s
                3. Удалить запись\s
                4. Добавить запись\s
                5. Вернуться к выбору словаря
                6. Закрыть сервис\s
                """);

    }

    static void connectorToDictMenu(){
        System.out.println("""
                -------------------\s
                Выберите словарь с которым хотите работать: \s
                1. Английский\s
                2. Числовой\s
                """);
    }

    static void resultToConsole(Map<String,String> map){
        System.out.println("Результат запроса: ");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    static int readIntMenu(String conditionsAsRegex){
        int result = 0;
        do {
            String line = readWord();
            if(!line.matches(conditionsAsRegex)) System.out.println("Ошибка! Выберите номер пункта меню из указанных выше");
            else result = Integer.parseInt(line);
        }while (result == 0);

        return result;
    }

    private static String readWord(){
        String word = null;

        try {
            word = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Непредвиденная ошибка");
        }

        return word;
    }

    static String readWordTranslate(){
        System.out.println("Введите перевод");
        return readWord();
    }

    static String readSearchWord(){
        System.out.println("Введите слово");
        return readWord();
    }

    static void messageToConsole(String message){
        System.out.println(message);
    }
}
