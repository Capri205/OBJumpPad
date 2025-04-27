package net.obmc.objumppad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bukkit.configuration.Configuration;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;

public class OBJumpPadLoader implements PluginLoader {

    private OBJumpPad plugin;
    private Configuration config;

   	static Logger log = LoggerFactory.getLogger(OBJumpPad.class);

	public final String DEFAULT_PLATE = "STONE_PRESSURE_PLATE";
	public final String DEFAULT_BLOCK = "REDSTONE_BLOCK";
    public final String DEFAULT_SOUND = "ENTITY.CREEPER.PRIMED";

    public OBJumpPadLoader() {
    }
    public OBJumpPadLoader(OBJumpPad plugin) {
        this.plugin = plugin;
    }

    // not implemented yet, but let's not stop the server loading the plugin
    @Override
    public void classloader(PluginClasspathBuilder pcb) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void loadConfiguration() {

        plugin.saveDefaultConfig();
		config = plugin.getConfig();

        // validate settings and set any failed config reads to a default value
        boolean doConfigSave = false;
		String plateName = config.getString("plate");
		if (plateName == null) {
			log.info("Config value for 'plate' is null. Using default of " + DEFAULT_PLATE);
            config.set("plate", DEFAULT_PLATE);
            doConfigSave = true;
		}
		String blockName = config.getString("block");
		if (blockName == null) {
			log.info("Config value for 'block' is null. Using default of " + DEFAULT_BLOCK);
            config.set("block", DEFAULT_BLOCK);
            doConfigSave = true;
		}
		String soundName = config.getString("sound");
		if (soundName == null) {
			log.info("Config value for 'sound' is null. Using default of " + DEFAULT_SOUND);
            config.set("sound", DEFAULT_SOUND);
            doConfigSave = true;
		}
        if (doConfigSave) {
            plugin.saveConfig();
        }
        log.info("Configuration loaded");
    }

    public void loadClasses() {
    }

    public void loadDependencies() {
    }

    public Configuration getConfig() {
        return config;
    }
}