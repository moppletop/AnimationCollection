package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;

public class ParticleExample extends Particle {

	private final double r = 4; // The radius remains constant so it can be a private final variable.

	public ParticleExample(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
	}

	@Override
	public void onUpdate() {
		for (double t = 0; t < 2 * Math.PI; t += Math.PI / 20) {
			// Until theta (t) is greater than 2PI (a full circle).

			double x = r * Math.sin(t);
			double z = r * Math.cos(t);

			getLocation().add(x, 0, z);
			// Add to our location

			ParticleEffect.FLAME.display(0, 0, 0, 0, 1, getLocation(), 1000);
			// Send our particle

			getLocation().subtract(x, 0, z);
			// Always subtract the values you added to the location or remember
			// to clone the location object every time you iterate.
		}
	}
}
