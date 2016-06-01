# Term2CSFInalProject

Team Name: Word Masters

Project Name: Wizarding Word of Java
Description: Our project is essentially a word processor in GUI form. Users will be able to accomplish the following in our text editor:
  -Undo work line by line
  -Change text from standard style into bold, italic
  -Highlight text
  -Change selected text font and size
  -Change orientation of the text (left-justified,right-justified,centered)

Ideas to possibly implement later (WAY later):
-collaborating with other users and editing the doc in real time
-allowing users to email documents to other users
-allowing users to print their edited documents

#Start here when updating the readme. I will fix the version numbering later.



05-30-16
Version 0.4.0
Sammy-Saving is a thing now!
Only saves as a text file. Can't open files either....
Yvonne - able to grab selected text but somehow I can't change only that part of the text like I could before, even when I select part of text the action changes the whole thing :(  must fix
       - also realized something is causing null pointer exception in Textbank, don't understand why it is

05-29-16
Yvonne - something broke with size selection and font selection and couldn't get it working, also text won't appear until action is performed
Sammy - helped fix those problems^ yay!


05-27-16/05-28-16
Yvonne - reformatted Textbank and nodes to only contain the String text and Font (which keeps track of the style and size as well)

05-26-16
Yvonne - continued working on changing specific parts of the text, got it working if I change each character/string manually in the code beforehand -- just have to get it working in realtime and making sure it updates

05-25-16
Yvonne - finally able able to obtain text in the textPane by utilizing position of caret(cursor) on the screen

05-24-16
Yvonne - mostly a research day to find alternatives to JTextField, tested a bunch of sample code to see what works best
       - found JTextPane and began replacing JTextField with that!
Sammy - began work on saving and saving as

05-23-16
Yvonne - changed name of textbox to textbank as to avoid confusion and began integrating textbank into the main Menu.java
       - found out we must replace JTextField since it is incapable of doing what we need it to do
*I realize the names are a bit confusing, please bare with me...

05-22-16
Yvonne - continued working on textbox to keep track of what is written, and each character's properties (i.e. its style, font, size)

05-20-16/05-21-16
Version 0.3.0
Yvonne - created a textbox to be implemented in the GUI (using an array of nodes to keep track of properties of each character)

05-19-16
Version 0.2.2
Yvonne - adjusted size and orientation of dropdown menu. Made it editable so users can type in desired font for easy access.
       - created new drop down menu to adjust the size of text

05-17-16
Version 0.2.1
Sammy - Made the default font size size 16 instead of size 12

05-17-16
Version 0.2.0
Sammy - Additions:
Different Fonts!
The ability to have a font be both bold AND italic
The ability to deselect bold and italic
The ability to select the font size

Things to fix:
The dropdown menu and button positions
The dropdown menu has multiple selections that are the same thing. Need a way to remove them...

05-17-16
Version 0.1.1
Sammy - Fixed the bold and italic buttons to actually work properly. Default font is not longer Java's default, but the first font that appears in the list of fonts.

05-15-16
Version 0.1.0
Yvonne - created GUI with textbox, bold/italic buttons, checkbox that allows user to pick text alignment

05-11-16
Hello! Welcome to MissingNo.!
