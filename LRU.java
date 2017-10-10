package PageReplace;
import java.util.*;
import static org.junit.Assert.*;

public class LRU {
    private List<Buffer> lst;
    private List<Integer> relativelst;
    private int pagehits; 
    private int totalhits; 

    public LRU(List<Buffer> lst, List<Integer> relativeList) {
        assertEquals(lst.size(), relativeList.size());
        this.lst = lst;
        this.relativelst = relativeList;
        this.pagehits = 0;
        this.totalhits = 0;
        for(int i = 0; i < lst.size(); i++) { //cache is initially empty
            if (lst.get(i).get_val() == null) {
                relativelst.set(i, -1);
            }
        } 
    }

    public void insert(Buffer buf) {
        int contains = checkIfContains(buf);
        int index;
        if (contains == -1) {
            index = findLRUIndex(buf);
            lst.set(index, buf);
        } else {
            pagehits++;
            index = contains;
        }
        
        relativelst.set(index, 1);
        for(int i = 0; i < lst.size(); i++) {
            if (i != index && relativelst.get(i) != -1) {
                relativelst.set(i, relativelst.get(i) + 1);
            }
        }
        totalhits++;

    }   

    public int checkIfContains(Buffer buf) {
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals(buf)) { //returns false if you try to match null
                return i; 
            }
        }
        return -1;
    }

    public int findLRUIndex(Buffer buf) {
        for(int i = 0; i < relativelst.size(); i++) {
            if (relativelst.get(i) == relativelst.size()) {
                return i;
            }
            if (lst.get(i).get_val() == null) {
                return i;
            }
        }
        return -1;//should never happen 

    }


    @Override
    public String toString() {
        String buffs = "";
        String times = "";
        for(int i = 0; i < lst.size(); i++) {
            buffs += "[" + lst.get(i).get_val() + "]";
            times += "[" + relativelst.get(i) + "]";
        }

        return buffs + "\n" + times + "\n";

    }

    public static void main(String[] args) {
        List<Buffer> list_of_buf = new ArrayList<>();
        List<Integer> rel = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Buffer temp = new Buffer(null);
            list_of_buf.add(temp);
            rel.add(0);
        }
        LRU test = new LRU(list_of_buf, rel);
        System.out.println(test.toString());
        
        for(int i = 0; i < 10; i++) {
            test.insert(new Buffer(i*i));
            System.out.println(test.toString());
        }

        //now the fun begins
        test.insert(new Buffer(100));
        System.out.println(test.toString());

        test.insert(new Buffer("jebaiiiited"));
        System.out.println(test.toString());

    }


}