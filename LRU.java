package PageReplace;
import java.util.*;

public class LRU {
    private List<LRUBuffer> lst;
    private int pagehits; 
    private int totalhits; 
    private int pointer;

    public LRU(List<LRUBuffer> lst) {
        this.lst = lst;
        this.pagehits = 0;
        this.totalhits = 0;
        this.pointer = 0;
    }

    public void insert(LRUBuffer buf) {
        int index = findLRUIndex();
        lst.set(index, buf);
    }   

    public int findLRUIndex() {
        int time = lst.get(0).time();
        int index = 0;
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).time() < time) {
                time = lst.get(i).time();
                index = i;
            }
        }
        return index;

    }

    public void tickAll() {
        for(LRUBuffer b: lst) {
            b.tick();
        }
    }

    @Override
    public String toString() {
        String buffs = "";
        for(int i = 0; i < lst.size(); i++) {
            buffs += "[" + lst.get(i).get_val() + "]";
        }
        return buffs;

    }
    public static void main(String[] args) {
        List<LRUBuffer> list_of_buf = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            LRUBuffer temp = new LRUBuffer(i);
            list_of_buf.add(temp);
        }
        LRU lru = new LRU(list_of_buf);
        System.out.println(lru.toString());
    }


}