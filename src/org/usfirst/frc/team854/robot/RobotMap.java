package org.usfirst.frc.team854.robot;

public class RobotMap {
	public static int climberPDPChannel = 10;

	public static int leftMotorPort = 1;
	public static int rightMotorPort = 0;
	public static int leftMiniCIMPort = 3;
	public static int rightMiniCIMPort = 2;
	public static int intakeMotorPort = 4;
	public static int climberMotorPort = 5;
	public static int servo0 = 8;
	public static int servo1 = 9;

	public static int indexerCANMotorPort = 1;
	public static int shooterCANMotorPort = 2;

	public static int leftEncoder1 = 0;
	public static int leftEncoder2 = 1;
	public static int rightEncoder1 = 2;
	public static int rightEncoder2 = 3;
	public static int shooterEncoder1 = 4;
	public static int shooterEncoder2 = 5;
	public static int switch0 = 8;
	public static int switch1 = 9;

	public static int gyroPort = 0;

	public static double gyroGain = -0.00165 * 3.805555 * 1.1041666 * 1.0166666666;

	public static double encoderCountsPerInch = 141.7761453778885;
	public static double leftEncoderMaxRate = 20000;
	public static double rightEncoderMaxRate = 20000;
	public static double shooterEncoderMaxRate = 10000000;

	public static boolean leftMotorInverted = true;
	public static boolean rightMotorInverted = false;
	public static boolean shooterMotorInverted = true;
	public static boolean intakeMotorInverted = true;
	public static boolean climberMotorInverted = true;
	public static boolean leftMiniCIMInverted = true;
	public static boolean rightMiniCIMInverted = false;
	public static boolean indexerMotorInverted = true;

	public static int joystickPort = 0;

	public static double joystickCenterSensitivity = 0.003;
	public static int SpeedAxis = 1;
	public static int TurnAxis = 0;
	public static int throttleAxis = 3;

	public static double shooterSpeed = 0.8;
	public static double indexerSpeed = 0.7;
	public static double intakeSpeed = 0.8;
	public static double climberSpeed = 1.0;
}
