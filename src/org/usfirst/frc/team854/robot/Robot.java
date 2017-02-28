
package org.usfirst.frc.team854.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import OI.OI;
import auto.AutoShot;
import auto.AutoSwitch;
import auto.GoStraightPID;
import auto.MiddleGearPlace;
import auto.RotateToAngle;
import subsystems.ChassisSubsystem;
import subsystems.ClimberSubsystem;
import subsystems.IndexerSubsystem;
import subsystems.IntakeSubsystem;
import subsystems.OpenLoopShooterSubsystem;
import subsystems.ServoSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final OpenLoopShooterSubsystem shooterSubsystem = new OpenLoopShooterSubsystem();
	public static final IndexerSubsystem indexerSubsystem = new IndexerSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	public static final ServoSubsystem servoSubsystem = new ServoSubsystem(RobotMap.servo0, RobotMap.servo1);
	public static OI oi;
	public static PowerDistributionPanel pdp;

	public static List<M_Subsystem> subsystemList = new ArrayList<M_Subsystem>();

	private AutoSwitch autoSwitch;
	
	Command autonomousCommand;

	public void robotInit() {
		pdp = new PowerDistributionPanel();
		oi = new OI();

		autoSwitch = new AutoSwitch(RobotMap.switch0, RobotMap.switch1);
		
		// Add all the subsystems to the subsystem list.
		subsystemList.add(chassisSubsystem);
		subsystemList.add(shooterSubsystem);
		subsystemList.add(indexerSubsystem);
		subsystemList.add(intakeSubsystem);
		subsystemList.add(climberSubsystem);

		for (M_Subsystem s : subsystemList) {
			s.init();
		}

		updateDashboard();
	}

	public void disabledInit() {
		updateDashboard();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

	public void autonomousInit() {
		autoSwitch.update();
		
		switch (autoSwitch.getState()) {
			case 0:
				System.out.println("Mode 0");
				break;
			case 1:
				System.out.println("Mode 1");
				break;
			case 2:
				System.out.println("Mode 2");
				break;
			case 3:
				System.out.println("Mode 3");
				break;
			default:
				System.out.println("How did you even get here?!");
				break;
		}
		
		Robot.chassisSubsystem.resetGyroHeading();
		autonomousCommand = new AutoShot(false);

		Scheduler.getInstance().add(autonomousCommand);

		updateDashboard();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		updateDashboard();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void subsystemPeriodic() {
		// update all subsystem runtime data.
		for (M_Subsystem r : subsystemList) {
			r.periodic();
		}
		oi.periodic();

		// COMMAND PID UPDATES!!!
		GoStraightPID.periodic();
	}

	private void updateDashboard() {
		// Need to work on updateDashboard!!!

		// update all subsystems and the OI dashboard items.
		for (M_Subsystem r : subsystemList) {
			r.updateDashboard();
		}
		oi.updateDashboard();

		GoStraightPID.updateDashboard();
		SmartDashboard.putNumber("Joystick speed value", Robot.oi.getSpeed());
		SmartDashboard.putNumber("Joystick turn value", Robot.oi.getTurn());
	}
}
