package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KadaneAlgorithmTest {

    @Test
    void testNullArray() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        assertThrows(IllegalArgumentException.class, () -> algo.findMaxSubarray(null));
    }

    @Test
    void testEmptyArray() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        assertThrows(IllegalArgumentException.class, () -> algo.findMaxSubarray(new int[]{}));
    }

    @Test
    void testSingleElement() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        KadaneAlgorithm.Result r = algo.findMaxSubarray(new int[]{7});
        assertEquals(7, r.maxSum);
        assertEquals(0, r.startIndex);
        assertEquals(0, r.endIndex);
    }

    @Test
    void testAllNegative() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        KadaneAlgorithm.Result r = algo.findMaxSubarray(new int[]{-4, -2, -8, -3});
        assertEquals(-2, r.maxSum);
        assertEquals(1, r.startIndex);
        assertEquals(1, r.endIndex);
    }

    @Test
    void testTypicalCase() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        KadaneAlgorithm.Result r = algo.findMaxSubarray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        assertEquals(6, r.maxSum);
        assertEquals(3, r.startIndex);
        assertEquals(6, r.endIndex);
    }

    @Test
    void testMultipleMaximaChooseLeftmost() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        KadaneAlgorithm.Result r = algo.findMaxSubarray(new int[]{2, -1, 2, -3, 2, -1, 2});
        assertEquals(3, r.maxSum);
        assertEquals(0, r.startIndex);
        assertEquals(2, r.endIndex);
    }

    @Test
    void testAllZeros() {
        PerformanceTracker t = new PerformanceTracker();
        KadaneAlgorithm algo = new KadaneAlgorithm(t);
        KadaneAlgorithm.Result r = algo.findMaxSubarray(new int[]{0,0,0,0});
        assertEquals(0, r.maxSum);
        assertEquals(0, r.startIndex);
    }
}
