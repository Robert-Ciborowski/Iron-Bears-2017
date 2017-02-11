package subsystems;

import java.util.ArrayList;
import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotMap;
import PID.M_Gyro;
import PID.M_PIDController;
import PID.M_PIDInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import teleopdrive.JoystickCommand;

/**
 *
 */
public class ChassisSubsystem extends M_Subsystem {
	
	private Spark leftMotor = new Spark(RobotMap.rightMotorPort);
	private Spark rightMotor = new Spark(RobotMap.leftMotorPort);
	private Encoder leftEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2);
	private Encoder rightEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2);
	M_Gyro gyro = new M_Gyro(RobotMap.gyroPort);
    
	/*
	 * Motor PID Controllers
	 */
	M_PIDInput leftPIDInput = new M_PIDInput() {
		@Override
		public double pidGet() {
			return -leftEncoder.getRate() / RobotMap.leftEncoderMaxRate;
		}
	};

	M_PIDInput rightPIDInput = new M_PIDInput() {
		@Override
		public double pidGet() {
			return rightEncoder.getRate() / RobotMap.rightEncoderMaxRate;
		}
	};

	M_PIDController leftMotorPID = new M_PIDController(1.0, 0, 0, 1.0, leftPIDInput, leftMotor);

	M_PIDController rightMotorPID = new M_PIDController(1.0, 0, 0, 1.0, rightPIDInput, rightMotor);

	ArrayList<M_PIDController> pidControllers = new ArrayList<>();

	//Motor inversions MUST be declared in the constructor!!!
    public ChassisSubsystem() {
    	leftMotor.setInverted(RobotMap.leftMotorInverted);
    	rightMotor.setInverted(RobotMap.rightMotorInverted);
    }
    
    //MIGHT NEED TO CALL THIS!
    public void init() {
    	pidControllers.add(leftMotorPID);
		pidControllers.add(rightMotorPID);
		
    	gyro.initGyro();
    	gyro.setSensitivity(RobotMap.gyroGain);
    	gyro.calibrate();
    }
    
    @Override
	public void periodic() {
		// Update all of the PIDs every loop
		for (M_PIDController pid : pidControllers) {
			pid.calculate();
		}
	}
    
    public double getEncoderDistance() {
    	return (this.leftEncoder.getDistance() - this.rightEncoder.getDistance()) / 2.0
				/ RobotMap.encoderCountsPerInch;
	}
    
    public void resetEncoders() {
		this.leftEncoder.reset();
		this.rightEncoder.reset();
	}
    
    public double getCurrentAngle() {
		return gyro.getAngle();
	}

	public double getAngleDifference(double targetAngle) {
		return gyro.getAngleDifference(targetAngle);
	}
    
    public void resetGyroHeading() {
		gyro.reset();
	}
    
    public void setMotorSpeed(double leftSpeed,double rightSpeed){
    	
    	/*//Don't think we need these dashboard updates here
    	SmartDashboard.putNumber("LeftMotorSpeed", leftSpeed);
		SmartDashboard.putNumber("RightMotorSpeed", rightSpeed);
		*/

		//If it doesn't drive, this is the likely culprit.
		leftMotorPID.setSetpoint(leftSpeed);
		rightMotorPID.setSetpoint(rightSpeed);

		if (!leftMotorPID.isEnabled()) {
			leftMotorPID.enable();
		}
		if (!rightMotorPID.isEnabled()) {
			rightMotorPID.enable();
		}
    }

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	@Override
	public void updateDashboard() {
		SmartDashboard.putNumber("Gyro angle", getCurrentAngle());
		SmartDashboard.putData("Left Encoder",leftEncoder);
		SmartDashboard.putData("Right Encoder",rightEncoder);
		SmartDashboard.putData("Gyro",gyro);
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Left PID Controller", leftMotorPID);
		SmartDashboard.putData("Right PID Controller", rightMotorPID);
	}
}

