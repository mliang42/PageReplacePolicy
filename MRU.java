package PageReplace;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MRU extends LRU {

	public MRU(List<Buffer> lst) {
		super(lst);
	}

	public MRU(Integer size) {
		super(size);
	}

	public void insert(Buffer buf) {
		int contains = checkIfContains(buf);
		if (contains == -1) {
			
		} else {

		}
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
}