package com.company;

 /*Дан список Programmer(String name, String city, List<Task>  tasks). Каждый программист  имеет список задач
 Task (int Number, String description, String status, int daysInProcessing) . Сформировать лист из всех задач, которые
 относятся к программистам из Берлина, не завершены (т.е. имеют статус, отличный от «done») и висят в обработке более 5 дней.*/


import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Programmer p1 = new Programmer("Jack", "Berlin", List.of(
                new Task(1, "Task1", "done", 0),
                new Task(2, "Task2", "process", 2),
                new Task(3, "Task3", "process", 6)
        ));
        Programmer p2 = new Programmer("Nick", "Frankfurt", List.of(
                new Task(1, "Task1", "process", 8),
                new Task(2, "Task2", "done", 0),
                new Task(3, "Task3", "process", 1)
        ));

        Programmer p3 = new Programmer("Anna", "Berlin", List.of(
                new Task(1, "Task1", "process", 7),
                new Task(2, "Task2", "done", 0),
                new Task(3, "Task3", "process", 8)
        ));

       List<Programmer> programmers = List.of(p1,p2,p3);
       List<Task> tasks = getTasks(programmers);

        for (Task pp: tasks ) {
            System.out.println(pp.toString());

        }



    }

    public static List<Task> getTasks(List<Programmer> programmers) {

       return programmers.stream()
                .filter(p->p.city=="Berlin")
                .flatMap(p->p.getTasks().stream())
                .filter(t->t.status!="done" && t.daysInProcessing>5)
                .distinct()
                .collect(Collectors.toList());


    }

}
