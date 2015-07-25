import lejos.robotics.subsumption.Behavior;

public class Kill implements Behavior {
	
	boolean suppressed;
	Robot r;
	
	public Kill(Robot r) {
		this.r = r;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return r.n.getPoseProvider().getPose().getX() > r.GRENZE;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Kill");
		while(!r.ts.isPressed()){
			r.n.goTo(r.ziel);
		}
		
		while(!suppressed)
			Thread.yield();
	}

	@Override
	public void suppress() {
		suppressed = true;	
	}

}
