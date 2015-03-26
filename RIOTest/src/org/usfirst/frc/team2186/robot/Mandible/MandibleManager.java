package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotStates;

public class MandibleManager{
	private Lifter lifter;
	private Mandible mandible;
	RobotStates mandibleState = RobotStates.MANDIBLE_STOPPED;
	RobotStates lifterState = RobotStates.LIFTER_NEUTRAL;
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
	public void setLifterState(RobotStates state){
		lifterState = state;
	}
	
	public void setMandibleState(RobotStates state){
		mandibleState = state;
	}
	public void update(){
		switch(mandibleState){
		case MANDIBLE_STOPPED:
			mandible.setState(0);
			break;
		case MANDIBLE_RUNNING:
			mandible.setState(1);
			break;
		default:
			break;
		}
		
		switch(lifterState){
		case LIFTER_NEUTRAL:
			lifter.setState(RobotStates.LIFTER_NEUTRAL);
			break;
		case LIFTER_UP:
			lifter.setState(RobotStates.LIFTER_UP);
			break;
		case LIFTER_DOWN:
			lifter.setState(RobotStates.LIFTER_DOWN);
			break;
		default:
			break;
		}
	}
	
	
	public Lifter getLifter(){
		return lifter;
	}
	
	public Mandible getMandible(){
		return mandible;
	}
}
