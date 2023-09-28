package example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<ThreadCon> threads = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            threads.add(new ThreadCon(inputNumber));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            ThreadCon factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.print("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getRessult());
            } else {
                System.out.print("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

    }

    public static class ThreadCon extends Thread {

        private long inputNumber;
        private BigInteger ressult = BigInteger.ZERO;
        private boolean isFinished = false;

        public ThreadCon(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.ressult = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i++) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getRessult() {
            return ressult;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
