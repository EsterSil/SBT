import java.util.HashMap;
import java.util.Map;

public class CountMap <E> {


    private Map<E, Integer> internalMap;

    public CountMap() {
        internalMap = new HashMap<>();
    }

    public  void add(E element) {
        if (internalMap.containsKey(element)) {
            internalMap.put(element,internalMap.get(element) + 1 );
        } else {
            internalMap.put(element, 1);
        }
    }


    public int getCount(E element) {
        if (internalMap.containsKey(element)) {
            return internalMap.get(element);
        } else {
            return 0;
        }
    }

    public int remove(E element) {
       return internalMap.remove(element);
    }

    public int size() {
        return internalMap.size();
    }

    public void addAll(CountMap<? extends E> source){
        source.toMap().forEach((k, v) -> {
            if (internalMap.containsKey(k)) {
                internalMap.put(k, internalMap.get(k) + v);
            } else {
                internalMap.put(k,v);
            }
        });
    }

    public Map<E, Integer> toMap(){ return internalMap;}


    public void toMap(Map<? super E, Integer> destination){
        destination.putAll(this.toMap());
    }



}


