Buildings should consist of a png file that is an image of the building and a txt file that contains data on the builidng.

The txt file's first line should be a float that describes the size of the building (e.g. 10 is 10 tiles wide, and since a tile is 50 pixels, 500 pixels large)

The txt files's next lines should be collision data. You should describe them as x,y,xSize,ySize
	Your numbers should be floats IN FRACTIONS OF THE BUILDING SIZE 0.0f to 1.0f measured from the bottom left! 
When you're done naming colliders, type in "END"

After this, you should describe spawn details. They should be in the form x:y:objectA:objectB:objectC:objectN
	x and y are the float positions IN FRACTIONS OF THE BUILDING SIZE 0.0f to 1.0f measured from the bottom left! 
		For instance, if the building is 100px wide, and you want the x coord of 10 px, then enter 0.1f for it.
	The odds of the object spawning are how many times the object appears out of how many objects there are in the list.
		To make a spawn point that has a 25% chance of spawning a goblin, a 50% chance of spawning a chest, and a 25% chance of spawning nothing, type .5f:.5f:goblin:chest:chest:none
