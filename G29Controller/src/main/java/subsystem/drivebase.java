package subsystem;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class drivebase {
    public static double direction,hp,chp=0;
    public static int gear,speed=1;
    public static double speedd=speed;
    public static boolean whilefor =false;
    public static VictorSP RF = new VictorSP(0);
    public static VictorSP RM = new VictorSP(1);
    public static VictorSP LF = new VictorSP(2);
    public static VictorSP LM = new VictorSP(4);
    public static SpeedControllerGroup R = new SpeedControllerGroup(RF, RM);
    public static SpeedControllerGroup L = new SpeedControllerGroup(LF, LM);
    public static DifferentialDrive drive = new DifferentialDrive(LF, RF);

    public static void teleop(){
        feedWatchDog();
        speedd=speed;
        if(Robot.stick.getRawButton(15)){
            gear=1;
        }else if(Robot.stick.getRawButton(16)){
            gear=2;
        }else if(Robot.stick.getRawButton(14)){
            gear=4;
        }else{
        gear=3;
        }//1=forward,2=backward,3=N,4=原地轉



        if(Robot.stick.getRawButtonPressed(5)){
            speed++;
        }else if(Robot.stick.getRawButtonPressed(6)){
            speed--;
        }else{

        }//1=35%,2=50%,3=75%,4=100%
    
        if(Robot.stick.getRawButtonPressed(4)){
            whilefor=!whilefor;
        }else{}

        if(whilefor){
            if(speed>4){
                speed=1;
            }else if(speed<1){
                speed=4;
            }else{}
        }else{
            if(speed>4){
                speed=4;
            }else if(speed<1){
                speed=1;
            }else{}
        }
    

        switch(speed){
            case 1:
                hp=((-Robot.stick.getRawAxis(1)+1)*.5)*.35;
                break;
            case 2:
                hp=((-Robot.stick.getRawAxis(1)+1)*.5)*.5;
                break;
            case 3:
                hp=((-Robot.stick.getRawAxis(1)+1)*.5)*.75;
                break;
            case 4:
                hp=((-Robot.stick.getRawAxis(1)+1)*.5);
                break;
            default:
                hp=0;
        }
        // hp=(-stick.getRawAxis(1)+1)*.5;
    
    
        switch(gear){
            case 1:
                if(Math.abs(Robot.stick.getRawAxis(0))>hp){
                    if(Robot.stick.getRawAxis(0)>0){
                        direction=hp; 
                    }else{
                        direction=-hp;
                    }
                }else{
                    direction=Robot.stick.getRawAxis(0);
                }
                if(Robot.stick.getRawAxis(0)>.1){
                    R.set(-(hp-direction));
                    L.set(hp);
                }else if(Robot.stick.getRawAxis(0)<-.1){
                    R.set(-hp);
                    L.set(hp+direction);  
                }else{
                    R.set(-hp);
                    L.set(hp);
                }
                break;
            case 2:
                if(Math.abs(Robot.stick.getRawAxis(0))>hp){
                    if(Robot.stick.getRawAxis(0)>0){
                        direction=hp; 
                    }else{
                        direction=-hp;
                    }
                }else{
                    direction=Robot.stick.getRawAxis(0);
                }
                if(Robot.stick.getRawAxis(0)>.1){
                    L.set(-hp);
                    R.set(hp-direction);  
                }else if(Robot.stick.getRawAxis(0)<-.1){
                    L.set(-hp-direction);
                    R.set(hp);
                }else{
                    R.set(hp);
                    L.set(-hp);
                }
                break;
            case 3:
                R.set(0);
                L.set(0);
                break;
            case 4:
                if(Robot.stick.getRawAxis(0)>.1){
                    R.set(hp);
                    L.set(hp);
                }else if(Robot.stick.getRawAxis(0)<-.1){
                    R.set(-hp);
                    L.set(-hp);
                }else{
                    R.set(0);
                    L.set(0);
                }
                break;
            default:
                R.set(0);
                L.set(0);
        }
        SmartDashboard.putNumber("Speed", speedd);
        SmartDashboard.putNumber("Throttle", Math.abs(hp)*100);    
    }
    public static void feedWatchDog() {
        drive.feedWatchdog();
    }
}