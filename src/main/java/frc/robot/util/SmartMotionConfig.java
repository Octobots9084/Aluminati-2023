/*
 * This file is part of Placeholder-2023, licensed under the GNU General Public License (GPLv3).
 *
 * Copyright (c) Octobots <https://github.com/Octobots9084>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package frc.robot.util;

public class SmartMotionConfig {
    private boolean reset;
    private Double maxVel;
    private Double minVel;
    private Double maxAccel;
    private Double closedLoopAllowedError;

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
