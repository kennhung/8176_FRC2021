/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.can.*;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
//import com.kauailabs.navx.frc.AHRS;

import frc.subsystem.*;

public class Robot extends TimedRobot {

  public static Joystick stick = new Joystick(0);
  public static Boolean Ballopen = false, BallShootopen = false, BallShootRead = false;
  public static double spxleftspeed = 0, spxrightspeed2 = 0;
  public static int speeddetail = 0;

  // AHRS ahrs = new AHRS(SerialPort.Port.kMXP);

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void robotInit() {
    Moving.init();
    Ball.init();
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    Ballopen = false;
    BallShootopen = false;
    BallShootRead = false;
    spxleftspeed = 0;
    spxrightspeed2 = 0;
    speeddetail = 0;
  }

  PowerDistributionPanel pdp = new PowerDistributionPanel(1);

  @Override
  public void teleopPeriodic() {
    Moving.teleop();
    Ball.teleop();
    SmartDashboard.putNumber("pdp amp", pdp.getCurrent(3));
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
  }
}
