package frc.robot.commands.arm.LowLevel;

import java.lang.module.ModuleDescriptor.Requires;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.ArmExtension;
import frc.robot.util.MathUtil;

public class MoveArmExtensionToPos extends CommandBase {
    double target;

    ArmExtension armExtension;
    double startTime = Timer.getFPGATimestamp();
    double currentTime;
    public MoveArmExtensionToPos(double pos) {

        
        this.target = pos;
        this.armExtension = ArmExtension.getInstance();
        this.addRequirements(this.armExtension);
    }

    @Override
    public void initialize() {
        
        armExtension.SetPosition(target, false);
    }

    @Override
    public void execute() {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        boolean timeout = !MathUtil.isWithinTolerance(startTime, currentTime, 180);
        return (timeout || MathUtil.isWithinTolerance(armExtension.GetPosition(),target,5));
    }
}
