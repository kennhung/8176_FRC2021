/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import subsystem.RGB;
import subsystem.autonomous;
import subsystem.collect;
import subsystem.deliver;
import subsystem.drivebase;
import subsystem.encoderGyro;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static Joystick stick = new Joystick(0);
  public static Joystick stick2 = new Joystick(1);
  
  static UsbCamera camera;

  @Override
  public void robotInit() {
    camera = CameraServer.getInstance().startAutomaticCapture();
    encoderGyro.robotInit();
    RGB.init(); 
  }

  @Override
  public void robotPeriodic() {
    RGB.ARGBControl();
  }

  @Override
  public void autonomousInit() {
    autonomous.init(1);
  }

  @Override
  public void autonomousPeriodic() {
    autonomous.autonomousPeriodic();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    drivebase.teleop();
    collect.teleop();
    deliver.teleop();
    // encoderGyro.gyro();  
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    drivebase.teleop();
  }

}
