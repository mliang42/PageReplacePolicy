package PageReplace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ARC { //adaptive replacement cache
    public LRU T1;
    public LRU T2;
    public LRU B1;
    public LRU B2; //fixed of size c/2 where c is the size of the cache
    public int pagehits; 
    public int totalhits; 
    public int index; //separator between L1 and L2 caches in lst


    public ARC(Integer size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        T1 = new LRU(size / 2);
        T2 = new LRU(size / 2);
        B1 = new LRU(size / 2);
        B2 = new LRU(size / 2); 
        pagehits = 0;
        totalhits = 0;
    }

    public void insert(Buffer b) {
        int index_t1 = T1.checkIfContains(b);
        int index_t2 = T2.checkIfContains(b);
        //Case 1: b is in T1 or T2
        if (index_t1 != -1 || index_t2 != -1) {
            if (index_t1 != -1) { //it was a hit in T1
                //remove entry from T1, update all LRU values
            }
        }
        

    }



    public int checkIfGhostContains(Buffer b) { //returns the number corresponding to the ghost lst that contains it, otherwise -1
        if (B1.checkIfContains(b) != -1) {
            return 1; //1 means B1 contains it
        } else if (B2.checkIfContains(b) != -1) {
            return 2; //2 means B2 contains it
        } else {
            return -1; //-1 means neither contains it
        }
    }



    public static void main(String[] args) {
  
    }

    
}