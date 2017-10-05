package PageReplace;
import java.util.*;

public class Clock<T> {
    private List<ClockBuffer> lst;
    private int pagehits; 
    private int totalhits; 
    private int pointer;

    /*
    public Clock() {
        this.lst = new ArrayList<ClockBuffer>();
        this.pagehits = 0;
        this.totalhits = 0;
        this.pointer = 0;

    }*/

    public Clock(ArrayList<ClockBuffer> buffers) {
        this.lst = buffers;
        this.pagehits = 0;
        this.totalhits = 0;
        this.pointer = 0;
    }

    public int getIndex(T val) {
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).get_val() == val) {
                return i;
            }
        }
        return -1;
    }
 

    public int request(ClockBuffer b) { //put
        int index = getIndex((T) b.get_val()); //may need to reconsider generics
        totalhits++;
        if (index != -1) { //cache hit, set ref bit and done
            //lst.get(index).set_bit(new Bit(1));
            lst.get(index).set_ref(1);
            pagehits++;
            return 1; //cache hit
        } else {
            clockIter(b);
            return 0; //load from disk
        }
    }

    public void clockIter(ClockBuffer b) {
        for(int i = 0; i < lst.size(); i++) {
            ClockBuffer curr = lst.get(pointer);
            if (curr.get_ref() == 1) {
                curr.set_ref(0);
            } else {
                break;
            }
            System.out.println(pointer);
            pointer = (pointer + 1) % (lst.size()); 

        }
        b.set_ref(1);
        lst.set(pointer, b);
        pointer = (pointer + 1) % (lst.size()); 
        return;

    }

    @Override
    public String toString() {
        //TODO must deal with variable length inputs and pointer
        String temp = "";
        String point = "";
        for(int i = 0; i < lst.size(); i++) {
            int addition = 2;
            if (lst.get(i).get_ref() == 0) {
                temp += "[" + lst.get(i).get_val() + "]";
                
            } else {
                temp += "[" + lst.get(i).get_val() + "*" +"]";
                addition++;
            }

            int length = String.valueOf(lst.get(i).get_val()).length();
            //System.out.println((int) ((length+1) / 2));
            for(int j = 0; j < length+addition; j++) {
                if (i == pointer && j == (int) ((length+1) / 2)) {
                    point += "|";
                } else {
                    point += " ";
                }
            }
        }
        return temp + "\n" + point;
    }

    public String info() {
        return "PageHits: " + pagehits + " , TotalHits: " + totalhits;
    }


    public static void main(String[] args) {
        ArrayList<ClockBuffer> lstofbuffers=  new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            lstofbuffers.add(new ClockBuffer(i));
        }
        Clock clk = new Clock(lstofbuffers);
        System.out.println(clk.toString());

        clk.request(new ClockBuffer(9));
        System.out.println(clk.toString());

        clk.request(new ClockBuffer(999));
        System.out.println(clk.toString());

        for(int i = 0; i < 10; i++) {
            clk.request(new ClockBuffer(i));
            System.out.println(clk.toString());
        } 

        ArrayList<ClockBuffer> lst = new ArrayList<>();
        lst.add(new ClockBuffer("A"));
        lst.add(new ClockBuffer("B"));
        lst.add(new ClockBuffer("Q"));
        lst.add(new ClockBuffer("R"));
        clk = new Clock(lst);

        clk.request(new ClockBuffer("A"));
        System.out.println(clk.toString());
        clk.request(new ClockBuffer("B"));
        System.out.println(clk.toString());
        clk.request(new ClockBuffer("D"));
        System.out.println(clk.toString());
        clk.request(new ClockBuffer("Z"));
        System.out.println(clk.toString());


    }


}