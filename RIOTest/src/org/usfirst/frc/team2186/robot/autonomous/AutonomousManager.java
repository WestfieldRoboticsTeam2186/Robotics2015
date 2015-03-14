package org.usfirst.frc.team2186.robot.autonomous;

import java.io.IOException;

import org.usfirst.frc.team2186.robot.DriveManager;
import org.usfirst.frc.team2186.robot.IO;
import org.usfirst.frc.team2186.robot.Mandible.MandibleManager;

import edu.wpi.first.wpilibj.*;



public class AutonomousManager {
	DriveManager drive = DriveManager.getInstance();
	MandibleManager mandible = MandibleManager.getInstance();
	Timer timer;
	
	IO io = IO.getInstance();
	
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
		
	}
	public void driveForward(double secs){
		this.timer.start();
		while(!timer.hasPeriodPassed(secs)){
			drive.update(0, 0.5, 0);
		}
	}
	
	public void update(){
		try {
			PixyPacket pkt = io.getPacket();
			if(pkt.Distance >0.25){
				movement_state = MOVING_FORWARD;
			}
			if(pkt.X < 120){
				movement_state = MOVING_LEFT;
			}
			if(pkt.X > 200){
				movement_state = MOVING_RIGHT;
			}
			
			if(pkt.Distance < 0.25){
				movement_state = NEUTRAL;
				lifter_state = LIFTER_UP;
			}
			io.write("ready");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			drive.update(0, -1, 0);
		}
		}
		
		switch(lifter_state){
		case LIFTER_NEUTRAL:
		{
			mandible.getLifter().update(false, false, true);
			break;
		}
		case LIFTER_UP:
		{
			mandible.getLifter().update(true, false, false);
			break;
		}
		case LIFTER_DOWN:
		{
			mandible.getLifter().update(false, true, false);
			break;
		}
		}
	}
}
