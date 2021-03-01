package subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;

public class collect {
    public static VictorSP Collect = new VictorSP(8);
    public static void teleop(){
      if(Math.abs(Robot.stick2.getRawAxis(1))>.1){
        Collect.set(Robot.stick2.getRawAxis(1)*.25);
      }else if(Math.abs(Robot.stick.getRawAxis(3))>.1){
        if(Robot.stick.getRawButton(8)){
          Collect.set((-Robot.stick.getRawAxis(3)+1)*.25);
        }else{
          Collect.set(-(-Robot.stick.getRawAxis(3)+1)*.25);
        }
      }else{
          Collect.set(0);
      }
    }
}