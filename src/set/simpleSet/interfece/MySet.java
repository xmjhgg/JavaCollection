package set.simpleSet.interfece;


import iterator.MyIterator;

/**
 * @author ：xmj
 * @date ：Created in 2021/1/27 9:23
 * @description：
 */
public interface MySet<T>{


    boolean add(T t);

    int size();

    boolean contain(T t);

    Object getContent();

    MyIterator getIterator();


}
