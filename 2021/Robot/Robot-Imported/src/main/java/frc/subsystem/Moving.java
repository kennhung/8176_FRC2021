package frc.subsystem;

import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

public class Moving {
    static WPI_VictorSPX LBM = new WPI_VictorSPX(12); // 左馬達1
    static WPI_VictorSPX LFM = new WPI_VictorSPX(16); // 左馬達2
    static WPI_VictorSPX RBM = new WPI_VictorSPX(15); // 右馬達1
    static WPI_VictorSPX RFM = new WPI_VictorSPX(17); // 右馬達2
    static SpeedControllerGroup leftDrive = new SpeedControllerGroup(LBM, LFM);
    static SpeedControllerGroup rightDrive = new SpeedControllerGroup(RBM, RFM);
    static DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

    //////////////////
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    //////////////////
    public static void init() {

    }

    public static void teleop() {
        // drive.arcadeDrive(-stick.getRawAxis(1) * 0.5, stick.getRawAxis(0) / 2);
        // drive.tankDrive((-stick.getRawAxis(1)) / 2 + (-stick.getRawAxis(0) / 2),
        // (-stick.getRawAxis(1)) / 2 + (-stick.getRawAxis(0) / 2));
        if (Robot.stick.getRawAxis(1) == 0 && (Robot.stick.getRawAxis(4) == 0)) {
            drive.tankDrive(-(-Robot.stick.getRawAxis(1) + Robot.stick.getRawAxis(4)) * (Robot.speeddetail * 0.5),
                    -(-Robot.stick.getRawAxis(1) + (-Robot.stick.getRawAxis(4))) * (Robot.speeddetail * 0.5));
            wait(500);
            drive.tankDrive(0, 0);
        }

        if (Robot.stick.getRawButtonPressed(1)) { // 按A可切換檔次(0,0.5,1)
            Robot.speeddetail += 1;
            Robot.speeddetail %= 3;
        }
        drive.tankDrive((-Robot.stick.getRawAxis(1) + Robot.stick.getRawAxis(4) * 0.9) * (Robot.speeddetail * 0.5),
                (-Robot.stick.getRawAxis(1) + (-Robot.stick.getRawAxis(4) * 0.9)) * (Robot.speeddetail * 0.5));
    }

}