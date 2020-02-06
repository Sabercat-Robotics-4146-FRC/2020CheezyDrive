package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends Subsystem {
    TalonSRX intake = new TalonSRX(6);
    TalonSRX armPivot = new TalonSRX(7);
    DigitalInput bottomLimitSwitch = new DigitalInput(3);
    DigitalInput topLimitSwitch = new DigitalInput(4);

    
    public void intake (boolean input) {
        if (input) {
            intake.set(ControlMode.PercentOutput, 1);

            if (!bottomLimitSwitch.get()) {
                armPivot.set(ControlMode.PercentOutput, 0);
            }
            else {
                armPivot.set(ControlMode.PercentOutput, .5);
            }
        }
        else {
            intake.set(ControlMode.PercentOutput, 0);


            if (!topLimitSwitch.get()) {
                armPivot.set(ControlMode.PercentOutput, 0);
            }
            else {
                armPivot.set(ControlMode.PercentOutput, -1);
            }
        }
    }
}