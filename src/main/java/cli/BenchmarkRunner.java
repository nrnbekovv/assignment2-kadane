package cli;

import algorithms.KadaneAlgorithm;
import algorithms.KadaneAlgorithm.Result;
import metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final Random rnd = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -cp target/classes cli.BenchmarkRunner <size> <distribution>");
            System.out.println("Distributions: random, sorted, reverse, allneg, repeated, longpos");
            return;
        }

        int size;
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.err.println("First argument must be an integer size.");
            return;
        }
        String dist = args[1].toLowerCase();

        int[] arr = generateArray(size, dist);

        PerformanceTracker tracker = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(tracker);

        long start = System.nanoTime();
        Result res;
        try {
            res = algo.findMaxSubarray(arr);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        long end = System.nanoTime();

        System.out.println("Size: " + size + ", distribution: " + dist);
        System.out.println("Result: " + res);
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Array accesses (approx): " + tracker.getArrayAccesses());
        System.out.println("Other ops: " + tracker.getOtherOps());
        System.out.println("Execution time ms: " + (end - start) / 1_000_000.0);
        if (size <= 100) {
            System.out.println("Array: " + Arrays.toString(arr));
        }
    }

    private static int[] generateArray(int size, String dist) {
        int[] arr = new int[size];
        switch (dist) {
            case "random":
                for (int i = 0; i < size; i++) arr[i] = rnd.nextInt(201) - 100;
                break;
            case "sorted":
                for (int i = 0; i < size; i++) arr[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < size; i++) arr[i] = size - i;
                break;
            case "allneg":
                for (int i = 0; i < size; i++) arr[i] = -(rnd.nextInt(100) + 1);
                break;
            case "repeated":
                int val = rnd.nextInt(20) - 10;
                Arrays.fill(arr, val);
                break;
            case "longpos":

                Arrays.fill(arr, -5);
                int posStart = Math.max(0, size / 4);
                int posLen = Math.max(1, size / 2);
                for (int i = posStart; i < Math.min(size, posStart + posLen); i++) arr[i] = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown distribution: " + dist);
        }
        return arr;
    }
}
