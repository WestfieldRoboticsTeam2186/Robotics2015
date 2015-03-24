package org.usfirst.frc.team2186.robot;

import org.usfirst.frc.team2186.robot.autonomous.PixyPacket;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IO {
	
	
	private static IO instance = null;
	public static IO getInstance(){
		if(instance == null){
			instance = new IO();
		}
		return instance;
	}
	public void writeInit(){
		SmartDashboard.putNumber("ready", 1);
	}
	
	public PixyPacket getPacket(){
		SmartDashboard.putNumber("ready", 0);
		PixyPacket pkt = new PixyPacket();
		pkt.X = (int)SmartDashboard.getNumber("x");
		pkt.Y = (int)SmartDashboard.getNumber("y");
		pkt.Width = (int)SmartDashboard.getNumber("width");
		pkt.Height = (int)SmartDashboard.getNumber("height");
		pkt.Angle = (int)SmartDashboard.getNumber("angle");
		pkt.Distance = SmartDashboard.getNumber("distance");
		
		return pkt;
	}
	
	public void write(String str){
		
	}
	
	protected IO() {
	}
	
	DigitalInput lifterMin = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MIN);
	DigitalInput lifterMax = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MAX);
	DigitalInput lifterGot = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFTER);
	
	public boolean getLifterMin(){
		return !lifterMin.get();
	}
	
	public boolean getLifterMax(){
		return !lifterMax.get();
	}
}
