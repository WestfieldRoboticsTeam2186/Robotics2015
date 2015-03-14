package org.usfirst.frc.team2186.robot;

import java.io.IOException;
import org.usfirst.frc.team2186.robot.autonomous.PixyPacket;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;

public class IO {
	//Pixy's server
	SerialPort out = new SerialPort(115200, SerialPort.Port.kMXP);
	
	private static IO instance = null;
	public static IO getInstance(){
		if(instance == null){
			instance = new IO();
		}
		return instance;
	}
	public void writeInit(){
		out.writeString("init");
	}
	
	public void write(String data){
		out.writeString(data);
	}
	
	public String read() throws IOException{
		return out.readString();
	}
	String delims = " ,[]{}";
	
	public PixyPacket getPacket() throws IOException{
		String data = read();
		PixyPacket pkt = new PixyPacket();
		String[] numbers = data.split(delims);
		pkt.X = Integer.parseInt(numbers[0]);
		pkt.Y = Integer.parseInt(numbers[1]);
		pkt.Width = Integer.parseInt(numbers[2]);
		pkt.Height = Integer.parseInt(numbers[3]);
		pkt.Angle = Integer.parseInt(numbers[4]);
		pkt.Distance = Double.parseDouble(numbers[5]);
		return pkt;
		
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
