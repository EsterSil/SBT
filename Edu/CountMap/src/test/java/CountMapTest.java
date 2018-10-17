import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountMapTest {

    private CountMap<Integer> countMap;

    @Before
    public void init() {
        countMap = new CountMap<>();
    }

    @Test
    public void addTest() {
        countMap.add(12);
        Assert.assertTrue(countMap.size() == 1);
        countMap.add(12);
        Assert.assertTrue(countMap.size() == 1);
        countMap.add(10);
        countMap.add(10);
        countMap.add(11);
        Assert.assertTrue(countMap.size() == 3);
    }

    @Test
    public void getCountTest() {
        countMap.add(12);
        Assert.assertTrue(countMap.getCount(12) == 1);
        countMap.add(12);
        Assert.assertTrue(countMap.getCount(12) == 2);
        countMap.add(10);
        countMap.add(10);
        countMap.add(10);
        Assert.assertTrue(countMap.getCount(10) == 3);
        Assert.assertTrue(countMap.getCount(11) == 0);
    }
    @Test(expected = NullPointerException.class)
    public void removeTest() {
        countMap.add(12);
        countMap.add(12);
        countMap.add(10);
        Assert.assertTrue(countMap.remove(12) == 2);
        Assert.assertTrue(countMap.remove(10) == 1);
        //exception
        Assert.assertTrue(countMap.remove(11) == 1);
    }

    @Test
    public void toMapTest() {
        countMap.add(12);
        countMap.add(12);
        countMap.add(10);
        Map<Integer, Integer> result =  countMap.toMap();
        Map<Integer, Integer> control = new HashMap<>();
        control.put(12,2);
        control.put(10,1);
        Assert.assertArrayEquals(result.entrySet().toArray(), control.entrySet().toArray());
    }


    @Test
    public void addAllTest() {
        CountMap<Integer> source = new CountMap<>();
        source.add(12);
        source.add(12);
        source.add(10);
        source.add(10);
        source.add(10);
        source.add(11);
        countMap.addAll(source);
        Assert.assertArrayEquals(source.toMap().entrySet().toArray(), countMap.toMap().entrySet().toArray());
    }


    @Test
    public void toMapWithDestinationTest() {
        Map<Integer, Integer> destination = new HashMap<>();
        countMap.add(12);
        countMap.add(12);
        countMap.add(10);
        countMap.add(10);
        countMap.add(10);
        countMap.add(11);
        countMap.toMap(destination);
        Assert.assertArrayEquals(destination.entrySet().toArray(), countMap.toMap().entrySet().toArray());
    }


}
