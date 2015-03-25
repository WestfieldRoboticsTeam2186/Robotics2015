
package org.usfirst.frc.team2186.robot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	Class autonomous = autoManager.getClass();
	Method method;

	

	
	//Init robot systems
    public void robotInit() {
    	//TODO: Add more values to init.
    	mandible = MandibleManager.getInstance();
    	io = IO.getInstance();
    	boolean button1Checked = SmartDashboard.getBoolean("DB/Button1");
    	boolean button2Checked = SmartDashboard.getBoolean("DB/Button2");
    	boolean button3Checked = SmartDashboard.getBoolean("DB/Button3");
    	
    	if(button1Checked){
    		try {
				method = autonomous.getMethod("leftAutonomousUpdate", null);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else if(button2Checked){
    		try {
				method = autonomous.getMethod("midAutonomousUpdate", null);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	} else if(button3Checked){
    		try {
				method = autonomous.getMethod("rightAutonomousUpdate", null);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit(){
    	io.writeInit();
    }
    
    public void autonomousPeriodic() {
    	try {
			Object ret = method.invoke(null, null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	driveTrain.update(j1);
    	mandible.update(j2);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
}
