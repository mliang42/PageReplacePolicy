package PageReplace;
import java.util.*;
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
        int index = T1.checkIfContains(b);
        if (index == -1) {
            
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