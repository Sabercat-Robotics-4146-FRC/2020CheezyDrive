package frc.robot;

public class Constants {
    public static final double kLooperDt = 0.01;

    // CAN
    public static final int kLongCANTimeoutMs = 100; // use for constructors
    public static final int kCANTimeoutMs = 10; // use for important on the fly updates

    // Pneumatics
    public static final int kPCMId = 0;

    // Drive
    public static final int kDriveRightMasterId = 3;
    public static final int kDriveRightSlaveId = 4;
    public static final int kDriveLeftMasterId = 1;
    public static final int kDriveLeftSlaveId = 2;

    public static final double kDriveWheelTrackWidthInches = 25.42;
    public static final double kTrackScrubFactor = 1.0469745223;


    // Contoller
    public static final int kControllerPort = 0;


    // Joysticks
    public static final int kThrottleStickPort = 0;
    public static final int kTurnStickPort = 1;


    // turret
    public static final int kFlywheelMasterId = 5;
    public static final int kTurretId = 8;


    // Intake
    public static final int kIntakeId = 6;
    public static final int kArmPivotId = 7;
    public static final int kSolenoidId = 2;
}
