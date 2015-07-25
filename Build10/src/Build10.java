import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Build10 {

	public static void main(String[] args) {
		Robot r = new Robot();
		r.setTarget(r.winkelBestimmen());
		System.out.println("Start");
		Button.waitForAnyPress();
		Behavior b[] = {new Fahren(r),new Ausweichen(r),new Kill(r)};
		Arbitrator a = new Arbitrator(b);
		a.start();
	}

}
