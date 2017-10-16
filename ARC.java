package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ARC { //adaptive replacement cache
    public List<Buffer> lst;
    public LRU ghostlst1;
    public LRU ghostlst2; //fixed of size c/2 where c is the size of the cache
    public int pagehits; 
    public int totalhits; 
    public int index; //separator between L1 and L2 caches in lst


    public ARC(Integer size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        lst = new ArrayList<Buffer>();
        for(int i = 0; i < size; i++) {
            lst.add(new Buffer(null));
        }
        ghostlst1 = new LRU(size / 2);
        ghostlst2 = new LRU(size / 2); 
        pagehits = 0;
        totalhits = 0;
        index = lst.size() / 2; //rounds up
    }

    public insert(Buffer b) {

    }

    public int checkIfContains(Buffer b) {
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals(b)) {
                return i;
            }
        }
        return -1;
    }

    

    public static void main(String[] args) {
        ARC test = new ARC(4);
        System.out.println(test.index);
        System.out.println(test.lst.size());
        System.out.println(test.ghostlst1.size());
    }

    
}