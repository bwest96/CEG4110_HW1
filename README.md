# CEG4110_HW1 Brian West
git repo: https://github.com/bwest96/CEG4110_HW1.git
## Screen Shots
<img src="https://github.com/bwest96/CEG4110_HW1/blob/master/SmartSelect_20180918-162134.jpg" align="left" width="300" height= "600" hspace="10" vspace="10"></a>
<img src="https://github.com/bwest96/CEG4110_HW1/blob/master/SmartSelect_20180918-162205.jpg" align="left" width="300" height= "600" hspace="10" vspace="10"></a>
<img src="https://github.com/bwest96/CEG4110_HW1/blob/master/SmartSelect_20180918-162231.jpg" align="left" width="300" height= "600" hspace="10" vspace="10"></a>

## Usage
To install and run app execute app-release.apk located in the bin directory.
The app requires permission to write to storage.

The app has two different UIs. The first UI is an interface to change the color of a text field. On the button, "Change Color", click the text field text changes color to a random rgb color and text changes to show rgb value and HTML color code. 

The app uses a switch to change between the two UIs.

The second UI is a paint canvas to draw images on. The UI allows the ability to clear and save the canvas. The save funtcion saves the image to the DCIM directory. Also the app has the ability to let you change the color you draw with. Change color uses an UI with 3 seekBars for red, green and blue, with a color previewer that updates when pushed. 

## Design
I designed the software to switch UIs using a switch control. Each UI has it own set of controlls when the switch is swithed on the controls for the first UI visibility is turned of and the second UI (paint UI) visibility is turned on and vice versa. I did it this way because they there were not that many controls that this works quickly. The drawing interface I used two classes that I added to a package, which was obtained from https://gist.github.com/ssaurel/688a4d5cdc3d96b3ee50ebfae6b3bd10#file-paintview-java and https://gist.github.com/ssaurel/747c5c591f783450a30925543ba93c10#file-fingerpath-java . I used these packages because it was a simple paint classes and easy to edit, and had all the functions I needed except 2 functions. I edited PaintView.java to create a setter for currentColor to change FingerPath color and also create a getter to the bitmap to save the PaintView. To change the color I there are 3 seekBars to get rgb values to change the fingerpaths color. There is a preview update button that updates to show the color the seekbars values make, and the done to make the color change interface disappear and change the fingerpaths color.
