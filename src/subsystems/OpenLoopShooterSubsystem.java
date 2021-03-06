package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotMap;
import shootercommands.ShooterOff;
import com.ctre.CANTalon;

public class OpenLoopShooterSubsystem extends M_Subsystem{
	
	private CANTalon shooterMotor = new CANTalon(RobotMap.shooterCANMotorPort);

	//Motor inversions MUST be declared in the constructor!!!
    public OpenLoopShooterSubsystem() {
    	shooterMotor.setInverted(RobotMap.shooterMotorInverted);
    }
    
  //MIGHT NEED TO CALL THIS!
    public void init() {
    }
    
    @Override
	public void periodic() {
	}
    
    public void setShooterSpeed(double speed){
    	shooterMotor.set(speed);
    }
    
    public void shooterOff() {
    	setShooterSpeed(0);
    }

	public void initDefaultCommand() {
	    setDefaultCommand(new ShooterOff());
	}
	
	@Override
	public void updateDashboard() {
	}
}
