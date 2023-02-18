// package frc.robot.commands.arm;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.robot.ControlMap;
// import frc.robot.subsystems.arm.CaliGirls;

// public class WristControl extends CommandBase {
//     private CaliGirls caliGirls;
//     // private XboxController xboxController;

//     public WristControl() {
//         this.caliGirls = CaliGirls.getInstance();
//         // this.xboxController = ControlMap.XBOX;
//         addRequirements(caliGirls);
//     }

//     @Override
//     public void initialize() {
//         // TODO document why this method is empty
//     }

//     @Override
//     public void execute() {
//         var pos = caliGirls.lastPosTop + ControlMap.CO_DRIVER_LEFT.getY();
//         SmartDashboard.putNumber("armpos", caliGirls.lastPosTop);
//         caliGirls.setBottomPos(pos);
//     }
// }
