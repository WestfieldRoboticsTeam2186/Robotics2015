package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDGyroController {
	private double m_P;                 // factor for "proportional" control
    private double m_I;                 // factor for "integral" control
    private double m_D;                 // factor for "derivative" control
    private double m_input;             // sensor input for pid controller
    private double m_maximumOutput = 1.0;       // |maximum output|
    private double m_minimumOutput = -1.0;      // |minimum output|
    private double m_maximumInput = 0.0;                // maximum input - limit setpoint to this
    private double m_minimumInput = 0.0;                // minimum input - limit setpoint to this
    private boolean m_continuous = false;       // do the endpoints wrap around? eg. Absolute encoder
    private boolean m_enabled = false;                  //is the pid controller enabled
    private double m_prevError = 0.0;   // the prior sensor input (used to compute velocity)
    private double m_totalError = 0.0; //the sum of the errors for use in the integral calc
    private double m_tolerance = 0.05;  //the percetage error that is considered on target
    private double m_setpoint = 0.0;
    private double m_error = 0.0;
    private double m_result = 0.0;
	private PIDSource m_Encoder;
	private double m_diffError = 0.0;
	
	public PIDGyroController(double kP, double kI, double kD, PIDSource enc){
		m_P = kP;
		m_I = kI;
		m_D = kD;
		m_Encoder = enc;
	}
	
	private void calculate(){
		if(m_enabled){
			m_input = m_Encoder.pidGet();
			m_error = m_setpoint - m_input;
			m_diffError = m_error-m_prevError;
			m_diffError *= m_I;
			m_error *= m_P;
			
			m_result += m_error - m_diffError;
			SmartDashboard.putNumber("Gyro deg/s", m_input);
			SmartDashboard.putNumber("Gyro set", m_setpoint);
			SmartDashboard.putNumber("Gyro result", m_result);
			m_prevError = m_error;
		}
	}
	
	public void setSetPoint(double setPoint){
		if(m_maximumInput > m_minimumInput){
			if(setPoint > m_maximumInput){
				m_setpoint = m_maximumInput;
			} else if(setPoint < m_minimumInput){
				m_setpoint = m_minimumInput;
			} else {
				m_setpoint = setPoint;
			} 
		} else {
			m_setpoint = setPoint;
		}
	}
	
	public void setInputRange(double minimumInput, double maximumInput){
		m_minimumInput = minimumInput;
		m_maximumInput = maximumInput;
		setSetPoint(m_setpoint);
	}
	
	public void setOutputRange(double minimum, double maximum){
		m_minimumOutput = minimum;
		m_maximumOutput = maximum;
	}
	
	public void setContinuous(boolean continuous){
		m_continuous = continuous;
	}
	
	public void enable(){
		m_enabled = true;
	}
	
	public double get(){
		calculate();
		return m_result;
	}
}
