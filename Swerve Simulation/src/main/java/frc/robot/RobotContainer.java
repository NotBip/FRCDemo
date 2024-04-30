package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.Intake.IntakeNote;
import frc.robot.commands.Intake.IntakeStop;
import frc.robot.commands.Intake.ShootNote;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveSim;

public class RobotContainer {
        
        // Initializing Auto Choooser
        SendableChooser<Command> autoChooser = new SendableChooser<>(); 

        // Initializing Robot's Subsystems
        private final SwerveSim swerveSubsystem = new SwerveSim();
        private final Intake intake = new Intake(); 

        // Initializing Intake Commands
        private final IntakeNote intakeNote = new IntakeNote(intake); 
        private final ShootNote shootNote = new ShootNote(intake); 
        private final IntakeStop intakeStop = new IntakeStop(intake);

        // Initializing Controllers and Joysticks
        private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
        
        // Game Controllers
        public JoystickButton drBtnA, drBtnB, drBtnX, drBtnY, drBtnLB, drBtnRB, drBtnStrt, drBtnSelect;
        
        //Get X and Y axis from the joystick to control the robot
        public RobotContainer() {
                configureNamedCommads();

                autoChooser.setDefaultOption("Do Nothing", null);
                autoChooser.addOption("Amp + Mid", new PathPlannerAuto("Amp + Mid"));
                autoChooser.addOption("1Note", new PathPlannerAuto("1Note"));
                autoChooser.addOption("DriveStraight", new PathPlannerAuto("DriveStraight"));
                Shuffleboard.getTab("Autonomous").add("Select Auto", autoChooser).withSize(2, 1);


                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                        swerveSubsystem,
                        () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
                        () -> -driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
                        () -> -driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
                        () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));
                intake.setDefaultCommand(intakeStop);
                
                // Xbox Driver Controller Buttons
                drBtnA = new JoystickButton(driverJoystick, OIConstants.KXboxButtonA);
                drBtnB = new JoystickButton(driverJoystick, OIConstants.KXboxButtonB);
                drBtnX = new JoystickButton(driverJoystick, OIConstants.KXboxButtonX);
                drBtnY = new JoystickButton(driverJoystick, OIConstants.KXboxButtonY);
                drBtnLB = new JoystickButton(driverJoystick, OIConstants.KXboxLeftBumper);
                drBtnRB = new JoystickButton(driverJoystick, OIConstants.KXboxRightBumper);
                drBtnSelect = new JoystickButton(driverJoystick, OIConstants.KXboxSelectButton);
                drBtnStrt = new JoystickButton(driverJoystick, OIConstants.KXboxStartButton);

                configureButtonBindings(); 
        }

        
        private void configureButtonBindings() {
                drBtnLB.whileTrue(intakeNote); 
                drBtnRB.whileTrue(shootNote); 
        }
        
        private void configureNamedCommads() { 
                NamedCommands.registerCommand("ShootIntake", shootNote);
        }
  



        public Command getAutonomousCommand() {
                return autoChooser.getSelected();
        }
}