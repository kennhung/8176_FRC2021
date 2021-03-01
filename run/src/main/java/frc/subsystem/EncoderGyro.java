package frc.subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.LinearFilter;
// import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.VictorSP;
// import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
/**
 * encoder
 */
public class EncoderGyro {
    public static Encoder encoder = new Encoder(0, 1);
    public static Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    public static double heading;
    public static LinearFilter filter = LinearFilter.singlePoleIIR(0.1, 0.02);
    public static double speed = 0;
    static Timer timer = new Timer();
    public static PIDController pid = new PIDController(0.009, 0.008, .0005);

    public static void robotInit() {
        encoder.setDistancePerPulse(18.84 / 360.0);
        gyro.calibrate();
    
    }
    public static void autonomousInit() {
        encoder.reset();
        gyro.reset();
        timer.reset();
        timer.start();
        //heading = 135;
        SmartDashboard.putNumber("heading", 0);
        SmartDashboard.putNumber("speed", 0);
        
        filter = LinearFilter.movingAverage(25);
        // filter.movingAverage(5);
    
    
      }
      // public void autonomousPeriodic() {
    
      //   // heading = SmartDashboard.getNumber("heading", heading);
        
      //   // speed = SmartDashboard.getNumber("speed", speed);
      //   SmartDashboard.putNumber("Gyroscopes", gyro.getAngle());
      //   SmartDashboard.putNumber("Timer", timer.get());
      
      //   pid.setSetpoint(heading);
    
      //   double angle = gyro.getAngle();
      //   SmartDashboard.putNumber("angle", angle);
      //   double fillterAngle = filter.calculate(angle);
    
      //   double calculateValue = pid.calculate(angle);
    
      //   SmartDashboard.putNumber("error", pid.getPositionError());
      //   SmartDashboard.putNumber("calculateValue", calculateValue);
        
      //   SmartDashboard.putNumber("fillterAngle", fillterAngle);
      //   DriveBase.drive.tankDrive((.0 - calculateValue)*speed, (.0 +calculateValue)*speed);
      //   SmartDashboard.putNumber("encoder", encoder.getDistance());
      // }
      public static void encoder(){
        if(encoder.getDistance()<=Robot.distance){

        }
      }
      
      
      public static void gyro(){
        SmartDashboard.putNumber("Gyroscopes", gyro.getAngle());
        SmartDashboard.putNumber("Timer", timer.get());
      
        pid.setSetpoint(heading);
    
        double angle = gyro.getAngle();
        SmartDashboard.putNumber("angle", angle);
        double fillterAngle = filter.calculate(angle);
    
        double calculateValue = pid.calculate(angle);
    
        SmartDashboard.putNumber("error", pid.getPositionError());
        SmartDashboard.putNumber("calculateValue", calculateValue);
        
        SmartDashboard.putNumber("fillterAngle", fillterAngle);
        DriveBase.drive.tankDrive((Robot.forward_speed - calculateValue)*speed, (Robot.forward_speed +calculateValue)*speed);
        SmartDashboard.putNumber("encoder", encoder.getDistance());
      
      }
} 