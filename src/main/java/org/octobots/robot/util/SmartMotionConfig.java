package org.octobots.robot.util;

public class SmartMotionConfig {
    private boolean reset;
    private Double maxVel;
    private Double minVel;
    private Double maxAccel;
    private Double closedLoopAllowedError;

    /**
     * Constructs a Motion Magic configuration with the specified constants.
     *
     * @param reset reset the controller to factory default if true.
     * @param maxVel maximum velocity of Motion Magic controller in ticks per 100ms.
     * @param maxAccel maximum acceleration of Motion Magic controller in ticks per 100ms.
     *
     * @since 0.3.0
     */
    public SmartMotionConfig(boolean reset,
                             Double maxVel, Double minVel, Double maxAccel, Double closedLoopAllowedError) {
        this.reset = reset;
        this.maxVel = maxVel;
        this.minVel = minVel;
        this.maxAccel = maxAccel;
        this.closedLoopAllowedError = closedLoopAllowedError;

    }

    /**
     * <p>Constructs a Smart Motion configuration with the specified constants.
     * Initializes all values except timeout and period to null/false/empty.</p>
     *
     * <p>Overload for {@link #SmartMotionConfig(boolean, Double, Double, Double, Double)}.</p>
     *
     * @see #SmartMotionConfig(boolean, Double, Double, Double, Double)
     * @since 0.3.0
     */

    public SmartMotionConfig() {
        this(false, null, null, null, null);
    }

    /**
     * <p>Constructs a Motion Magic configuration with the specified constants.
     * Initializes timeout and period to 10ms each.</p>
     *
     * <p>Overload for {@link #SmartMotionConfig()}.</p>
     *
     * @see #SmartMotionConfig()
     * @since 0.3.0
     */

    public SmartMotionConfig setReset(boolean reset) {
        this.reset = reset;
        return this;
    }

    public SmartMotionConfig setMaxVel(Double maxVel) {
        this.maxVel = maxVel;
        return this;
    }

    public SmartMotionConfig setMinVel(Double minVel) {
        this.minVel = minVel;
        return this;
    }

    public SmartMotionConfig setMaxAccel(Double maxAccel) {
        this.maxAccel = maxAccel;
        return this;
    }

    public SmartMotionConfig setClosedLoopAllowedError(Double closedLoopAllowedError) {
        this.closedLoopAllowedError = closedLoopAllowedError;
        return this;
    }

    public boolean doReset() {
        return reset;
    }

    public Double getMaxVel() {
        return maxVel;
    }

    public Double getMinVel() {
        return minVel;
    }

    public Double getMaxAccel() {
        return maxAccel;
    }

    public Double getClosedLoopAllowedError() {
        return closedLoopAllowedError;
    }
}
