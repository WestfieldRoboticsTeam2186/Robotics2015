package org.team2186.FRC;

import edu.wpi.first.wpilibj.Encoder;

public class PIDController {
	private double m_P;                 // factor for "proportional" control
    private double m_I;                 // factor for "integral" control
    private double m_input;             // sensor input for pid controller
    private double m_maximumInput = 0.0;                // maximum input - limit setpoint to this
    private double m_minimumInput = 0.0;                // minimum input - limit setpoint to this
    private boolean m_enabled = false;                  //is the pid controller enabled
    private double m_prevError = 0.0;   // the prior sensor input (used to compute velocity)
    private double m_setpoint = 0.0;
    private double m_error = 0.0;
    private double m_result = 0.0;
	private Encoder m_Encoder;
	private double m_diffError = 0.0;
	
	public PIDController(double kP, double kI, double kD, Encoder enc){
		m_P = kP;
		m_I = kI;
		m_Encoder = enc;
	}
	
	private void calculate(){
		if(m_enabled){
			m_input = m_Encoder.getRate();
			m_error = m_setpoint - m_input;
			m_diffError = m_error-m_prevError;
			m_diffError *= m_I;
			m_error *= m_P;
			
			m_result += m_error - m_diffError;
			
			
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
	}
	
	public void setContinuous(boolean continuous){
	}
	
	public void enable(){
		m_enabled = true;
	}
	
	public double get(){
		calculate();
		return m_result;
	}
}
