import PageReplace.bit;


public class Buffer {
    private Integer val;
    private Bit ref_bit;
    public Buffer(Integer val, Bit referenced_bit) {
        this.val = val;
        this.referenced_bit = ref_bit;
    }
}