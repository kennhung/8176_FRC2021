package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.LinearFilter;
// import edu.wpi.first.wpilibj.PIDController;
// // import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.interfaces.Gyro;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * DriveBase
 */
public class DriveBase {
    static WPI_VictorSPX LBM = new WPI_VictorSPX(5);
    static WPI_VictorSPX LFM = new WPI_VictorSPX(3);
    static WPI_VictorSPX RBM = new WPI_VictorSPX(9);
    static WPI_VictorSPX RFM = new WPI_VictorSPX(0);
    static SpeedControllerGroup leftDrive = new SpeedControllerGroup(LBM, LFM);
    static SpeedControllerGroup rightDrive = new SpeedControllerGroup(RBM, RFM);
    static DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);
    // static Encoder encoder = new Encoder(0, 1);
    // static Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    // static double heading;
    // static LinearFilter filter = LinearFilter.singlePoleIIR(0.1, 0.02);
    // static double speed = 0;
    // static Timer timer = new Timer();
    // static PIDController pid = new PIDController(0.009, 0.008, 0.0005);

    public static void init() {
        // encoder.setDistancePerPulse(18.84 / 360.0);
        // gyro.calibrate();
    }

    // public static void autoinit() {
    //     encoder.reset();
    //     gyro.reset();
        
    //     //heading = 135;
    //     SmartDashboard.putNumber("heading", 0);
    //     SmartDashboard.putNumber("speed", 0);
    //     filter = LinearFilter.movingAverage(25);
    //     // filter.movingAverage(5);
    // }
    public static void teleop() {
        leftDrive.set(-Robot.stick.getRawAxis(5) / 2);
        // LFM.set(-Robot.stick.getRawAxis(5) / 2);
        rightDrive.set(Robot.stick.getRawAxis(1) / 2);
        // RFM.set(Robot.stick.getRawAxis(1) / 2);
    }
    // public static void auto(){
    //     heading = SmartDashboard.getNumber("heading", heading);
    
    // speed = SmartDashboard.getNumber("speed", speed);
    // SmartDashboard.putNumber("Gyroscopes", gyro.getAngle());
    // SmartDashboard.putNumber("Timer", timer.get());
    
    // pid.setSetpoint(heading);

    // double angle = gyro.getAngle();
    // SmartDashboard.putNumber("angle", angle);
    // double fillterAngle = filter.calculate(angle);

    // double calculateValue = pid.calculate(angle);

    // SmartDashboard.putNumber("error", pid.getPositionError());
    // SmartDashboard.putNumber("calculateValue", calculateValue);
    
    // SmartDashboard.putNumber("fillterAngle", fillterAngle);
    // //  drive.tankDrive((.0 - calculateValue)*speed, (.0 +calculateValue)*speed);
    // }
}