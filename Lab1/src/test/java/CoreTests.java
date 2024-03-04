import dev.danyaao.core.LinkedList;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class CoreTests {
    @Test
    public void testAddInEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(3);

        assertEquals(1, list.size());
        assertTrue(list.contains(3));
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddInNotEmptyList() {

        LinkedList<Integer> list = new LinkedList<>(3);

        int size = list.size();

        list.add(11);

        assertEquals(size + 1, list.size());
        assertTrue(list.contains(11));
    }

    @Test
    public void testAddWithIndexInNotEmptyList() {

        LinkedList<Integer> list = new LinkedList<>(3);

        int size = list.size();

        list.add(11, 3);

        assertEquals(size + 1, list.size());
        assertTrue(list.contains(11));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithInvalidIndexException() {
        LinkedList<Integer> list = new LinkedList<>(3);

        list.add(10, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddWithIndexWithNull() {
        LinkedList<Integer> list = new LinkedList<>(3);

        list.add(null, 2);
    }
}