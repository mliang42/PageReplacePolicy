package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PercentHit<T> {
    private LRU lru_cache;
    private MRU mru_cache;
    public Clock clk;
    private RandomR random;
    private LFU leastf;
    public PercentHit(Integer size) throws IllegalArgumentException{
        if (size <= 0) {
            throw new IllegalArgumentException("Must have positive size."); 
        }
        lru_cache = new LRU(size);
        mru_cache = new MRU(size);
        clk = new Clock(size);
        random = new RandomR(size);
        leastf = new LFU(size);
           
    }

    public void insert(T val) {
        lru_cache.insert(new Buffer(val));
        mru_cache.insert(new Buffer(val));
        clk.request(new ClockBuffer(val));
        random.insert(new Buffer(val));
        leastf.insert(new Buffer(val));
    }

    public String hitrate() {
        return "lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate() + ", clk: " +  
            clk.hitrate() + ", random: " + random.hitrate() + ", lfu: " + leastf.hitrate();
    }

	public static void main(String[] args) {
        System.out.println("Random insertion");
		PercentHit testRandom = new PercentHit(100);
		for(int i = 0; i < 10000; i++) {
			Integer rand = ThreadLocalRandom.current().nextInt(0, 1000); 
            testRandom.insert(rand);
		}
        System.out.println(testRandom.hitrate());
        
        System.out.println("Sequential Flooding");
        PercentHit flooding = new PercentHit(500);
        for(int j = 0; j < 3; j++) { 
            for(int i = 0; i < 1000; i++) {
                flooding.insert(i);
               
            }//MRU  hits 1000 out of 3000, therefore 33%
        }
        System.out.println(flooding.hitrate());

        System.out.println("Tiny");
        PercentHit tiny = new PercentHit(10);
        for(int i = 0; i < 50; i++) {
            tiny.insert(ThreadLocalRandom.current().nextInt(0, 1000));
            System.out.println(tiny.clk);
            System.out.println(tiny.hitrate());

        }
        
        System.out.println(tiny.hitrate());

      
	}


}