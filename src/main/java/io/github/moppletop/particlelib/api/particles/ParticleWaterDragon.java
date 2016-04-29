package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;

public class ParticleWaterDragon extends Particle {

	public ParticleWaterDragon(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
	}

	private double t;

	@Override
	public void onUpdate() {
		double x = Math.sin(t) * 2;
		double m = Math.sin(t) * 4;
		double y = Math.cos(m) / 2;
		double z = Math.cos(t) * 2;

		getLocation().add(x, 1 + y, z);

		ParticleEffect.DRIP_WATER.display(0, 0, 0, 0.1F, 1, getLocation(), 1000.0D);
		ParticleEffect.CLOUD.display(0, 0, 0, 0.01F, 2, getLocation(), 1000.0D);

		getLocation().subtract(x, 1 + y, z);

		t += Math.PI / 25;
	}
}
