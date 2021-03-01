package subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;

public class deliver {
    public static VictorSP Deliver = new VictorSP(9);
    public static void teleop(){
      if(Math.abs(Robot.stick2.getRawAxis(5))>.1){
        Deliver.set(-Robot.stick2.getRawAxis(5)*.6);
      }else if(Math.abs(Robot.stick.getRawAxis(2))>.1){
        if(Robot.stick.getRawButton(7)){
          Deliver.set(-(-Robot.stick.getRawAxis(2)+1)*.6);
        }else{
          Deliver.set((-Robot.stick.getRawAxis(2)+1)*.6);
        }
      }else{
          Deliver.set(0); 
      }
    }
    // public static void Auto(){
    //   Deliver.set(.3);
    // }
}