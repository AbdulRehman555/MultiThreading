/*
=================================================================
Program : Writing 0-900 integers into shared array using single and
          Multiple threads then printing the integers which are
          divisible by 25.Then comparing the time elapsed by both(single and Multiple threads)
Coded by : Abdul Rehman
Reg no.  : 4040-FBAS/BSCS/F18(A)
=================================================================
*/
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MultiThreading {
        public static void main(String[] arg) {
            //Start time
            long startTime = System.nanoTime();
            //construct the shared object
            SimpleArray sharedSimpleArray = new SimpleArray(900);
            // create three threads(tasks) to write to the shared SimpleArray
            ArrayWriter writer1 = new ArrayWriter(1, sharedSimpleArray);
            ArrayWriter writer2 = new ArrayWriter(11, sharedSimpleArray);
            ArrayWriter writer3 = new ArrayWriter(21, sharedSimpleArray);

            // execute the tasks with an ExecutorService
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(writer1);
            executorService.execute(writer2);
            executorService.execute(writer3);

            executorService.shutdown();

            try {
                // wait 1 minute for both writers to finish executing
                boolean tasksEnded =
                        executorService.awaitTermination(10, TimeUnit.MINUTES);

                if (tasksEnded) {
                    //System.out.printf("%nContents of SimpleArray:%n");
                    System.out.println("Total number of Integrs divisible by 25 are: "+sharedSimpleArray); // print contents
                    }
                else {
                    System.out.println("Timed out while waiting for tasks to finish.");
                    }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //End Time
            long endTime = System.nanoTime();
            //Displaying Total time elapsed
            System.out.println("Time elapsed: "+((endTime-startTime)/1000000000)+ " seconds");
        }
}