package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem {
    private static Pneumatics mInstance;

    public static Pneumatics getInstance() {
        if (mInstance == null) {
            mInstance = new Pneumatics();
        }

        return mInstance;
    }

    private final Compressor compressor;
    private final Solenoid solenoid;

    private Pneumatics() {
        compressor = new Compressor(0);
        solenoid = new Solenoid(0);
    }

    public static class PeriodicIO {
      public boolean solenoidDemand;
    }

    private PeriodicIO mPeriodicIO = new PeriodicIO();

    public void compressor() {


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
        compressor.setClosedLoopControl(true);
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
