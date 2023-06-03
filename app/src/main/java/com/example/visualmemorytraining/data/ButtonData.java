package com.example.visualmemorytraining.data;

import java.util.ArrayList;
import java.util.List;

public class ButtonData {
   //Возвращает лист данных кнопок
    public static ArrayList<Data> getSource(){
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 1; i < 26; i++) {
            Data button = new Data(String.valueOf(i));
            list.add(button);
        }
        return list;
    }
}
