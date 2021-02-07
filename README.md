# StrongholdMC

**This is work in progress. Currently not available for download.** <br />

This application takes four measurements in-game as input and calculates estimated position of the stronghold. In the minecraft speedrunning community it is often referred to as "triangulation".
To make it convenient, the app scans for keyboard input to automatically paste coordinates upon copying them using F3 + C command in-game. As it currently stands, being external software,
this app is not allowed in official speedruns and using it may result in run invalidation or a ban.

### How to use <br />
1. Launch the app
2. Press F3 + C while in game, throw a pearl, walk in the direction of the pearl, press F3 + C again
3. Walk a number of blocks away (the further away you are from the stronghold, the more you should walk for accurate measurement)
4. Repeat step 2
5. The app should by now show coordinates of the stronghold

## Optimal usage recommendations <br />
- you can use optifine to zoom in on the pearl (by default C button), and then walk in its direction for the most accurate measurement (make sure to disable cinematic camera during zoom, you have to manually disable it everytime you zoom)
- this program is usually pinpoint chunk accurate when measurements are taken accurately (crosshair in the middle of the floating pearl, walk forward while not moving your crosshair), but results under 3 degree angle of intersection (rare) should be taken with a grain of salt
- the higher the degrees and the further you walk away from the first throw measurement, the more erroneous the second measurement can be while still being accurate.
- you don't have to reach the pearl's location when taking measurements, it's enough to walk just 3 blocks or so in its direction.
- it's best to take measurements at around 500-1000 blocks away from origin point and on relatively flat terrain (don't move your crosshair while walking in the direction of the pearl)
- display chunk borders (F3 + G) to make sure the program doesn't point on the border between two chunks (in which case it's either of them)
- done in the most optimal way, it only takes 3 eye throws to reach the stronghold.

### TODO:
- Add fat jar download
- Clean the code
