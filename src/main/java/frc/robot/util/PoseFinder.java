package frc.robot.util;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;

public class PoseFinder {
    private static Translation2d[] scoringPos = new Translation2d[8];

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
}
