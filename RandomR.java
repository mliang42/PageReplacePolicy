package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomR { //random replacement
    private List<Buffer> lst;
    private int pagehits; 
    private int totalhits; 
    private int index;
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
        index = 0;
    }

    public void insert(Buffer b) {
        totalhits++;
        int nullIndex = -1;
        //if lst contains the buffer b
        for(int i = 0; i < lst.size(); i++) { //double for loop condensed with a null index...
            if (lst.get(i).equals(b)) {
                pagehits++;
                index = i;
                return;
            }
            if (lst.get(i).get_val() == null) {
                nullIndex = i;
            }
        }

        if (nullIndex != -1) {
            index = nullIndex;
            lst.set(nullIndex, b);
        } else {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, lst.size());
            lst.set(randomIndex, b);
            index = randomIndex;
        }
        
    }

    public double hitrate() {
        if (totalhits == 0) {
            return 0;
        }
        return (double) pagehits / totalhits;
    }

    public int size() {
        return lst.size();
    }

    @Override
    public String toString() {
        String temp = "";
        for(int i = 0; i < lst.size(); i++) {
            if (i == index) {
                temp += "[*" + lst.get(i).get_val() + "*]"; //can be misleading if your entry is all *****
            } else {
                temp += "[" + lst.get(i).get_val() + "]";
            }
        }
        return temp + "\n";
    }

    public static void main(String[] args) {
        RandomR random = new RandomR(10);
        for(int j = 0; j < 2; j++) {
            for(int i = 0; i < 15; i++) {
                random.insert(new Buffer(i));
                System.out.println(random);
                System.out.println(random.hitrate());
            }
        }
        
    }
}