package io.github.moppletop.particlelib.api.particles;

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.util.ParticleEffect;
import io.github.moppletop.particlelib.api.util.UtilVector;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ParticleFireworkSpiral extends Particle {

	private boolean calculated = false;
	private List<Location> points = new ArrayList<>();
	private int i = 0;
	private double r = 2;

	public ParticleFireworkSpiral(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
	}

	@Override
	public void onUpdate() {
		if (!calculated) {
			Location start = getLocation();
			double t = 0, x = 0;

			for (int i = 0; i < 300; i++) {
				points.add(getLocation().clone().add(x, r * Math.cos(t), r * Math.sin(t)));
				points.add(getLocation().clone().add(x, r * Math.cos(t + Math.PI), r * Math.sin(t + Math.PI)));

				t += Math.PI / 20;
				x += .2;
			}

			double yaw = getLocation().getYaw(), pitch = getLocation().getPitch();

			for (int i = 0; i < points.size(); i++) {
				Location point = points.get(i);
				Vector vector = UtilVector.getVector(start, point);

				UtilVector.rotateAroundAxisY(vector, yaw + 90);
				UtilVector.rotateAroundAxisZ(vector, pitch);

				point = start.clone().add(vector);

				points.set(i, point);
			}

			calculated = true;
		} else {
			for (int j = 0; j < 3; j++) {
				if (i + j > points.size() - 1)
					break;
				Location point = points.get(i);

				ParticleEffect.FIREWORKS_SPARK.display(0F, 0F, 0F, 0.001F, 1, point, 1000D);

				i++;
			}
		}

	}
}
