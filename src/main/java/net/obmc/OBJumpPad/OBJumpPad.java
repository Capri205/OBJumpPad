package net.obmc.OBJumpPad;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class OBJumpPad extends JavaPlugin
{
	static Logger log = Logger.getLogger("Minecraft");
	
	public static OBJumpPad instance;

    private EventListener listener;

    private Double power;
    private Double vpower;
    private Material plate;
    private Material block;
    private Sound sound;
    private Particle effect;

	private static String plugin = "OBJumpPad";
	private static String pluginprefix = "[" + plugin + "]";
	private static String chatmsgprefix = ChatColor.AQUA + "" + ChatColor.BOLD + plugin + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.LIGHT_PURPLE + "";
	private static String logmsgprefix = pluginprefix + " » ";
	
    public OBJumpPad() {
    	instance = this;
    }

    /**
     * Make our (public) main class methods and variables available to other classes
     */
    public static OBJumpPad getInstance() {
    	return instance;
    }

	/**
	 * Plugin Start
	 */
	public void onEnable() {
		/**
		 * Initialize Stuff
		 */
		initializeStuff();

		/**
		 * Register stuff
		 */
		registerStuff();

		log.log(Level.INFO, getLogMsgPrefix() + " Plugin Version " + this.getDescription().getVersion() + " activated!");
	}

	/**
	 * Plugin Stop
	 */
	public void onDisable() {
		/**
		 * Output message
		 */
		log.log(Level.INFO, getLogMsgPrefix() + " Plugin deactivated!");
	}

	/**
	 * Initialize Stuff
	 */
	public void initializeStuff() {
		this.saveDefaultConfig();
		Configuration config = this.getConfig();

		this.power = config.getDouble("power");
		this.vpower = config.getDouble("vpower");
		this.plate = Material.matchMaterial(config.getString("plate"));
		this.block = Material.matchMaterial(config.getString("block"));
		this.sound = Sound.valueOf(config.getString("sound"));
		this.effect = Particle.valueOf(config.getString("effect")); 
	}

	/**
	 * Register things
	 */
	public void registerStuff() {
        this.listener = new EventListener();
        this.getServer().getPluginManager().registerEvents((Listener)this.listener, (Plugin)this);
	}
	
    /**
     * Routine getters
     */
    public Double getPower() {
    	return this.power;
    }
    public Double getVPower() {
    	return this.vpower;
    }
    public Material getPlate() {
    	return this.plate;
    }
    public Material getBlock() {
    	return this.block;
    }
    public Sound getSound() {
    	return this.sound;
    }
    public Particle getEffect() {
    	return this.effect;
    }
	// consistent messaging
	public static String getPluginName() {
		return plugin;
	}
	public static String getPluginPrefix() {
		return pluginprefix;
	}
	public static String getChatMsgPrefix() {
		return chatmsgprefix;
	}
	public static String getLogMsgPrefix() {
		return logmsgprefix;
	}

}
