import lejos.robotics.subsumption.Behavior;

//Behavoir zum Berühren der Gegnerbase
public class Kill implements Behavior {
	
	//Flag, ob ein anderes Behavior die Kontrolle übernommen hat
	boolean suppressed;
	//Hilfsobjekt
	Robot r;
	
	//Konstruktor
	public Kill(Robot r) {
		this.r = r;
		suppressed = false;
	}
	
	//Falls die Grenze überfahren wurde
	@Override
	public boolean takeControl() {
		return r.n.getPoseProvider().getPose().getX() > r.GRENZE;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Kill");
		//Einfach weiterfahren
		while(!r.ts.isPressed()){
			r.n.goTo(r.ziel);
		}
		
		while(!suppressed)
			Thread.yield();
	}
	
	//Eigentlich unnötig, da höchste Priorität
	@Override
	public void suppress() {
		suppressed = true;	
	}

}
