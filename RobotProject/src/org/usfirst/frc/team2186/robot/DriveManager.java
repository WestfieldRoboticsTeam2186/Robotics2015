package org.usfirst.frc.team2186.robot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDSource;
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
	PIDGyroController gyroController;
	I2CGyro gyro = new I2CGyro();
	//RobotDrive
	@Deprecated
	RobotDrive drive = new RobotDrive(left, right);
	
	
	//PID values
	private final double kLeftP = 0.00005;
	private final double kLeftI = 0.000005;
	private final double kLeftD = 0.1;
	
	private final double kRightP = 0.00005;
	private final double kRightI = 0.000005;
	private final double kRightD = 0.1;
	
	private final double kLeftSideP = 0.00005;
	private final double kLeftSideI = 0.000005;
	private final double kLeftSideD = 0.1;
	
	private final double kRightSideP = 0.00005;
	private final double kRightSideI = 0.000005;
	private final double kRightSideD = 0.1;
	
	private final double kGyroP = 0.0005;
	private final double kGyroI = 0;
	private final double kGyroD = 0.2;
	//Singleton
	protected static DriveManager instance = null;
	
	public static DriveManager getInstance(){
		if(instance == null){
			instance = new DriveManager();
		}
		return instance;
	}
	
	public double getRaw(){
		return rightController.getRaw();
	}
	
	public double getSideRaw(){
		return rightSideController.getRaw();
	}
	
	//Constructor
	protected DriveManager(){
		
		leftController = new PIDSpeedController(kLeftP, kLeftI, kLeftD, leftEnc);
		rightController = new PIDSpeedController(kRightP, kRightI, kRightD, rightEnc);
		leftSideController = new PIDSpeedController(kLeftSideP, kLeftSideI, kLeftSideD, leftSideEnc);
		rightSideController = new PIDSpeedController(kRightSideP, kRightSideI, kRightSideD, rightSideEnc);
		
		leftController.enable();
		rightController.enable();
		leftSideController.enable();
		rightSideController.enable();
		
		
		leftController.setContinuous(true);
		rightController.setContinuous(true);
		leftSideController.setContinuous(true);
		rightSideController.setContinuous(true);
		
		leftController.setOutputRange(-1, 1);
		rightController.setOutputRange(-1, 1);
		leftSideController.setOutputRange(-1, 1);
		rightSideController.setOutputRange(-1, 1);
		
		leftController.setInputRange(-5000, 5000);
		rightController.setInputRange(-5000, 5000);
		leftSideController.setInputRange(-5000, 5000);
		rightSideController.setInputRange(-5000, 5000);
		
		
		gyro.enable();
		gyroController = new PIDGyroController(kGyroP, kGyroI, kGyroD, (PIDSource)gyro);
		gyroController.enable();
		gyroController.setInputRange(-100, 100);
		gyroController.setOutputRange(-1, 1);
		
	}
	
	public void update(double x, double y, double z){
		left.set(y);
		right.set(-y);
		
		fifthWheel.set(x);
		otherWheel.set(-x);
	}
	
	//Call this method during Teleop
	public void update(Joystick j){
		//Arcade Drive
		gyro.update();
		double zAxis = j.getAxis(AxisType.kZ);
		gyroController.setSetPoint(zAxis*100);
		double result = gyroController.get();
		if(Math.abs(result) < 0.1){
			result = 0;
		}
		
		result = clamp(result, -1, 1);
		
		double leftAmt = j.getAxis(AxisType.kY);
		leftAmt *= -1;
		leftAmt = clamp(leftAmt, -1, 1);
		double rightAmt = j.getRawAxis(3);
		rightAmt = clamp(rightAmt, -1, 1);
		
		//PID Drive
		//leftController.setSetpoint((leftAmt+1.0)*2.5);
		//rightController.setSetpoint((rightAmt+1.0)*2.5);
		SmartDashboard.putNumber("Left Input", leftAmt);
		SmartDashboard.putNumber("Right Input", j.getRawAxis(3));

		leftController.setSetPoint(leftAmt*1000);
		left.set(leftAmt);
		SmartDashboard.putNumber("Left Amount", leftAmt);
		
		rightController.setSetPoint(rightAmt * 1000);
		right.set(rightAmt);
		SmartDashboard.putNumber("Right Amount", rightAmt);
		
		//Side Drive
		double amt = j.getAxis(AxisType.kZ);
		if(Math.abs(amt) < 0.1){
			amt = 0;
		}
		amt = clamp(amt, -1, 1);
		//PID Drive
		leftSideController.setSetPoint(amt * 750);
		rightSideController.setSetPoint(amt * 750);
		SmartDashboard.putNumber("Left Side", amt);
		fifthWheel.set(amt);
		otherWheel.set(amt);
		
	}
	
	public void nopidupdate(Joystick j){
		
	}
	static double clamp(double val, double min, double max){
		return Math.max(min, Math.min(max, val));
	}
}
