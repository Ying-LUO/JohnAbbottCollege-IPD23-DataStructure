package quiz1rollingfifo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollingPriorityFIFO_JunitTest {

    @Test
    public void constructor_AssertSucceed(){
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        assertEquals(5, fifo.sizeMax());
        assertEquals(0, fifo.size());
    }

    @Test
    public void constructor_AssertExceptionThrow(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RollingPriorityFIFO fifo = new RollingPriorityFIFO(4);});
    }

    @Test
    public void enqueueInitially_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", true);
        assertEquals(1, fifo.size());
    }

    @Test
    public void enqueueOverSize_AssertExceptionThrow() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", true);
        fifo.enqueue("Number2", true);
        fifo.enqueue("Number3", true);
        fifo.enqueue("Number4", true);
        fifo.enqueue("Number5", true);

        assertEquals(5, fifo.size());
        Assertions.assertThrows(FIFOFullException.class, () -> {
            fifo.enqueue("Number6", true);});
    }

    @Test
    public void dequeueAtFirst_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", true);
        assertTrue(fifo.dequeue().equals("Number1"));
    }

    @Test
    public void dequeueInMiddle_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", false);
        fifo.enqueue("Number2", true);
        fifo.enqueue("Number3", false);
        fifo.enqueue("Number4", true);
        fifo.enqueue("Number5", false);
        assertTrue(fifo.dequeue().equals("Number2"));
    }

    @Test
    public void dequeueMultipleTimes_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", false);
        fifo.enqueue("Number2", true);
        fifo.enqueue("Number3", false);
        fifo.enqueue("Number4", true);
        fifo.enqueue("Number5", false);
        assertTrue(fifo.dequeue().equals("Number2"));
        assertTrue(fifo.dequeue().equals("Number4"));
        assertTrue(fifo.dequeue().equals("Number1"));
    }

    @Test
    public void toArrayOnlyPriorityTrue_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", true);
        fifo.enqueue("Number2", true);
        fifo.enqueue("Number3", true);
        assertEquals(3, fifo.size());
        assertArrayEquals(new String[]{"Number1", "Number2", "Number3"}, fifo.toArrayOnlyPriority());
    }

    @Test
    public void toArrayOnlyPriorityFalse_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", false);
        fifo.enqueue("Number2", false);
        fifo.enqueue("Number3", false);
        assertEquals(3, fifo.size());
        assertArrayEquals(null, fifo.toArrayOnlyPriority());
    }

    @Test
    public void toArray_AssertSucceed() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Number1", false);
        fifo.enqueue("Number2", true);
        fifo.enqueue("Number3", false);
        assertEquals(3, fifo.size());
        assertArrayEquals(new String[]{"Number1", "Number2", "Number3"}, fifo.toArray());
    }
  
}