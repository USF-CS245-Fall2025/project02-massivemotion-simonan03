# MassiveMotion
CS 245 Project 02

Simona Nigusse 
CS 245 EJ Jung 
Project 2

The repository I submitted includes the MassiveMotion.java and body.java, as well as all the implementations of the List interface which are ArrayList.java, DoublyLinkedList.java, DummyHeadLinkedList.java, and LinkedList.java.

Requirement 1: Reading a property file: This is met by running the main method in MassiveMotion.java, which will use the MassiveMotion constructor to read the configuration file and properly use the properties library. This will utilize the java.util.properties.

Requirement 2: Creating List realisations: This is met by using my own realizations, the implementations of the List interface, which are ArrayList.java, DoublyLinkedList.java, DummyHeadLinkedList.java, and LinkedList.java.

Requirement 3: Creating a canvas: This is met starting from the MassiveMotion constructor. Once the method reads what should be window_size_y and window_size_x, it will apply those dimensions to the array called canvas_dimensions.

Requirement 4: Creating and maintaining a List of celestial objects — one “star” and several random “comets”: This requirement has been started in the constructor. The constructor will read the values required for the additional celestial bodies that are to come. Using those values, it will be input into the body generator function, which will randomly assign velocities and spawn locations for the celestial bodies based on the configuration data. Then the actionPerformed function will help manage how the celestial bodies will move and be removed/deleted.

MassiveMotion acts as the main program that helps create and manage all the celestial bodies and stars that my project should simulate. The constructor itself is meant to read all values that would exist in the configuration file and properly assign them to their appropriate variables. The paint component will paint the main star and other celestial bodies. What I did include that wasn't asked in the requirements was a border designated by a blue line, which can help me see where the boundary is when the celestial body crosses it. To see it, it maximizes the canvas itself.

The body class is used to make a single celestial body as it stores its velocity, their position, and their size. It also has methods to update how the object moved so MassiveMotion knows where to draw it. The list implementations handles the tracking of all the celestial bodies in the simulation by providing the add, remove, and get methods.

How to Run The program:
In the terminal (or command prompt), make sure you are in the src folder and run this line "javac *.java". Once You had done that, enter "java MassiveMotion MassiveMotion.txt". You can replace MassiveMotion.txt with any other filename you would like to use for testing.