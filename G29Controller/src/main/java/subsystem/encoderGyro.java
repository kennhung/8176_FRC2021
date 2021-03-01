package subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoderGyro {
    public static Encoder encoder = new Encoder(2, 3);
    public static Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    public static double heading;
    public static LinearFilter filter = LinearFilter.singlePoleIIR(0.1, 0.02);
    public static double speed = 0;
    public static Timer Encodertimer = new Timer();
    public static PIDController pid = new PIDController(0.009, 0.008, .0005);

    public static void robotInit() {
        encoder.setDistancePerPulse(18.84 / 360.0);
        gyro.calibrate();
    }

    public static void autonomousInit(Double a,Double b) {
        encoder.reset();
        gyro.reset();
        Encodertimer.reset();
        Encodertimer.start();
        // heading = 135;
        SmartDashboard.putNumber("heading", 0);
        SmartDashboard.putNumber("speed", 0);
        filter = LinearFilter.movingAverage(25);
        // filter.movingAverage(5);
        a = heading;
        b = speed;
        return;
    }

      public static void gyro() {
        SmartDashboard.putNumber("Gyroscopes", gyro.getAngle());
        pid.setSetpoint(heading);
        double angle = gyro.getAngle();
        SmartDashboard.putNumber("angle", angle);
        double fillterAngle = filter.calculate(angle);
        double calculateValue = pid.calculate(angle);
        SmartDashboard.putNumber("error", pid.getPositionError());
        SmartDashboard.putNumber("calculateValue", calculateValue);
        SmartDashboard.putNumber("fillterAngle", fillterAngle);
        drivebase.drive.tankDrive((autonomous.forward_speed + calculateValue), (autonomous.forward_speed - calculateValue));
        SmartDashboard.putNumber("encoder", encoder.getDistance());
    }
}