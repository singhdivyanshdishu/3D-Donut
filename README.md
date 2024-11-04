# Donut Animation in Java

This project is a simple console-based donut animation implemented in Java. The animation creates a 3D rotating donut shape using ASCII characters and color coding to enhance the visual effect.

## Features

- **3D Donut Animation**: The program uses mathematical functions to simulate a rotating 3D donut.
- **ASCII Art**: Utilizes characters to represent different brightness levels of the donut surface.
- **Color Output**: Implements ANSI color codes to create a gradient effect for the donut, enhancing the 3D illusion.
- **Smooth Animation**: The donut rotates smoothly on the console, providing a visually appealing effect.

## Requirements

- **Java Development Kit (JDK)**: Ensure you have Java JDK installed on your machine (version 8 or later recommended).
- **ANSI-Compatible Terminal**: The program requires a terminal or console that supports ANSI escape codes for color output (e.g., Linux terminal, Windows Terminal, or compatible IDE consoles).

## How to Run

1. **Clone the Repository** (if applicable):
   ```bash
   git clone https://github.com/singhdivyanshdishu/3D-Donut.git
   ```
2. **Compile the Program:**
   Open your terminal or command prompt, navigate to the directory containing the Donut.java file, and compile it using:
   ```bash
   javac Donut.java
   ```
3. **Run the Program:** After compiling, execute the program with the following command:
   ```bash
   java Donut
   ```
## Code Explanation
- **Mathematical Functions:** The program calculates the x, y, and z coordinates for points on the donut's surface using sine and cosine functions.
- **Color Management:** The donut surface is represented with different characters based on brightness, where brighter sections are denoted with more complex characters.
- **Animation Loop:** The donut continuously rotates by updating the angles A and B, which affects the appearance of the shape.

## Customization
You can customize the following parameters in the code:

- Speed of Animation: Modify the Thread.sleep(30); line to speed up or slow down the animation.
- Character Set: Change the string in the b[o] = ".,-~:;=!*#$@".charAt(Math.max(N, 0)); line to use different characters for the donut's surface.


## Troubleshooting
- If you encounter issues with character rendering or colors, ensure your terminal supports ANSI escape codes.
- Ensure that your console window is large enough to display the entire donut (80 characters wide and at least 22 lines tall).




https://github.com/user-attachments/assets/490c3d7d-8a76-4ef0-b2f3-d6a979c9c34c



