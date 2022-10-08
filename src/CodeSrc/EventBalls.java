
package CodeSrc;

public class EventBalls extends Event{
	Balls balls;
	public EventBalls(Balls balls,long date) {
		super(date);
		this.balls=balls;
	}
     void execute() {
    	 int dx = 10;
 		int dy = 10;
 		this.balls.translate(dx, dy);
     }
}
