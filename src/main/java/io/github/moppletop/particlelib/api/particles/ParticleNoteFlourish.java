package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.ParticleEffect.NoteColor;

public class ParticleNoteFlourish extends Particle {

	private double y = 3, r = 0;
	private boolean increase = true;
	private int colour = 4;

	public ParticleNoteFlourish(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
	}

	@Override
	public void onUpdate() {
		for (double t = 0; t < 2 * Math.PI; t += Math.PI / 10) {
			double x = r * Math.cos(t);
			double z = r * Math.sin(t);

			getLocation().add(x, y, z);

			ParticleEffect.NOTE.display(new NoteColor(colour), getLocation(), 1000);

			getLocation().subtract(x, y, z);
		}

		if (colour == 24) {
			colour = -1;
		}

		colour += 1;

		if (y < 0) {
			increase = false;
		} else if (y > 3) {
			increase = true;
		}

		if (increase) {
			y -= .1;
			r += .1;
		} else {
			y += .1;
			r -= .1;
		}
	}
}