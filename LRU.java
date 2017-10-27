package PageReplace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class LRU { //least recently used
    List<Buffer> lst; 
    List<Integer> relativelst;
    int pagehits; 
    int totalhits; 
    //elements are declared "default" because subclasses need to access these elements.

    public LRU(List<Buffer> lst) {
        this.lst = lst;
        this.relativelst = new ArrayList<Integer>();
        this.pagehits = 0;
        this.totalhits = 0;
        for(int i = 0; i < lst.size(); i++) { //initialize the relativelst in some arbitrary order
            relativelst.add(i, i+1);
        } 
    }

    public LRU(Integer size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Must initialize cache with positive number of entries.");
        }
        this.lst = new ArrayList<Buffer>();
        this.relativelst = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            Buffer temp = new Buffer(null);
            lst.add(temp);
        }
        this.pagehits = 0;
        this.totalhits = 0;
        for(int i = 0; i < lst.size(); i++) { //initialize the relativelst in some arbitrary order
            relativelst.add(i, i+1);
        } 
    }

    public LRU() {
        //throws an error about LFU complaining about some constructor not existing if I don't include this...
    }

    public Buffer insert(Buffer buf) { //returns the buffer that was either replaced, or is contained.
        Buffer temp;
        int contains = checkIfContains(buf);
        int index;
        if (contains == -1) {
            index = findLRUIndex();
            temp = lst.get(index);
            lst.set(index, buf);

        } else {
            pagehits++;
            index = contains;
            temp = buf;

        }
        int previous = relativelst.get(index);
        relativelst.set(index, 1);
        for(int i = 0; i < lst.size(); i++) {
            if (relativelst.get(i) <= previous && i != index) {
                relativelst.set(i, relativelst.get(i)  + 1 );
            }
        }
        
        totalhits++;
        return temp;
    }

    public int checkIfContains(Buffer buf) { //returns the index of i in the lst if it contains the buffer
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals(buf)) { //returns false if you try to match null
                return i; 
            }
        }
        return -1;
    }

    public int findLRUIndex() { //finds the index i in relativelst which is the least recently used
        for(int i = 0; i < relativelst.size(); i++) {
            if (lst.get(i).get_val() == null) {
                return i;
            }
        }
        //why do I have two for loops looping over the same thing? 
        //I need to check if there is an empty space before replacing a value
        for(int i = 0; i < relativelst.size(); i++) { 
            if (relativelst.get(i) == relativelst.size()) {
                return i;
            } 
        }
        return -1; //essentially unreachable unless something broke

    }

    public Buffer removeLRU() {
        int index = findLRUIndex();
        Buffer temp = lst.get(index);
        lst.remove(index);
        relativelst.remove(index);
        return temp;

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

    public int totalhits() {
        return totalhits;
    }

    public int pagehits() {
        return pagehits;
    }

    //used for ARC
    public Buffer reduce() throws IllegalArgumentException { 
        //reduces size of cache by 1, drops least recently used element (or null element) and returns the buffer.
        int index = findLRUIndex();
        if (index == -1) {
            throw new IllegalArgumentException("LRU indices are broken.");
        } 
        Buffer least = lst.get(index); //could be null
        lst.remove(index); //deletes the buffer and its corresponding relative index. 
        relativelst.remove(index); //does not require shifting relative list values because we choose the least recently used.
        return least;
    }

    //used for ARC
    public void increase() { //increases size of cache by 1, inserts null element
        lst.add(new Buffer(null));
        relativelst.add(lst.size()-1);
        return;
    }

    @Override
    public String toString() {
        String buffs = "";
        String times = "";
        for(int i = 0; i < lst.size(); i++) {
            buffs += "[" + lst.get(i).get_val() + "]";
            times += "[" + relativelst.get(i) + "]";
        }

        return buffs + "\n" + times + "\n" + "pagehits:" + pagehits + ", totalhits:" + totalhits + "\n";

    }

    public static void main(String[] args) {
        List<Buffer> list_of_buf = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Buffer temp = new Buffer(null);
            list_of_buf.add(temp);
        }
        //LRU test = new LRU(list_of_buf);
        LRU test = new LRU(10);
        System.out.println(test);
        
        for(int i = 0; i < 10; i++) {
            test.insert(new Buffer(i*i));
            System.out.println(test);
        }

        //now the fun begins
        test.insert(new Buffer(100));
        System.out.println(test);

        test.insert(new Buffer("oh no, a string!"));
        System.out.println(test);

        test.insert(new Buffer(81));//cache hit
        System.out.println(test); 
        
        for(int i = 0; i < 1000; i++) {
            test.insert(new Buffer(ThreadLocalRandom.current().nextInt(0, 1000)));
            System.out.println(test);
        } 

        test.insert(new Buffer(null)); //null can be inserted but does not count as a cache hit.
        System.out.println(test);


        
        

    }


}