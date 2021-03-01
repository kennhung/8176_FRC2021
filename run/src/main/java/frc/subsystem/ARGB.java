package frc.subsystem;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Robot;

/**
 * ARGB
 */
public class ARGB {
   static Joystick stick = new Joystick(1);
    static AddressableLED m_led;
    static AddressableLEDBuffer m_ledBuffer;
    //double z = 1;
    static int light = 0;
    static int m_rainbowFirstPixelHue = 0;
    static int a = 1;
    static int mode = 2;
    Timer timer = new Timer();

    public static void init() {
        m_led = new AddressableLED(9);
        m_ledBuffer = new AddressableLEDBuffer(100);
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    private static void rainbow() {

        SmartDashboard.putNumber("mode2 Number", a);
        SmartDashboard.putNumber("mode", mode);
        // mode_dou = SmartDashboard.getNumber("mode", mode_dou);
        // String str = String.substring(0, string.indexOf(".")) +
        // String.substring(string.indexOf(".") + 1);

        // z = SmartDashboard.getNumber("mode2 Number", z);
        // String numb_led = Double.toString(z);
        // a = Integer.parseInt(numb_led);

        if (light >= m_ledBuffer.getLength() / a)
            light = 0;

        if (mode == 1) {
            for (var i = 0; i < m_ledBuffer.getLength() / a; i++) {
                final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
                m_ledBuffer.setHSV(i, hue, 255, (light == i) ? 128 : 128);
            }
        } else if (mode == 2) {
            for (var i = 0; i < m_ledBuffer.getLength() / a; i++) {
                final var hue = (m_rainbowFirstPixelHue + i * 180 / m_ledBuffer.getLength()) % 180;
                for (int j = 0; j < a; j++) {
                    m_ledBuffer.setHSV(i + (m_ledBuffer.getLength() / a) * j, hue, 255, (light == i) ? 128 : 0);
                }
            }
        }
        // switch(mode_int){
        // case 1:
        // for (var i = 0; i < m_ledBuffer.getLength() / a; i++) {
        // final var hue = (m_rainbowFirstPixelHue + (i * 180 /
        // m_ledBuffer.getLength())) % 180;

        // m_ledBuffer.setHSV(i, hue, 255, (light == i) ? 128 : 128);

        // for (int j = 0; j < a; j++) {
        // m_ledBuffer.setHSV(i + (m_ledBuffer.getLength() / a) * j, hue, 255, (light ==
        // i) ? 128 : 0);
        // }

        //

        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        light++;
    }

    public static void ARGBControl() {
        // long x = 1;
        rainbow();
        m_led.setData(m_ledBuffer);
        if (stick.getRawButton(5)) {
            mode++;
            // TimeUnit.MILLISECONDS.sleep(10);
            // Thread.sleep(10);
            Timer.delay(0.5);
        } else if (stick.getRawButton(6)) {
            mode--;
            Timer.delay(0.5);
        } else {

        }
        if (stick.getPOV() == 0) {
            a++;
      Timer.delay(0.5);
    }else if(stick.getPOV()== 180){
      a--;
      Timer.delay(0.5);
    }
    if(mode==1){
      a = 1;
    }
    if(a>=51){
      a=1;
    }else if(a<=0){
      a= 50;
    }
    
      }
}