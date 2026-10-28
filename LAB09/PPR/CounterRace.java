class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void safeIncrement() {
        count++;
    }
}

public class CounterRace {
    static void runTest(boolean safe) throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    if (safe) {
                        counter.safeIncrement();
                    } else {
                        counter.increment();
                    }
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Actual total: " + counter.count);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Expected total: 1000000");

        System.out.println("Without synchronization:");
        runTest(false);

        System.out.println("With synchronization:");
        runTest(true);
    }
}