package PageReplace;
import java.util.*;



public class Buffer {
    private Integer val;
    private Bit ref_bit;

    public Buffer(Integer val, Bit referenced_bit) {
        this.val = val;
        this.ref_bit = referenced_bit;
    }

    public Buffer(Integer val) {
        this.val = val;
        this.ref_bit = new Bit(0);
    }
    public Buffer() {
        this.val = 0;
        this.ref_bit = new Bit(0);
    }

    public Integer get_val() {
        return this.val;
    }

    public Bit get_bit() {
        return this.ref_bit;
    }

    public static void main(String[] args) {
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

    }

    @Override
    public String toString() {
        // [1] means unreferenced. [1*] means referenced
        if (this.ref_bit.get() == 0) {
            return "[" + this.val + "]";
        } else {
            return "[" + this.val + "*]";
        }
    }

    //@Override
    public int compareTo(Buffer b) {
        // Useful for sorting an array of buffers?
        // sort by value only for now. 
        // will have a runtime exception if o is not a buffer. TODO
        return this.get_val().compareTo(b.get_val());
    }

    
}