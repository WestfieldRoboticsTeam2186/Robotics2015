package org.usfirst.frc.team2186.robot.autonomous;

public class PixyController {
	Pixy pixy;
	double error;
	double objRatio;
	public final double ratio = 109/80;
	
	public PixyController(Pixy p){
		pixy = new Pixy(); 
	}
	
	public double autoCenter(PixyPacket pkt){
		if (pkt != null){
			System.out.println(pkt.X + " " + pkt.Y);
			try{
				objRatio = pkt.Height/pkt.Width;
			} catch (Exception e){
				System.out.println("Dividing by zero");
				objRatio = 0;
			}
//			if ((ratio-.1) <= (objRatio) && (ratio+.1) >= (objRatio)){
				if (pkt.X < 150 || pkt.X > 170){
					error = pkt.X - 160;
					error = error/160;
					if(-0.3 < error && error < 0.3 && error < 0){
						error = -0.3;
					}
					if(-0.3 < error && error < 0.3 && error > 0){
						error = 0.3;
					}
				}
				else{
					System.out.println("Tote within band");
					error = 0;
				}
//			}
//			else if((objRatio) > (ratio+0.1)){
//				error = 0;
//			}
//		    else{
//				error = 0;
//			}
		}
		return error;
	}
	
	double focalLength = 2.8;
	double pixelsPerMM = 3000;
	double sizeOfToteInMM = 139719.8374;
	public double calculateDistance(PixyPacket pkt){
		double distance = 0.0;
		double size = (pkt.Width * pkt.Height);
		double sizeInMM = size / pixelsPerMM;
		
		distance = (sizeOfToteInMM * focalLength)/ sizeInMM;
		return distance;
	}
}
