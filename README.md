# Animation Collection
A simple particle animation collection for developers to build their own particles animations and learn from pre-made ones. Credit to DarkBlade12 for the ParticleEffect and ReflectionUtils classes.

## Animations Plugin

### Animation Table
ParticleLib contains over 10 different pre-made particle animations that you are free to use, adapt and learn from. Below is a table containing all the information of the animations.

| Name        | Class         |  Update Frequency | ParticleStyleType | Image  |
| ----------- | ------------- | ----------------- | ----------------- | ------ |
| Swirl       | ParticleSwirl | 4                 | STATIC           |![alttext](https://github.com/moppletop/AnimationCollection/tree/master/images/swirl.png "Swirl")        |
| Blood Helix | ParticleHelix | 1                 | PLAYER_ORIENTATED |        |
| Flame Wheel | ParticleFlame | 1                 | PLAYER_ORIENTATED |        |

### Command usage
If you would like to see these particles in a live environment you can download a compiled version of this library here. Just use this command.

```

/particle <name>

Name being the class name without "Particle" infront of it.
For example for the Blood Helix animation you would use

/particle Helix

```

## Animations In Code
To use these particles in pre-made code is very easy, simply add the ```example JAR``` into your build-path or add the ```api``` package into your plugin.

###Pre-made Animations
**Step One** is to create an instance of ParticleManager. This is the class that manages all active animations. This can be done using the following code.

``` java

public class Main extends JavaPlugin {

	private ParticleManager particleManager;

	@Override
	public void onEnable() {
		particleManager = new ParticleManager(this); //You must pass in the instance of your plugin.
	}
	
	public ParticleManager getParticleManager() {
		return particleManager;
	}
}

```

**Step Two** is to create an instance of the actual particle class. This is an example of the Helix particle..

``` java

	ParticleHelix particleHelix = new ParticleHelix(getParticleManager(), 1); 
	//You must pass in your instance of ParticleManager and the frequency of the onUpdate() method. Same way as if you were using a BukkitRunnable.
	
	particleHelix.setIterations(10 * 20); 
	//This sets the number of iterations the onUpdate method will run.
	//We multiply the number of seconds (10) by the number of iterations the animation makes every second (20).
	
	particleHelix.setParticleStyleType(ParticleStyleType.STATIC); 
	//This means that the centre location of the animation will remain at the same x, y and z. 
	
	particleHelix.setLocation(myCustomLoc);
	
	particleHelix.start();

```

However, say if you wanted the animation to follow a player as they move around. This is also possible with the following code.

``` java

	ParticleHelix particleHelix = new ParticleHelix(particleManager, 1); 
	//You must pass in your instance of ParticleManager and the frequency of the onUpdate() method. 
	//Same way as if you were using a BukkitRunnable.
	
	particleHelix.setIterations(10 * 20); 
	//This sets the number of iterations the onUpdate method will run.
	//We multiply the number of seconds (10) by the number of iterations the animation makes every second (20).
	
	particleHelix.setParticleStyleType(ParticleStyleTypePLAYER_ORIENTATED); 
	
	particleHelix.setTarget(player); //You can either pass in a Player or an UUID.
	
	particleHelix.start();

```

Finally you may want to be aware of some other uses of the particle class:
* ```particle.stop()``` - Stops the particle.
* ```particle.setAutoTerminating(boolean)``` - Setting this to false will mean that the particle will never stop.
* When using ```ParticleStyleType.PLAYER_ORIENTATED``` if the target player leaves the server ```particle.stop()``` will be called automatically. 

### Custom Animations
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

**Step Two** is to write the logic for our particle. This is where your maths skills will be reqiured

> As a side note this is something which I cannot teach you in such a short time, I would recommend reading up on circle and co-ordinate geometry including radian measurements. If you are studying A-Level Mathematics (UK) you will learn about these topics and be able to apply it here. It is also a good idea to have a "play" around with the existing animations and see if you can make anything cool.

The following code creates a circle with a radius of 4 of flame particles around a player. 

``` java 

import io.github.moppletop.particlelib.api.Particle;
import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.util.ParticleEffect;

public class ParticleExample extends Particle {

	private final double r = 4; // The radius remains constant so it can be a private final variable.

	public ParticleExample(ParticleManager particleManager, int frequency) {
		super(particleManager, frequency);
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

```
Now lets allow users to type a command to use our animation.

``` java

import io.github.moppletop.particlelib.api.ParticleManager;
import io.github.moppletop.particlelib.api.ParticleStyleType;
import io.github.moppletop.particlelib.api.particles.ParticleExample;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private ParticleManager particleManager;

	@Override
	public void onEnable() {
		particleManager = new ParticleManager(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) { //Make sure the sender is a player
			Player player = (Player) sender; //The sender is a player so you can safely cast sender to a Player.
			if (command.getName().equalsIgnoreCase("example")) { //Is the command /example?
				if (player.hasPermission("particle.example")) { //Lets just check if they have a permission
					ParticleExample particleExample = new ParticleExample(particleManager, 5); //Now setup our animation
					particleExample.setAutoTerminating(false);
					particleExample.setParticleStyleType(ParticleStyleType.PLAYER_ORIENTATED);
					particleExample.setTarget(player);
					particleExample.start();
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission!"); //Send them a nice message
				}
			}
		} else {
			sender.sendMessage("You must be a player!"); //They are the console.
		}
		return false;
	}

	public ParticleManager getParticleManager() {
		return particleManager;
	}
}

```

**Step Three** Try it out! See how the effect looks, it may take you many attempts to make it look perfect but keep trying. 
