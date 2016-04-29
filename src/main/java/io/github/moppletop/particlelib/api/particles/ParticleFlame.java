package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.UtilVector;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ParticleFlame extends Particle {

	private double t1 = 0, t2 = (Math.PI * 2) / 3, r = 5;

	public ParticleFlame(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
	}

	@Override
	public void onUpdate() {
		Location playerLocation = Bukkit.getPlayer(getTarget()).getLocation();

		for (double t = 1; t <= 2 * Math.PI; t += (Math.PI * 2) / 3) {
			double x = r * Math.cos(t1 + t);
			double z = r * Math.sin(t1 + t);

			getLocation().add(x, .25, z);

			ParticleEffect.FLAME.display(UtilVector.getVector(getLocation(), playerLocation).multiply(.75), .1F, getLocation(), 1000D);

			getLocation().subtract(x, .25, z);

			x = r * Math.cos(t2 + t);
			z = r * Math.sin(t2 + t);

			getLocation().add(x, .25, z);

			ParticleEffect.FLAME.display(UtilVector.getVector(getLocation(), playerLocation).multiply(.75), .1F, getLocation(), 1000D);

			getLocation().subtract(x, .25, z);
		}
		t1 += (Math.PI * 2 / 3 / 20);
		t2 -= (Math.PI * 2 / 3 / 20);
	}
}