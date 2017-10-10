package PageReplace;
import java.util.*;

public class Buffer<T> {
    private T val;

    public Buffer(T val) {
        this.val = val;
    }

    public T get_val() {
        return this.val;
    }


    public boolean equals(Buffer b) {
        if (this.get_val() == null || b.get_val() == null) {
            return false;
        } else {
            return this.get_val().equals(b.get_val());    
        }
        
    }    

    public static void main(String[] args) {
        Buffer buf = new Buffer("hello");
        System.out.println(buf.get_val());

        Buffer newBuf = new Buffer(102238);
        System.out.println(newBuf.get_val());

    }


}