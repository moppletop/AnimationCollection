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

public class ParticleArc extends Particle {

	private boolean calculated = false;
	private List<Location> points = new ArrayList<>();

	public ParticleArc(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
		this.setParticleStyleType(ParticleStyleType.STATIC);
	}

	@Override
	public void onUpdate() {
		if (!calculated) {
			Location start = getLocation();
			double x = 0;

			for (double t = 0; t < Math.PI; t += Math.PI / 50) {
				double y = 5 * Math.sin(t);
				points.add(getLocation().clone().add(8 * x, y, 0));
				points.add(getLocation().clone().add(8 * x, y, 0));

				x += .05;
			}

			double yaw = getLocation().getYaw();

			for (int i = 0; i < points.size(); i++) {
				Location point = points.get(i);

				Vector vector = UtilVector.getVector(start, point);
				UtilVector.rotateAroundAxisY(vector, yaw + 90);

				point = start.clone().add(vector);

				points.set(i, point);
			}

			calculated = true;
		} else {
			for (Location point : points) {
				ParticleEffect.FLAME.display(0, 0, 0, 0, 1, point, 1000);
			}
		}
	}
}