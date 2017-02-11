package climbercommands;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import subsystems.ClimberSubsystem;

/**
 *
 */
public class ClimberUp extends Command {

    public ClimberUp() {
        requires(Robot.climberSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climberSubsystem.setClimberSpeed(RobotMap.climberSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.pdp.getCurrent(RobotMap.climberPDPChannel) >= ClimberSubsystem.MAX_CURRENT) {
    		return true;
    	}
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
