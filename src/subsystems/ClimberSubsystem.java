package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotMap;

import climbercommands.ClimberOff;
import edu.wpi.first.wpilibj.Spark;

public class ClimberSubsystem extends M_Subsystem{
	public static final double MAX_CURRENT = 15.0;
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
		setDefaultCommand(new ClimberOff());
	}
	
	@Override
	public void updateDashboard() {
	}
	
}
