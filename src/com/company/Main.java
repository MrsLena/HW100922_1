package com.company;

 /*1. Дан список Programmer(String name, String city, List<Task>  tasks). Каждый программист  имеет список задач
 Task (int Number, String description, String status, int daysInProcessing) . Сформировать лист из всех задач, которые
 относятся к программистам из Берлина, не завершены (т.е. имеют статус, отличный от «done») и висят в обработке более 5 дней.*/

/*2. Дан список Programmer(String name, String city, List<Task>  tasks). Каждый программист  имеет список задач
  Task (int Number, String description, String status, int daysInProcessing) .
  Сформировать лист из дести задач которые дольше всего находятся в работе.
 */

/*3. Дан список Programmer(String name, String city, List<Task>  tasks).
Каждый программист  имеет список задач Task (int Number, String description, String status, int daysInProcessing) .
Сформировать лист строк вида «Программист:НомерЗадача:ДнейВOбработке».
 */

 /*4. Дан список строк «Иванов Иван Иванович: 645» Сформировать отсортированный по числовому полю список строк вида:
     «645:Иванов И.И.», при этом отобрать только те строки, где числовое поле больше 500.*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Programmer p1 = new Programmer("Jack", "Berlin", List.of(
                new Task(1, "Task1", "done", 0),
                new Task(2, "Task2", "process", 2),
                new Task(5, "Task5", "done", 0),
                new Task(6, "Task6", "process", 10),
                new Task(3, "Task3", "process", 6)
        ));
        Programmer p2 = new Programmer("Nick", "Frankfurt", List.of(
                new Task(1, "Task1", "process", 8),
                new Task(2, "Task2", "done", 0),
                new Task(5, "Task5", "process", 8),
                new Task(6, "Task6", "process", 12),
                new Task(3, "Task3", "process", 1)
        ));

        Programmer p3 = new Programmer("Anna", "Berlin", List.of(
                new Task(1, "Task1", "process", 7),
                new Task(2, "Task2", "done", 0),
                new Task(5, "Task5", "process", 1),
                new Task(6, "Task6", "process", 5),
                new Task(3, "Task3", "process", 8)
        ));

       List<Programmer> programmers = List.of(p1,p2,p3);

       getTasksFirst(programmers).forEach(t->System.out.println(t.toString()));
       getTasksSecond(programmers,10).forEach(t->System.out.println(t.toString()));
       getTasksThird(programmers).forEach(t->System.out.println(t.toString()));
       tasksFour().forEach(t->System.out.println(t));


    }



    //1. Сформировать лист из всех задач, к программистам из Берлина, не завершены (т.е. имеют статус, отличный от «done») и висят в обработке более 5 дней.*/
    public static List<Task> getTasksFirst(List<Programmer> programmers) {

        return programmers.stream()
                .filter(p->p.city=="Berlin")
                .flatMap(p->p.getTasks().stream())
                .filter(t->t.status!="done" && t.daysInProcessing>5)
                .distinct()
                .collect(Collectors.toList());
    }


    //2. Сформировать лист из дести задач которые дольше всего находятся в работе.
    //!!!!! НЕ ПОЛУЧИЛАСЬ СОРТИРОВКА ПО УБЫВАНИЮ(((((
    public static List<Task> getTasksSecond(List<Programmer> programmers, Integer Amount) {

        return programmers.stream()
                .flatMap(p->p.getTasks().stream())
                .sorted(Comparator.comparingInt(t->t.getDaysInProcessing()))
                .limit(Amount)
                .collect(Collectors.toList());
    }


    //3. Сформировать лист строк вида «Программист:НомерЗадача:ДнейВOбработке».
    public static List<String> getTasksThird(List<Programmer> programmers) {

        return programmers.stream()
                .flatMap(p->  p.getTasks().stream()
                .map(tt->p.getName() + ": " + tt.getNumber() + ": " + tt.getDaysInProcessing() ))
                .collect(Collectors.toList());
    }


    /*4. Дан список строк «Иванов Иван Иванович: 645» Сформировать отсортированный по числовому полю список строк вида:
     «645:Иванов И.И.», при этом отобрать только те строки, где числовое поле больше 500.*/
    private static List<String > tasksFour() {

        List<String> list = List.of("Иванов И.И.:645","Петров П.П.:501","Сидоров С.С.:600","Федоров Ф.Ф.:300");

        return list.stream()
                .filter(e-> Integer.valueOf(e.substring(e.indexOf(":")+1,e.length()))>500   )
                .map(e-> e.substring(e.indexOf(":")+1,e.length()) + ":" + e.substring(0,e.indexOf(":")))
                .sorted()
                .collect(Collectors.toList());
    }


}
