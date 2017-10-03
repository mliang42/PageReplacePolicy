package PageReplace;
import java.util.*;

public class ClockBuffer extends Buffer {
    private Integer val;
    private Bit ref_bit;

    public ClockBuffer(Integer val, Bit bit) {
        this.val = val;
        this.ref_bit = bit;
    }

    public ClockBuffer(Integer val) {
        this.val = val;
        this.ref_bit = new Bit(0);
    }

    public ClockBuffer() {
        this.val = 0;
        this.ref_bit = new Bit(0);
    }

    public Bit get_bit() {
        return this.ref_bit;
    }

    public void set_bit(Bit b) {
        this.ref_bit = b;
    }

    public Integer get_val() {
        return this.val;
    }

    public int compareTo(ClockBuffer b) {
        return this.get_val().compareTo(b.get_val());
    }

    public static void main(String[] args) {
        ClockBuffer buf = new ClockBuffer(3, new Bit(0));
        System.out.println(buf.get_bit().get());
        System.out.println(buf.get_val());

        ArrayList<ClockBuffer> lst = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            lst.add(new ClockBuffer(i, new Bit(1)));

        }
        for(int i = 0; i < 10; i++) {
            System.out.println(lst.get(i).get_val());
            System.out.println(lst.get(i).get_bit().get());
        }
    }


}