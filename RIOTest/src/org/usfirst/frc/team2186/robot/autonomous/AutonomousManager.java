package org.usfirst.frc.team2186.robot.autonomous;

import org.usfirst.frc.team2186.robot.DriveManager;

import edu.wpi.first.wpilibj.*;



public class AutonomousManager {
	DriveManager drive = DriveManager.getInstance();
	Timer timer;
	
	Pixy pixy;
	PixyPacket pkt;
	PixyController c;
	
	final int NEUTRAL = 0;
	final int MOVING_FORWARD = 1;
	final int MOVING_LEFT = 2;
	final int MOVING_BACK = 3;
	final int MOVING_RIGHT = 4;
	final int STOPPING = 5;
	
	int movement_state = NEUTRAL;
	
	final int LIFTER_NEUTRAL = 0;
	final int LIFTER_UP = 1;
	final int LIFTER_DOWN = 2;
	
	int lifter_state = LIFTER_NEUTRAL;
	
	protected static AutonomousManager instance = null;
	public static AutonomousManager getInstance(){
		if(instance == null){
			instance = new AutonomousManager();
		}
		return instance;
	}
	
	protected AutonomousManager(){
		pixy = new Pixy();
		c = new PixyController(pixy);
	}
	public void driveForward(double secs){
		this.timer.start();
		while(!timer.hasPeriodPassed(secs)){
			drive.update(0, 0.5, 0);
		}
	}
	
	public void update(){
		switch(movement_state){
		case NEUTRAL:
		{
			drive.update(0, 0, 0);
			break;
		}
		case MOVING_FORWARD:
		{
			drive.update(0, 1, 0);
			break;
		}
		case MOVING_LEFT:
		{
			drive.update(-1, 0, 0);
			break;
		}
		case MOVING_BACK:
		{
			
		}
		}
		
	}
}
