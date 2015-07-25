import lejos.robotics.subsumption.Behavior;

public class Fahren implements Behavior {
	
	Robot r;
	boolean suppressed;
	
	public Fahren(Robot r) {
		this.r = r;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		
		if(!suppressed){
			r.n.goTo(r.ziel);
		}
		
//		while(!suppressed)
//			Thread.yield();
	}

	@Override
	public void suppress() {
		r.n.stop();
		suppressed = true;
	}

}
