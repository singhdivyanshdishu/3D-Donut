import java.util.Arrays;

public class Donut {
    public static void main(String[] args) throws InterruptedException {
        float A = 0, B = 0;
        float i, j;
        int k;
        float[] z = new float[1760];
        char[] b = new char[1760];

        // ANSI color codes for various brightness levels in green
        String[] colors = {
            "\033[38;5;22m",  // dark green
            "\033[38;5;34m",  // green
            "\033[38;5;46m",  // bright green
            "\033[38;5;82m",  // light green
            "\033[38;5;118m", // very light green
            "\033[38;5;154m", // even brighter green
            "\033[38;5;190m", // lighter green
            "\033[38;5;154m", // near white green
            "\033[0m"         // reset color
        };

        System.out.print("\033[2J"); // Clear the screen

        while (true) {
            Arrays.fill(b, ' ');
            Arrays.fill(z, 0);

            for (j = 0; j < 6.28; j += 0.07) {
                for (i = 0; i < 6.28; i += 0.02) {
                    float c = (float) Math.sin(i);
                    float d = (float) Math.cos(j);
                    float e = (float) Math.sin(A);
                    float f = (float) Math.sin(j);
                    float g = (float) Math.cos(A);
                    float h = d + 4;
                    float D = 1 / (c * h * e + f * g + 7);
                    float l = (float) Math.cos(i);
                    float m = (float) Math.cos(B);
                    float n = (float) Math.sin(B);
                    float t = c * h * g - f * e;

                    int x = (int) (40 + 30 * D * (l * h * m - t * n));
                    int y = (int) (12 + 15 * D * (l * h * n + t * m));
                    int o = x + 80 * y;
                    int N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));

                    if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) {
                        z[o] = D;
                        b[o] = ".,-~:;=!*#$@".charAt(Math.max(N, 0));
                    }
                }
            }

            System.out.print("\033[H"); // Move the cursor to the top-left corner
            for (k = 0; k < 1760; k++) {
                int brightness = b[k] == ' ' ? 0 : Math.min((b[k] - ' ') / 2, colors.length - 1);

                // Print character with the corresponding brightness color
                System.out.print(colors[brightness] + (k % 80 > 0 ? b[k] : "\n"));
            }

            A += 0.04;
            B += 0.02;
            Thread.sleep(30);
        }
    }
}
