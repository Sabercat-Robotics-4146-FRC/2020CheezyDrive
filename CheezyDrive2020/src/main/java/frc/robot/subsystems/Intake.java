package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends Subsystem {
    TalonSRX intake = new TalonSRX(6);
    TalonSRX armPivot = new TalonSRX(7);
    DigitalInput bottomLimitSwitch = new DigitalInput(3);
    DigitalInput topLimitSwitch = new DigitalInput(4);

public static class PeriodicIO {
  public double intakeDemand;
  public double armPivotDemand;
}

private PeriodicIO mPeriodicIO = new PeriodicIO();


    public void intake (boolean input) {
        if (input) {
            mPeriodicIO.intakeDemand = 1;

            if (!bottomLimitSwitch.get()) {
                mPeriodicIO.armPivotDemand = 0;
            }
            else {
                mPeriodicIO.armPivotDemand = .5;
            }
        }
        else {
            mPeriodicIO.intakeDemand = 0;


            if (!topLimitSwitch.get()) {
                mPeriodicIO.armPivotDemand = 0;
            }
            else {
                mPeriodicIO.armPivotDemand = -1;
            }
        }
    }

    @Override
    public void writePeriodicOutputs() {
      intake.set(ControlMode.PercentOutput, mPeriodicIO.intakeDemand);
      armPivot.set(ControlMode.PercentOutput, mPeriodicIO.armPivotDemand);
    }

    @Override
    public void stop() {
        intake.set(ControlMode.PercentOutput, 0.0);
        armPivot.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}
