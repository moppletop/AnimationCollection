package io.github.moppletop.particlelib.api;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleManager implements Listener {

	private JavaPlugin plugin;
	private Set<Particle> activeParticles = new HashSet<>();

	public ParticleManager(JavaPlugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		for (Particle particle : activeParticles) {
			if (particle.getParticleStyleType() == ParticleStyleType.PLAYER_ORIENTATED) {
				Player player = event.getPlayer();
				if (player.getUniqueId().equals(particle.getTarget())) {
					particle.stop();
				}
			}
		}
	}

	public Set<Particle> getActiveParticles() {
		return activeParticles;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}
}