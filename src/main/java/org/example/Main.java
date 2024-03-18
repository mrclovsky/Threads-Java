package org.example;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Main {
    private static void initThreads(TaskQueue taskQueue, ResultList resultList,int threadsNum){
        for (int i = 0; i < threadsNum; i++) {
            StringBuilder sb = new StringBuilder("Thread");
            sb.append(" " + i + ":");
            String name = sb.toString();
            Thread thread = new Thread(new CalculationThread(taskQueue, resultList, name));
            thread.start();
        }
    }

    private static void addTasks(TaskQueue taskQueue, Scanner scanner){
        Boolean running = true;
        LinkedList<Task> tasks = new LinkedList<>();
        while (running) {
            System.out.println("Add task:  [input/stop]");
            String input = scanner.nextLine();
            if (input.equals("stop")) {
                running = false;
            }else{
                Task task = new Task(Integer.parseInt(input));
                tasks.add(task);
            }
        }
        for(Task task:tasks){
            taskQueue.add(task);
        }
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int threadsNum = Integer.parseInt(args[0]);
        TaskQueue taskQueue = new TaskQueue();
        ResultList resultList = new ResultList();

        initThreads(taskQueue, resultList, threadsNum);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:  [add/print/exit]");
            String input = scanner.nextLine();
            if (input.equals("add")) {
                addTasks(taskQueue, scanner);
            } else if(input.equals("print")) {
                System.out.println("Results:");
                resultList.print();
                System.out.println();
            }else if(input.equals("exit")){
                Thread.getAllStackTraces().keySet().forEach(thread -> {
                    if (thread.getState() == Thread.State.RUNNABLE) {
                        thread.interrupt();
                    }
                });
                System.exit(0);
            }
        }

    }
}