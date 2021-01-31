package quiz1rollingfifo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RollingPriorityFIFOTest {

    public RollingPriorityFIFOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test(timeout=1000)
    public void InOutOne() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        String result = fifo.dequeue();
        assertEquals("Jerry", result);
    }

    @Test(timeout=1000)
    public void InOutTwo() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", false);
        String result1 = fifo.dequeue();
        assertEquals("Jerry", result1);
        String result2 = fifo.dequeue();
        assertEquals("Terry", result2);
    }

    @Test(timeout=1000)
    public void InOutThreeMix() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", false);
        fifo.enqueue("Eva", false);
        String result1 = fifo.dequeue();
        assertEquals("Jerry", result1);
        fifo.enqueue("Marry", false);
        for (String expected : new String[]{"Terry", "Eva", "Marry"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
    }

    @Test(timeout=1000)
    public void InOutThreeMixToFail() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", false);
        fifo.enqueue("Eva", false);
        String result1 = fifo.dequeue();
        assertEquals("Jerry", result1);
        fifo.enqueue("Marry", false);
        for (String expected : new String[]{"Terry", "Eva", "Marry"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
        String resultNull = fifo.dequeue(); // queue should be empty
        assertEquals(null, resultNull);
    }

    @Test(timeout=1000)
    public void SeveralItemsToArray() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", false);
        fifo.enqueue("Eva", false);
        String []result = fifo.toArray();
        assertArrayEquals(new String[]{"Jerry", "Terry", "Eva"}, result);
    }

    @Test(timeout=1000)
    public void SeveralItemsToString() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", true); // priority
        fifo.enqueue("Eva", false);
        String result = fifo.toString();
        assertEquals("[Jerry,Terry*,Eva]", result);
    }

    @Test(timeout=1000)
    public void SeveralItemsToArrayPriorityOnly() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Jerry", false);
        fifo.enqueue("Terry", true); // priority
        fifo.enqueue("Eva", false);
        fifo.enqueue("Evelyn", true); // priority
        String []result = fifo.toArrayOnlyPriority();
        assertArrayEquals(new String[]{"Terry", "Evelyn"}, result);
    }


    @Test(timeout=1000)
    public void PriorityDequeMiddle() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Albert", false);
        fifo.enqueue("Barry", true); // priority
        fifo.enqueue("Charlie", false);
        String result1 = fifo.dequeue(); // priority dequeue from the middle
        assertEquals("Barry", result1);
        // todo: add more operations afterwards
        fifo.enqueue("Eva", false);
        fifo.enqueue("Marry", false);
        String result2 = fifo.dequeue();
        assertEquals("Albert", result2);
        for (String expected : new String[]{"Charlie", "Eva", "Marry"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
        String resultNull = fifo.dequeue(); // queue should be empty
        assertEquals(null, resultNull);
    }

    @Test(timeout=1000)
    public void PriorityDequeAtEnd() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Albert", true); // priority - first item
        fifo.enqueue("Barry", false);
        fifo.enqueue("Charlie", false);
        String result1 = fifo.dequeue(); // priority dequeue from the middle
        assertEquals("Albert", result1);
        // todo: add more operations afterwards
        fifo.enqueue("Eva", false);
        fifo.enqueue("Marry", false);
        String result2 = fifo.dequeue();
        assertEquals("Barry", result2);
        for (String expected : new String[]{"Charlie", "Eva", "Marry"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
        String resultNull = fifo.dequeue(); // queue should be empty
        assertEquals(null, resultNull);
    }

    @Test(timeout=1000)
    public void PriorityDequeAtStart() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Albert", false);
        fifo.enqueue("Barry", false);
        fifo.enqueue("Charlie", true); // priority - last item
        String result1 = fifo.dequeue(); // priority dequeue from the middle
        assertEquals("Charlie", result1);
        // todo: add more operations afterwards
        fifo.enqueue("Eva", false);
        fifo.enqueue("Marry", false);
        String result2 = fifo.dequeue();
        assertEquals("Albert", result2);
        for (String expected : new String[]{"Barry", "Eva", "Marry"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
        String resultNull = fifo.dequeue(); // queue should be empty
        assertEquals(null, resultNull);
    }

    @Test(timeout=1000)
    public void PriorityDequeMultiple() throws FIFOFullException {
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        fifo.enqueue("Albert", true); // prio
        fifo.enqueue("Barry", false);
        fifo.enqueue("Charlie", true); // prio
        fifo.enqueue("Darryl", false);
        fifo.enqueue("Emily", true); // prio
        for (String expected : new String[]{"Albert", "Charlie", "Emily", "Barry", "Darryl"}) {
            String result = fifo.dequeue();
            assertEquals(expected, result);
        }
        String resultNull = fifo.dequeue(); // queue should be empty
        assertEquals(null, resultNull);
    }

}