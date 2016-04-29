package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.ParticleEffect.OrdinaryColor;

import org.bukkit.Color;

public class ParticleSwirl extends Particle {

	public ParticleSwirl(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.STATIC);
	}

	@Override
	public void onUpdate() {
		double t2 = 0;

		for (double t = 0; t < 2 * Math.PI; t += Math.PI / 3) {
			for (double r = 0; r < 5; r += .2) {
				double x = r * Math.sin(t + t2);
				double y = 0;
				double z = r * Math.cos(t + t2);

				getLocation().add(x, y, z);

				ParticleEffect.REDSTONE.display(new OrdinaryColor(Color.AQUA), getLocation(), 1000);

				getLocation().subtract(x, y, z);

				t2 += Math.PI / 150;
			}
			t2 = 0;
		}
	}
}