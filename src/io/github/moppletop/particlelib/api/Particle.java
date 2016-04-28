package io.github.moppletop.particlelib.api;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Particle {

	private final ParticleManager particleManager;
	private int task;

	private int frequency;
	private int iterations = 10 * 20;
	private boolean autoTerminate = true;

	private Location loc;
	private ParticleStyleType particleStyleType = ParticleStyleType.STATIC;
	private UUID uuid;

	public Particle(ParticleManager particleManager, int frequency) {
		this.particleManager = particleManager;
		this.frequency = frequency;
	}

	public abstract void onUpdate();

	public final void start() {
		particleManager.getActiveParticles().add(this);
		task = new BukkitRunnable() {

			int iterations = 0;

			@Override
			public void run() {
				if (particleStyleType == ParticleStyleType.PLAYER_ORIENTATED && uuid != null) {
					loc = Bukkit.getPlayer(uuid).getLocation();
				}
				if (this.iterations > Particle.this.iterations && autoTerminate) {
					stop();
				}
				if (loc != null) {
					onUpdate();
				}
				this.iterations++;
			}
		}.runTaskTimerAsynchronously(particleManager.getPlugin(), 1, frequency).getTaskId();
	}

	public final void stop() {
		particleManager.getActiveParticles().remove(this);
		Bukkit.getScheduler().cancelTask(task);
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public boolean isAutoTerminating() {
		return autoTerminate;
	}

	public void setAutoTerminate(boolean autoTerminate) {
		this.autoTerminate = autoTerminate;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public ParticleStyleType getParticleStyleType() {
		return particleStyleType;
	}

	public void setParticleStyleType(ParticleStyleType particleStyleType) {
		this.particleStyleType = particleStyleType;
	}

	public final void setTarget(Player player) {
		this.uuid = player.getUniqueId();
	}

	public final void setTarget(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getTarget() {
		return uuid;
	}

	public final ParticleManager getParticleManager() {
		return particleManager;
	}
}
