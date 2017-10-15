package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PercentHit {

	public static void main(String[] args) {
		LRU lru_cache = new LRU(100);
		MRU mru_cache = new MRU(100);
		Clock clk = new Clock(100);

		for(int i = 0; i < 10000; i++) {
			Integer rand = ThreadLocalRandom.current().nextInt(0, 1000); 
            
            //System.out.println(rand);
            lru_cache.insert(new Buffer(rand));
            //System.out.println(lru_cache);
            mru_cache.insert(new Buffer(rand));
            //System.out.println(mru_cache);
            clk.request(new ClockBuffer(rand));
            //System.out.println(clk);
            System.out.println("lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate() + ",clk: " + clk.hitrate());
		}
        System.out.println(lru_cache.lst.size());
        System.out.println(mru_cache.lst.size());
        System.out.println(clk.lst.size());

        lru_cache = new LRU(500);
        mru_cache = new MRU(500);
        clk = new Clock(500);
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
                System.out.println("lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate() + ",clk: " + clk.hitrate());
            }//MRU  hits 1000 out of 3000, therefore 33%, everything else is a miss
        }

        try {
            lru_cache = new LRU(0); //uh oh
            mru_cache = new MRU(-1);
            clk = new Clock(-134123);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return;
        }
        

        for(int i = 0; i < 10; i++) { //insert same value over and over and see what happens
            lru_cache.insert(new Buffer(3.14));
            mru_cache.insert(new Buffer(3.14));
            clk.request(new ClockBuffer(3.14));
        }
        
	}


}