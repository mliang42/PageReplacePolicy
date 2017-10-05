package PageReplace;
import java.util.*;

public class ClockBuffer extends Buffer {
    private Integer val;
    private int ref_bit;

    public ClockBuffer(Integer val, int bit) {
        this.val = val;
        this.ref_bit = bit;
    }

    public ClockBuffer(Integer val) {
        this.val = val;
        this.ref_bit = 0;
    }

    public ClockBuffer() {
        this.val = 0;
        this.ref_bit = 0;
    }

    public Integer get_val() {
        return this.val;
    }

    public int get_ref() {
        return this.ref_bit;
    }

    public void set_ref(int i) {
        this.ref_bit = i;
    }

    public int compareTo(ClockBuffer b) {
        return this.get_val().compareTo(b.get_val());
    }

    public static void main(String[] args) {

    }


}