public class SineWave {
    public static void main(String[] args) {
        int height = 10; // Height of the wave
        int width = 100; // Width of the wave
        double frequency = 0.1; // Frequency of the sine wave

        // Print the wave with axes
        for (int y = height; y >= -height; y--) {
            for (int x = 0; x < width; x++) {
                // Calculate the sine wave value
                double sineValue = Math.sin(frequency * (x - (width / 4))); // Shift to center the wave

                // Scale and shift sineValue to fit in the height
                int scaledY = (int) (sineValue * (height / 2)); // Scale from [-1, 1] to [-height/2, height/2]

                // Draw the sine wave
                if (y == scaledY) {
                    System.out.print('*'); // Print the sine wave
                } else if (y == 0) {
                    System.out.print('-'); // Print the x-axis
                } else if (x == width / 4) { // Y-axis position
                    System.out.print('|'); // Print the y-axis
                } else {
                    System.out.print(' '); // Print space
                }
            }
            System.out.println(); // Move to the next line after printing one wave line
        }

        // Print the x-axis (no labels)
        System.out.print(" "); // Indent for the labels
        for (int i = 0; i < width; i++) {
            System.out.print(' '); // Print spaces for x-axis without labeling
        }
        System.out.println(); // New line for the labels
    }
}
