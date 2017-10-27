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
        //attempts to insert Buffer b into T1
        //TODO: read the papers on ARC cause this is a mess
        if (T1.checkIfContains(b) == -1) { //T1 does not contain b
            int index = T1.findLRUIndex();
            Buffer buf = T1.insert(b); //returns buffer that was "replaced"
            if (buf.get_val() != null && !buf.equals(b)) {
                //if the buffer value isn't null, which means it didn't replace an empty slot or
                //if the buffer value isn't the input buffer, which means a page hit, 
                //Proceed to insert buffer into B1.
                int check = B1.checkIfContains(buf);
                T1.insert(buf); //updates LRU if it is there
                if (check == -1) { //does not contain buffer b, so we must insert into B1
                    
                } else { //it does contain b, so move the page in B1 to T2, update resulting LRU values of B1, 
                    //try to insert into T2.
                    B1.removeLRU();
                    T1.increase(); //increase T1 accordingly
                    Buffer last = T2.decrease();//increase T2 accordingly
                    Buffer toB2 = T2.insert(buf);
                    B2.insert(toB2);
                    B2.insert(last);
                }
                
            } //else, filled an empty entry
        } else { //T1 contains b, cache hit by removing entry from T1 and move it into T2
            //

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