class Connection implements AutoCloseable {
    boolean failOnClose;

    Connection(boolean failOnClose) {
        this.failOnClose = failOnClose;
        System.out.println("Connection opened");
    }

    public void close() throws Exception {
        System.out.println("Connection closed");

        if (failOnClose) {
            throw new Exception("Closing error");
        }
    }
}

public class ResourceDemo {
    public static void main(String[] args) {
        System.out.println("Normal case:");

        try (Connection connection = new Connection(false)) {
            System.out.println("Work completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nException case:");

        try (Connection connection = new Connection(true)) {
            throw new Exception("Original work error");
        } catch (Exception e) {
            System.out.println("Main error: " + e.getMessage());

            for (Throwable error : e.getSuppressed()) {
                System.out.println("Suppressed: " + error.getMessage());
            }
        }
    }
}