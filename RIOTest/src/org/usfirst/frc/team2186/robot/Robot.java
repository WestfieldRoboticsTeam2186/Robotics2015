
package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
	AutonomousManager autoManager = new AutonomousManager();
	DriveManager driveTrain = DriveManager.getInstance();
	//VisionManager vision = new VisionManager();
	Joystick j1 = new Joystick(0);
	Joystick j2 = new Joystick(1);
	double deadzone = 0.1d;
	
	boolean isEnabled;
	//VisionManager visionManager = new VisionManager();
    public void robotInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	driveTrain.Update(j1);
        //sp1.set(motorX);
        //sp2.set(motorY);
    	//vision.update();
    }
    
    /*private void arcadeDrive(){
    	double joyY = j1.getY();
    	double joyX = j1.getX();
    	double motorX, motorY;
    	motorX = joyX + joyY;
    	motorY = joyY - joyX;
    	if(motorX < deadzone && motorX > -deadzone){
    		sp1.set(0.0d);
    	} else if(motorX > deadzone || motorX < -deadzone){
    		sp1.set(motorX);
    	}
    	
    	if(motorY < deadzone && motorY > -deadzone){
    		sp2.set(0.0d);
    	} else if(motorY > deadzone || motorY < -deadzone){
    		sp2.set(motorY);
    	}
    }*/
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	/*double joyY = j1.getY();
    	double joyX = j1.getX();
    	double motorX, motorY;
    	motorX = joyX + joyY;
    	motorY = joyY - joyX;
    	if(motorX < deadzone && motorX > -deadzone){
    		sp1.set(0.0d);
    	} else if(motorX > deadzone || motorX < -deadzone){
    		sp1.set(motorX);
    	}
    	
    	if(motorY < deadzone && motorY > -deadzone){
    		sp2.set(0.0d);
    	} else if(motorY > deadzone || motorY < -deadzone){
    		sp2.set(motorY);
    	}*/
    }
    
}
