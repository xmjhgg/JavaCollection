package map;

import map.simpleMap.impl.MyHashMap;
import map.simpleMap.interfece.MyMap;

public class MapTest {


    public static void main(String[] args) {
        MyMap<String,String> myMap = new MyHashMap();

        for(int i = 0 ;i < 2000 ; i ++){
            myMap.put("k"+i,"v"+i);
        }

        System.out.println(myMap.size());


        System.out.println(myMap.getEntries().length);


        System.out.println(myMap.get("k1790"));


    }
    


}
