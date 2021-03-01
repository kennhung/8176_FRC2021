package frc.subsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Camera
 */
public class Camera {
    static UsbCamera camera;
    public static void CameraInit() {
        camera = CameraServer.getInstance().startAutomaticCapture();
      }
    public static void CameraOn(){
        SmartDashboard.putNumber("FPS", camera.getActualFPS());
    }
}