# Project 1: NBody

## Background
This assignment heavily borrows from Princeton and Berkeley Computer Science and the work of Robert Sedgewick, Kevin Wayne and Josh Hug.

Context. In 1687, Isaac Newton formulated the principles governing the motion of two particles under the influence of their mutual gravitational attraction in his famous Principia. However, Newton was unable to solve the problem for three particles. Indeed, in general, solutions to systems of three or more particles must be approximated via numerical simulations.
For a more complete understanding of the Physics you can reference this document.

In this assignment, you will write a program to simulate the motion of N objects in a plane, mutually affected by gravitational forces, and animate the results. Such methods are widely used in cosmology, semiconductors, and fluid dynamics to study complex physical systems. Ultimately, you will be creating a program NBody.java that draws an animation of bodies floating around in space tugging on each other with the power of gravity.
Here's an animation of a completed project running with some planets in our solar system. The animation repeats after one earth year, but your program should continue.

## Starter Code
You should have been invited to join the project in GitHub classroom via this link: https://classroom.github.com/a/s6z8ULQg  

If that doesn't work, you can clone the repo directly from GitHub here: https://github.com/DukeCompsci201Fall2018/nbody-fall2018  
The starter code contains several image files in the images folder, data for several simulations in the data folder, the beginning of the NBody class in NBody.java, many testing Java files and library files for drawing and audio. If you're using Eclipse, you'll need to use the Eclipse configuration files (e.g., .classpath and .project) that you receive from the starter code. If you're not using Eclipse, you might want to delete the dot files.
Note: When you use your GitHub repository and clone the starter code and import to Eclipse you'll see several red-X error indications. Each time you successfully complete and test the code/methods in the class Body.java below some of the red-X's will be removed.

### Importing into Eclipse
Use these steps to clone the project and import to Eclipse:

Navigate in your Git shell using cd commands to your Eclipse workspace. Use pwd to verify you're there.
In your project’s homepage on GitHub, you'll see the SSH URL for the project you’ll use to clone the repository: see the image to the right.
Copy the SSH URL using Command-C/Control-C
DO NOT use “Download ZIP”!
In the Git shell, type `git clone <your-project-URL>`
Replace “<your-project-URL>” with the SSH URL you copied. Use Command-V/Control-V to paste.
Typing `ls` should show a directory with the project name, which contains the files for the project.
Using the Eclipse File/Import menu, import an existing project into the workspace. You'll need to navigate to the folder/directory created when you used the git clone command. Use this for the project. DO NOT DO NOT import using the Git open. See the screen shot to the right. Using General>Existing Projects Into Workspace.


### Pushing to Git

When you make a series of changes you want to 'save', you'll push those changes to your GitHub repository. You should do this after major changes, certainly every hour or so of coding. You'll need to use the standard Git sequence to commit and push to GitHub:

```bash
  git add .
  git commit -m 'a short description of your commit here'
  git push
```
  
## High-Level TODOs

You'll create a class `Body.java` as described below. This class represents a Celestial Body such as a planet or a sun. You'll implement constructors, methods to get the state of a Body (getters), and methods that determine the interactions between Body objects due to gravitational forces. There are classes provided that help you test whether your constructors, getters, and interaction methods are correct.
You'll create a class `NBody.java` that drives a simulation between planets, suns, and celestial bodies interacting. This class will read a file of data that specifies the initial positions and masses of the bodies and then simulates there interaction over a set time period. The simulation will also animate the interactions between the bodies.


## Details
You'll implement the `Body` class as described below. You must create this class from scratch, no class in part of the starter files. You'll do this in six steps, some of which require reading about the gravitational, physical interactions between two Body objects. Each step has a class to test whether your code works -- tests that are a strong indication your code works correctly. Be sure you pass the tests in each step before proceeding to the next step.

When your Body.java class passes all tests you'll write code to simulate the interactions between N bodies. This code will be in the class NBody.java. You'll have some testing code for this class, but testing and debugging will require running simulations to see if the visualization and output match expected results.

Implement the Body class
The outline from Eclipse to the right shows all the instance variables, constructors, and methods of the Body class. 

All instance variables should be private. All methods should be public (if you write helper methods they should be private).
Instance variables, Constructors, Getters
You'll have six instance variables as shown in the Body outline: myXPos, myYPos, myXVel, myYVel, myMass, myFileName. You can see this in the diagram above.

You'll have two constructors: one in which values for each instance variables are given as parameters, and one that creates a Body by copying another. The signatures of these constructors are shown below. 








You'll also write six so-called getter methods specified in the diagram above. The body of each method is a single return statement, returning the value of the corresponding instance variable. These getter methods allow the values of private instance variables to be accessed outside the class. For example, the method getYVel() is shown to the right. These are getter methods because they do not allow client programs to set the values, only to get the values.

When you've implemented the two constructors and the six getter methods you should be able to run the program in TestBodyConstructor.java to see if your code is correct. When it reports that everything works you can proceed to the next step in implementing the Body class.
The method Body.calcDistance
This method returns the distance between two Body objects. Use the standard distance formula to determine the distance between this body (using myXPos and myYPos) and the Body object specified by the parameter. The distance is the value of r in the formula below where
 
where dx is delta/difference between x-coordinates, similarly for dy.  Use Math.sqrt to calculate the return value. 

Test your implementation by running the program in TestCalcDistance.java.

The method Body.calcForceExertedBy
This method calculates and returns the force exerted on this body by the body specified as the parameter. You should calculate the force using the formula below. You can read about the physics of this formula in the NBody Physics document.
 
Here m1 and m2 are the masses of the two bodies, G is the gravitational constant (6.67 * 10-11 N-m2 / kg2), and r is the distance between the two objects. Call calcDistance to determine the distance. You can specify G as 6.67*1e-11 using scientific notation in Java. 

When you've implemented this method, test it by running TestCalcForceExertedBy.java.

The methods Body.calcForceExertedByX and calcForceExertedByY
These two methods describe the force exerted in the X and Y directions, respectively. The signature of calcForceExertedByX is shown above; calcForceExertedByY has a similar signature. 

You can obtain the x- and y-components from the total force using these formulas below, where F is the value returned by calcForceExertedBy, r is the distance between two bodies, and Fx and Fy are the values returned by calcForceExertedByX and calcForceExertedByY, respectively. Note that dx that dy in the formula are DeltaX and DeltaY, the difference between x and y coordinates, respectively.


Note: Be careful with the signs! In particular, be aware that dx and dy are signed (positive or negative). By convention, we define the positive x-direction as towards the right of the screen, and the positive y-direction as towards the top. You likely do not need to worry about this if you simply use the formulas shown here.

You can read about the physics for these formula in the NBody Physics document. You can test them using the program in TestCalcForceExertedByXY.java.

The method Body.calcNetForceExertedByX and calcNetForceExertedByY
This method returns the total/net force exerted on this body by all the bodies in the array parameter. The principle of superposition (see Physics) says that the net force acting on a body by many bodies is the sum of the pairwise forces acting on the body. So you'll need to sum the forces returned by calcForceExertedByX (or Y) in calculating the value to return. 

You must make sure NOT to include the force exerted by a body on itself! The universe might collapse if an object attracted itself. If you loop over each element in array bodies, you'll need code like this to avoid summing the force of an object on itself. In the body of the if statement you'd write code to accumulate the sum of all forces exerted on this Body by the Body b.

You can test your code by running the program in TestCalcNetForceExertedByXY.java.

The method Body.update
This method is a so-called mutator. It doesn't return a value, but updates the state/instance variables of the Body object on which it's called. 

This method will be called during the simulation to update the body's position and velocity with small time steps (the value of the first parameter, deltaT). The values of parameter xforce and yforce are the net forces exerted on this body by all other bodies in the simulation. When you’re calling the update method from NBody.java, you will determine the values of the arguments passed as these two parameters when calling calcNetForceExertedByX (or Y). In the formulas below the parameter xforce is Fx and yforce is Fy. 

This method updates the instance variables myXPos, myYPos, myXVel, and myYVel in four steps. In the formulas below the parameter xforce is Fx and yforce is Fy. 

First, calculate the acceleration using Newton's second law of motion where m is the mass of the body. This creates two variables for acceleration in the x and y directions.
 

You'll then calculate values for new myXVel and myYVel, we'll call these nvx and nvy where the n is for new, using the relationship between acceleration and velocity, e.g., nvx = myXVel + deltaT*ax.

You'll use nvx (and a corresponding nvy) to calculate new values for myXPos and myYPos using the relationship between position and velocity, e.g., nx = myXPos + deltaT*nvx.

After you've calculated nx,ny,nvx, and nvy  you'll assign these to the instance variables myXPos, myYPos, myXVel, and myYVel, respectively. 

These steps will update the position and velocity of the body making the simulation possible. You can test this method using TestUpdate.java.

After developing, implementing, testing, and debugging these Body methods you're ready to move to the simulation code.

The method Body.draw
This void method is described below in the section for NBody that describes where to call the Body.draw method.
Implement the NBody class
This class consists only of static methods, including the main method that runs the simulation. Your task will be to implement the three static methods that have been started for you in the starter code you clone from Git. That code has // TODO comments indicating the code you need to add in the three static methods. These methods are described below.
File Format
The data for planets, suns, and celestial bodies in general is in the format shown below. All files in the folder data are in this format. This is the file planets.txt:


The first value is an integer n, the number of bodies for which data is given in the file. The next value is a double, the radius of the universe for the simulation. This value is used to set the scale for the animation.

There are n lines, one line for each body. Each line contains six values as shown above. The first five values are doubles: the first two are initial x and y coordinates; the next two are initial x and y velocities; the next is the mass of the body. The last value on a line is a String specifying the file in the images folder used for the animation of the simulation.
The Method NBody.readRadius
Given a file name, this method should return a double corresponding to the radius of the universe in that file, e.g. readRadius("./data/planets.txt") should return 2.50e+11. You'll need to read the int value that's the number of bodies, then read the double value for the radius. Use s.nextInt() and s.nextDouble() for the Scanner variable s to read an int and double value, respectively.

You can test your method using the provide TestReadRadius.java program.
The Method NBody.readBodies
This method returns an array of Body objects using the data read from the file. For example, readBodies("./data/planets.txt") should return an array of 5 Body objects.

You will find the nextInt(), nextDouble(), and next() methods in the Scanner useful in reading int, double, and string values, respectively.

You can test this method using the supplied TestReadBodies.java class. You should be sure to call this method in main to initialize the array of Body objects there. 
The Method NBody.main
You'll see four TODO comments in the loop of the main program. Completing these will make your simulation run correctly and provide an animation of the simulation. 

Completing the last TODO first will show a non-moving image for each body in the simulation. You'll write a for-each loop over each Body in the array bodies in the main method. In the loop you'll call the Body.draw method on each Body in the array. You'll need to implement this method in the Body class as shown above.

Most of the other TODOs in the loop will also be replaced by a loop, just as the drawing TODO used a loop over all the bodies.
Create an xForces array and yForces array. Each should have the same size as the number of bodies in the simulation. You'll make new arrays on each iteration of the outer/simulation loop.
(loop over bodies) Calculate the net x and y forces for each body, storing these in the xForces and yForces arrays respectively. You'll need to loop over bodies to do this, updating array entries in your loop. You'll call calcNetForceExertedByX, for example, to determine the values stored in the xForces array.
(loop over bodies) Call update on each of the bodies. This will update each body's position and velocity. Again, you'll write a loop over bodies to do this. A separate loop after the previous one.

Printing the Universe
When the simulation is over your code prints out the final state of the universe in the same format as the input, e.g.:
5
2.50e11
 1.4925e+11 -1.0467e+10  2.0872e+03  2.9723e+04  5.9740e+24    earth.gif
-1.1055e+11 -1.9868e+11  2.1060e+04 -1.1827e+04  6.4190e+23     mars.gif
-1.1708e+10 -5.7384e+10  4.6276e+04 -9.9541e+03  3.3020e+23  mercury.gif
 2.1709e+05  3.0029e+07  4.5087e-02  5.1823e-02  1.9890e+30      sun.gif
 6.9283e+10  8.2658e+10 -2.6894e+04  2.2585e+04  4.8690e+24    venus.gif
The code for printing is given to you in the NBody.java you start with. This code isn't all that exciting (which is why we've provided a solution), but we'll need this method to work correctly to autograde your assignment. You should only print after your simulation completes. You should NOT print anything other than the final printing shown here.


## Submitting
Push your code to Git. Do this often.  You can use the autograder on Gradescope to test your code. UTAs will be looking at your source code to view documentation and your analysis.txt file, but you will be able to see the autograding part of your grade -- worth 16 points. Note that you must complete the reflect form to get all 16 points, but you should NOT complete the reflect form until you're done with all the coding portion of NBody. Since you may uncover bugs from the autograder, you should wait until you've completed debugging and coding before completing the reflect form.

After completing the reflect form you should run the autograder again to be sure to get the point for completing the reflect.

## Reflect Form
https://www2.cs.duke.edu/courses/fall18/compsci201/reflect/ 


## Grading
Each method you write in the Body class should have a Javadoc comment. You can have the skeleton for such a comment generated automatically after you write the method header. With the cursor on the line above the header type /** and hit return. You'll see the outline generated and you can fill in the comment. See examples above given for the constructors and one of the getter methods.

The autograder will check all your Body methods and the NBody methods as well. You'll be able to see the results of running the tests when you submit to gradescope.
