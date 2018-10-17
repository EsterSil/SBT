

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionUtilsTest {

    @Test
    public void addAllTest() {
        List<Integer> source = Arrays.asList(12, 13, 14, 15);
        List<Integer> destination = new ArrayList<>();
        CollectionUtils.addAll(source, destination);
        Assert.assertArrayEquals(source.toArray(), destination.toArray());
    }

    @Test
    public void newArrayListTest() {
        List<String> list = CollectionUtils.newArrayList();
        if (list instanceof ArrayList) {
            list.add("Hello");
            Assert.assertEquals(list.get(0), "Hello");
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void indexOfTest() {
        List<Integer> source = Arrays.asList(12, 13, 14, 15);
        int index = CollectionUtils.indexOf(source, 12);
        int index2 = CollectionUtils.indexOf(source, 2);
        Assert.assertTrue(index == 0);
        Assert.assertTrue(index2 == -1);
    }

    @Test
    public void limitTest() {
        List<Integer> source = Arrays.asList(12, 13, 14, 15);
        List<Integer> result = CollectionUtils.limit(source, 2);
        Assert.assertEquals(source.get(0), result.get(0));
        Assert.assertEquals(source.get(1), result.get(1));
    }

    @Test
    public void addTest() {
        List<Integer> source = new ArrayList<>();
        source.addAll(Arrays.asList(12, 13, 14, 15));
        CollectionUtils.add(source, 100);
        List<Integer> result = Arrays.asList(12, 13, 14, 15, 100);
        Assert.assertArrayEquals(source.toArray(), result.toArray());
    }

    @Test
    public void removeAllTest() {
        List<Integer> source = new ArrayList<>();
        source.addAll(Arrays.asList(12, 13, 14, 15));
        CollectionUtils.removeAll(source, Arrays.asList(12, 13));
        List<Integer> result = Arrays.asList(14, 15);
        Assert.assertArrayEquals(source.toArray(), result.toArray());
    }

    @Test
    public void containsAllTest() {
        List<Integer> source = new ArrayList<>();
        source.addAll(Arrays.asList(12, 13, 14, 15));
        Assert.assertTrue(CollectionUtils.containsAll(source, Arrays.asList(12, 13)));
    }

    @Test
    public void containsAnyTest() {
        List<Integer> source = new ArrayList<>();
        source.addAll(Arrays.asList(12, 13, 14, 15));
        Assert.assertTrue(CollectionUtils.containsAny(source, Arrays.asList(12, 17, 22)));
        Assert.assertTrue(CollectionUtils.containsAny(source, Arrays.asList(17, 22, 14)));
        Assert.assertTrue(!CollectionUtils.containsAny(source, Arrays.asList(17, 22)));
    }

    @Test
    public void rangeTest() {
        List<Integer> source = Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Integer> result = CollectionUtils.range(source, 14, 18);
        List<Integer> expected = Arrays.asList(14, 15, 16, 17, 18);
        Assert.assertArrayEquals(result.toArray(), expected.toArray());
    }

    @Test
    public void rangeWithComparatorTest() {
        List<TestClass> source = Arrays.asList(new TestClass(12), new TestClass(13),
                new TestClass(14), new TestClass(15), new TestClass(16),
                new TestClass(17));
        List<TestClass> result = CollectionUtils.range(source, new TestClass(14),
                new TestClass(16),
                (o1, o2) -> {
                    if (o1.getParameter() > o2.getParameter()) {
                        return 1;
                    } else if (o1.getParameter() == o2.getParameter()) {
                        return 0;
                    } else {
                        return -1;
                    }
                });
        List<TestClass> expected = Arrays.asList(new TestClass(14), new TestClass(15),
                new TestClass(16));
        Assert.assertArrayEquals(result.toArray(), expected.toArray());
    }

}
