package PageReplace;
import java.util.*;

public class Clock {
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

    public int getIndex(Integer pagenum) {
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).get_val() == pagenum) {
                return i;
            }
        }
        return -1;
    }
 

    public int request(ClockBuffer b) { //put
        int index = getIndex(b.get_val());
        if (index != -1) { //cache hit, set ref bit and done
            lst.get(index).set_bit(new Bit(1));
            return 1; //cache hit
        } else {
            clockIter(b);
            return 0; //load from disk
        }
    }

    public void clockIter(ClockBuffer b) {
        for(int i = 0; i < lst.size(); i++) {
            ClockBuffer curr = lst.get(pointer);
            if (curr.get_bit().get() == 1) {
                curr.get_bit().set(0);
            } else {
                break;
            }
            pointer = (pointer + 1) % (lst.size() - 1); 
        }
        lst.add(pointer, b);
        pointer = (pointer + 1) % (lst.size() - 1); 
        return;

    }

    @Override
    public String toString() {
        String temp = "";
        String point = "";
        for(int i = 0; i < lst.size(); i++) {
            if (lst.get(i).get_bit().get() == 0) {
                temp += "[" + lst.get(i).get_val() + "]";
                if (pointer == i) {
                    point += " | ";    
                } else {
                    point += "   ";
                }
                
            } else {
                temp += "[" + lst.get(i).get_val() + "*" +"]";
                if (pointer == i) {
                    point += " |  ";
                } else {
                    point += "    ";
                }
            }
        }
        return temp + "\n" + point;
    }

    public static void main(String[] args) {
        ArrayList<ClockBuffer> lstofbuffers=  new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            lstofbuffers.add(new ClockBuffer(i));
        }
        Clock clk = new Clock(lstofbuffers);
        System.out.println(clk.toString());

        

        /*
        int rq = clk.request(new ClockBuffer(9));
        System.out.println(clk.toString());
        System.out.println(rq);*/

    }


}