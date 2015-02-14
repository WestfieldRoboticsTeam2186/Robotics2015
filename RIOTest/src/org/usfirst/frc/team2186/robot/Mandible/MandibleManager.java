package org.usfirst.frc.team2186.robot.Mandible;

import edu.wpi.first.wpilibj.Joystick;

public class MandibleManager{
	Lifter lifter;
	Mandible mandible;
	public MandibleManager(){
		lifter = new Lifter();
		mandible = new Mandible();
	}
	public void update(Joystick j){
		lifter.update(j);
		mandible.update(j);
	}
}
