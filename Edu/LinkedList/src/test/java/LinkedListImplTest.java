import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class LinkedListImplTest {

    LinkedListImpl<Integer> intList;
    LinkedListImpl<String> strList;
    @Before
    public void init() {
        intList = new LinkedListImpl<>();
        strList = new LinkedListImpl<>();
    }
    @Test
    public void addTest() {

        intList.add(12);
        intList.add(22);
        Assert.assertEquals(intList.getSize(), 2);
        strList.add("12");
        strList.add("22");
        Assert.assertEquals(strList.getSize(), 2);

    }

    @Test
    public void getTest() {
        intList.add(12);
        intList.add(22);
        Assert.assertTrue(intList.get(0) ==12);
        Assert.assertTrue(intList.get(1) ==22);
        strList.add("12");
        strList.add("22");
        Assert.assertEquals(strList.get(0), "12");
        Assert.assertEquals(strList.get(1), "22");
    }

    @Test
    public void addByIndexTest() {
        intList.add(12);
        intList.add(22);
        intList.add(1, 15);
        Assert.assertTrue(intList.get(1) == 15);
        Assert.assertTrue(intList.get(2) == 22);
        intList.add(0, 3);
        Assert.assertTrue(intList.get(0) == 3);
        intList.add(4, 17);
        Assert.assertTrue(intList.get(4) == 17);

    }

    @Test
    public void removeTest() {
        intList.add(12);
        intList.remove(0);
        Assert.assertTrue(intList.getSize() == 0);
        intList.add(22);
        intList.add(15);
        intList.remove(0);
        Assert.assertTrue(intList.get(0) == 15);
    }

    @Test
    public void addAllTest() {
        Collection<Integer> toAdd = Arrays.asList(15,14,18,17,16,22);
        intList.addAll(toAdd);
        Assert.assertTrue(intList.getSize() == 6);
        Assert.assertTrue(intList.get(0) == 15);
        Assert.assertTrue(intList.get(5) == 22);
    }

    @Test
    public void iteratorTest() {
        Collection<Integer> toAdd = Arrays.asList(15,14,18,17,16,22);
        intList.addAll(toAdd);
        Collection<Integer> result = new ArrayList<>();
        for (Iterator<Integer> iterator =intList.iterator(); iterator.hasNext();) {
             result.add(iterator.next());
        }
        Assert.assertArrayEquals(toAdd.toArray(), result.toArray());
    }

    @Test
    public void copyTest() {
        Collection<Integer> toAdd = Arrays.asList(15,14,18,17,16,22);
        intList.addAll(toAdd);
        Collection<Integer> toGet = new ArrayList<>();
        intList.copy(toGet);
        Assert.assertArrayEquals(toAdd.toArray(), toGet.toArray());
    }

}
