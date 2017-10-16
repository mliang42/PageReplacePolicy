package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LFU extends LRU {
    //relative list stores the number of hits for each element. 
    public LFU(Integer size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        this.lst = new ArrayList<Buffer>();
        this.relativelst = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            Buffer temp = new Buffer(null);
            relativelst.add(0); //initially all null entries are 0.
            lst.add(temp);
        }
        this.pagehits = 0;
        this.totalhits = 0;
    }

    @Override
    public void insert(Buffer b) {
        totalhits++;
        int index = checkIfContains(b);
        if (index == -1) {
            int least = leastUsed();
            lst.set(least, b);
            relativelst.set(least, 1);// new entry, one access    
        } else {
            pagehits++;
            relativelst.set(index, relativelst.get(index) + 1); //incremenet 
        }
    }

    public int leastUsed() {
        int minval = 1; //entries with 0 are empty, will prioritize null entries by default
        int index = 0;
        for(int i = 0; i < lst.size(); i++) {
            if (relativelst.get(i) < minval) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        LFU cache = new LFU(10);
        for(int i = 0; i < 15; i++) {
            cache.insert(new Buffer(i));
            System.out.println(cache);
        }
    }

    
}