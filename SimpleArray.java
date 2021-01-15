
// Class that manages an integer array to be shared by multiple threads.
import java.security.SecureRandom;

public class SimpleArray {
        private static final SecureRandom generator = new SecureRandom();
        private final int[] array; // the shared integer array
        private int writeIndex = 0; // shared index of next element to write

        // construct a SimpleArray of a given size
        public SimpleArray(int size) {array = new int[size];}
        // add a value to the shared array
        public synchronized void add(int value) {
        int position = writeIndex; // store the write index
        try {
            // put thread to sleep for 0-499 milliseconds
            Thread.sleep(generator.nextInt(500));
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }

        // put value in the appropriate element
        array[position] = value;
        System.out.printf("%s wrote %2d to element %d.%n", Thread.currentThread().getName(), value, position);

        ++writeIndex; // increment index of element to be written next
        System.out.printf("Next write index: %d%n", writeIndex);
        }

        // Dipslaying result
        @Override
        public synchronized String toString() {
                int counter = 0;
                for(int i = 0 ; i < 900 ; i++){
                        if(array[i]%25 == 0){
                                counter++;
                        }
                }
            return String.valueOf(counter);
        }
}