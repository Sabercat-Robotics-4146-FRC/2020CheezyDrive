package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//class for controlling the turret

public class TurretAndFlywheel extends Subsystem {
    private static TurretAndFlywheel mInstance;

    public static TurretAndFlywheel getInstance() {
        if (mInstance == null) {
            mInstance = new TurretAndFlywheel();
        }

        return mInstance;
    }

    private final TalonSRX flywheel;
    private final TalonSRX turret;
    private final DigitalInput leftLimitSwitch;
    private final DigitalInput rightLimitSwitch;
    private LimelightManager mLLManager;

    private TurretAndFlywheel() {
        flywheel = new TalonSRX(5);
        turret = new TalonSRX(8);
        leftLimitSwitch = new DigitalInput(2);
        rightLimitSwitch = new DigitalInput(1);
        mLLManager = LimelightManager.getInstance();
    }

    double output;
    

    

    public static class PeriodicIO {
      public double turretDemand;
      public double flywheelDemand;
    }

    private PeriodicIO mPeriodicIO = new PeriodicIO();

   public void turretTurning (double manualInput, Boolean buttonInput) {
       double input;
        
        if (buttonInput) {
            
            mLLManager.setLeds(Limelight.LedMode.ON);

            if (mLLManager.SeesTarget()) {
                if (mLLManager.getXOffset() > 1) {
                    input = -.1;
                } else if (mLLManager.getXOffset() < -1) {
                    input = .1;
                } else {
                    input = 0;
                }
            } else {
                input = manualInput;
            }

        } else {
            input = manualInput;
            mLLManager.setLeds(Limelight.LedMode.ON);
        }
    
        if (leftLimitSwitch.get()) { // If the forward limit switch is pressed, we want to keep the values between -1 and 0
            output = Math.min(input, 0);
         }
        else if(rightLimitSwitch.get()) { // If the reversed limit switch is pressed, we want to keep the values between 0 and 1
            output = Math.max(input, 0);
        }
        else{
            output = input;
        }
        
        mPeriodicIO.turretDemand = output;

        SmartDashboard.putBoolean("limelight toggle", buttonInput);
        SmartDashboard.putNumber("input", input);
        SmartDashboard.putNumber("output", output);
    }

   public void flywheel (boolean input) {
        if (input) {
            mPeriodicIO.flywheelDemand = -1;
        }
        else {
            mPeriodicIO.flywheelDemand = 0;
        }
    }

    @Override
    public void readPeriodicInputs() {
        mLLManager.readPeriodicInputs();
    }
    
    @Override
    public void writePeriodicOutputs() {
      flywheel.set(ControlMode.PercentOutput, mPeriodicIO.flywheelDemand);
      turret.set(ControlMode.PercentOutput, mPeriodicIO.turretDemand);
        SmartDashboard.putBoolean("right limit switch", rightLimitSwitch.get());
        SmartDashboard.putBoolean("left limit switch", leftLimitSwitch.get());
        SmartDashboard.putBoolean("limelight sees target", mLLManager.SeesTarget());
        SmartDashboard.putNumber("xOffset", mLLManager.getXOffset());
        mLLManager.writePeriodicOutputs();
    }

    @Override
    public void stop() {
        flywheel.set(ControlMode.PercentOutput, 0.0);
        turret.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}
    
}
