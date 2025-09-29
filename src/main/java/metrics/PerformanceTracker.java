package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long allocations = 0;
    private long otherOps = 0;

    public void incrementComparisons() { comparisons++; }
    public void addComparisons(long delta) { comparisons += delta; }

    public void incrementArrayAccesses() { arrayAccesses++; }
    public void addArrayAccesses(long delta) { arrayAccesses += delta; }

    public void incrementAllocations() { allocations++; }
    public void addAllocations(long delta) { allocations += delta; }

    public void incrementOtherOps() { otherOps++; }
    public void addOtherOps(long delta) { otherOps += delta; }

    public long getComparisons() { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAllocations() { return allocations; }
    public long getOtherOps() { return otherOps; }

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        allocations = 0;
        otherOps = 0;
    }

    @Override
    public String toString() {
        return "PerformanceTracker{" +
                "comparisons=" + comparisons +
                ", arrayAccesses=" + arrayAccesses +
                ", allocations=" + allocations +
                ", otherOps=" + otherOps +
                '}';
    }
}
