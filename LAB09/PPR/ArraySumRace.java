import java.util.Arrays;

class SharedTotal {
    long total = 0;

    void add(int number) {
        total += number;
    }

    synchronized void safeAdd(int number) {
        total += number;
    }
}

public class ArraySumRace {
    static void runTest(int[] numbers, int mode)
            throws InterruptedException {

        SharedTotal shared = new SharedTotal();
        Thread[] threads = new Thread[4];
        long[] partial = new long[4];

        long startTime = System.nanoTime();

        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            final int start = i * numbers.length / threads.length;
            final int end = (i + 1) * numbers.length / threads.length;

            threads[i] = new Thread(() -> {
                long localTotal = 0;

                for (int j = start; j < end; j++) {
                    if (mode == 1) {
                        shared.add(numbers[j]);
                    } else if (mode == 2) {
                        shared.safeAdd(numbers[j]);
                    } else {
                        localTotal += numbers[j];
                    }
                }

                partial[index] = localTotal;
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        if (mode == 3) {
            for (long value : partial) {
                shared.total += value;
            }
        }

        double time = (System.nanoTime() - startTime) / 1000000.0;

        System.out.println("Total: " + shared.total);
        System.out.printf("Time: %.2f ms%n", time);
    }

    public static void main(String[] args) throws InterruptedException {
        int[] numbers = new int[1000000];
        Arrays.fill(numbers, 1);

        System.out.println("Expected total: " + numbers.length);

        System.out.println("\nWithout synchronization:");
        runTest(numbers, 1);

        System.out.println("\nUsing synchronized method:");
        runTest(numbers, 2);

        System.out.println("\nUsing partial totals:");
        runTest(numbers, 3);
    }
}