package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;

/**
 * Ball
 */
public class Ball {
    static WPI_VictorSPX SL = new WPI_VictorSPX(6);// 左射球
    static WPI_VictorSPX SR = new WPI_VictorSPX(2);// 右射球
    static WPI_VictorSPX collect = new WPI_VictorSPX(8);// 吸球工具
    static VictorSP TF = new VictorSP(0);// 運輸球

    public static void shoot() {
        if(Robot.stick.getRawButton(1)){
            SL.set(0.5);
            SR.set(0.5);
        }else{
            SL.set(0);
            SR.set(0);
        }
    }

    public static void collect() {
        if(Robot.stick.getRawAxis(3)>0){
            collect.set(-0.5);
        }else if(Robot.stick.getRawAxis(2)>0){
            collect.set(0.5); 
        }else{
            collect.set(0); 
        }
     }
    

    public static void TF() {
        if (Robot.stick.getRawButton(6)) {
            TF.set(0.8);
          } else if (Robot.stick.getRawButton(5)) {
            TF.set(-0.8);
          } else {
            TF.set(0);
          }
    }
}