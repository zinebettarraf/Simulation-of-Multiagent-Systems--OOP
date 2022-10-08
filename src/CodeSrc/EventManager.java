package CodeSrc;
import java.util.*;

public class EventManager {
	long currentDate;
	Comparator comparator = (Comparator)(new EventComparator());
	Queue<Event> events;
	Queue<Event> eventsSaver;
	public EventManager() {
		this.currentDate=0;
		this.events=new PriorityQueue<Event>(comparator);
		this.eventsSaver=new PriorityQueue<Event>(comparator);
	}
	
	void addEvent(Event e) {
		this.events.add(e);
		this.eventsSaver.add(e);
	}
	void next() {
		this.currentDate+=1;
		while (this.events.peek() != null && this.events.peek().getDate()<= this.currentDate) {
			this.events.poll().execute();
		}
	}
	boolean isFinished() {
		return this.events.peek()==null;
	}
	void restart() {
		// ajouter les evenements qui sont déjà executés 
		Iterator<Event> it=this.eventsSaver.iterator();
		while (it.hasNext() ) {	
			Event event=it.next();	
			if(event.getDate()<=this.currentDate) {
				this.events.add(event);
			}
			else break;
		}
		this.currentDate=this.events.peek().getDate();
		}
        
		
	
}
