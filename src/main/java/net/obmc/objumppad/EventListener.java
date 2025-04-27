package net.obmc.objumppad;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class EventListener implements Listener {

	private final OBJumpPadLoader loader;

	private Material plate;
	private Material block;
	private Double power;
	private Double vpower;
	private Sound sound;
	private int numparticles;
	private Particle effect;

	public EventListener(OBJumpPadLoader loader) {

		this.loader = loader;
		loadConfig();

	}

	private void loadConfig() {
 		String plateName = loader.getConfig().getString("plate");
		this.plate = Material.matchMaterial(plateName != null ? plateName : loader.DEFAULT_PLATE);
 		String blockName = loader.getConfig().getString("block");
		this.block = Material.matchMaterial(blockName != null ? blockName : loader.DEFAULT_BLOCK);
		String soundName = loader.getConfig().getString("block");
		soundName = soundName != null ? soundName.toLowerCase() : loader.DEFAULT_SOUND;
		this.sound = Registry.SOUNDS.get(NamespacedKey.minecraft(soundName));
		this.numparticles = loader.getConfig().getInt("particles");
		this.effect = Particle.valueOf(loader.getConfig().getString("effect")); 
		this.power = loader.getConfig().getDouble("power");
		this.vpower = loader.getConfig().getDouble("vpower");
	}

	// see if the player has interacted in some way with the block and plate
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.PHYSICAL && event.useInteractedBlock() != Event.Result.DENY) {
			Block interactedBlock = event.getClickedBlock();
			if (interactedBlock != null) {
				Material eventblock = interactedBlock.getLocation().subtract(0, 1, 0).getBlock().getType();
				Material eventplate = interactedBlock.getLocation().getBlock().getType();
				if (eventplate == plate && eventblock == block) {
			    	launchPlayer(event.getPlayer());
			    	event.setCancelled(true);
				}
			}
		}
	}

	// launch player
	private void launchPlayer(Player player) {
        player.setVelocity(calculateVector(player));
        player.playSound(player.getLocation(), this.sound, 15.0f, 2.5f);
        displayEffect(this.effect, player.getLocation(), 1.0f, 1.0f, 1.0f, 1.0f, this.numparticles);
	}

	// do some math
	private Vector calculateVector(Player player) {
		double radians = Math.toRadians(player.getLocation().getYaw());
		double x = -Math.sin(radians) * this.power;
		double y = this.vpower;
		double z = Math.cos(radians) * this.power;
		return new Vector(x, y, z);
	}

	// spawn a partile effect at a players location
	public void displayEffect(Particle particle, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(), amount, offsetX, offsetY, offsetZ, speed);
	}
}