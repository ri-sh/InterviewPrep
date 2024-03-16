import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

class Task implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> f1 = waitAndReturn(3000, "Fu",service);
//        CompletableFuture<Void> f2 = waitAndThrow(100, "em");
        CompletableFuture<String> f3 = waitAndReturn(2000 , "wangchu",service) ;
        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(f1, f3);

        combinedFutures.exceptionally(err->{
            System.out.println("oops error occured");

            return null;
        });
        Stream.of(f1,f3).map(CompletableFuture::join).forEach(System.out::println);

        combinedFutures.join();


    }

    private static CompletableFuture waitAndReturn(int millis, String value,ExecutorService service){
        return CompletableFuture.supplyAsync(()->{
        try {
            Thread.sleep(millis);

            return value;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    },service);
    }

    private static CompletableFuture waitAndThrow(int millis, String Value,ExecutorService service){

        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                throw new RuntimeException();
            }
        } , service);

    }
}

