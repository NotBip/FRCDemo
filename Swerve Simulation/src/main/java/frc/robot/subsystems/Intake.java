package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{

    private REVPhysicsSim sim; 
    private CANSparkMax intakeMotorFront;
    private CANSparkMax intakeMotorBack;

    public Intake() { 
        sim = new REVPhysicsSim();

        intakeMotorFront = new CANSparkMax(9, MotorType.kBrushless); 
        intakeMotorBack = new CANSparkMax(10, MotorType.kBrushless); 
        
        sim.addSparkMax(intakeMotorFront, DCMotor.getNEO(1));
        sim.addSparkMax(intakeMotorBack, DCMotor.getNEO(1));
    }

    public void periodic() { 
        SmartDashboard.putNumber("Front Motors", intakeMotorFront.get()); 
        SmartDashboard.putNumber("Back Motors", intakeMotorBack.get()); 
    }
    
    public void ShootNote(double speed) { 
        intakeMotorFront.set(-Math.abs(speed));
        intakeMotorBack.set(-Math.abs(speed));
    }

    public void InakeNote(double speed) { 
        intakeMotorFront.set(-Math.abs(speed));
        intakeMotorBack.set(Math.abs(speed));
    }

    public void intakeStop() { 
        intakeMotorFront.set(0);
        intakeMotorBack.set(0);
    }


}