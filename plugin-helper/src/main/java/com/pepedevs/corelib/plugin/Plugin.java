package com.pepedevs.corelib.plugin;

import com.pepedevs.corelib.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/** An implementation of {@link JavaPlugin} that adds some useful utilities. */
public abstract class Plugin extends JavaPlugin {

    protected String compilation_id;

    @Override
    public final void onEnable() {
        /* checking the plugin dependencies */
        if (getDependences() != null && getDependences().length > 0) {
            for (PluginDependence dependence : getDependences()) {
                final org.bukkit.plugin.Plugin plugin =
                        Bukkit.getPluginManager().getPlugin(dependence.getName());
                final Boolean result = dependence.apply(plugin);

                if (result == null || !result) {
                    Bukkit.getPluginManager().disablePlugin(this);
                    return;
                }
            }
        }

        /* plugin setup */
        if (!setUp() || !isEnabled()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        /* finalizing plugin setup */
        try {
            this.setUpConfig();
            this.setUpHandlers();
            this.setUpCommands();
            this.setUpListeners();
        } catch (Throwable ex) {
            // any exception will disable the plugin
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    /**
     * Setups this plugin. This methods will be called for the initialization of this plugin after
     * checking the required core version, and the dependences.
     *
     * <p>Also this methods should return <strong>{@code true}</strong> if the initialization was
     * successfully, and <strong>{@code false}</strong> if not. Returning <strong>{@code
     * false}</strong> from this method means that the initialization was unsuccessfully, then the
     * plugin will be disabled automatically.
     *
     * <p>
     *
     * @return true if the initialization was successfully.
     */
    protected abstract boolean setUp();

    /**
     * Gets the plugins on which this plugin depends.
     *
     * <p>This methods might return an empty array or <strong>{@code null}</strong> if this plugin
     * doesn't depend on another.
     *
     * <p>
     *
     * @return The dependencies or null if this plugin doesn't depend on another.
     * @see PluginDependence
     */
    public abstract PluginDependence[] getDependences();

    /**
     * This method should setups the configuration.
     *
     * <p>Also this should return <strong>{@code true}</strong> if the configuration was loaded
     * successfully, and <strong>{@code false}</strong> if not.
     *
     * <p>Note that if this methods returns <strong>{@code false}</strong>, then the plugin will be
     * disabled automatically.
     *
     * <p>Note that this methods will be called after:
     *
     * <ul>
     *   <li>{@link #setUp()}
     * </ul>
     *
     * <p>
     *
     * @return whether the configuration was loaded successfully.
     */
    protected abstract boolean setUpConfig();

    /**
     * This method should setups the plugin handlers.
     *
     * <p>Also this should return <strong>{@code true}</strong> if the initialization of the
     * handlers was successfully, and <strong>{@code false}</strong> if not.
     *
     * <p>Note that if this methods returns <strong>{@code false}</strong>, then the plugin will be
     * disabled automatically.
     *
     * <p>Note that this methods will be called after:
     *
     * <ul>
     *   <li>{@link #setUp()}
     *   <li>{@link #setUpConfig()}
     * </ul>
     *
     * <p>
     *
     * @return Whether the initialization of the handlers was successfully.
     */
    protected abstract boolean setUpHandlers();

    /**
     * This method should setups the commands of the plugin.
     *
     * <p>Also this should return <strong>{@code true}</strong> if the initialization of the
     * commands was successfully, and <strong>{@code false}</strong> if not.
     *
     * <p>Note that if this methods returns <strong>{@code false}</strong>, then the plugin will be
     * disabled automatically.
     *
     * <p>Note that this methods will be called after:
     *
     * <ul>
     *   <li>{@link #setUp()}
     *   <li>{@link #setUpConfig()}
     *   <li>{@link #setUpHandlers()}
     * </ul>
     *
     * <p>
     *
     * @return Whether the initialization of the commands was successfully.
     */
    protected abstract boolean setUpCommands();

    /**
     * This method should setups the listeners of the plugin.
     *
     * <p>Also this should return <strong>{@code true}</strong> if the initialization of the
     * listeners was successfully, and <strong>{@code false}</strong> if not.
     *
     * <p>Note that if this methods returns <strong>{@code false}</strong>, then the plugin will be
     * disabled automatically.
     *
     * <p>Note that this methods will be called after:
     *
     * <ul>
     *   <li>{@link #setUp()}
     *   <li>{@link #setUpConfig()}
     *   <li>{@link #setUpHandlers()}
     *   <li>{@link #setUpCommands()}
     * </ul>
     *
     * @return Whether the initialization of the listeners was successfully.
     */
    protected abstract boolean setUpListeners();

    /**
     * Saves the raw contents of any resource embedded with this plugin's .jar file assuming it can
     * be found using {@link #getResource(String)}.
     *
     * <p>The resource is saved into the desired {@code out_directory}.
     *
     * <p>
     *
     * @param resource_path The embedded resource path to look for within the plugin's .jar file.
     *     (No preceding slash).
     * @param out_directory The directory into which the resource will be saved.
     * @param replace If true, the embedded resource will overwrite the contents of an existing
     *     file.
     */
    public void saveResource(String resource_path, File out_directory, boolean replace) {
        URL url =
                this.getClassLoader().getResource(resource_path = resource_path.replace('\\', '/'));
        if (url != null) {
            File out =
                    new File(
                            out_directory,
                            resource_path.lastIndexOf('/') != -1
                                    ? resource_path.substring(resource_path.lastIndexOf('/') + 1)
                                    : resource_path);

            if (!out.exists() || replace) {
                try {
                    FileUtils.copyURLToFile(url, out);
                } catch (IOException ex) {
                    this.getLogger()
                            .severe("Couldn't save resource " + resource_path + " to " + out);
                    ex.printStackTrace();
                }
            }
        } else {
            throw new IllegalArgumentException(
                    "The embedded resource '"
                            + resource_path
                            + "' cannot be found in "
                            + getFile());
        }
    }

    protected String getCompilationId() {
        if (compilation_id == null) {
            YamlConfiguration plugin =
                    YamlConfiguration.loadConfiguration(
                            new InputStreamReader(this.getResource("plugin.yml")));

            compilation_id = plugin.getString("compid", String.valueOf(0));
        }

        return compilation_id;
    }
}
