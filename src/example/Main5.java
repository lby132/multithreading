package example;

public class Main5 {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        incrementingThread.join();

        decrementingThread.start();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
        System.out.println("decrementingThread = " + decrementingThread);

    }

    private static class DecrementingThread extends Thread {
        private final InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class IncrementingThread extends Thread {

        private final InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        Object lock = new Object();

        public void increment() {
            synchronized (this.lock) {
                items++;
            }
        }

        public void decrement() {
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lock) {
                return items--;
            }
        }
    }


}
