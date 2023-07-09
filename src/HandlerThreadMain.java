public class HandlerThreadMain {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Intentional Exception");
        });

        thread.setName("Misbehaving thread");

        // setUncaughtExceptionHandler 로 처음부터 전체 스레드에 해당하는 예외 핸들러를 지정할 수 있다.
        // 스레드 내에 발생한 예외가 어디서도 캐치되지 않으면 이 핸들러가 호출되어서 캐치되지 않은 스레드와 예외를 출력하기만 하면 된다.
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                        + " the error is " + e.getMessage());
            }
        });

        thread.start();
    }
}
