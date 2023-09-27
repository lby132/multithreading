package example;

import java.math.BigInteger;

public class ThreadCon extends Thread {
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
