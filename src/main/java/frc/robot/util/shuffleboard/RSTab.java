package frc.robot.util.shuffleboard;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.VideoException;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

/**
 * <p>Represents a {@code Shuffleboard} tab as part of the {@code RobotShuffleboard}.</p>
 *
 * <p>Contains WPILib API tab object and internal lists for tiles.
 * Can add primitives, {@code Sendable}s, {@code CameraServer} streams,
 * and {@code VideoSources}. Tables can also be created in tabs.
 * Positioning and sizing of each tile is managed by {@code RSTileOptions}.</p>
 *
 * <p>Methods are arranged as a builder. Daisy-chaining is encouraged.</p>
 *
 * @see RobotShuffleboard
 * @see RSTileOptions
 * @see RSTable
 * @since 0.1.0
 */
public class RSTab {
    private final ShuffleboardTab tab;
    private final String name;
    private final Map<String, GenericEntry> entries;
    private final Map<String, RSTable> tables;

    /**
     * <p>Creates a new tab with a specified name.</p>
     *
     * <p>If an existing tab exists on the {@code Shuffleboard} with the same name,
     * any {@code SimpleWidget} tiles will be logged as entries. Existing
     * tables will not be logged and may either be added again or re-initialized.</p>
     *
     * @param name the title/name of the {@code Shuffleboard} tab to create.
     * @since 0.1.0
     */
    public RSTab(String name) {
        this.tab = Shuffleboard.getTab(name);
        this.name = name;
        this.entries = new LinkedHashMap<>();
        this.tables = new LinkedHashMap<>();

        for (ShuffleboardComponent<?> comp : tab.getComponents()) {
            if (comp instanceof SimpleWidget) {
                entries.put(comp.getTitle(), (((SimpleWidget) comp).getEntry()));
            }
        }
    }

    /**
     * <p>Adds a named {@code CameraServer} stream to the {@code RSTab}.
     * Uses default {@link RSTileOptions}.</p>
     *
     * <p>Overload for {@link #setCamera(String, RSTileOptions)}.</p>
     *
     * @see #setCamera(String, RSTileOptions)
     * @since 0.1.0
     */
    public RSTab setCamera(String name) throws VideoException {
        return setCamera(name, RSTileOptions.DEFAULT);
    }

    /**
     * <p>Adds a named {@code CameraServer} stream to the {@code RSTab} with properties.
     * Grabs stream source from the {@code CameraServer}.</p>
     *
     * <p>Overload for {@link #setVideoSource(VideoSource, RSTileOptions)}.</p>
     *
     * @param name the named {@code CameraServer} to add.
     * @param options camera placement and sizing options on the {@code RSTab}.
     * @return the current {@code RSTab} where the {@code CameraServer} stream was added.
     * @throws VideoException if a video error occurred while retrieving the {@code CameraServer} stream.
     *
     * @see #setVideoSource(VideoSource, RSTileOptions)
     * @since 0.1.0
     */
    public RSTab setCamera(String name, RSTileOptions options) throws VideoException {
        return setVideoSource(CameraServer.getVideo(name).getSource(), options);
    }

    /**
     * <p>Adds a {@code VideoSource} to the {@code RSTab}.
     * Uses default {@link RSTileOptions}.</p>
     *
     * <p>Overload for {@link #setVideoSource(VideoSource, RSTileOptions)}.</p>
     *
     * @param src the {@code VideoSource} to add.
     * @return the current {@code RSTab} where the {@code VideoSource} stream was added.
     *
     * @see #setVideoSource(VideoSource, RSTileOptions)
     * @since 0.1.0
     */
    public RSTab setVideoSource(VideoSource src) {
        return setVideoSource(src, RSTileOptions.DEFAULT);
    }

    /**
     * Adds a {@code VideoSource} to the {@code RSTab} with properties.<br>
     *
     * Applied as a {@code ComplexWidget} type to the WPILib {@code ShuffleboardTab}.<br>
     * Title/name of {@code Shuffleboard} tile equal to source name of {@code VideoSource}.
     *
     * @param src the video source to add.
     * @param options video source placement and sizing options on the {@code RSTab}.
     * @return the current {@code RSTab} where the {@code VideoSource} stream was added.
     *
     * @since 0.1.0
     */
    public RSTab setVideoSource(VideoSource src, RSTileOptions options) {
        options.applyToComplex(tab.add(src.getName(), src));
        return this;
    }

    /**
     * <p>Adds a {@code Sendable} type object to the {@code RSTab}.
     * Uses default {@link RSTileOptions}.</p>
     *
     * <p>Overload for {@link #setSendable(Sendable, RSTileOptions)}.</p>
     *
     * @see #setSendable(Sendable, RSTileOptions)
     * @since 0.1.0
     */
    public RSTab setSendable(Sendable sendable) {
        return setSendable(sendable, RSTileOptions.DEFAULT);
    }

    /**
     * <p>Adds a {@code Sendable} type object to the {@code RSTab} with properties.</p>
     *
     * <p>Applied as a {@code ComplexWidget} type to the WPILib {@code ShuffleboardTab}.
     * Title/name of {@code Shuffleboard} tile equal to registered name of {@code Sendable}.
     * Typically used for WPILib {@code SendableChooser} objects.</p>
     *
     * @param sendable the sendable type object to add.
     * @param options placement and sizing options on the {@code RSTab}.
     * @return the current {@code RSTab} where the {@code Sendable} was added.
     *
     * @since 0.1.0
     */
    public RSTab setSendable(Sendable sendable, RSTileOptions options) {
        options.applyToComplex(tab.add(SendableRegistry.getName(sendable), sendable));
        return this;
    }

    /**
     * <p>Adds a primitive value to the {@code RSTab}.
     * Uses default {@link RSTileOptions}</p>
     *
     * <p>Overload for {@link #setEntry(String, Object, RSTileOptions)}.</p>
     *
     * @see #setEntry(String, Object, RSTileOptions)
     * @since 0.1.0
     */
    public <T> RSTab setEntry(String title, T value) {
        return setEntry(title, value, RSTileOptions.DEFAULT);
    }

    /**
     * <p>Adds a primitive value to the {@code RSTab} with properties.</p>
     *
     * <p>Ensures a key with the same title does not already exist in logged entries, then
     * creates a new {@code SimpleWidget} with matching default value type.
     * If not, the entry is retrieved from the local listing and the value is set.</p>
     *
     * <p>Note that the tile's type will be forced to the newest value.
     * It is suggested to use primitive types only, as only a limited subset of objects
     * are compatible. Most are wrapped by other methods in {@code RSTab}.</p>
     *
     * @param title the key/title of the {@code Shuffleboard} tile.
     * @param value the primitive value to set.
     * @param options placement and sizing options on the {@code RSTab}.
     * @return the current {@code RSTab} where the value was added.
     *
     * @since 0.1.0
     */
    public <T> RSTab setEntry(String title, T value, RSTileOptions options) {
        if (!entries.containsKey(title)) {
            entries.put(title, options.applyToSimple(tab.add(title, value)).getEntry());
        } else {
            entries.get(title).setValue(value);
        }
        return this;
    }

    /**
     * <p>Retrieves the {@code NetworkTableEntry} associated with a named tile.</p>
     *
     * <p>Entries will be taken from the pre-existing entries list. If there is no
     * entry of a matching title in the list, one will be created with a default
     * value of an empty String. Note that the value must be force-set if done
     * manually if is is not a String.</p>
     *
     * @param title the name/key of the {@code Shuffleboard} tile.
     * @return the logged entry associated with the name/key.
     *
     * @since 0.1.0
     */
    // public GenericEntry getEntry(String title) {
    //     SimpleWidget entry = entries.;
    //     if (entry == null) {
    //         entry = tab.add(title, "");
    //         entry = tab.
    //         // entry = tab.
    //         entries.put(title, entry);
    //     }
    //     return entry;
    // }

    public Map<String, GenericEntry> getEntries() {
        return entries;
    }

    /**
     * <p>Retrieves a key/value table from the {@code RSTab}.
     * Uses default {@link RSTileOptions}.</p>
     *
     * <p>Overload for {@link #getTable(String, RSTileOptions)}.</p>
     *
     * @see #getTable(String, RSTileOptions)
     * @since 0.1.0
     */
    public RSTable getTable(String tableName) {
        return getTable(tableName, RSTileOptions.DEFAULT);
    }

    /**
     * <p>Retrieves a key/value table from the {@code RSTab} with properties.</p>
     *
     * <p>If the table does not exist (or is not present in the logged tables list)
     * a new one will be created. Note that height for tables has a maximum of 3 units.
     * Properties will not be used for preexisting tables.
     * This is the suggested method of managing table access.</p>
     *
     * @param tableName the name/key/title of the table to get.
     * @param options placement and sizing options on the {@code RSTab}.
     * @return the resulting table for adding key/value pairs to.
     *
     * @since 0.1.0
     */
    public RSTable getTable(String tableName, RSTileOptions options) {
        if (tables.get(tableName) == null) {
            addTable(tableName, options);
        }
        return tables.get(tableName);
        // return tables.get(options)
    }

    /**
     * <p>Adds a new key/value table to the {@code RSTab}.
     * Uses default {@link RSTileOptions}.</p>
     *
     * <p>Overload for {@link #addTable(String, RSTileOptions)}.</p>
     *
     * @see #addTable(String, RSTileOptions)
     * @since 0.1.0
     */
    public void addTable(String tableName) {
        addTable(tableName, RSTileOptions.DEFAULT);
    }

    /**
     * <p>Adds a new key/value table to the {@code RSTab} with properties.</p>
     *
     * <p>Creates a new table and then places it on current tab.</p>
     *
     * @param tableName the name/key/title of the table to add.
     * @param options placement and sizing options on the {@code RSTab}.
     *
     * @since 0.1.0
     */
    public void addTable(String tableName, RSTileOptions options) {
        tables.put(tableName, new RSTable(tableName, this, options));
    }

    /**
     * <p>Adds an existing key/value table to the {@code RSTab}.</p>
     *
     * <p>Does not make a new table. The pre-exsting table is instead passed through and added.
     * The table name will be preset by the {@code ShuffleboardTable} object.</p>
     *
     * @param table the table object to display on the current tab.
     *
     * @see #addTable(String, RSTileOptions)
     * @since 0.1.0
     */
    public void addTable(RSTable table) {
        tables.put(table.getName(), table);
    }

    public String getName() {
        return name;
    }

    public ShuffleboardTab getAPITab() {
        return tab;
    }
}