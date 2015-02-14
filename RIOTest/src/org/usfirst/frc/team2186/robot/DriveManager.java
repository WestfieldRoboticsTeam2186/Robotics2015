package org.usfirst.frc.team2186.robot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveManager {
	//Declare Speed Controllers
	SpeedController left = new Talon(RobotMap.MOTOR_LEFT);
	SpeedController right = new Talon(RobotMap.MOTOR_RIGHT);
	SpeedController fifthWheel = new Talon(RobotMap.MOTOR_LEFT_SIDE);
	SpeedController otherWheel = new Talon(RobotMap.MOTOR_RIGHT_SIDE);
	//Declare PID Objects
	Encoder leftEnc = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, true, EncodingType.k4X);
	Encoder rightEnc = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B, true, EncodingType.k4X);
	Encoder leftSideEnc = new Encoder(RobotMap.ENCODER_LEFT_SIDE_A, RobotMap.ENCODER_LEFT_SIDE_B, true, EncodingType.k4X);
	Encoder rightSideEnc = new Encoder(RobotMap.ENCODER_RIGHT_SIDE_A, RobotMap.ENCODER_RIGHT_SIDE_B, true, EncodingType.k4X);
	PIDSpeedController leftController, rightController, leftSideController, rightSideController;
	//RobotDrive
	@Deprecated
	RobotDrive drive = new RobotDrive(left, right);
	
	
	//Values from the RobotDrive
	@Deprecated
	private double twist = 0d;
	@Deprecated
	private double forward = 0d;
	
	//PID values
	private final double kLeftP = 0.00015;
	private final double kLeftI = 0;
	private final double kLeftD = 0;
	
	private final double kRightP = 0.1;
	private final double kRightI = 0.001;
	private final double kRightD = 0.1;
	
	private final double kLeftSideP = 0.1;
	private final double kLeftSideI = 0.001;
	private final double kLeftSideD = 0.1;
	
	private final double kRightSideP = 0.1;
	private final double kRightSideI = 0.001;
	private final double kRightSideD = 0.1;
	//Singleton
	protected static DriveManager instance = null;
	
	public static DriveManager getInstance(){
		if(instance == null){
			instance = new DriveManager();
		}
		return instance;
	}
	
	//Constructor
	protected DriveManager(){
		
		leftController = new PIDSpeedController(left, leftEnc, kLeftP, kLeftI, kLeftD);
		//rightController = new PIDSpeedController(right, leftEnc, kRightP, kRightI, kRightD);
		//leftSideController = new PIDSpeedController(fifthWheel, leftSideEnc, kLeftSideP, kLeftSideI, kLeftSideD);
		//rightSideController = new PIDSpeedController(otherWheel, rightSideEnc, kRightSideP, kRightSideI, kRightSideD);
		
		//leftController.enable();
		//rightController.enable();
		//leftSideController.enable();
		//rightSideController.enable();
		
		
		/*leftController.setContinuous(true);
		rightController.setContinuous(true);
		leftSideController.setContinuous(true);
		rightSideController.setContinuous(true);*/
	}
	
	//Call this method during Teleop
	public void update(Joystick j){
		//Arcade Drive
		double leftAmt = (-j.getAxis(AxisType.kY)) + j.getAxis(AxisType.kZ);
		if(leftAmt > 1) leftAmt = 1.0;
		else if(leftAmt < -1) leftAmt = -1.0;
		
		double rightAmt = (j.getAxis(AxisType.kY)) - j.getAxis(AxisType.kZ);
		if(rightAmt > 1) rightAmt = 1.0;
		else if(rightAmt < -1) rightAmt = -1.0;
		
		//PID Drive
		//leftController.setSetpoint((leftAmt+1.0)*2.5);
		//rightController.setSetpoint((rightAmt+1.0)*2.5);
		
		SmartDashboard.putNumber("Right Encoder", rightEnc.getRaw());
		SmartDashboard.putNumber("Left Side Encoder", leftSideEnc.getRaw());
		SmartDashboard.putNumber("Right Side Encoder", rightSideEnc.getRaw());
		SmartDashboard.putNumber("Amount", 0.0d);
		double in = SmartDashboard.getNumber("Amount", 0.0d);
		leftController.set(leftAmt);
		//rightController.set(rightAmt);
		
		
		//Side Drive
		double amt = j.getAxis(AxisType.kX);
		//PID Drive
		//this.leftSideController.setSetpoint((amt+1.0) * 2.5);
		//rightSideController.setSetpoint((-amt+1.0) * 2.5);
		//leftSideController.set(amt);
		//rightSideController.set(-amt);
	}
	
	public void stopAll(){
		leftController.disable();
		rightController.disable();
		leftSideController.disable();
		rightSideController.disable();
	}
}
