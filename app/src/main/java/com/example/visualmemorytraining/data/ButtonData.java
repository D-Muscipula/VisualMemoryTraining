package com.example.visualmemorytraining.data;

import java.util.ArrayList;
import java.util.List;

public class ButtonData {
    //TODO свойство для изменения цвета при выполнении задания(получение из сохраненных данных)
    //map?
    public static ArrayList<Data> getSource(){
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            list.add(new Data(String.valueOf(i)));
        }
        return list;
    }
}
