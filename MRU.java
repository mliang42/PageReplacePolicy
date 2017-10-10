package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.*;

public class MRU<T> extends LRU {

	public MRU(List<Buffer> lst) {
		super(lst);
	}

	public MRU(Integer size) {
		super(size);
	}


	@Override
	public void insert(Buffer buf) {
		int contains = checkIfContains(buf);
		int index;
		if (contains == -1) {
			index = findMRUIndex(buf);
			lst.set(index, buf);
		} else {
			pagehits++;
			index = contains;
		}

		int previous = relativelst.get(index);
        relativelst.set(index, 1);
        for(int i = 0; i < lst.size(); i++) {
            if (relativelst.get(i) <= previous && i != index) {
                relativelst.set(i, relativelst.get(i)  + 1 );
            }
        }
        
        totalhits++;
	}

	public int findMRUIndex(Buffer buf) {
		for(int i = 0; i < relativelst.size(); i++) {
            if (lst.get(i).get_val() == null) {
                return i;
            }
        }
        for(int i = 0; i < relativelst.size(); i++) {
        	if (relativelst.get(i) == 1) { //Most recently used
                return i;
            }
        }
        return -1;
	}

	public static void main(String[] args) {
		MRU cache = new MRU(10);
		for(int i = 0; i < 10; i++) {
			cache.insert(new Buffer(i));
			System.out.println(cache.toString());
		}

		cache.insert(new Buffer("haha!"));
		System.out.println(cache.toString());

		cache.insert(new Buffer(5));
		System.out.println(cache.toString());

		for(int i = 0; i < 1000; i++) {
            cache.insert(new Buffer(ThreadLocalRandom.current().nextInt(0, 1000)));
            System.out.println(cache.toString());
        } 

        cache.insert(new Buffer(null)); //null can be inserted but does not count as a cache hit.
        System.out.println(cache.toString());
	}
}