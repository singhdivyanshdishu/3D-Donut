import java.util.Arrays;

public class SpinningCube {
    public static void main(String[] args) throws InterruptedException {
        float A = 0, B = 0; // Rotation angles
        int k;
        float[] depthBuffer = new float[1760]; // Depth buffer
        char[] b = new char[1760];

        // Characters to represent different depths
        String characters = ".,-~:;=!*#$@";

        // Define ANSI color codes for different cube faces
        String[] colors = {
            "\033[31m", // Red for back face
            "\033[32m", // Green for front face
            "\033[34m", // Blue for bottom face
            "\033[33m", // Yellow for top face
            "\033[35m", // Magenta for left face
            "\033[36m", // Cyan for right face
            "\033[0m"   // Reset color
        };

        // Clear the screen
        System.out.print("\033[2J");

        while (true) {
            Arrays.fill(b, ' ');
            Arrays.fill(depthBuffer, 0);

            // Define the vertices of the cube
            float[][] vertices = {
                {-1, -1, -1}, {1, -1, -1}, {1, 1, -1}, {-1, 1, -1}, // Back face
                {-1, -1, 1},  {1, -1, 1},  {1, 1, 1},  {-1, 1, 1}   // Front face
            };

            // Edges of the cube connecting the vertices
            int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, // Back face
                {4, 5}, {5, 6}, {6, 7}, {7, 4}, // Front face
                {0, 4}, {1, 5}, {2, 6}, {3, 7}  // Connecting edges
            };

            // Rotate vertices around the X and Y axes
            for (int j = 0; j < vertices.length; j++) {
                float[] v = vertices[j];

                // Rotation around Y
                float x = (float) (v[0] * Math.cos(A) - v[2] * Math.sin(A));
                float z = (float) (v[0] * Math.sin(A) + v[2] * Math.cos(A));
                v[0] = x;
                v[2] = z;

                // Rotation around X
                float y = (float) (v[1] * Math.cos(B) - v[2] * Math.sin(B));
                z = (float) (v[1] * Math.sin(B) + v[2] * Math.cos(B));
                v[1] = y;
                v[2] = z;
            }

            // Projection and rendering of edges
            for (int[] edge : edges) {
                float[] v0 = vertices[edge[0]];
                float[] v1 = vertices[edge[1]];
                float d0 = v0[2] + 4; // Distance for vertex 0
                float d1 = v1[2] + 4; // Distance for vertex 1

                // Projection for vertex 0
                int x0 = (int) (40 + 30 * v0[0] / d0);
                int y0 = (int) (12 + 15 * v0[1] / d0);
                // Projection for vertex 1
                int x1 = (int) (40 + 30 * v1[0] / d1);
                int y1 = (int) (12 + 15 * v1[1] / d1);

                // Draw the edge in the buffer based on depth
                if (0 <= x0 && x0 < 80 && 0 <= y0 && y0 < 22) {
                    int o0 = x0 + 80 * y0;
                    if (d0 > depthBuffer[o0]) {
                        depthBuffer[o0] = d0; // Update depth
                        b[o0] = characters.charAt(Math.min((int) ((d0 / 8) * (characters.length() - 1)), characters.length() - 1));
                    }
                }

                if (0 <= x1 && x1 < 80 && 0 <= y1 && y1 < 22) {
                    int o1 = x1 + 80 * y1;
                    if (d1 > depthBuffer[o1]) {
                        depthBuffer[o1] = d1; // Update depth
                        b[o1] = characters.charAt(Math.min((int) ((d1 / 8) * (characters.length() - 1)), characters.length() - 1));
                    }
                }
            }

            // Fill the sides of the cube (faces)
            fillCubeFaces(vertices, b, depthBuffer, characters, colors);

            // Print the rendered frame
            System.out.print("\033[H"); // Move the cursor to the top-left corner
            for (k = 0; k < 1760; k++) {
                if (b[k] != ' ') {
                    int colorIndex = getFaceIndex(k); // Get color index based on face
                    System.out.print(colors[colorIndex] + b[k]); // Print with corresponding color
                } else {
                    System.out.print(' '); // Print space
                }
                if (k % 80 == 79) System.out.print('\n'); // New line every 80 characters
            }

            // Increment rotation angles
            A += 0.08; // Rotate angle A
            B += 0.04; // Rotate angle B
            Thread.sleep(30); // Delay for smoother animation
        }
    }

    private static int getFaceIndex(int k) {
        // This method returns the color index based on face
        // Customize this logic to determine the face index based on the k value
        if (k < 80) return 0; // Back face
        if (k >= 80 && k < 160) return 1; // Front face
        return 2; // Default to another face color
    }

    private static void fillCubeFaces(float[][] vertices, char[] b, float[] depthBuffer, String characters, String[] colors) {
        // Define faces of the cube by specifying vertex indices
        int[][] faces = {
            {0, 1, 2, 3}, // Back face
            {4, 5, 6, 7}, // Front face
            {0, 1, 5, 4}, // Bottom face
            {2, 3, 7, 6}, // Top face
            {0, 3, 7, 4}, // Left face
            {1, 2, 6, 5}  // Right face
        };

        // Fill each face
        for (int i = 0; i < faces.length; i++) {
            int[] face = faces[i];
            float[] v0 = vertices[face[0]];
            float[] v1 = vertices[face[1]];
            float[] v2 = vertices[face[2]];
            float[] v3 = vertices[face[3]];

            // Calculate average depth for the face
            float d = (v0[2] + v1[2] + v2[2] + v3[2]) / 4 + 4; // Average distance

            // Fill the area of the face
            for (int x = Math.min(Math.min((int)(40 + 30 * v0[0] / d), (int)(40 + 30 * v1[0] / d)),
                    Math.min((int)(40 + 30 * v2[0] / d), (int)(40 + 30 * v3[0] / d)));
                 x <= Math.max(Math.max((int)(40 + 30 * v0[0] / d), (int)(40 + 30 * v1[0] / d)),
                    Math.max((int)(40 + 30 * v2[0] / d), (int)(40 + 30 * v3[0] / d)));
                 x++) {
                for (int y = Math.min(Math.min((int)(12 + 15 * v0[1] / d), (int)(12 + 15 * v1[1] / d)),
                        Math.min((int)(12 + 15 * v2[1] / d), (int)(12 + 15 * v3[1] / d)));
                     y <= Math.max(Math.max((int)(12 + 15 * v0[1] / d), (int)(12 + 15 * v1[1] / d)),
                        Math.max((int)(12 + 15 * v2[1] / d), (int)(12 + 15 * v3[1] / d)));
                     y++) {
                    int index = x + 80 * y;
                    if (0 <= x && x < 80 && 0 <= y && y < 22 && d > depthBuffer[index]) {
                        depthBuffer[index] = d; // Update depth
                        b[index] = characters.charAt(0); // Use a single character for face filling
                    }
                }
            }
        }
    }
}
