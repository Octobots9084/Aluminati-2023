plugins {
    id("org.rivierarobotics.gradlerioredux") version "0.9.7"
}

gradleRioRedux {
    robotClass = "org.octobots.robot.Robot"
    teamNumber = 9084
    applyGradleRioConfiguration()
}