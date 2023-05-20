package com.example.visualmemorytraining.data;

import java.util.ArrayList;

public class Tasks {
    private static ArrayList<Task> tasks;
private static ArrayList<String> urls;
    static public ArrayList<Task> getTasks() {
        tasks = new ArrayList<>();
        ArrayList<Question> list1 = new ArrayList<>();
        /*1*/ list1.add(new Question("Какой цвет отсутствует на изображении?",
                "Красный", "Жёлтый",
                "Оранжевый", "Синий",
                "Оранжевый"));
        /*2*/ list1.add(new Question("Сколько прямоугольников, разделенных черными линиями?",
                "4", "5",
                "6", "7",
                "7"));
        /*3*/ list1.add(new Question("Какого цвета второй по величине прямоугольник?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Белый"));
        /*4*/list1.add(new Question("Какого цвета прямоугольник в правом нижнем углу?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Жёлтый"));
        /*5*/ list1.add(new Question("Количество белых прямоугольников?",
                "5", "6",
                "3", "4",
                "4"));
        /*6*/list1.add(new Question("Какого цвета первый по величине прямоугольник?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Красный"));
        /*7*/list1.add(new Question("В скольких местах пересекают друг друга черные линии?",
                "4", "5",
                "3", "2",
                "4"));
      /*  *//*8*//*list1.add(new Question("Сколько на изображении синих треугольников?",
                "1", "2",
                "5", "0",
                "0"));
        *//*9*//*list1.add(new Question("Сколько горизонтальных черных линий на изображении?",
                "1", "2",
                "3", "4",
                "3"));
        *//*10*//* list1.add(new Question("Сколько вертикальных черных линий на изображении?",
                "1", "2",
                "3", "4",
                "2"));*/


        ArrayList<Question> list2 = new ArrayList<>();
        /*1*/ list2.add(new Question("Какой цвет отсутствует на изображении?",
                "Красный", "Жёлтый",
                "Оранжевый", "Синий",
                "Оранжевый"));
        /*2*/ list2.add(new Question("Сколько прямоугольников, разделенных черными линиями?",
                "4", "5",
                "6", "7",
                "7"));
        /*3*/ list2.add(new Question("Какого цвета второй по величине прямоугольник?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Белый"));
        /*4*/list2.add(new Question("Какого цвета прямоугольник в правом нижнем углу?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Жёлтый"));
        /*5*/ list2.add(new Question("Количество белых прямоугольников?",
                "5", "6",
                "3", "4",
                "4"));
        /*6*/list2.add(new Question("Какого цвета первый по величине прямоугольник?",
                "Красный", "Жёлтый",
                "Белый", "Синий",
                "Красный"));
        /*7*/list2.add(new Question("В скольких местах пересекают друг друга черные линии?",
                "4", "5",
                "3", "2",
                "4"));
        /*8*//*list2.add(new Question("Сколько на изображении синих треугольников?",
                "1", "2",
                "5", "0",
                "0"));
        *//*9*//*list2.add(new Question("Сколько горизонтальных черных линий на изображении?",
                "1", "2",
                "3", "4",
                "3"));
        *//*10*//* list2.add(new Question("Сколько вертикальных черных линий на изображении?",
                "1", "2",
                "3", "4",
                "2"));*/
        Task task1 = new Task(list1);
        Task task2 = new Task(list2);
        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }

    static public ArrayList<String> getUrls() {
        urls = new ArrayList<>();
        urls.add("https://gallerysmart.ru/images/blog/geometricheskaya-abstrakcziya-pit-mondrian.png");
        urls.add("https://upload.wikimedia.org/wikipedia/commons/4/4c/%D0%9A%D0%B0%D0%B7%D0%B8%D0%BC%D0%B8%D1%80_%D0%9C%D0%B0%D0%BB%D0%B5%D0%B2%D0%B8%D1%87._%D0%A1%D1%83%D0%BF%D1%80%D0%B5%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%BD%D0%B0_%D0%BA%D0%BE%D0%BC%D0%BF%D0%BE%D0%B7%D0%B8%D1%86%D1%96%D1%8F._1916.jpg");
        return urls;
    }
}
