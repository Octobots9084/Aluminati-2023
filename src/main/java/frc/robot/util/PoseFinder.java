package frc.robot.util;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.subsystems.swerve.FieldPositions;

public class PoseFinder {
    private static Translation2d[] scoringPos = new Translation2d[8];
    private static int grid;
    
    public static int getGrid() {
        return grid;
    }

    public static void setGrid(int grid) {
        PoseFinder.grid = grid;
    }

    @Deprecated
    public static Translation2d getScoringPos(double x) {
        if (0.0 > x && x > 0.0) {
            return scoringPos[0];
        } else if (0.0 > x && x > 0.0) {
            return scoringPos[1];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[2];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[3];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[4];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[5];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[6];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[7];
        }else if (0.0 > x && x > 0.0) {
            return scoringPos[8];
        }
        return null;
    }

    public static Pose2d getPositionFromID(int ID) {
        if (DriverStation.getAlliance() == DriverStation.Alliance.Blue) {
            if (ID==1) {
                return new Pose2d(FieldPositions.BLUE_1_CONE.xPos, FieldPositions.BLUE_1_CONE.yPos, new Rotation2d());
            } else if (ID==2) {
                return new Pose2d(FieldPositions.BLUE_2_CUBE.xPos, FieldPositions.BLUE_2_CUBE.yPos, new Rotation2d());
            } else if (ID==3) {
                return new Pose2d(FieldPositions.BLUE_3_CONE.xPos, FieldPositions.BLUE_3_CONE.yPos, new Rotation2d());
            } else if (ID==4) {
                return new Pose2d(FieldPositions.BLUE_4_CONE.xPos, FieldPositions.BLUE_4_CONE.yPos, new Rotation2d());
            } else if (ID==5) {
                return new Pose2d(FieldPositions.BLUE_5_CUBE.xPos, FieldPositions.BLUE_5_CUBE.yPos, new Rotation2d());
            } else if (ID==6) {
                return new Pose2d(FieldPositions.BLUE_6_CONE.xPos, FieldPositions.BLUE_6_CONE.yPos, new Rotation2d());
            } else if (ID==7) {
                return new Pose2d(FieldPositions.BLUE_7_CONE.xPos, FieldPositions.BLUE_7_CONE.yPos, new Rotation2d());
            } else if (ID==8) {
                return new Pose2d(FieldPositions.BLUE_8_CUBE.xPos, FieldPositions.BLUE_8_CUBE.yPos, new Rotation2d());
            } else if (ID==9) {
                return new Pose2d(FieldPositions.BLUE_9_CONE.xPos, FieldPositions.BLUE_9_CONE.yPos, new Rotation2d());
            } else {
                return new Pose2d();
            }

        } else {
            if (ID==1) {
                return new Pose2d(FieldPositions.RED_1_CONE.xPos, FieldPositions.RED_1_CONE.yPos, new Rotation2d());
            } else if (ID==2) {
                return new Pose2d(FieldPositions.RED_2_CUBE.xPos, FieldPositions.RED_2_CUBE.yPos, new Rotation2d());
            } else if (ID==3) {
                return new Pose2d(FieldPositions.RED_3_CONE.xPos, FieldPositions.RED_3_CONE.yPos, new Rotation2d());
            } else if (ID==4) {
                return new Pose2d(FieldPositions.RED_4_CONE.xPos, FieldPositions.RED_4_CONE.yPos, new Rotation2d());
            } else if (ID==5) {
                return new Pose2d(FieldPositions.RED_5_CUBE.xPos, FieldPositions.RED_5_CUBE.yPos, new Rotation2d());
            } else if (ID==6) {
                return new Pose2d(FieldPositions.RED_6_CONE.xPos, FieldPositions.RED_6_CONE.yPos, new Rotation2d());
            } else if (ID==7) {
                return new Pose2d(FieldPositions.RED_7_CONE.xPos, FieldPositions.RED_7_CONE.yPos, new Rotation2d());
            } else if (ID==8) {
                return new Pose2d(FieldPositions.RED_8_CUBE.xPos, FieldPositions.RED_8_CUBE.yPos, new Rotation2d());
            } else if (ID==9) {
                return new Pose2d(FieldPositions.RED_9_CONE.xPos, FieldPositions.RED_9_CONE.yPos, new Rotation2d());
            } else {
                return new Pose2d();
            }
        }
        
        
    }

    public static Pose2d getPositionFromID(int grid, int node) {
        return getPositionFromID((grid-1)*3+node);
        
    }
}
