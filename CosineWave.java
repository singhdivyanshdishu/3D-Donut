public class CosineWave {
    public static void main(String[] args) {
        int height = 10; // Height of the wave
        int width = 100; // Width of the wave
        double frequency = 0.1; // Frequency of the cosine wave

        // Print the cosine wave
        for (int y = height; y >= -height; y--) {
            for (int x = 0; x < width; x++) {
                // Calculate the cosine wave value
                double cosineValue = Math.cos(frequency * x); // Cosine value based on x

                // Scale and shift cosineValue to fit in the height
                int scaledY = (int) (cosineValue * (height / 2)); // Scale from [-1, 1] to [-height/2, height/2]

                // Draw the cosine wave
                if (y == scaledY) {
                    System.out.print('*'); // Print the cosine wave
                } else if (y == 0) {
                    if (x ==  3) {
                        System.out.print('|'); // Print the y-axis
                        }
                    System.out.print('-'); // Print the x-axis
                } else if (x ==  3) {
                    System.out.print('|'); // Print the y-axis
                } else {
                    System.out.print(' '); // Print space
                }
            }
            System.out.println(); // Move to the next line after printing one wave line
        }
    }
}
