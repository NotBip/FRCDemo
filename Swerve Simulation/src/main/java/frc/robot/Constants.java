package frc.robot;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public final class Constants {


    // Module for Each Swerve Module.
    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(3.0);
        public static final double kDriveMotorGearRatio = 1 / 6.75;
        public static final double kTurningMotorGearRatio = 1 / (150.0/7); 
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
        public static final double kPTurning = 0.3;             
    }

    // Module for Swerve Drive. 
    public static final class DriveConstants {

        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(30);

        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(30);
        
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            // front left
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            // front right
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            // back left
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            // back right
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond/2;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = //
                kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    }

    // Module for Controller Joystick
    public static final class OIConstants {

        // Joystick Values Used for Swerve Controlls
        public static final int kDriverControllerPort = 0;
        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;
        public static final double kDeadband = 0.1;

        // Xbox Controller Map
        public static final int KXboxButtonA = 1; 
        public static final int KXboxButtonB = 2;
        public static final int KXboxButtonX = 3;  
        public static final int KXboxButtonY = 4; 
        public static final int KXboxLeftBumper = 5; 
        public static final int KXboxRightBumper = 6; 
        public static final int KXboxSelectButton = 7; 
        public static final int KXboxStartButton = 8; 
        public static final int KXboxLeftTrigger = 2; 
        public static final int KXboxRightTrigger = 3; 
        
    }
}
