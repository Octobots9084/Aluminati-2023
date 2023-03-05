package frc.robot.commands.arm;

import java.io.Console;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.arm.basic.MoveArmRotationToPos;
import frc.robot.subsystems.arm.CaliGirls;

public class CaliGirlsBottomMoveDownALittle extends InstantCommand {


    @Override
    public void initialize() {
        double pos = CaliGirls.getInstance().lastPosBottom-0.04;
        System.out.println(pos);
        CommandScheduler.getInstance().schedule(new MoveArmRotationToPos(pos, CaliGirls.getInstance().getBottomKf()));
    }

}
    