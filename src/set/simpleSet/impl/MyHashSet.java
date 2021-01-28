package set.simpleSet.impl;

import iterator.MyIterator;
import iterator.SetIterator;
import map.simpleMap.impl.MyHashMap;
import map.simpleMap.interfece.MyMap;
import set.simpleSet.interfece.MySet;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author ：xmj
 * @date ：Created in 2021/1/27 11:38
 * @description：
 */
public class MyHashSet<T> implements MySet<T> {

    private static final Object DEFAULT_VALUE = new Object();

    MyMap<T,Object> map = new MyHashMap();

    @Override
    public boolean add(T t) {
        if (t == null) throw new IllegalArgumentException("arg can not be null");
        return  map.put(t,DEFAULT_VALUE) == null;

    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean contain(T t) {
        return map.get(t) != null;
    }

    @Override
    public Object getContent(){
        return map;
    }

    @Override
    public MyIterator getIterator() {
        return new SetIterator(this);
    }


}
