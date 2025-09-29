package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public class KadaneAlgorithm {

    public static class Result {
        public final long maxSum;
        public final int startIndex;
        public final int endIndex;

        public Result(long maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return "Result{maxSum=" + maxSum + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Result)) return false;
            Result r = (Result) o;
            return maxSum == r.maxSum && startIndex == r.startIndex && endIndex == r.endIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(maxSum, startIndex, endIndex);
        }
    }

    private final PerformanceTracker tracker;

    public KadaneAlgorithm(PerformanceTracker tracker) {
        this.tracker = tracker == null ? new PerformanceTracker() : tracker;
    }

    public Result findMaxSubarray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array must not be null.");
        }
        if (nums.length == 0) {
            throw new IllegalArgumentException("Input array must not be empty.");
        }

        tracker.incrementArrayAccesses();
        long maxSoFar = nums[0];
        long maxEndingHere = nums[0];
        int start = 0;
        int tempStart = 0;
        int end = 0;

        for (int i = 1; i < nums.length; i++) {
            tracker.incrementArrayAccesses();
            int value = nums[i];

            tracker.incrementComparisons();
            if (value > maxEndingHere + value) {
                tracker.incrementOtherOps();
                maxEndingHere = value;
                tempStart = i;
            } else {
                tracker.incrementOtherOps();
                maxEndingHere = maxEndingHere + value;
            }

            tracker.incrementComparisons();
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new Result(maxSoFar, start, end);
    }
}
