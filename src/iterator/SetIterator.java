package iterator;

import map.simpleMap.interfece.MyMap;
import set.simpleSet.interfece.MySet;

/**
 * @author ：xmj
 * @date ：Created in 2021/1/27 17:03
 * @description：
 */
public class SetIterator implements MyIterator{

    MySet set;
    MyMap myMap;

    int index = 0;
    int curArrayIndex = 0;
    MyMap.MyEntry myEntry;

    public SetIterator(MySet set) {
        this.set = set;
        myMap = (MyMap) set.getContent();
    }


    @Override
    public Object next() {
        if (index <= this.myMap.size() ){
            index++;
            if (myEntry == null){
                for (;curArrayIndex < myMap.getEntries().length;curArrayIndex++){
                    if ((myEntry = myMap.getEntries()[curArrayIndex]) != null){

                        return myEntry.getKey();
                    }
                }
            }else {
                System.out.println("");
                if ((myEntry = myEntry.next()) == null){
                    curArrayIndex++;
                    for (;curArrayIndex < myMap.getEntries().length;curArrayIndex++){
                        if ((myEntry = myMap.getEntries()[curArrayIndex]) != null){
                            return myEntry.getKey();
                        }
                    }
                }
            }
            return myEntry.getKey();
        }
        return null;
    }

    @Override
    public boolean haveNext() {
        return index < myMap.size();
    }
}
