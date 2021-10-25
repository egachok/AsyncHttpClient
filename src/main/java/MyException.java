import java.util.function.Consumer;

public class MyException implements Consumer<Exception> {
    @Override
    public void accept(Exception e) {
        System.out.println("Error while executing request: " + e.getMessage());
    }
}