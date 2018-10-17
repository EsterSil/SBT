import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T object) {
        return source.indexOf(object);
    }

    ///????
    public static <T> List<T> limit(List< T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List< T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<T> c1, List<T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<T> c1, List<T> c2) {
        for (T object : c2) {
            if (c1.contains(object)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        List<T> result = new ArrayList<>();
        for (T object : list) {
            if (object.compareTo(max) <= 0 && object.compareTo(min) >= 0) {
                result.add(object);
            }
        }
        return result;
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        for (T object : list) {
            if (comparator.compare(object, max) <= 0 && comparator.compare(object, min) >= 0) {
                result.add(object);
            }
        }
        return result;
    }

}
