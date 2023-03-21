// /*
//  * MIT License
//  *
//  * Copyright (c) 2022 PhotonVision
//  *
//  * Permission is hereby granted, free of charge, to any person obtaining a copy
//  * of this software and associated documentation files (the "Software"), to deal
//  * in the Software without restriction, including without limitation the rights
//  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  * copies of the Software, and to permit persons to whom the Software is
//  * furnished to do so, subject to the following conditions:
//  *
//  * The above copyright notice and this permission notice shall be included in all
//  * copies or substantial portions of the Software.
//  *
//  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//  * SOFTWARE.
//  */

//  package frc.robot.subsystems.vision;

//  import edu.wpi.first.apriltag.AprilTagFieldLayout;
// import edu.wpi.first.apriltag.AprilTagFields;
// import edu.wpi.first.math.geometry.Pose2d;
// import frc.robot.subsystems.vision.Constants.RightCameraVisionConstants;

// import java.io.IOException;
//  import java.util.Optional;
//  import org.photonvision.EstimatedRobotPose;
//  import org.photonvision.PhotonCamera;
//  import org.photonvision.PhotonPoseEstimator;
//  import org.photonvision.PhotonPoseEstimator.PoseStrategy;

//  public class RightCameraWrapper {
//      public PhotonCamera photonCamera;
//      public PhotonPoseEstimator photonPoseEstimator;
//      private AprilTagFieldLayout fieldLayout;

//      public RightCameraWrapper() { 
//          try {
//                 fieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
//         } catch (IOException err) {
//                 throw new RuntimeException();
//         }

//          // Forward Camera
//          photonCamera =
//                  new PhotonCamera(
//                          RightCameraVisionConstants
//                                  .cameraName); 

//          // Create pose estimator
//          photonPoseEstimator =
//                  new PhotonPoseEstimator(
//                         fieldLayout, PoseStrategy.AVERAGE_BEST_TARGETS, photonCamera, RightCameraVisionConstants.robotToCam);
//      }

//      /**
//       * @param estimatedRobotPose The current best guess at robot pose
//       * @return A pair of the fused camera observations to a single Pose2d on the field, and the time
//       *     of the observation. Assumes a planar field and the robot is always firmly on the ground
//       */
//      public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose) {
//          return photonPoseEstimator.update();
//      }
//  }