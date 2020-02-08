package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem {

    Compressor compressor = new Compressor(0);
    Solenoid solenoid = new Solenoid(0);

    public static class PeriodicIO {
      public boolean solenoidDemand;
    }

    private PeriodicIO mPeriodicIO = new PeriodicIO();

    public void compressor() {

        compressor.setClosedLoopControl(true);

        boolean enabled = compressor.enabled();
        boolean pressureSwitch = compressor.getPressureSwitchValue();
        double current = compressor.getCompressorCurrent();
    }

    public void solenoid(boolean input) {

        mPeriodicIO.solenoidDemand = input;

        SmartDashboard.putBoolean("solenoid input", input);
    }

    @Override
    public void writePeriodicOutputs() {
      solenoid.set(mPeriodicIO.solenoidDemand);
    }

    @Override
    public void stop() {
        solenoid.set(false);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}
