package org.usfirst.frc.team2186.robot.Mandible;

import edu.wpi.first.wpilibj.Joystick;

public class MandibleManager{
	private Lifter lifter;
	private Mandible mandible;
	
	private static MandibleManager instance = null;
	public static MandibleManager getInstance(){
		if(instance == null){
			instance = new MandibleManager();
		}
		return instance;
	}
	protected MandibleManager(){
		lifter = new Lifter();
		mandible = new Mandible();
	}
	public void update(Joystick j){
		lifter.update(j);
		mandible.update(j);
	}
	
	public Lifter getLifter(){
		return lifter;
	}
	
	public Mandible getMandible(){
		return mandible;
	}
}
