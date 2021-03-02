package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;

import frc.robot.Robot;
import frc.subsystem.*;

public class Ball {
    static WPI_VictorSPX CollectBall = new WPI_VictorSPX(18);// 吸球
    static WPI_VictorSPX BallShoot = new WPI_VictorSPX(11);// 射球
    static WPI_VictorSPX BallShootReady = new WPI_VictorSPX(14);// 預備射球

    ////////////////////
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    ////////////////////
    public static void init() {
    }

    public static void teleop() {
        if (Robot.stick.getRawButtonPressed(5)) {
            Robot.Ballopen = !Robot.Ballopen;
        }
        if (Robot.stick.getRawButtonPressed(6)) {
            Robot.BallShootopen = !Robot.BallShootopen;
        }
        if (Robot.stick.getRawButtonPressed(2)) {
            Robot.BallShootRead = !Robot.BallShootRead;
        }
        if (Robot.Ballopen) {
            CollectBall.set(-0.5);
        } else {
            CollectBall.set(0);
        }
        if (Robot.BallShootopen) {
            BallShoot.set(-1);
        } else {
            BallShoot.set(0);
        }
        if (Robot.BallShootRead) {
            BallShootReady.set(0.4);
        } else {
            BallShootReady.set(0);
        }
    }

}
