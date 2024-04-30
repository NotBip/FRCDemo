package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;


public class IntakeNote extends Command{

    private Intake intake; 
    

    public IntakeNote(Intake intake) {
        this.intake = intake; 
        addRequirements(intake);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        intake.InakeNote(1);
    }


    @Override
    public void end(boolean interrupted) {
        intake.intakeStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}