# StrongholdMC

**This is work in progress. Currently not available for download.** <br />

This application takes four measurements in-game as input and calculates estimated position of the stronghold in the process known as triangulation. 
To make it convenient, the app scans for keyboard input to automatically paste coordinates upon copying them using F3 + C command in-game.
StrongholdMC also introduces a toggable in-game overlay to display results live without the need for a second monitor or an alt-tab to see the results.
Given minecraft's limitation, this program is very accurate; at ~1500 blocks distance and ~100 block walk away from the first throw, it can pinpoint the location of the stronghold chunk to which the eye points to.
As it currently stands, being external software,
StrongholdMC is not allowed in official speedruns and using it may result in your run being invalidated or a ban.

### How to use
1. Launch the app
2. Press F3 + C while in game, throw a pearl, walk in the direction of the pearl, press F3 + C again
3. Walk a number of blocks away (the further away you are from the stronghold, the more you should walk for accurate measurement)
4. Repeat step 2
5. The app should by now show coordinates of the stronghold

### Optimal usage recommendations
- **when measuring, focus your crosshair on the middle of the eye and don't move it while walking forward; it's enough for walk for 3 blocks or so for accurate measurements**
- you can use optifine to zoom in on the eye (by default C button), and then walk in its direction for the most accurate measurement (make sure to disable cinematic camera during zoom, you have to manually disable it everytime you zoom)
- results under 3 degree angle of intersection (rare) should be taken with a grain of salt as they leave no room for error in the measurements
- the further you walk away from the first throw measurement (the higher the angle of intersection), the more erroneous the second measurement can be while still being accurate
- display chunk borders (F3 + G) to make sure the program doesn't point on the border between two chunks (in which case it's either of them)

### Usage during a speedrun 
- there are two ways of utilizing this program while speedrunning:
- throw about 500-1000 blocks away from the origin point, measure, walk 50 blocks to the side, measure again, and it should pinpoint the chunk of the stronghold. If you want to make sure, throw once again while at the presumed stronghold location. This takes 3 eye throws to do, 4 if you want to be sure at the destination.
- throw upon exiting the portal, which you would do anyways, and measure. Then, upon travelling 500-100 blocks, walk some blocks to the side of your path, measure again. Again, this should pinpoint the location, but pay attention to the degrees of intersection in case you somehow lost track while travelling and took the second measurement at near parallel angle. This takes 2 eye throws to do, 3 if you want to be sure at the destination.

### How does it work
StrongholdMC utilizes coordinate geometry to locate the stronghold in the process called triangulation, meaning the process of determining the location of a point by forming triangles to it from known points. The program calculates the slope of each line based on four coordinates taken as input (first throw XZ and location of the eye XZ, and second throw XZ and location of the eye XZ). Then, given slope, it calculates intercept of each line, meaning the point at which each line crosses and y-axis. Lastly, it takes both line equations to calculate the locaion of the line. Simple equations are added to calculate angle at which the lines intersect and the number of blocks away from last known location. In order to automate the measurement process, due to limitations of Java, JNativeHook is used to scan for keyboard input. On F3 + C, Minecraft will copy the location of the player to clipboard, and StrongholdMC will automatically copy the contents of clipboard as input.


### TODO:
- Add fat jar download
- Clean the code
- Credits
