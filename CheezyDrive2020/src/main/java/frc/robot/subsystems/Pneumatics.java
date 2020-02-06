package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics {
    
    Compressor compressor = new Compressor(0);
    Solenoid solenoid = new Solenoid(0);

    public void compressor() {

        compressor.setClosedLoopControl(true);

        boolean enabled = compressor.enabled();
        boolean pressureSwitch = compressor.getPressureSwitchValue();
        double current = compressor.getCompressorCurrent();
    }

    public void solenoid(boolean input) {

        solenoid.set(input);

        SmartDashboard.putBoolean("solenoid input", input);
    }
}