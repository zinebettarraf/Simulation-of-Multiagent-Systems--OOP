package CodeSrc;

public class EventFlock extends Event{
	Boids flock;
	public EventFlock(Boids flock,long date) {
		super(date);
		this.flock=flock;
	}
     void execute() {
 		this.flock.moveBoids();
     }
}
