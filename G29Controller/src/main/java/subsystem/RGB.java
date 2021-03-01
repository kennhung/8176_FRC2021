package subsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
public class RGB {
    public static AddressableLED m_led;
    public static AddressableLEDBuffer m_ledBuffer;
    public static int light = 0;
    public static int m_rainbowFirstPixelHue = 0;
    public static int a = 1;
    public static int b = 20;
    static Timer timer = new Timer();
    static int switch0 = 0 ;
    
    public static void init() {
        m_led = new AddressableLED(7);
        m_ledBuffer = new AddressableLEDBuffer(200);
        for(int i=0;i<m_ledBuffer.getLength();i++) {
            m_ledBuffer.setRGB(i, 255, 255, 255);
        }
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
        timer.reset();
        timer.start();
    }

    static void rainbow() {
        
        
        // for (var i = 35; i <145; i++) {
        //     final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
        //     m_ledBuffer.setHSV(i, hue, 255, (light == i) ? 128 : 128);
        // }
        // for (var x = 0; x < 34; x++) {
        //     final var hue = (m_rainbowFirstPixelHue + x * 180 / m_ledBuffer.getLength()) % 180;
        //       for (int j = 0; j < b; j++) {
        //         m_ledBuffer.setHSV(x + (m_ledBuffer.getLength() / b) * j, hue, 255, (light == x) ? 128 : 0);
        //     }
        // }
        // for (var y = 180; y < 144; y--) {
        //     final var hue = (m_rainbowFirstPixelHue + y * 180 / m_ledBuffer.getLength()) % 180;
        //       for (int j = 0; j < b; j++) {
        //         m_ledBuffer.setHSV(y + (m_ledBuffer.getLength() / b) * j, hue, 255, (light == y) ? 128 : 0);
        //       }
        // }
        // for (var i = 0; i < m_ledBuffer.getLength() / b; i++) {
        //     final var hue = (m_rainbowFirstPixelHue + i * 180 / m_ledBuffer.getLength()) % 180;
        //       for (int j = 0; j < b; j++) {
        //         m_ledBuffer.setHSV(i + (m_ledBuffer.getLength() / b) * j, hue, 255, (light == i) ? 128 : 0);
        //     }
        // }
        
        // if(((int)timer.get())<=10){
        //     if (light >= m_ledBuffer.getLength() / b)
        //     light = 0;
        //     for (var i = 0; i < m_ledBuffer.getLength() / b; i++) {
        //         final var hue = (m_rainbowFirstPixelHue + i * 180 / m_ledBuffer.getLength()) % 180;
        //             for (int j = 0; j < b; j++){
        //                 m_ledBuffer.setHSV(i + (m_ledBuffer.getLength() / b) * j, hue, 255, (light == i) ? 128 : 0);
        //         }
        //     }
        // }else if(((int)timer.get())<=20){
        //     if (light >= m_ledBuffer.getLength() / a)
        //         light = 0;
        //     for (var i = 0; i < m_ledBuffer.getLength() / a; i++) {
        //         final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
        //         m_ledBuffer.setHSV(i, hue, 255, (light == i) ? 128 : 128);
        //     }
        // }else{
        //     timer.reset();
        // }
        // System.out.println((int)timer.get());
        if (light >= m_ledBuffer.getLength() / a)
                light = 0;
            for (var i = 0; i < m_ledBuffer.getLength() / a; i++) {
                final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
                m_ledBuffer.setHSV(i, hue, 255, (light == i) ? 128 : 128);
            }
        
        m_rainbowFirstPixelHue += 3;
        m_rainbowFirstPixelHue %= 180;
        light++;



        // if (light >= m_ledBuffer.getLength() / b)
        //     light = 0;
        //     for (var i = 0; i < m_ledBuffer.getLength() / b; i++) {
        //         final var hue = (m_rainbowFirstPixelHue + i * 180 / m_ledBuffer.getLength()) % 180;
        //             for (int j = 0; j < b; j++){
        //                 m_ledBuffer.setHSV(i + (m_ledBuffer.getLength() / b) * j, hue, 255, (light == i) ? 128 : 0);
        //         }
        //     }
    }

    public static void ARGBControl() {
        rainbow();
        m_led.setData(m_ledBuffer);
    }
    
}