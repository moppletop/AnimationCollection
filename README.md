# Animation Collection
A simple particle animation collection for developers to build their own particles animations and learn from pre-made ones. Credit to DarkBlade12 for the ParticleEffect and ReflectionUtils classes.

## Premade Animations

### Animation Table
ParticleLib contains over 10 different pre-made particle animations that you are free to use, adapt and learn from. Below is a table containing all the information of the animations.

| Name        | Class         |  Update Frequency | ParticleStyleType | Image  |
| ----------- | ------------- | ----------------- | ----------------- | ------ |
| Swirl       | ParticleSwirl | 4                 | STATIC            |        |
| Blood Helix | ParticleHelix | 1                 | PLAYER_ORIENTATED |        |
| Flame Wheel | ParticleFlame | 1                 | PLAYER_ORIENTATED |        |

### Command usage
If you would like to see these particles in a live environment you can download a compiled version of this libary here. Just use this command.

```

/particle <name>

Name being the class name without "Particle" infront of it.
For example for the Blood Helix animation you would use

/particle Helix

```

### Animations In Code
To use these particles in pre-made code is very easy, simply add the ```example JAR``` into your build-path or add the ```api``` package into your plugin.

**Step One** is to create an instance of ParticleManager. This is the class that manages all active animations. This can be done using the following code.

``` java

public class Main extends JavaPlugin {

	private ParticleManager particleManager;

	@Override
	public void onEnable() {
		particleManager = new ParticleManager(this); //You must pass in your instance of your plugin.
	}
	
	public ParticleManager getParticleManager() {
		return particleManager;
	}
}

```

**Step Two** is to create an instance of the actual particle class. This is an example of the Helix particle..

``` java

	ParticleHelix particleHelix = new ParticleHelix(getParticleManager(), 1); //You must pass in your instance of 
	//ParticleManager and the frequency of the onUpdate() method. 
	//Same way as if you were using a BukkitRunnable.
	
	particleHelix.iterations = 10 * 20; //This sets the number of iterations the onUpdate method will run.
	//We multiply the number of seconds (10)
	//by the number of iterations the animation makes every second (20).
	
	particleHelix.particleStyleType = ParticleStyleType.STATIC; //This means that the centre location of the 
	//animation will remain at the same x, y and z. 
	
	particleHelix.loc = myCustomLocation;
	
	particleHelix.start();

```

However, say if you wanted the animation to follow a player as they move around. This is also possible with the following code.

``` java

	ParticleHelix particleHelix = new ParticleHelix(particleManager, 1); //You must pass in your instance of 
	//ParticleManager and the frequency of the onUpdate() method. 
	//Same way as if you were using a BukkitRunnable.
	
	particleHelix.iterations = 10 * 20; //This sets the number of iterations the onUpdate method will run.
	//We multiply the number of seconds (10)
	//by the number of iterations the animation makes every second (20).
	
	particleHelix.particleStyleType = ParticleStyleType.PLAYER_ORIENTATED; 
	
	particleHelix.setTarget(player); //You can either pass in a Player or an UUID.
	
	particleHelix.start();

```

Finally you can also stop a particle early by using ```particleHelix.stop();```.

## Custom Animations
Now that you have a grasp on how to use pre-made animations, lets make some of our own.

**Step One** is to create our class and setup the constructor. You can do this like so.

``` java

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;

public class ParticleExample extends Particle {

	public ParticleExample(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
	}

}

```

**Step Two** is to write the logic for our particle. This is where your maths skills will be requrired

> As a side note this is something which I cannot teach you in such a short time, I would recommend reading up on circle and co-ordinate geometry including radian measurements. If you are studying A-Level Mathematics (UK) you will learn about these topics and be able to apply it here. It is also a good idea to have a "play" around with the existing animations and see if you can make anything cool.
