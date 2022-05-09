# StrongholdMC

This application takes four measurements in-game as input and calculates estimated position of the stronghold in the process known as triangulation. 
To make it convenient, it scans for keyboard input to automatically paste coordinates from the clipboard upon copying them using F3 + C command in-game.
Uses a toggable in-game overlay to display results live without the need for a second monitor or an alt-tab to see the results.
At ~1500 blocks distance and ~100 block walk away from the first throw, you can pinpoint the location of the stronghold chunk to which the eye points to.
StrongholdMC is not allowed in official speedruns and using it may result in your run being invalidated or a ban.

### How to use
1. Launch (and toggle overlay)
2. Press F3 + C while in game, throw a pearl, walk in the direction of the pearl, press F3 + C again
3. Walk ~100 blocks away to the side (the further away you are from the stronghold, the more you should walk for accurate measurement)
4. Repeat step 2
5. Results will be displayed (coordinates of the stronghold, angle of line intersection, amount of blocks away)

### Optimal usage recommendations
- when measuring, focus your crosshair on the middle of the eye and don't move it while walking forward; it's enough for walk for 3 blocks or so for accurate measurements
- you can use optifine to zoom in on the eye (by default C button)
- results under 3 degree angle of intersection should be taken with a grain of salt as they leave little to no room for error in the measurements
- display chunk borders (F3 + G) when looking for the stronghold

### How does it work
StrongholdMC utilizes coordinate geometry to locate the stronghold in the process called triangulation, meaning the process of determining the location of a point by forming triangles to it from known points. The script calculates the slope of two lines based on four coordinates taken as input (first throw XZ and eye XZ, and second throw XZ and eye XZ). Then, given slope, it calculates intercept of each line, meaning the point at which each line crosses the y-axis. Finally, given line equations (expressed as e.g. y = 3x-3 and y = 2.3x+4), the script calculates their point of intersection, angle of intersection, and the number of blocks away from the player. Due to limitations of Java, [JNativeHook](https://github.com/kwhat/jnativehook) is used to scan for keyboard input, and [GHTools](https://github.com/Erarnitox/java-game-hacking) is used display the overlay over the game's window.

### Dependencies
- [Java Native Access Platform](https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform)
- [JNativeHook](https://github.com/kwhat/jnativehook)
- [GHTools](https://github.com/Erarnitox/java-game-hacking)

### TODO:
- Fat jar download
