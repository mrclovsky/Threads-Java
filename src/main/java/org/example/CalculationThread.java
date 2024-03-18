package org.example;

class CalculationThread implements Runnable {
    private TaskQueue taskQueue;
    private ResultList resultList;
    private String name;

    public CalculationThread(TaskQueue taskQueue, ResultList resultList, String name) {
        this.taskQueue = taskQueue;
        this.resultList = resultList;
        this.name = name;
    }

    private boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        while (true) {
            try {
                Task task = taskQueue.get();
                System.out.println(name + " got " + task.getNumber());
                boolean isPrime = isPrime(task.getNumber());
                resultList.add(new Result(task.getNumber(), isPrime));
                System.out.println(name + " added " + task.getNumber());

            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}