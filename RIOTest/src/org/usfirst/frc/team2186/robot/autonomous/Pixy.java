package org.usfirst.frc.team2186.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class Pixy{
	public Pixy(){
		
	}
	
	@SuppressWarnings("deprecation")
	public PixyPacket getPacket(){
		PixyPacket pkt = new PixyPacket();
		pkt.X = SmartDashboard.getInt("x");
		pkt.Y = SmartDashboard.getInt("y");
		pkt.Width = SmartDashboard.getInt("width");
		pkt.Height = SmartDashboard.getInt("height");
		pkt.Angle = SmartDashboard.getInt("angle");
		pkt.Distance = SmartDashboard.getNumber("distance");
		
		return pkt;
	}
}