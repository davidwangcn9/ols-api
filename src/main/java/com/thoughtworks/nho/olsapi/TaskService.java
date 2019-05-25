package com.thoughtworks.nho.olsapi;

import com.thoughtworks.nho.olsapi.camp.Task;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskService {
    public static List<Task> getTasks(int index) {
        //have 5 groups of fake data?
        Task task1 = new Task("hello", "hello world", "2019-05-06");
        Task task2 = new Task("good morning", "hello world", "2019-05-06");
        Task task3 = new Task("nice to see you", "hello world", "2019-05-06");
        Task task4 = new Task("nice to see you!", "hello world", "2019-05-06");
        Task task5 = new Task("nice to see you!!", "hello world", "2019-05-06");
        return Stream.of(task1, task2, task3, task4, task5).collect(Collectors.toList());
    }
}
