package io.github.moppletop.particlelib.plugin;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	/**
	 * @author Sam
	 * @see https://github.com/moppletop/AnimationCollection
	 *
	 *      Created as a VERY SIMPLE plugin to see all the animations available
	 *      to you.
	 */

	private ParticleManager particleManager;
	private final String packageName = "io.github.moppletop.particlelib.api.particles";

	@Override
	public void onEnable() {
		particleManager = new ParticleManager(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("particlelib")) {
				Player player = (Player) sender;
				if (args.length == 1 || args.length == 2) {
					try {
						int frequency = 1;
						if (args.length == 2) {
							frequency = Integer.parseInt(args[1]);
						}
						Class<?> clazz = Class.forName(packageName + ".Particle" + args[0]);
						Particle particle = (Particle) clazz.getConstructor(ParticleManager.class, int.class).newInstance(particleManager, frequency);
						if (particle.getParticleStyleType() == ParticleStyleType.PLAYER_ORIENTATED) {
							particle.setTarget(player);
						} else {
							particle.setLocation(player.getLocation());
						}
						particle.start();
					} catch (Exception e) {
						player.sendMessage(ChatColor.RED + "That is not a particle. Check https://github.com/moppletop/AnimationCollection");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /particlelib <name> [frequency]");
					return true;
				}
			}
		} else {
			sender.sendMessage("You must be a player!");
		}
		return false;
	}
}