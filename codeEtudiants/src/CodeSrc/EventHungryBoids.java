package CodeSrc;

public class EventHungryBoids extends Event{
	HungryBoids flock;
	
	public EventHungryBoids(HungryBoids flock,long date) {
		super(date);
		this.flock=flock;
	}
     void execute() {
 		this.flock.moveBoids();	 
     }

}
