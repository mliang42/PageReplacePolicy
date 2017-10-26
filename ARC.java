package PageReplace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ARC { //adaptive replacement cache
    public LRU T1;
    public LRU T2;
    public LRU ghostlst1;
    public LRU ghostlst2; //fixed of size c/2 where c is the size of the cache
    public int pagehits; 
    public int totalhits; 
    public int index; //separator between L1 and L2 caches in lst


    public ARC(Integer size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        T1 = new LRU(size / 2);
        T2 = new LRU(size / 2);
        ghostlst1 = new LRU(size / 2);
        ghostlst2 = new LRU(size / 2); 
        pagehits = 0;
        totalhits = 0;
    }

    public void insert(Buffer b) {
        //attempts to insert Buffer b into T1
        
        if (T1.checkIfContains(b) == -1) { //T1 does not contain b
            int index = T1.findLRUIndex();
            Buffer buf = T1.insert(b); //returns buffer that was "replaced"
            if (buf.get_val() != null && !buf.equals(b)) {
                //if the buffer value isn't null, which means it didn't replace an empty slot or
                //if the buffer value isn't the input buffer, which means a page hit, 
                //Proceed to insert buffer into B1.
                //If no space in B1, then we trash the value
                
            } //else, filled an empty entry
        } else { //T1 contains b, cache hit by removing entry from T1 and move it into T2
            //

        }

    }



    public int checkIfGhostContains(Buffer b) { //returns the number corresponding to the ghost lst that contains it, otherwise -1
        if (ghostlst1.checkIfContains(b) != -1) {
            return 1; //1 means ghostlst1 contains it
        } else if (ghostlst2.checkIfContains(b) != -1) {
            return 2; //2 means ghostlst2 contains it
        } else {
            return -1; //-1 means neither contains it
        }
    }



    public static void main(String[] args) {
  
    }

    
}