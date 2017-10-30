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
        int L1 = T1.size() + B1.size();
        int L2 = T2.size() + B2.size();
        //Case 1: b is in T1 or T2
        if (index_t1 != -1 || index_t2 != -1) { //it was a hit in T1 OR T2
            if (index_t1 != -1) { //hit from T1
                //remove entry from T1, update all LRU values
                T1.remove(b); 
                T2.insert(b);
            } else { //hit in T2
                T2.insert(b);
            }
        } else if (index_b1 != -1) { //ghost hit in B1
            p = Math.min(c, p + Math.max(B2.size() / B1.size(), 1)); //adjustments toward p
            replace(index_b1); 
            B1.remove(b);
            T2.insert(b);
        } else if (index_b2 != -1) { //ghost hit in B2
            p = Math.max(0, p - Math.max(B1.size() / B2.size(), 1));
            replace(index_b1);
            B2.remove(b);
            T2.insert(b);
        } else { //misses everything
            if (L1 == c) { //L1 = T1 + B1
                if (T1.size() < c) {
                    B1.removeLRU();
                    replace(index_b1);
                } else { 
                    T1.removeLRU();
                }
            } else if (L1 < c && L1 + L2 >= c) {
                if (L1 + L2 == 2*c) {//maximum capacity
                    B2.removeLRU();
                    replace(index_b1);
                } 
            }
            T1.insert(b);
        }
        //TODO: Include hit rates in insert and replace
        

    }

    public void replace(int index_b1) {
        if (T1.size() >= 1 && ((index_b1 != -1 && T1.size() == p) || T1.size() > p)) {
            Buffer leastRU = T1.get(T1.findLRUIndex());
            T1.remove(leastRU);
            B1.insert(leastRU);
        } else {
            Buffer leastRU = T2.get(T2.findLRUIndex());
            T2.remove(leastRU);
            B2.insert(leastRU);
        }
    }

    public String toString() {
        String composite = "";
        composite += B1.toString() + T1.toString() + T2.toString() +  B2.toString() + "\n";
        return composite; 
    }




    public static void main(String[] args) {
        ARC tiny = new ARC(4);
        System.out.println(tiny);
        for(int i = 0; i < 4; i++) { //TODO: hilariously broken atm, replacing MRU element
            tiny.insert(new Buffer(i));
            System.out.println(tiny);
        }
    }

    
}