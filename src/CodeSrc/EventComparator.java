package CodeSrc;
import java.util.*;

public class EventComparator implements Comparator<Event> {
	@Override
	public int compare(Event e1,Event e2) {
		if(e1.getDate()<e2.getDate()) return -1;
		else if(e1.getDate()>e2.getDate()) return 1;
		else return 0;
	}

}
