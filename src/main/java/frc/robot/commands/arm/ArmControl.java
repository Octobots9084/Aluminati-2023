// package frc.robot.commands.arm;

// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.robot.ControlMap;
// import frc.robot.subsystems.arm.ArmExtension;

// public class ArmControl extends CommandBase {
//     private ArmExtension armExtension;
//     private XboxController xboxController;

//     public ArmControl() {
//         this.armExtension = ArmExtension.getInstance();
//         this.xboxController = ControlMap.XBOX;
//         addRequirements(armExtension);
//     }

//     @Override
//     public void initialize() {

//     }

//     @Override
//     public void execute() {
//         var pos = armExtension.lastpos + 20 * xboxController.getLeftY();
//         SmartDashboard.putNumber("armpos", armExtension.lastpos);
//         armExtension.SetPosition(pos, false);
//     }
// }
