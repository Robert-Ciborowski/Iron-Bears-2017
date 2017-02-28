package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import indexercommands.IndexerOn;
import shootercommands.ShooterOn;

public class AutoShot extends CommandGroup {
    
	private int firstDistance = 10;
	private int secondDistance = 10;
	private int thirdDistance = 10;
	
    public  AutoShot(boolean leftRight) { //if false turn left, if true turn right
    	int angle1 = 0;
    	if(leftRight) {angle1 = 90;}
    	else {angle1 = -90;}
    	
    	int angle2 = 0;
    	if(leftRight) {angle2 = 145;}
    	else {angle2 = -145;}
    	
    	Robot.chassisSubsystem.resetGyroHeading();
    	
    	addSequential(new DriveToDistance(0.2,0,firstDistance));
    	addSequential(new RotateToAngle(angle1,2));
    	addSequential(new DriveToDistance(0.2,angle1,secondDistance));
    	addSequential(new RotateToAngle(angle2,2));
    	addSequential(new DriveToDistance(0.2,angle2, thirdDistance));
    	addParallel(new ShooterOn());
    	addSequential(new IndexerOn());
    }
}
