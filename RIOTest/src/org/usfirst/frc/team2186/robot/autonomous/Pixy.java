package org.usfirst.frc.team2186.robot.autonomous;

import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Pixy{
	NetworkTable connection = NetworkTable.getTable("SmartDashboard");
	public Pixy(){
		
	}
	
	@SuppressWarnings("deprecation")
	public PixyPacket getPacket(){
		PixyPacket pkt = new PixyPacket();
		pkt.X = connection.getInt("x");
		pkt.Y = connection.getInt("y");
		pkt.Width = connection.getInt("width");
		pkt.Height = connection.getInt("height");
		pkt.Angle = connection.getInt("angle");
		pkt.Distance = connection.getNumber("distance");
		
		return pkt;
	}
}