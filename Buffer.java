package PageReplace;
import java.util.*;

//todo make this a general buffer class, other buffer classes extend it.

public class Buffer {
    private Integer val;

    public Buffer(Integer val) {
        this.val = val;
        //this.ref_bit = referenced_bit;
    }

    public Buffer() {
        this.val = 0;
    }

    public Integer get_val() {
        return this.val;
    }

    public static void main(String[] args) {
        /*
        Buffer empty = new Buffer(); 
        System.out.println(empty.get_val());
        System.out.println(empty.get_bit().get());

        List<Buffer> lst = new ArrayList<Buffer>();
        for(int i = 0; i < 10; i++) {
            lst.add(new Buffer(i));
        }
        for(int i = 0; i < 10; i++) {
            System.out.println(lst.get(i).get_val());
            System.out.println(lst.get(i).get_bit());
        }
        */

    }

    @Override
    public String toString() {
        // [1] means unreferenced. [1*] means referenced
        
        return "[" + this.val + "]";
    }

    //@Override
    public int compareTo(Buffer b) {
        // Useful for sorting an array of buffers?
        // sort by value only for now. 
        // will have a runtime exception if o is not a buffer. TODO
        return this.get_val().compareTo(b.get_val());
    }


    
}