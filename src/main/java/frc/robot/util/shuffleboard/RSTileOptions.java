package frc.robot.util.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

/**
 * <p>A utility class for storing position and sizing details of
 * {@code RobotShuffleboard} tiles, tabs, and tables.</p>
 *
 * <p>The field {@link RSTileOptions#DEFAULT} is used by default in many
 * wrapper methods to specify that the options should not be applied
 * and the Shuffleboard defaults should be used instead.</p>
 *
 * @see RSTab
 * @see RSTable
 * @since 0.1.0
 */
public class RSTileOptions {
    public static final RSTileOptions DEFAULT = new RSTileOptions(-1, -1, -1, -1);
    private final int width;
    private final int height;
    private final int posX;
    private final int posY;

    /**
     * <p>Constructs a new utility class and passes through data.</p>
     *
     * <p>A value of -1 for any parameter indicates that it should not be
     * applied to the target and the default should instead persist.
     * Position coordinates are defined in screen-space where the origin
     * is the top-left and both coordinates increase as they approach
     * the bottom-right of the screen.</p>
     *
     * @param width the width, in tile units, of the item.
     * @param height the height, in tile units, of the item.
     * @param posX the position in the X direction (horizontal), in tile units, of the item.
     * @param posY the position in the Y direction (vertical), in tile units, of the item.
     *
     * @since 0.1.0
     */
    public RSTileOptions(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /**
     * Applies the current properties to a {@code SimpleWidget} (primitive tile).
     *
     * @param sWidget the simple widget to set the properties of.
     * @return the configured simple widget.
     *
     * @since 0.1.0
     */
    public SimpleWidget applyToSimple(SimpleWidget sWidget) {
        sWidget.withSize(width, height).withPosition(posX, posY);
        return sWidget;
    }

    /**
     * Applies the current properties to a {@code ComplexWidget}
     * ({@code VideoSource} or {@code Sendable}, other types)
     *
     * @param cWidget the complex widget to set the properties of.
     * @return the configured complex widget.
     *
     * @since 0.1.0
     */
    public ComplexWidget applyToComplex(ComplexWidget cWidget) {
        cWidget.withSize(width, height).withPosition(posX, posY);
        return cWidget;
    }
}