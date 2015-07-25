import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;

public class Robot {
	
	UltrasonicSensor us;
	TouchSensor ts;
	
	final int LEANGE = 237;
	final int BREITE = 115;
	
	DifferentialPilot p;
	Navigator n;
	
	RegulatedMotor leftM;
	RegulatedMotor rightM;
	RegulatedMotor topM;
	
	Waypoint ziel;
	
	final int MIN = 15;
	final int GRENZE = 200;
	
	boolean a = false;
	
	public Robot(){
		us = new UltrasonicSensor(SensorPort.S4);
		ts = new TouchSensor(SensorPort.S2);
		
		leftM = Motor.C;
		rightM = Motor.A;
		topM = Motor.B;
		
		p = new DifferentialPilot(4.4, 9.5, leftM, rightM);
		n = new Navigator(p);
	}
	
	public boolean check(){
		return this.us.getDistance() < this.MIN;
	}
	
	public void setTarget(int winkel){
		this.ziel = new Waypoint(LEANGE, Math.tan(Math.toRadians(winkel)) * LEANGE );
	}
		
	public int winkelBestimmen(){
		LCD.clear();
		LCD.drawString("Enter", 0, 0);
		Motor.B.resetTachoCount();
		while(!Button.ENTER.isDown()){
			 if(Button.LEFT.isDown()){
				 Motor.B.rotate(5);
			 }
			 if(Button.RIGHT.isDown()){
				 Motor.B.rotate(-5);
			 }
		 }
		return Motor.B.getTachoCount();
	}


}
