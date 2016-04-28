package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.UtilVector;

import org.bukkit.Location;

public class ParticleFlame extends Particle {

	private double angle = 0, angle2 = (Math.PI * 2) / 3, r = 5;
	private Location playerLocation;

	public ParticleFlame(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
	}

	@Override
	public void onUpdate() {
		playerLocation = getLocation();
		for (double t = 1; t <= 2 * Math.PI; t += (Math.PI * 2) / 3) {
			double x = r * Math.cos(angle + t);
			double z = r * Math.sin(angle + t);
			getLocation().add(x, 0.25, z);
			ParticleEffect.FLAME.display(UtilVector.getVector(getLocation(), playerLocation).multiply(0.75), 0.1F, getLocation(), 1000D);
			getLocation().subtract(x, 0.25, z);
			x = r * Math.cos(angle2 + t);
			z = r * Math.sin(angle2 + t);
			getLocation().add(x, 0.25, z);
			ParticleEffect.FLAME.display(UtilVector.getVector(getLocation(), playerLocation).multiply(0.75), 0.1F, getLocation(), 1000D);
			getLocation().subtract(x, 0.25, z);
		}
		angle += (Math.PI * 2 / 3 / 20);
		angle2 -= (Math.PI * 2 / 3 / 20);
	}
}