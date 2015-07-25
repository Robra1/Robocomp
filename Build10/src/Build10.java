import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Build10 {

	public static void main(String[] args) {
		//Erstellen des Hilfsobjektes
		Robot r = new Robot();
		//Setzen des Ziels
		r.setTarget(r.winkelBestimmen());
		//Abfrage vor dem Start
		System.out.println("Start");
		Button.waitForAnyPress();
		Behavior b[] = {new Fahren(r),new Ausweichen(r),new Kill(r)};
		Arbitrator a = new Arbitrator(b);
		a.start();
	}

}
