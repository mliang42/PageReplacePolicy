package PageReplace;

import java.io.*;


public class Bit {
    private static int value;
    public Bit(int value) throws IllegalArgumentException {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Cannot assign " + value + " to a bit"); 
        } else {
            this.value = value;
        }
    }

    public int get() {
        return this.value;
    }

    public static void main(String[] args) {
        Bit zero = new Bit(0);
        System.out.println(zero.get());
        Bit one = new Bit(1);
        System.out.println(one.get());
        System.out.println(one.toString());
        Bit oh_no = new Bit(100000);
        System.out.println(oh_no.get());
    }

    @Override
    public String toString() {
        return "(" + this.value + ")";
    }

}