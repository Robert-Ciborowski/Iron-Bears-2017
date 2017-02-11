package subsystems;

import java.util.ArrayList;
import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotMap;
import PID.M_PIDController;
import PID.M_PIDInput;
import edu.wpi.first.wpilibj.Encoder;
import shootercommands.ShooterOff;
import com.ctre.CANTalon;

public class ShooterSubsystem extends M_Subsystem{
	
	private CANTalon shooterMotor = new CANTalon(RobotMap.shooterCANMotorPort);
	private Encoder shooterEncoder = new Encoder(RobotMap.shooterEncoder1, RobotMap.shooterEncoder2);

	M_PIDInput shooterPIDInput = new M_PIDInput() {
		@Override
		public double pidGet() {
			return shooterEncoder.getRate() / RobotMap.shooterEncoderMaxRate;
		}
	};
	
	M_PIDController shooterMotorPID = new M_PIDController(0.05, 0, 0, 1.0, shooterPIDInput, shooterMotor);

	ArrayList<M_PIDController> pidControllers = new ArrayList<>();
	
	//Motor inversions MUST be declared in the constructor!!!
    public ShooterSubsystem() {
    	shooterMotor.setInverted(RobotMap.shooterMotorInverted);
    }
    
  //MIGHT NEED TO CALL THIS!
    public void init() {
    	pidControllers.add(shooterMotorPID);
    }
    
    @Override
	public void periodic() {
		// Update all of the PIDs every loop
		for (M_PIDController pid : pidControllers) {
			pid.calculate();
		}
	}
    
    public void resetShooterEncoder() {
    	this.shooterEncoder.reset();
    }
    
    public double getEncoderRate() {
    	return shooterEncoder.getRate();
    }
    
    public void setShooterSpeed(double shooterSpeed){
    	
    	/*//Don't think we need these dashboard updates here
    	SmartDashboard.putNumber("LeftMotorSpeed", leftSpeed);
		SmartDashboard.putNumber("RightMotorSpeed", rightSpeed);
		*/

		shooterMotorPID.setSetpoint(shooterSpeed);

		if (!shooterMotorPID.isEnabled()) {
			shooterMotorPID.enable();
		}
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
