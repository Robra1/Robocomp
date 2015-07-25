import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class Ausweichen implements Behavior {
	
	boolean suppressed;
	Robot r;
	byte w;
	
	public Ausweichen(Robot r) {
		this.r = r;
		suppressed = false;
		w = 30;
	}

	@Override
	public boolean takeControl() {
		return r.check();
	}

	@Override
	public void action() {
		suppressed = false;
		w = 30;
		r.n.stop();
		
		while(!suppressed && (w <= 75)){
			r.topM.rotateTo(w);
			if(!r.check()){
				r.topM.rotateTo(0);
				r.p.rotate(w);
				r.p.travel(r.MIN * 2);
				break;
			}
			else{
				r.topM.rotateTo(-w);
				if(!r.check()){
					r.topM.rotateTo(0);
					r.p.rotate(-w);
					r.p.travel(r.MIN * 2);
					break;
				}
			}
			w += 15;
		}
		if(w >= 75){
			Sound.twoBeeps();
			System.out.println("Scheisse");
		}
		
		
			
		
//		while(!suppressed)
//			Thread.yield();
	}

	@Override
	public void suppress() {
		suppressed = true;
//		r.n.stop();
	}

}

