package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomR {
    public List<Buffer> lst;
    public int pagehits; 
    public int totalhits; 
    public RandomR(Integer size) throws IllegalArgumentException{
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        this.lst = new ArrayList<Buffer>();
        for(int i = 0; i < size; i++) {
            Buffer temp = new Buffer(null);
            lst.add(temp);
        }
        this.pagehits = 0;
        this.totalhits = 0;
    }

    public void insert(Buffer b) {
        totalhits++;
        //if lst contains the buffer b
        for(int i = 0; i < lst.size(); i++) { //TODO: could be condensed
            if (lst.get(i).equals(b)) {
                pagehits++;
                return;
            }
        }

        //if lst contains null elements
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).get_val() == null) {
                lst.set(i, b);
                return;
            }
        }

        

        
    }
}