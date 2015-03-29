package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.*;

public class CompensatedSpeedController implements SpeedController {
	private SpeedController inner;
	private double add;
	private double scale;
	private double externalVal;
	
	public CompensatedSpeedController(SpeedController sc, double add_, double scale_){
		inner = sc;
		scale = scale_;
		add = add_;
	}
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		externalVal = output;
		inner.pidWrite((output+add)*scale);
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return externalVal;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		// TODO Auto-generated method stub
		externalVal = speed;
		inner.set((speed+add)*scale, syncGroup);
	}

	@Override
	public void set(double speed) {
		// TODO Auto-generated method stub
		externalVal = speed;
		inner.set((speed+add)*scale);
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		inner.disable();
	}

}
