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
            System.out.println(rand);
            lru_cache.insert(new Buffer(rand));
            //System.out.println(lru_cache);
            mru_cache.insert(new Buffer(rand));
            //System.out.println(mru_cache);
            clk.request(new ClockBuffer(rand));
            System.out.println(clk);
            //System.out.println("lru: " + lru_cache.hitrate() + ",mru: " + mru_cache.hitrate() + ",clk: " + clk.hitrate());
		}
        System.out.println(lru_cache.lst.size());
        System.out.println(mru_cache.lst.size());
        System.out.println(clk.lst.size());
	}
}