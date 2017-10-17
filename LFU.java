package PageReplace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LFU extends LRU { //least frequently used
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
    public Buffer insert(Buffer b) {
        totalhits++;
        Buffer temp;
        int index = checkIfContains(b);
        if (index == -1) {
            int least = leastUsed();
            temp = lst.get(least);
            lst.set(least, b);
            relativelst.set(least, 1);// new entry, one access    
        } else {
            pagehits++;
            temp = b;
            relativelst.set(index, relativelst.get(index) + 1); //incremenet 
        }
        return temp;
    }

    public int leastUsed() {
        int minval = 1; //entries with 0 are empty, will prioritize null entries by default
        int index = 0;
        for(int i = 0; i < lst.size(); i++) {
            if (relativelst.get(i) < minval) { //finds index that is least frequentially used, breaking ties by sticking with current smallest index
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        LFU cache = new LFU(10);
        for(int i = 0; i < 100; i++) {
            cache.insert(new Buffer(i % 10));
            System.out.println(cache);
        }

        cache = new LFU(10);
        for(int i = 0; i < 100; i++) {
            int rand = ThreadLocalRandom.current().nextInt(-10, 10);
            cache.insert(new Buffer(rand));
            System.out.println(cache);
            System.out.println(cache.hitrate());
        }

        for(int i = 0; i < 10; i++) {
            cache.insert(new Buffer(null)); //unintuitive behavior, nulls match each other, but does not increase hit count. 
            //probably need to rethink null elements
            System.out.println(cache);
            System.out.println(cache.hitrate());
        }

    }

    
}