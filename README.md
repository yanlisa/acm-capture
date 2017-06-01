# Java ACM GraphicsProgram Capture
This basic extension of the Java ACM GraphicsProgram allows you to capture images within an animation loop of a GraphicsProgram. By saving the canvas periodically to jpgs, you can create gifs with ImageMagick.

To use:

* Copy the GraphicsProgramCapture to your Eclipse project and extend your desired class:

```public class AwesomeProgram extends GraphicsProgramSolution { ... }```

* In your program, when you want to save an image, call ```saveFrame()```:

        while(true) {
            updateAnimation();
            saveFrame();
            pause(DELAY);
        }

* All images will be saved as jpgs to a folder called ```output``` in the Eclipse project.

You can then convert the jpg images to a gif with your favorite converter. There are many free converters online, or you can use imagemagick:

* ```brew install imagemagick``` for Mac or ```sudo apt-get install imagemagick``` for Linux

* ```convert -delay 100 -loop 0 output/*.jpg test.gif``` reads the jpgs in the directory ```output``` and creates ```test.gif``` that loops and changes images once a second (delay is in centiseconds).

It is recommended that you go through the images first and cull the ends so that you can make a perfect loop.
