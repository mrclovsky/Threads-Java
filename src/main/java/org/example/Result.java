package org.example;

class Result {
    private long numberChecked;
    private boolean isPrime;

    public Result(long numberChecked, boolean isPrime) {
        this.numberChecked = numberChecked;
        this.isPrime = isPrime;
    }

    public long getNumberChecked() {
        return numberChecked;
    }

    public boolean isPrime() {
        return isPrime;
    }
}
