package interrupt;

import java.math.BigInteger;

public class Main2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("1000000000"))); // 큰 숫자를 넣고 실행하면 계산에 오랜시간이 걸려 LongComputationTask 함수가 interrupt를해도 멈추지 않는다.

        //thread.setDaemon(true); // main 스레드만 종료되어도 전체 앱이 종료 된다.
        thread.start();
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                //코드 내에서 시간이 오래걸리는 곳에 인터럽트를 당했으면 계산을 멈추고 스레드를 종료하는 코드를 작성한다.
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
