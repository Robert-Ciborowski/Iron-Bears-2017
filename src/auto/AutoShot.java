package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import indexercommands.IndexerOn;
import shootercommands.ShooterAuto;

public class AutoShot extends CommandGroup {
    
	private double firstDistance = 31.35;
	private double secondDistance = 30.65-5;
	private double thirdDistance = -140;
	
    public  AutoShot(boolean leftRight) { //if 0 turn left, if 1 turn right
    	int angle1 = 0;
    	if(leftRight) {angle1 = 135;}
    	else {angle1 = -135;}
    	
    	int angle2 = 0;
    	if(leftRight) {angle2 = 180;}
    	else {angle2 = -180;}
    	
    	Robot.chassisSubsystem.resetGyroHeading();
    	
    	addSequential(new DriveToDistance(0.3,0,firstDistance));
    	addSequential(new RotateToAngle(angle1,2));
    	addSequential(new DriveToDistance(0.3,angle1,secondDistance));
    	addSequential(new ShootWaitCommand(2));
    	addParallel(new ShooterAuto());
    	addSequential(new IndexerOn(3));
    	addSequential(new DriveToDistance(0.5,angle1,-30));
    	addSequential(new RotateToAngle(angle2,2));
    	addSequential(new DriveToDistance(0.5,angle2,thirdDistance));
    }
}
