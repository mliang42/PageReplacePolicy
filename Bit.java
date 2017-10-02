import invalidArgumentException;

public class Bit throws invalidArgumentException {
    private static int value;
    public Bit(int value) {
        if (value < 0 || value > 1) {
            throw new invalidArgumentException("Cannot assign " + value + " to a bit"); 
        } else {
            this.value = value;
        }
    }

    public int get() {
        return value;
    }

    public static void main(string[] args) {
        Bit zero = new Bit(0);
        Bit one = new Bit(1);
        System.out.println(zero.get());
        System.out.println(one.get());
        
    }

}