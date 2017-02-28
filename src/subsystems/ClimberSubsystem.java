package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import teleopdrive.JoystickCommand;

public class ClimberSubsystem extends M_Subsystem{
	private Spark climberMotor = new Spark(RobotMap.climberMotorPort);
	
	public ClimberSubsystem() {
		climberMotor.setInverted(RobotMap.climberMotorInverted);
	}
	
	public void init() {
		
	}
	
	public void setClimberSpeed(double speed) {
		climberMotor.set(speed);
	}
	
	public void climberOff() {
		setClimberSpeed(0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	@Override
	public void updateDashboard() {
			}
	
}
