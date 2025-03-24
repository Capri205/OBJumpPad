package net.obmc.OBJumpPad;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.Event;

public class EventListener implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	// see if the player has interacted in some way with the block and plate
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.PHYSICAL && event.useInteractedBlock() != Event.Result.DENY) {
			Material eventblock = event.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().getType();
			Material eventplate = event.getClickedBlock().getLocation().getBlock().getType();
			if (eventplate == OBJumpPad.getInstance().getPlate() && eventblock == OBJumpPad.getInstance().getBlock()) {
			    launchPlayer(event.getPlayer());
			    event.setCancelled(true);
			} else {
				event.setCancelled(false);
			}
		}
	}

	// launch player
	private void launchPlayer(Player player) {
        player.setVelocity(calculateVector(player));
        player.playSound(player.getLocation(), OBJumpPad.getInstance().getSound(), 15.0f, 2.5f);
        displayEffect(OBJumpPad.getInstance().getEffect(), player.getLocation(), 1.0f, 1.0f, 1.0f, 1.0f, OBJumpPad.getInstance().getParticleCount());
	}

	// do some math
	private Vector calculateVector(Player player) {
		double radians = Math.toRadians(player.getLocation().getYaw());
		double x = -Math.sin(radians) * OBJumpPad.getInstance().getPower();
		double y = OBJumpPad.getInstance().getVPower();
		double z = Math.cos(radians) * OBJumpPad.getInstance().getPower();
		return new Vector(x, y, z);
	}

	// spawn a partile effect at a players location
	public void displayEffect(Particle particle, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(), amount, offsetX, offsetY, offsetZ, speed);
	}
}