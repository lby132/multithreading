package example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<ThreadCon> threads = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            threads.add(new ThreadCon(inputNumber));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            //thread.setDaemon(true); // 데몬스레드를 true를 주면 main 스레드만 종료되어도 전체 앱이 종료 된다.
            thread.join(2000); //스레드에 기다릴 시간을 추가해 계산 하나가 제시간에 완료되지 않아도 되는 문제 해결
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
