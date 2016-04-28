# ParticleLib
A simple particle library for developers to build their own particles animations and learn from pre-made ones. Credit to DarkBlade12 for the ParticleEffect and ReflectionUtils classes.

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
	particleHelix.particleStyleType = ParticleStyleType.STATIC;
	particleHelix.loc = myCustomLocation;
	particleHelix.start();

```
