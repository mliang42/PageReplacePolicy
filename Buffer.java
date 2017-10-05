package PageReplace;
import java.util.*;

//todo make this a general buffer class, other buffer classes extend it.

public class Buffer<T> {
    private T val;

    public Buffer(T val) {
        this.val = val;
        //this.ref_bit = referenced_bit;
    }


    public T get_val() {
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




    
}