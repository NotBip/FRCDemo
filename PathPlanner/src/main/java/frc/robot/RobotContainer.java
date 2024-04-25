package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Climber.ClimberDown;
import frc.robot.commands.Climber.ClimberManualPosition;
import frc.robot.commands.Climber.ClimberStop;
import frc.robot.commands.Climber.ClimberUp;
import frc.robot.commands.Climber.zeroClimber;
import frc.robot.commands.Intake.IntakeSpinBack;
import frc.robot.commands.Intake.IntakeSpinForward;
import frc.robot.commands.Intake.IntakeStop;
import frc.robot.commands.Intake.LeftIntakeJoystick;
import frc.robot.commands.Intake.RightIntakeJoystick;
import frc.robot.commands.Pneumatics.FullDetract;
import frc.robot.commands.Pneumatics.FullExtend;
import frc.robot.commands.Swerve.NudgeBack;
import frc.robot.commands.Swerve.NudgeFront;
import frc.robot.commands.Swerve.NudgeLeft;
import frc.robot.commands.Swerve.NudgeRight;
import frc.robot.commands.Swerve.SwerveJoystickCmd;
import frc.robot.commands.Swerve.ZeroGyro;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

        // Field Generator
        Field2d field = new Field2d(); 

        SendableChooser<Command> autoChooser = new SendableChooser<>(); 

        // Initializing Robot's Subsystems
        public final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
        private final Intake intake = new Intake();
        private final Climber climber = new Climber(); 
        private final Pneumatics pneumatics = new Pneumatics(); 

        // Initializing Controllers and Joysticks
        private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
        private final XboxController driverController = new XboxController(OIConstants.kDriverControllerPort); 
        private final Joystick operatorJoystick = new Joystick(OIConstants.kOperatorControllerPort); 
        private final XboxController operatorController = new XboxController(OIConstants.kOperatorControllerPort); 

        // Initializing Commands
        // Intake
        private final IntakeSpinBack intakeSpinBack = new IntakeSpinBack(intake); 
        private final IntakeStop intakeStop = new IntakeStop(intake);
        private final IntakeSpinForward intakeSpinForward = new IntakeSpinForward(intake);
        
        // Climber
        private final ClimberDown climberDown = new ClimberDown(climber); 
        private final ClimberUp climberUp = new ClimberUp(climber); 
        private final ClimberStop climberStop = new ClimberStop(climber); 
        private final zeroClimber zClimber = new zeroClimber(climber); 

        // Pneumatics
        private final FullExtend fullExtend = new FullExtend(pneumatics); 
        private final FullDetract fullDetract = new FullDetract(pneumatics); 

        // Swerve
        private final ZeroGyro zeroGyro = new ZeroGyro(swerveSubsystem); 
        private final NudgeLeft nudgeLeft = new NudgeLeft(swerveSubsystem);
        private final NudgeRight nudgeRight = new NudgeRight(swerveSubsystem); 
        private final NudgeFront nudgeFront = new NudgeFront(swerveSubsystem); 
        private final NudgeBack nudgeBack = new NudgeBack(swerveSubsystem); 

        // Game Controllers
        public JoystickButton drBtnA, drBtnB, drBtnX, drBtnY, drBtnLB, drBtnRB, drBtnStrt, drBtnSelect;
        public JoystickButton opBtnA, opBtnB, opBtnX, opBtnY, opBtnLB, opBtnRB, opBtnStrt, opBtnSelect; 
        
        public RobotContainer() {
                configureNamedCommands();

                autoChooser.setDefaultOption("Do Nothing", null);
                autoChooser.addOption("Amp + Mid", new PathPlannerAuto("Amp + Mid"));
                autoChooser.addOption("1Note", new PathPlannerAuto("1Note"));
                autoChooser.addOption("DriveStraight", new PathPlannerAuto("DriveStraight"));

                Shuffleboard.getTab("Autonomous").add("Auto Chooser", autoChooser); 

                // set default commands for each Subsystem
                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                        swerveSubsystem,
                        () -> driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
                        () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
                        () -> -driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
                        () -> !driverJoystick.getRawButton(6),          // RB button
                        () -> driverController.getRightTriggerAxis() > 0.5 ? true : false,
                        () -> driverController.getRawButton(3)));       // x button
                intake.setDefaultCommand(intakeStop);
                climber.setDefaultCommand(climberStop);

                // Xbox Driver Controller Buttons
                drBtnA = new JoystickButton(driverJoystick, OIConstants.KXboxButtonA);
                drBtnB = new JoystickButton(driverJoystick, OIConstants.KXboxButtonB);
                drBtnX = new JoystickButton(driverJoystick, OIConstants.KXboxButtonX);
                drBtnY = new JoystickButton(driverJoystick, OIConstants.KXboxButtonY);
                drBtnLB = new JoystickButton(driverJoystick, OIConstants.KXboxLeftBumper);
                drBtnRB = new JoystickButton(driverJoystick, OIConstants.KXboxRightBumper);
                drBtnSelect = new JoystickButton(driverJoystick, OIConstants.KXboxSelectButton);
                drBtnStrt = new JoystickButton(driverJoystick, OIConstants.KXboxStartButton);

                // Xbox Operator Controller Buttons. 
                opBtnA = new JoystickButton(operatorJoystick, OIConstants.KXboxButtonA);
                opBtnB = new JoystickButton(operatorJoystick, OIConstants.KXboxButtonB);
                opBtnX = new JoystickButton(operatorJoystick, OIConstants.KXboxButtonX);
                opBtnY = new JoystickButton(operatorJoystick, OIConstants.KXboxButtonY);
                opBtnLB = new JoystickButton(operatorJoystick, OIConstants.KXboxLeftBumper);
                opBtnRB = new JoystickButton(operatorJoystick, OIConstants.KXboxRightBumper);
                opBtnSelect = new JoystickButton(operatorJoystick, OIConstants.KXboxSelectButton);
                opBtnStrt = new JoystickButton(operatorJoystick, OIConstants.KXboxStartButton);
                configureButtonBindings(); 
        }

        private void configureButtonBindings() {
                configureNamedCommands();        

                // QOL Swerve Controls
                drBtnStrt.onTrue(zeroGyro); 
                opBtnX.whileTrue(intakeSpinBack); 
                drBtnB.whileTrue(new ClimberManualPosition(climber, 0));

                // Climber Controls
                new Trigger(()-> operatorController.getRightTriggerAxis() > 0.3).whileTrue(climberUp); 
                new Trigger(() -> operatorController.getLeftTriggerAxis() > 0.3).whileTrue(climberDown); 
                drBtnSelect.onTrue(zClimber); 
                opBtnA.whileTrue(new ClimberManualPosition(climber, -59.072261810302734));
                opBtnY.whileTrue(new ClimberManualPosition(climber, 79.21708679199219));

                // Intake Controls
                new Trigger(() -> Math.abs(operatorJoystick.getRawAxis(1)) > 0.3).whileTrue(
                        new LeftIntakeJoystick(() -> operatorJoystick.getRawAxis(1), intake));
                new Trigger(() -> Math.abs(operatorJoystick.getRawAxis(5)) > 0.3).whileTrue(
                        new RightIntakeJoystick(() -> operatorJoystick.getRawAxis(5), intake));

                // Pneumatics Controls 
                new POVButton(operatorJoystick, 0).onTrue(fullExtend); 
                new POVButton(operatorJoystick, 180).onTrue(fullDetract); 
                new POVButton(driverJoystick, 0).whileTrue(nudgeFront); 
                new POVButton(driverJoystick, 90).whileTrue(nudgeRight); 
                new POVButton(driverJoystick, 180).whileTrue(nudgeBack);
                new POVButton(driverJoystick, 270).whileTrue(nudgeLeft); 
        }

        public void configureNamedCommands() { 
                NamedCommands.registerCommand("ShootIntake", intakeSpinForward);
                NamedCommands.registerCommand("ClimberDown", new ClimberManualPosition(climber, 0));
                NamedCommands.registerCommand("ClimberUp", new ClimberManualPosition(climber, 79.21));
        }




        public Command getAutonomousCommand() {
                return autoChooser.getSelected(); 
        }        
}