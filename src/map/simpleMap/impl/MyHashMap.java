package map.simpleMap.impl;


import map.simpleMap.interfece.MyMap;

import java.util.Arrays;

/**
 * 这个类实现的Map为JDK7的Map,数据结构为数组+链表
 * JDK8的Map为数组+链表+红黑树，当链表的长度超过一定值时，会将链表转换为红黑树
 */
public class MyHashMap<K,V> implements MyMap<K,V> {

    private final int DEFAULT_CAPACITY = 16;

    private int size = 0;

    private float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] entryArray = new Entry[DEFAULT_CAPACITY];


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object get(Object key) {

        int index = getIndex(key);
        for (Entry entry = entryArray[index];entry!=null; entry=entry.next){
            if (entry.hash == hash(key) &&(entry.getKey() == key || entry.getKey().equals(key))){
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public V  put(Object key, Object value) {
        int index = getIndex(key);

        //根据hash值，获取到数组位置的值，如果key不同，就沿着链表继续寻找空的节点
        for (Entry entry = entryArray[index]; entry != null ; entry = entry.next ){
            if (entry.hash == hash(key) && (entry.getKey() == key||entry.getKey().equals(key))){
                V tempV = (V) entry.value;
                entry.value = value;
                return tempV;
            }
            if (entry.next == null){
                Entry newEntry = new Entry();
                newEntry.key = key;
                newEntry.value = value;
                newEntry.hash = hash(key);
                entry.next = newEntry;
                size ++;
                return null;
            }
        }
        //没有相同的，添加一个节点
        addEntry(key,value);
        return null;
    }


    private void addEntry (Object key ,Object value){

        int index =getIndex(key);

        //先判断有没有超过数组的容量*扩容因数
        if (entryArray.length * DEFAULT_LOAD_FACTOR  < ++size){
            increaseCapacity();
        }

        Entry entry = new Entry();
        entry.hash = hash(key);
        entry.key = key;
        entry.value = value;

        entryArray[index] = entry;
    }

    private int getIndex(Object key){
        return hash(key) % entryArray.length > 0 ? hash(key) % entryArray.length : -hash(key) % entryArray.length;
    }

    private void increaseCapacity(){
        Entry[] newEntryArray = new Entry[entryArray.length*2];
        for (int i = 0; i<entryArray.length ; i++){
            Entry e = entryArray[i];
            if (e != null){
                do{
                    //先保存当前节点的原本的下一个节点
                    Entry next = e.next;
                    //计算当前节点在新数组中的hash值
                    int index = getIndex(e.key);
                    //将原来在新数组这个位置的元素设置为当前节点的下一个节点
                    e.next = newEntryArray[index];
                    //将当前节点置入新的位置中
                    newEntryArray[index] = e;
                    //再访问原来的下一个节点，进入下一个循环，重新设置下一个节点在数组中的位置
                    e = next;

                    //这种遍历方式会让map中的链表头尾互换，并且需要访问map中的所有元素
                    //因此扩容是非常消耗性能的，使用map时最好能指定map需要的size，避免频繁的扩容
                }while (e !=null);
            }

        }
        //最后将map的数组设为新建的数组
        entryArray = newEntryArray;
    }

    @Override
    public MyEntry[] getEntries() {
        return entryArray;
    }

    private int hash(Object key){
        return key.hashCode();
    }

    class Entry<K,V> implements MyMap.MyEntry<K,V>{

        private K key;

        private V value;

        int hash;

        Entry next;


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public MyEntry next() {
            return this.next;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", hash=" + hash +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "entryArray=" + Arrays.toString(entryArray) +
                '}';
    }
}
