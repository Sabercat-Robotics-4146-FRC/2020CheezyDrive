package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//class for controlling the turret

public class TurretAndFlywheel {
    TalonSRX flywheel = new TalonSRX(5);
    TalonSRX turret = new TalonSRX(8);
    DigitalInput leftLimitSwitch = new DigitalInput(2);
    DigitalInput rightLimitSwitch = new DigitalInput(1);

    double output;

   public void turretTurning (double input) {
         if (leftLimitSwitch.get()) { // If the forward limit switch is pressed, we want to keep the values between -1 and 0
            output = Math.min(input, 0);
         }
        else if(rightLimitSwitch.get()) { // If the reversed limit switch is pressed, we want to keep the values between 0 and 1
            output = Math.max(input, 0);
        }
        else{
            output = input;
        }
        turret.set(ControlMode.PercentOutput, output);

        SmartDashboard.putBoolean("right limit switch", rightLimitSwitch.get());
        SmartDashboard.putBoolean("left limit switch", leftLimitSwitch.get());
        SmartDashboard.putNumber("input", input);
        SmartDashboard.putNumber("output", output);
    }

   public void flywheel (boolean input) {
        if (input) {
            flywheel.set(ControlMode.PercentOutput, -1);
        }
        else {
            flywheel.set(ControlMode.PercentOutput, 0);
        }
    }


}