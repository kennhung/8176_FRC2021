package frc.subsystem;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * ColorSensor
 */
public class ColorSensor {
    private final static I2C.Port i2cPort = I2C.Port.kOnboard;
    private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final static ColorMatch m_colorMatcher = new ColorMatch();
    private final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final static Color kYellowTarget = ColorMatch.makeColor(0.315430, 0.167725, 0.516846);

    public static void init() {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    public static void Color() {
        Color detectedColor = m_colorSensor.getColor();
        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
        if (match.color == kBlueTarget) {
          colorString = "Blue";
        } else if (match.color == kRedTarget) {
          colorString = "Red";
        } else if (match.color == kGreenTarget) {
          colorString = "Green";
        } else if (match.color == kYellowTarget) {
          colorString = "Yellow";
        } else {
          colorString = "Unknown";
        }
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }
    
}