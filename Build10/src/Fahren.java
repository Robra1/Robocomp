import lejos.robotics.subsumption.Behavior;

//Behavior, zum Fahren zur Gegnerbase
public class Fahren implements Behavior {
	
	//Hilfsobjekt
	Robot r;
	//Flag, ob ein anderes Behavior die Kontrolle übernommen hat
	boolean suppressed;
	
	//Konstruktor
	public Fahren(Robot r) {
		this.r = r;
		suppressed = false;
	}

	//Niedrigste Priorität, übernimmt Kontrolle wenn kein anderer sie will
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		
		//Fährt zum Ziel
		if(!suppressed){
			r.n.goTo(r.ziel);
		}
		
//		while(!suppressed)
//			Thread.yield();
	}

	@Override
	public void suppress() {
		//Stoppt den Roboter
		r.n.stop();
		suppressed = true;
	}

}
