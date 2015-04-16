
package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2186.robot.Mandible.MandibleManager;
import org.usfirst.frc.team2186.robot.autonomous.AutoScript;
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
	
	AutoScript auto = new AutoScript("Autonomous.as");
	
	int autoState = 0;
	
	//Init robot systems
    public void robotInit() {
    	//TODO: Add more values to init.
    	mandible = MandibleManager.getInstance();
    	io = IO.getInstance();
    	SmartDashboard.putNumber("Robot Autonomous State: ", autoState);
    	auto.interpret();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit(){
    	io.writeInit();
    	autoState = (int) SmartDashboard.getNumber("Robot Autonomous State:", 0);
    	timer.start();
    }
    final double TICKS_PER_REV = 1440;
    final double INCHES_PER_REV = 3*2* Math.PI;
    Timer timer = new Timer();
    boolean passed = false;
    public void autonomousPeriodic() {
    	auto.execute();
    }
    
    @Override
    public void teleopInit(){
    	driveTrain.update(0, 0, 0);
    	timer.stop();
    }

    /**
     * This function is called periodically during operator control
     */
    
    boolean wheelsEnabled = false;
    public void teleopPeriodic() {
    	driveTrain.update(j1);
    	mandible.getLifter().setMotor(j2.getAxis(AxisType.kY));
    	if(j2.getRawButton(5)){
    		mandible.getMandible().setOn(true);
    	}
    	
    	if(j2.getRawButton(6)){
    		mandible.getMandible().setInverted(true);
    	}
    	
    	if(j2.getRawButton(7)){
    		mandible.getMandible().setOn(false);
    	}
    	
    	if(j2.getRawButton(8)){
    		mandible.getMandible().setInverted(false);
    	}
    	mandible.setMandibleState(RobotStates.MANDIBLE_RUNNING);
    	
    	mandible.update();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    	driveTrain.update(j1);
    }
    
    @Override
    public void disabledPeriodic(){
    	driveTrain.passed = false;
    }
    
}
