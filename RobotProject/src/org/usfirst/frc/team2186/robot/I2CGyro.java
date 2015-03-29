package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class I2CGyro implements PIDSource {
	I2C i2c = new I2C(I2C.Port.kOnboard, 0x69);
	
	int x;

	int y, z;
	
	int[] xavg = new int[11];
	int[] yavg = new int[11];
	int[] zavg = new int[11];
	
	int i = 0;
	
	public void enable(){
		i2c.write(0x20, 0x4f);
	}
	
	
	public void disable(){
		i2c.write(0x20, 0xb7);
	}
	public void update(){
		whoAmI();
		recvData();
		zavg[3] = z;
		
		SmartDashboard.putNumber("Gyro X", x);
		SmartDashboard.putNumber("Gyro Y", y);
		SmartDashboard.putNumber("Gyro Z", zavg[3]);
	}
	
	private void recvData(){
		byte[] xbufferl = new byte[1];
		byte[] xbufferh = new byte[1];
		byte[] ybufferl = new byte[1];
		byte[] ybufferh = new byte[1];
		byte[] zbufferl = new byte[1];
		byte[] zbufferh = new byte[1];
		i2c.read(0x28, 1, xbufferl);
		i2c.read(0x29, 1, xbufferh);
		i2c.read(0x2a, 1, ybufferl);
		i2c.read(0x2b, 1, ybufferh);
		i2c.read(0x2c, 1, zbufferl);
		i2c.read(0x2d, 1, zbufferh);
		
		x = (((int)xbufferh[0] & 0xff) <<8 | ((int)xbufferl[0] & 0xff));
		if(x >= 32768) x -= 65536;
		
		
		
		y = (((int)ybufferh[0] & 0xff) << 8 | ((int)ybufferl[0] & 0xff));
		if(y >= 32768) y -= 65536;
		
		z = (((int)zbufferh[0] & 0xff) << 8 | ((int)zbufferl[0] & 0xff));
		if(z >= 32768) z -= 65536;
	}
	
	private void whoAmI(){
		byte[] buffer = new byte[1];
		boolean detected = i2c.read(0x0F, 1, buffer);
		
		SmartDashboard.putNumber("I2C whois", (int)buffer[0] & 0xff);
		SmartDashboard.putBoolean("Sees I2C device", detected);
	}


	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return (double)zavg[3] * 0.00763;
	}
}
