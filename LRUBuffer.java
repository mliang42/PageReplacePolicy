package PageReplace;
import java.util.*;

public class LRUBuffer<T> {
    private T val;
    private int time;
    public LRUBuffer(T val) {
        this.val = val;
        this.time = 0;
    }

    public T get_val() {
        return this.val;
    }

    public int time() {
        return this.time;
    }

    public void tick() {
        this.time++;
    }

    

    public static void main(String[] args) {
        LRUBuffer buf = new LRUBuffer("hello");
        System.out.println(buf.get_val());
        System.out.println(buf.time());

        LRUBuffer newBuf = new LRUBuffer(102238);
        System.out.println(newBuf.get_val());
        buf.tick();
        System.out.println(buf.time());
        System.out.println(newBuf.time());
    }


}