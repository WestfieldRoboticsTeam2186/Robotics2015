package org.usfirst.frc.team2186.robot.autonomous;

import org.usfirst.frc.team2186.robot.DriveManager;
import org.usfirst.frc.team2186.robot.IO;
import org.usfirst.frc.team2186.robot.RobotStates;
import org.usfirst.frc.team2186.robot.Mandible.MandibleManager;

import edu.wpi.first.wpilibj.*;



public class AutonomousManager {
	DriveManager drive = DriveManager.getInstance();
	MandibleManager mandible = MandibleManager.getInstance();
	Timer timer;
	
	IO io = IO.getInstance();
	
	RobotStates state = RobotStates.DRIVE_NEUTRAL;
	
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
	int numNeutral = 0;
	
	private final double TICKS_PER_REV = 1440;
	private final double INCHES_PER_REV = 3*2*Math.PI;
	private final double DRIVE_WHEEL_TEETH = 22;
	private final double WHEEL_FRONT_GEAR_TEETH = 28;
	private final double WHEEL_SIDE_GEAR_TEETH = 36;
	public void leftAutonomousUpdate(){
		switch(state){
		case DRIVE_NEUTRAL:
			if(numNeutral == 0){
				state = RobotStates.DRIVE_FORWARD;
				numNeutral++;
			}
			if(numNeutral == 1){
				state = RobotStates.DRIVE_BACKWARD;
				numNeutral++;
			}
			if(numNeutral == 2){
				state = RobotStates.DRIVE_LEFT;
				numNeutral++;
			}
			if(numNeutral == 3){
				state = RobotStates.DRIVE_FORWARD;
			}
			drive.update(0, 0, 0);
			break;
		case DRIVE_FORWARD:
			double inches = drive.getRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_FRONT_GEAR_TEETH * INCHES_PER_REV;
			if(inches > 115){
				state = RobotStates.DRIVE_NEUTRAL;
			} drive.update(0, 1, 0);
			break;
		case DRIVE_BACKWARD:
			double backInches = drive.getRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_FRONT_GEAR_TEETH * INCHES_PER_REV;
			if(-backInches > 115){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(0, -1, 0);
			break;
		case DRIVE_LEFT:
			double leftInches = drive.getSideRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_SIDE_GEAR_TEETH * INCHES_PER_REV;
			if(leftInches < -27){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(-0.5, 0, 0);
			break;
		}
	}
	
	public void midAutonomousUpdate(){
		double inches = drive.getRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_FRONT_GEAR_TEETH * INCHES_PER_REV;

		switch(state){
		case DRIVE_NEUTRAL:
			if(numNeutral == 0){
				state = RobotStates.DRIVE_FORWARD;
				numNeutral++;
			}
			if(numNeutral == 1){
				mandible.getLifter().setState(RobotStates.LIFTER_UP);
				state = RobotStates.DRIVE_LEFT;
				numNeutral++;
			}
			if(numNeutral == 2){
				state = RobotStates.DRIVE_FORWARD;
				numNeutral++;
			}
			if(numNeutral >= 3){
				drive.update(0, 0, 0);
			}
			drive.update(0, 0, 0);
			break;
		case DRIVE_FORWARD:
			if(inches > 40 && numNeutral !=2){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			else if(numNeutral ==  2){
				if(inches > 56){
					state = RobotStates.DRIVE_NEUTRAL;
					numNeutral++;
				}
			}
			drive.update(0, 0.5, 0);
			break;
		case DRIVE_BACKWARD:
			if(-inches > 5){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(0, -0.25, 0);
			break;
		case DRIVE_LEFT:
			double sideInches = drive.getSideRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_SIDE_GEAR_TEETH * INCHES_PER_REV;
			if(-sideInches > 36){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(-0.5, 0, 0);
			break;
		}
	}
	
	public void rightAutonomousUpdate(){
		double inches = drive.getRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_FRONT_GEAR_TEETH * INCHES_PER_REV;

		switch(state){
		case DRIVE_NEUTRAL:
			if(numNeutral == 0){
				state = RobotStates.DRIVE_FORWARD;
				numNeutral++;
			}
			if(numNeutral == 1){
				mandible.getLifter().setState(RobotStates.LIFTER_UP);
				state = RobotStates.DRIVE_LEFT;
				numNeutral++;
			}
			if(numNeutral == 2){
				state = RobotStates.DRIVE_FORWARD;
				numNeutral++;
			}
			if(numNeutral >= 3){
				drive.update(0, 0, 0);
			}
			drive.update(0, 0, 0);
			break;
		case DRIVE_FORWARD:
			if(inches > 40 && numNeutral !=2){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			else if(numNeutral ==  2){
				if(inches > 56){
					state = RobotStates.DRIVE_NEUTRAL;
					numNeutral++;
				}
			}
			drive.update(0, 0.5, 0);
			break;
		case DRIVE_BACKWARD:
			if(-inches > 5){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(0, -0.25, 0);
			break;
		case DRIVE_LEFT:
			double sideInches = drive.getSideRaw() / TICKS_PER_REV / DRIVE_WHEEL_TEETH * WHEEL_SIDE_GEAR_TEETH * INCHES_PER_REV;
			if(-sideInches > 72){
				state = RobotStates.DRIVE_NEUTRAL;
			}
			drive.update(-0.5, 0, 0);
			break;
		}
	}
	
	public void update(){
		
	}
}
