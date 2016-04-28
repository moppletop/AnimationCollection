package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.ParticleEffect.OrdinaryColor;

public class ParticleHelix extends Particle {

	private double angle = 0, angle2 = Math.PI;
	private OrdinaryColor colour = new OrdinaryColor(254, 1, 1);

	public ParticleHelix(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
	}

	@Override
	public void onUpdate() {
		double r = 1.5;
		for (double y = 0; y < 3; y += 0.1) {
			double x = r * Math.cos(angle);
			double z = r * Math.sin(angle);
			getLocation().add(x, y, z);
			ParticleEffect.REDSTONE.display(colour, getLocation(), 1000D);
			getLocation().subtract(x, y, z);
			x = r * Math.cos(angle2);
			z = r * Math.sin(angle2);
			getLocation().add(x, y, z);
			ParticleEffect.REDSTONE.display(colour, getLocation(), 1000D);
			getLocation().subtract(x, y, z);
			angle += Math.PI / 15;
			angle2 += Math.PI / 15;
			r -= 0.05;
		}
		angle += Math.PI / 40;
		angle2 += Math.PI / 40;
	}
}
