/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.subsystem.ARGB;
import frc.subsystem.Ball;
// import frc.subsystem.Camera;
// import frc.subsystem.ColorSensor;
import frc.subsystem.DriveBase;
import frc.subsystem.EncoderGyro;
// import frc.subsystem.EncoderGyro;;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  public static Joystick stick = new Joystick(0);  
  WPI_VictorSPX UL = new WPI_VictorSPX(1);// 左滑軌
  WPI_VictorSPX UR = new WPI_VictorSPX(10);//右滑軌
  public static double forward_speed = 0;
  public static int distance = 0;
  WPI_VictorSPX ULL = new WPI_VictorSPX(7);//上左鉤
  WPI_VictorSPX ULR = new WPI_VictorSPX(4);//上右鉤
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  @Override
  public void robotInit() {
    DriveBase.init();
    // ColorSensor.init();
    // ARGB.init();
    // Camera.CameraInit();
  }

  int step = 0;
  int mode = 0;

  @Override
  public void autonomousInit() {
    step = 0;
    EncoderGyro.heading = 0;
    EncoderGyro.speed = 0;
    
  }

  @Override
  public void autonomousPeriodic() {
    step = 0;
    switch(mode){
      case 0:
      //路線一
        switch (step) {
          case 0:
            forward_speed = 1;
            if(EncoderGyro.encoder.getDistance() <= 60){
              EncoderGyro.gyro();
            }else step++;
            break;
          case 1: 
            Ball.shoot();
            Ball.TF();
            break;
          }
        break;
      case 1:
      //路線二
        switch (step){
          case 0:
            forward_speed = 0;
            EncoderGyro.heading = 48.47;
            EncoderGyro.gyro();
            step++;
            break;
          case 1:
            forward_speed = 1;
            if(EncoderGyro.encoder.getDistance() <= 90.5){
              EncoderGyro.gyro();
            }else step++;
            break;
          case 2:
            forward_speed = 0;
            EncoderGyro.heading = 0;
            EncoderGyro.gyro();
            step++;
            break;
          case 3:
            Ball.shoot();
            Ball.TF();
            break;
        }
        break;
      case 2:
      //路線三
        switch (step){
          case 0:
            forward_speed = 0;
            EncoderGyro.heading = 51.71;
            EncoderGyro.gyro();
            step++;
            break;
          case 1:
            forward_speed = 1;
            if(EncoderGyro.encoder.getDistance() <= 96.83){
              EncoderGyro.gyro();
            }else step++;
            break;
          case 2:
            forward_speed = 0;
            EncoderGyro.heading = 0;
            EncoderGyro.gyro();
            step++;
            break;
          case 3:
            Ball.shoot();
            Ball.TF();
            break;
        }
        break;
      }
    
    
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("encoder value", EncoderGyro.encoder.getDistance());
    DriveBase.teleop();
    Ball.shoot();
    Ball.collect();
    Ball.TF();    
    if(stick.getRawButton(3)){
      ULL.set(-0.5);
      ULR.set(-0.5);
    }else if(stick.getRawButton(1)){
      ULL.set(0.5);
      ULR.set(0.5);
    }else{
      ULL.set(0);
      ULR.set(0);
    }
    // ColorSensor.Color();
    // Camera.CameraOn();
    // ARGB.ARGBControl();

    if (stick.getRawButton(4)) {
      UR.set(VictorSPXControlMode.PercentOutput, 0.5);
      UL.set(VictorSPXControlMode.PercentOutput, -0.5);
    } else if (stick.getRawButton(2)) {
      UR.set(VictorSPXControlMode.PercentOutput, -0.5);
      UL.set(VictorSPXControlMode.PercentOutput, 0.5);
    } else {
      UR.set(VictorSPXControlMode.PercentOutput, 0);
      UL.set(VictorSPXControlMode.PercentOutput, 0);
      
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
