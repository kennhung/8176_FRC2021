package subsystem;

import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autonomous {
    
    public static int step = 0;
    public static int mode = 0;
    public static double forward_speed = 0;
    public static int distance = 0;  
    static Timer time = new Timer();
    public static void init(int a){
        mode = a;
        encoderGyro.gyro.reset();
        step=0;
        time.reset();
        time.start();
        return;
        }
    public static void autonomousPeriodic() {
        SmartDashboard.putNumber("step", step);
        System.out.println(time.get());
        switch (mode) {
        case 0:
          // 路線一
          switch (step) {
          case 0://向前
            forward_speed = -0.5;
            deliver.Deliver.set(0);
            if (encoderGyro.encoder.getDistance() <= 100&&time.get()<10) {
              encoderGyro.gyro();
            } else{
                drivebase.LF.set(0);
                drivebase.RF.set(0);    
                step++;
            }
            break;
          case 1://放球
            deliver.Deliver.set(.3);
            break;
          }
          break;
        case 1:
          //往前
          // drivebase.RF.set(0.5);
          // drivebase.LF.set(0.5);
          // if(time.set)
          switch(step){
            case 0:
              if(time.get()<4){
                drivebase.LF.set(-0.5);
                drivebase.RF.set(0.5);
                deliver.Deliver.set(0);
              }else{
                drivebase.LF.set(0);
                drivebase.RF.set(0);
                // step++;
              }
              break;
            case 1:  
              drivebase.LF.set(0);
              drivebase.RF.set(0);
              break;
          }
          break;
        // DriveBase.feedWatchDog();
      }
    
    }
}