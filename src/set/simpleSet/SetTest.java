package set.simpleSet;

import iterator.MyIterator;
import set.simpleSet.impl.MyHashSet;
import set.simpleSet.interfece.MySet;

import java.util.HashSet;

/**
 * @author ：xmj
 * @date ：Created in 2021/1/27 16:38
 * @description：
 */
public class SetTest {


    public static void main(String[] args) {

        MySet<String> set = new MyHashSet();
        for (int i = 0 ; i< 50 ;i++){
            if (i>50){
                set.add(i-50 +"");
            }else {
                set.add(i +"");
            }
        }
        int max = 0;

        MyIterator iterator = set.getIterator();
        while (iterator.haveNext()){
            System.out.println(iterator.next());
            max++;
        }
        System.out.println(max);

        System.out.println("执行结束");


    }



}
