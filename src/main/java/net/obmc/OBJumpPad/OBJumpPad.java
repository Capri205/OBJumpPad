package net.obmc.OBJumpPad;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class OBJumpPad extends JavaPlugin {

	static Logger log = Logger.getLogger("Minecraft");

	public static OBJumpPad instance;

	private EventListener listener;

	private Double power;
	private Double vpower;
	private Material plate;
	private Material block;
	private Sound sound;
	private int numparticles;
	private Particle effect;

	private static String plugin = "OBJumpPad";
	private static String pluginPrefix = "[" + plugin + "]";
    private static String logMsgPrefix = pluginPrefix + " » ";
	private static TextComponent chatMsgPrefix = Component.text(plugin, NamedTextColor.AQUA, TextDecoration.BOLD)
	    .append(Component.text(" » ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD));
	
	public OBJumpPad() {
		instance = this;
	}

	public static OBJumpPad getInstance() {
    	return instance;
    }

	public void onEnable() {
		initializeStuff();
		registerStuff();
		log.log(Level.INFO, logMsgPrefix + " Plugin Version " + this.getPluginMeta().getVersion() + " activated!");
	}

	public void onDisable() {
		log.log(Level.INFO, logMsgPrefix + " Plugin deactivated!");
	}

	public void initializeStuff() {
		this.saveDefaultConfig();
		Configuration config = this.getConfig();

		this.power = config.getDouble("power");
		this.vpower = config.getDouble("vpower");
		this.plate = Material.matchMaterial(config.getString("plate"));
		this.block = Material.matchMaterial(config.getString("block"));
	    this.sound = Registry.SOUNDS.get(NamespacedKey.minecraft(config.getString("sound").toLowerCase()));
		this.numparticles = config.getInt("particles");
		this.effect = Particle.valueOf(config.getString("effect")); 
	}

	public void registerStuff() {
        this.listener = new EventListener();
        this.getServer().getPluginManager().registerEvents((Listener)this.listener, (Plugin)this);
	}
	
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
	public int getParticleCount() {
		return this.numparticles;
	}
	public Particle getEffect() {
		return this.effect;
	}
	// consistent messaging
	public static String getPluginName() {
		return plugin;
	}
	public static String getPluginPrefix() {
		return pluginPrefix;
	}
	public static TextComponent getChatMsgPrefix() {
		return chatMsgPrefix;
	}
	public static String getLogMsgPrefix() {
		return logMsgPrefix;
	}

}
