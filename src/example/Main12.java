package example;

import java.util.concurrent.atomic.AtomicReference;

public class Main12 {

    public static void main(String[] args) {
        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);

        atomicReference.set("Unexpected name");
        if (atomicReference.compareAndSet(oldName, newName)) {
            System.out.println("New Value is " + atomicReference.get());
        } else {
            System.out.println("Nothing Changed");
        }

    }
}
