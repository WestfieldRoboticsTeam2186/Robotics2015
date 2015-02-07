package org.usfirst.frc.team2186.robot;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class DriveManager {
	SpeedController left = new Talon(RobotMap.MOTOR_LEFT);
	SpeedController right = new Talon(RobotMap.MOTOR_RIGHT);
	SpeedController fifthWheel = new Talon(RobotMap.MOTOR_LEFT_SIDE);
	SpeedController otherWheel = new Talon(RobotMap.MOTOR_RIGHT_SIDE);
	
	Encoder leftEnc = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B, false, EncodingType.k4X);
	Encoder rightEnc = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B, false, EncodingType.k4X);
	Encoder leftSideEnc = new Encoder(RobotMap.ENCODER_LEFT_SIDE_A, RobotMap.ENCODER_LEFT_SIDE_B, false, EncodingType.k4X);
	Encoder rightSideEnc = new Encoder(RobotMap.ENCODER_RIGHT_SIDE_A, RobotMap.ENCODER_RIGHT_SIDE_B, false, EncodingType.k4X);
	PIDController leftController, rightController, leftSideController, rightSideController;
	RobotDrive drive = new RobotDrive(left, right);
	
	private double twist = 0d;
	private double forward = 0d;
	
	private final double accAmt = 0.025;
	
	private double fifthWheelAmnt = 0.0d;
	
	private boolean fifthwheelEnabled = false;
	private final double kLeftP = 0.1;
	private final double kLeftI = 0.001;
	private final double kLeftD = 0.0;
	
	private final double kRightP = 0.1;
	private final double kRightI = 0.001;
	private final double kRightD = 0.0;
	
	private final double kLeftSideP = 0.1;
	private final double kLeftSideI = 0.001;
	private final double kLeftSideD = 0.0;
	
	private final double kRightSideP = 0.1;
	private final double kRightSideI = 0.001;
	private final double kRightSideD = 0.0;
	
	protected static DriveManager instance = null;
	
	public static DriveManager getInstance(){
		if(instance == null){
			instance = new DriveManager();
		}
		return instance;
	}
	
	
	protected DriveManager(){
		leftController = new PIDController(kLeftP, kLeftI, kLeftD, leftEnc, left);
		rightController = new PIDController(kRightP, kRightI, kRightD, rightEnc, right);
		leftSideController = new PIDController(kLeftSideP, kLeftSideI, kLeftSideD, leftSideEnc, fifthWheel);
		rightSideController = new PIDController(kRightSideP, kRightSideI, kRightSideD, rightSideEnc, otherWheel);
		
		leftController.enable();
		rightController.enable();
		leftSideController.enable();
		rightSideController.enable();
		
		leftController.setContinuous(true);
		rightController.setContinuous(true);
		leftSideController.setContinuous(true);
		rightSideController.setContinuous(true);
	}
	
	public void forwards(double amount){
		leftController.setContinuous(true);
		rightController.setContinuous(true);
		leftController.setSetpoint(amount);
		rightController.setSetpoint(amount);
	}
	public void Update(Joystick j){
		//drive.arcadeDrive(-j.getAxis(AxisType.kY), j.getAxis(AxisType.kZ));
		double leftAmt = (-j.getAxis(AxisType.kY)) + j.getAxis(AxisType.kZ);
		double rightAmt = (j.getAxis(AxisType.kY)) - j.getAxis(AxisType.kZ);
		
		leftController.setSetpoint((leftAmt+1.0)*2.5);
		rightController.setSetpoint((rightAmt+1.0)*2.5);
		
		double amt = j.getAxis(AxisType.kX);
		
		this.leftSideController.setSetpoint((amt+1.0) * 2.5);
		rightSideController.setSetpoint((-amt+1.0) * 2.5);
	}
	
	
	public void togglefifthWheel(boolean enabled){
		this.fifthwheelEnabled = enabled;
	}
	
	public void turnOnDime(double speed){
		left.set(0);
		right.set(0);
		this.fifthWheel.set(speed);
	}
	
	public void StopAll(){
		left.set(0);
		right.set(0);
		fifthWheel.set(0);
	}
}
