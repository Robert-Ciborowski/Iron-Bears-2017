package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;

import edu.wpi.first.wpilibj.Servo;

public class ServoSubsystem extends M_Subsystem {

	private Servo servo0, servo1;

	public ServoSubsystem(int channel0, int channel1) {
		servo0 = new Servo(channel0);
		// servo1 = new Servo(channel1);
	}

	// Set position of servo, 0 is one extreme 1.0 is other extreme
	public void setServo(double setpoint) {
		servo0.set(setpoint);
	}

	// Set position of servo, 0 is one extreme 1.0 is other extreme
	public void setServoAngle(int angle) {
		servo0.setAngle(angle);
	}

	@Override
	public void updateDashboard() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
