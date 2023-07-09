public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 운영체제가 각 스레드에 동적 우선순위를 적용할 수 있는데 스레드 객체를 사용하면 동적 우선순위의 정적 요소를 계획적으로 설정할 수 있다.
        Thread thread = new Thread(() -> {
            System.out.println("We are now in thread " + Thread.currentThread().getName());
            System.out.println("We are now in thread " + Thread.currentThread().getPriority());
        });

        thread.setName("New Worker Thread");

        thread.setPriority(Thread.MAX_PRIORITY); // 스레드가 가질 수 있는 최대 우선순위. 여기에선 결과가 달라지지 않지만 많은 스레드 요청에는 달라진다고 함.

        System.out.println("We are now in thread " + Thread.currentThread().getName() + " before string a new thread");
        thread.start();
        System.out.println("We are now in thread " + Thread.currentThread().getName() + " after string a new thread");

        Thread.sleep(10000);
    }
}