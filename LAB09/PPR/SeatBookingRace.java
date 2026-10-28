class Booking {
    int seatsLeft = 5;
    int successful = 0;

    void unsafeBook() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            seatsLeft--;
            successful++;
        }
    }

    synchronized void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successful++;
        }
    }
}

public class SeatBookingRace {
    static void runTest(boolean safe) throws InterruptedException {
        Booking booking = new Booking();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                if (safe) {
                    booking.book();
                } else {
                    booking.unsafeBook();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Successful bookings: " + booking.successful);
        System.out.println("Seats left: " + booking.seatsLeft);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Without synchronization:");
        runTest(false);

        System.out.println("With synchronization:");
        runTest(true);
    }
}