package PageReplace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ARC { //adaptive replacement cache
    public LRU T1;
    public LRU T2;
    public LRU B1; //ghost list
    public LRU B2; //ghost list
    public int pagehits; 
    public int totalhits; 

    public int p; //maintains sizes between the 4 lsts
    public int c; //cache size


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
        p = 0;
        c = size;
    }

    public void insert(Buffer b) {
        int index_t1 = T1.checkIfContains(b);
        int index_t2 = T2.checkIfContains(b);
        int index_b1 = B1.checkIfContains(b);
        int index_b2 = B2.checkIfContains(b);
        //Case 1: b is in T1 or T2
        if (index_t1 != -1 || index_t2 != -1) {
            if (index_t1 != -1) { //it was a hit in T1
                //remove entry from T1, update all LRU values
                T1.remove(b); 
                T2.insert(b);
            }
        } else if (index_b1 != -1) { //ghost hit in B1
            p = min(c, p + max(B1.size() / B2.size(), 1)); //adjustments toward p
            replace(); 
            B1.remove(b);
            T2.insert(b);
        } else if (index_b2 != -1) { //ghost hit in B2
            p = max(0, p - max(B1.size() / B2, 1));
            replace();
            B2.remove(b);
            T2.insert(b);
        }
        

    }

    public void replace() {
        
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