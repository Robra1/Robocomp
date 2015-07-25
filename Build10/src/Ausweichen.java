import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;


//Behavior zum Ausweichen der Hindernisse
public class Ausweichen implements Behavior {
	
	//Flag, ob ein anderes Behavior die Kontrolle �bernommen hat
	boolean suppressed;
	//Hilfsobjekt
	Robot r;
	//Variable f�r den Winkel
	byte w;
	
	//Konstruktor
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
		//Schleife, Winkel wird immer um 15 grad erh�ht
		while(!suppressed && (w <= 75)){
			//US schwenkt
			r.topM.rotateTo(w);
			//Pr�fen ob frei
			if(!r.check()){
				//Ausrichten des US
				r.topM.rotateTo(0);
				//Ausweichbewegung
				r.p.rotate(w);
				r.p.travel(r.MIN * 2);
				break;
			}
			else{
				//US schwenkt
				r.topM.rotateTo(-w);
				//Pr�fen ob frei
				if(!r.check()){
					//Ausrichten des US
					r.topM.rotateTo(0);
					//Ausweichbewegung
					r.p.rotate(-w);
					r.p.travel(r.MIN * 2);
					break;
				}
			}
			w += 15;
		}
		//Roboter steckt fest und wei� nicht wohin auszuweichen
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

