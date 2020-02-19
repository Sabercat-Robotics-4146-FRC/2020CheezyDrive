/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.loops.Looper;
import frc.robot.subsystems.*;
import frc.robot.Constants;

public class Robot extends TimedRobot {
	Looper mEnabledLooper = new Looper();
	Looper mDisabledLooper = new Looper();

	private final SubsystemManager mSubsystemManager = SubsystemManager.getInstance();

	private Drive mDrive;

	private Joystick mController;

	public TurretAndFlywheel mTurret;
	public Flywheel mFlywheel;
  	public Intake mIntake;
	public Pneumatics mPneumatics; 

	private boolean AButtonFlag = false;
	public boolean BButtonFlag = false;
	public boolean RBButtonFlag = false;
	public boolean XButtonFlag = false;

	public boolean intakeToggle = false;
  	public boolean pneumaticsToggle = false;
	public boolean flywheelToggle = false;
	public boolean limelightToggle = false;





	@Override
	public void robotInit() {
		mDrive = Drive.getInstance();
		mIntake = Intake.getInstance();
		mTurret = TurretAndFlywheel.getInstance();
		mFlywheel = Flywheel.getInstance();
		mPneumatics = Pneumatics.getInstance();
		
		mSubsystemManager.setSubsystems(new Subsystem[] {mDrive, mIntake, mTurret, mPneumatics});

		mController = new Joystick(Constants.kDriver1USBPort);

		mSubsystemManager.registerEnabledLoops(mEnabledLooper);
		mSubsystemManager.registerDisabledLoops(mDisabledLooper);
	}

	@Override
	public void autonomousInit() {
		mDisabledLooper.stop();
		mEnabledLooper.start();
	}

	@Override
	public void disabledInit() {
		mEnabledLooper.stop();
		mDisabledLooper.start();
	}

	@Override
	public void teleopInit() {
		mDisabledLooper.stop();
		mEnabledLooper.start();
	}

	@Override
	public void teleopPeriodic() {
		//mDrive.setCheesyishDrive(mThrottleStick.getRawAxis(1), -mTurnStick.getRawAxis(0), mTurnStick.getRawButton(1));
		mDrive.setCheesyishDrive(mController.getRawAxis(1), -mController.getRawAxis(4), mController.getRawButton(4));

		if (mController.getRawButtonPressed(3) && !XButtonFlag) {
			XButtonFlag = true;
			limelightToggle = !limelightToggle;
		}

		if(!mController.getRawButtonPressed(3)) {
			XButtonFlag = false;
		}
		
		mTurret.turretTurning(mController.getRawAxis(2) - mController.getRawAxis(3), limelightToggle);

		if (mController.getRawButtonPressed(1) && !AButtonFlag) {
			AButtonFlag = true;
			intakeToggle = !intakeToggle;
		}

		if(!mController.getRawButtonPressed(1)) {
			AButtonFlag = false;
		}

		mIntake.intakeToggle(intakeToggle);

		if (mController.getRawButtonPressed(6) && !RBButtonFlag) {
			RBButtonFlag = true;
			flywheelToggle = !flywheelToggle;
		}

		if(!mController.getRawButtonPressed(6)) {
			RBButtonFlag = false;
		}


		mTurret.flywheel(flywheelToggle);

		if (mController.getRawButtonPressed(2) && !BButtonFlag) {
			BButtonFlag = true;
			pneumaticsToggle = !pneumaticsToggle;
		}

		if(!mController.getRawButtonPressed(2)) {
			BButtonFlag = false;
		}


		mPneumatics.solenoid(pneumaticsToggle);
	}

}
