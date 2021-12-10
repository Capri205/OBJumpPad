# OBJumpPad
Simple jump pad plugin for our lobby.

Using a block and a pressure plate combination to launch a player.

The configuration file contains these defaults, but set them per your liking:

power: 2.0<br>
vpower: 1.0<br>
plate: POLISHED_BLACKSTONE_PRESSURE_PLATE<br>
block: REDSTONE_BLOCK<br
sound: BLOCK_LAVA_EXTINGUISH<br>
effect: CLOUD<br>

Power determines the forward distance, whilst vpower determines the height the player is launched.<br>
The plate is the Minecraft pressure plate of choice and under that will be the block of choice.<br>
Likewise for sound and particle effects. To get a list of all the blocks, sounds and particles<br>
you should search up something like "spigot api sounds" etc.<br>

Compiled with Java 17 for Spigot 1.18.