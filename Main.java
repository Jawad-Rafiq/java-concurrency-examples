import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());

        // Measure ExecutorService time
        long start1 = System.currentTimeMillis();
        List<Integer> results1 = multiplyWithExecutor(numbers);
        long end1 = System.currentTimeMillis();
        System.out.println("ExecutorService results: " + results1);
        System.out.println("ExecutorService execution time: " + (end1 - start1) + " ms");

        // Measure Parallel Stream time
        long start2 = System.currentTimeMillis();
        List<Integer> results2 = multiplyWithParallelStream(numbers);
        long end2 = System.currentTimeMillis();
        System.out.println("ParallelStream results: " + results2);
        System.out.println("ParallelStream execution time: " + (end2 - start2) + " ms");

        // Measure Thread Stream time
        long start3 = System.currentTimeMillis();
        List<Integer> results3 = multiplyWithThreads(numbers);
        long end3 = System.currentTimeMillis();
        System.out.println("Thread results: " + results3);
        System.out.println("Thread execution time: " + (end3 - start3) + " ms");
    }


    public static List<Integer> multiplyWithExecutor(List<Integer> numbers) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        List<Future<Integer>> futures = new ArrayList<>();
        for (Integer num : numbers) {
            futures.add(executor.submit(() -> {
                return num * 2;
            }));
        }

        List<Integer> results = new ArrayList<>();
        for (Future<Integer> f : futures) {
            results.add(f.get());
        }

        executor.shutdown();
        return results;
    }

    public static List<Integer> multiplyWithParallelStream(List<Integer> numbers) {
        return numbers.parallelStream()
                .map(n -> {
                    return n * 2;
                })
                .collect(Collectors.toList());
    }

    public static List<Integer> multiplyWithThreads(List<Integer> numbers) throws InterruptedException {
        List<Integer> results = new ArrayList<>(numbers.size());

        // Initialize result list with placeholders
        for (int i = 0; i < numbers.size(); i++) {
            results.add(0);
        }

        // Each thread will process a chunk of 20 numbers
        int chunkSize = numbers.size() / 5;
        List<Thread> workers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final int start = i * chunkSize;
            final int end = (i == 4) ? numbers.size() : (i + 1) * chunkSize; // last chunk handles remainder

            Thread worker = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    results.set(j, numbers.get(j) * 2);
                }
            });
            workers.add(worker);
            worker.start();
        }

        // Wait for all threads to finish
        for (Thread worker : workers) {
            worker.join();
        }

        return results;
    }

}