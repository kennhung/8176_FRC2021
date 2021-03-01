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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
//import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  // VictorSP left = new VictorSP(0);
  // VictorSP right = new VictorSP(0);
  static WPI_VictorSPX LBM = new WPI_VictorSPX(12);
  static WPI_VictorSPX LFM = new WPI_VictorSPX(16);
  static WPI_VictorSPX RBM = new WPI_VictorSPX(15);
  static WPI_VictorSPX RFM = new WPI_VictorSPX(17);
  static WPI_VictorSPX Ball = new WPI_VictorSPX(18);
  static WPI_VictorSPX BallShoot = new WPI_VictorSPX(11);
  static WPI_VictorSPX BallShootReady = new WPI_VictorSPX(14);
  static SpeedControllerGroup leftDrive = new SpeedControllerGroup(LBM, LFM);
  static SpeedControllerGroup rightDrive = new SpeedControllerGroup(RBM, RFM);
  static DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);
  // PWMVictorSPX pwnspx = new PWMVictorSPX(3);
  Joystick stick = new Joystick(0);
  Boolean Ballopen = false, BallShootopen = false, BallShootRead = false;
  public double spxleftspeed, spxrightspeed2;

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

  }

  @Override
  public void robotPeriodic() {
    // spx.set(0.2);
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
  }

  PowerDistributionPanel pdp = new PowerDistributionPanel(1);

  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(-stick.getRawAxis(1) / 2, stick.getRawAxis(0) / 2);
    // drive.tankDrive((-stick.getRawAxis(1)) / 2 + (-stick.getRawAxis(0) / 2),
    // (-stick.getRawAxis(1)) / 2 + (-stick.getRawAxis(0) / 2));
    if (stick.getRawButtonPressed(1)) {
      Ballopen = !Ballopen;
    }
    if (stick.getRawButtonPressed(2)) {
      BallShootopen = !BallShootopen;
    }
    if (stick.getRawButtonPressed(3)) {
      BallShootRead = !BallShootRead;
    }
    if (Ballopen) {
      Ball.set(-0.5);
    } else {
      Ball.set(0);
    }
    if (BallShootopen) {
      BallShoot.set(-0.8);
    } else {
      BallShoot.set(0);
    }
    if (BallShootRead) {
      BallShootReady.set(0.4);
    } else {
      BallShootReady.set(0);
    }

    // BallShoot.set(stick.getRawAxis(2));
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
