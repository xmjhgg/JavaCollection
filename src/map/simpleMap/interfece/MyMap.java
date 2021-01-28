package map.simpleMap.interfece;

public interface MyMap<K,V> {

    int size();

    boolean isEmpty();

    Object get(Object key);

    V put(Object key , Object values);

    MyEntry[] getEntries();

    interface MyEntry<K,V>{
        K getKey();
        V getValue();
        MyEntry next();

    }

}
