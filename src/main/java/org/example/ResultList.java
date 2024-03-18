package org.example;
import java.util.LinkedList;

class ResultList {
    private LinkedList<Result> list = new LinkedList<>();

    public synchronized void add(Result result) {
        list.add(result);
    }

    public synchronized void print() {
        for (Result result : list) {
            System.out.println(result.getNumberChecked() + " is prime: " + result.isPrime());
        }
    }
}
