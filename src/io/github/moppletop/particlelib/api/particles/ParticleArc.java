package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.util.ParticleEffect;

import org.bukkit.Location;

public class ParticleArc extends Particle {

	public ParticleArc(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
	}

	@Override
	public void onUpdate() {
		double t = 0, x = 0;
		while (t < Math.PI) {
			Location loc2 = getLocation().clone().add(x * 8, Math.sin(t) * 5, 0);
			ParticleEffect.FLAME.display(0, 0, 0, 0, 1, loc2, 1000);
			t += Math.PI / 60;
			x += .05;
		}
	}
}