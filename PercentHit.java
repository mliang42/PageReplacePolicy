package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PercentHit {

	public static void main(String[] args) {
		LRU lru_cache = new LRU(100);
		MRU mru_cache = new MRU(100);
		Clock clk = new Clock(100);
        RandomR random = new RandomR(100);
        LFU leastf = new LFU(100);

		for(int i = 0; i < 10000; i++) {
			Integer rand = ThreadLocalRandom.current().nextInt(0, 1000); 
            
            //System.out.println(rand);
            lru_cache.insert(new Buffer(rand));
            //System.out.println(lru_cache);
            mru_cache.insert(new Buffer(rand));
            //System.out.println(mru_cache);
            clk.request(new ClockBuffer(rand));
            //System.out.println(clk);
            random.insert(new Buffer(rand));

            leastf.insert(new Buffer(rand));
            
		}
        System.out.println("lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate()
             + ",clk: " + clk.hitrate() + ",rand: " + random.hitrate() + ",lfu: " + leastf.hitrate());
        System.out.println(lru_cache.lst.size());
        System.out.println(mru_cache.lst.size());
        System.out.println(clk.lst.size());
        System.out.println(random.lst.size());
        System.out.println(leastf.lst.size());

        lru_cache = new LRU(500);
        mru_cache = new MRU(500);
        clk = new Clock(500);
        random = new RandomR(500);
        leastf = new LFU(500);
        for(int j = 0; j < 3; j++) { //sequential flooding
            for(int i = 0; i < 1000; i++) {
                Integer rand = i; 
                
                //System.out.println(rand);
                lru_cache.insert(new Buffer(rand));
                //System.out.println(lru_cache);
                mru_cache.insert(new Buffer(rand));
                //System.out.println(mru_cache);
                clk.request(new ClockBuffer(rand));
                //System.out.println(clk);
                random.insert(new Buffer(rand));

                leastf.insert(new Buffer(rand));
               
            }//MRU  hits 1000 out of 3000, therefore 33%
        }
        System.out.println("lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate()
             + ",clk: " + clk.hitrate() + ",rand: " + random.hitrate() + ",lfu: " + leastf.hitrate());

      
	}


}