
package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2186.robot.Mandible.MandibleManager;
import org.usfirst.frc.team2186.robot.autonomous.AutonomousManager;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	//Inputs
	Joystick j1 = new Joystick(0);
	Joystick j2 = new Joystick(1);
	
	//Managers
	IO io;
	AutonomousManager autoManager = AutonomousManager.getInstance();
	DriveManager driveTrain = DriveManager.getInstance();
	MandibleManager mandible;
	
	int autoState = 0;
	
	//Init robot systems
    @SuppressWarnings("unused")
	public void robotInit() {
    	//TODO: Add more values to init.
    	mandible = MandibleManager.getInstance();
    	io = IO.getInstance();
    	SmartDashboard.putNumber("Robot Autonomous State: ", autoState);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit(){
    	io.writeInit();
    	autoState = (int) SmartDashboard.getNumber("Robot Autonomous State:");
    }
    
    public void autonomousPeriodic() {
    	switch(autoState){
    	//Left Autonomous
    	case 0:
    		autoManager.leftAutonomousUpdate();
    		break;
    	//Middle Autonomous
    	case 1:
    		autoManager.midAutonomousUpdate();
    		break;
    	//Right Autonomous
    	case 2:
    		autoManager.rightAutonomousUpdate();
    		break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    
    boolean wheelsEnabled = false;
    public void teleopPeriodic() {
    	driveTrain.nopidupdate(j1);
    	if(j2.getRawButton(6) && !j2.getRawButton(8)){
    		mandible.setLifterState(RobotStates.LIFTER_UP);
    	} else if(j2.getRawButton(8) && !j2.getRawButton((6))){
    		mandible.setLifterState(RobotStates.LIFTER_DOWN);
    	} else if(!j2.getRawButton(6) && !j2.getRawButton(8)){
    		mandible.setLifterState(RobotStates.DRIVE_NEUTRAL);
    	}
    	
    	if(j2.getRawButton(5)){
    		wheelsEnabled = true;
    	} else if(j2.getRawButton(7)){
    		wheelsEnabled = false;
    	}
    	
    	if(wheelsEnabled){
    		mandible.setMandibleState(RobotStates.MANDIBLE_RUNNING);
    	} else {
    		mandible.setMandibleState(RobotStates.MANDIBLE_STOPPED);
    	}
    	
    	mandible.update();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	driveTrain.nopidupdate(j1);
    }
    
}
