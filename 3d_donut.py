import numpy as np
import time
import os

def clear_screen():
    # Clear the screen (works on Unix-like systems; use 'cls' for Windows)
    os.system('clear')

A, B = 0, 0
z = np.zeros(1760)  # Depth buffer
b = np.array([' '] * 1760)  # Character buffer

# ANSI color codes for various brightness levels in green
colors = [
    "\033[38;5;22m",  # dark green
    "\033[38;5;28m",  # medium dark green
    "\033[38;5;34m",  # green
    "\033[38;5;40m",  # medium green
    "\033[38;5;46m",  # bright green
    "\033[38;5;82m",  # light green
    "\033[38;5;118m", # very light green
    "\033[0m"         # reset color
]

def donut_frame(A, B):
    z.fill(0)
    b.fill(' ')

    for j in np.arange(0, 6.28, 0.07):
        for i in np.arange(0, 6.28, 0.02):
            c, d = np.sin(i), np.cos(j)
            e, f = np.sin(A), np.sin(j)
            g, h = np.cos(A), d + 4
            D = 1 / (c * h * e + f * g + 7)
            l, m, n = np.cos(i), np.cos(B), np.sin(B)
            t = c * h * g - f * e

            x = int(40 + 30 * D * (l * h * m - t * n))
            y = int(12 + 15 * D * (l * h * n + t * m))
            o = x + 80 * y
            N = int(8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n))

            if 22 > y > 0 and 80 > x > 0 and D > z[o]:
                z[o] = D
                b[o] = ".,-~:;=!*#$@"[max(N, 0)]

    # Prepare the frame output with colors
    frame_output = "\033[H"  # Move the cursor to the top-left corner
    for k in range(1760):
        brightness = 0 if b[k] == ' ' else min((ord(b[k]) - ord(' ')) // 2, len(colors) - 1)
        frame_output += colors[brightness] + (b[k] if k % 80 > 0 else "\n")

    return frame_output + colors[-1]  # Reset color at the end

# Run the donut animation
clear_screen()
while True:
    print(donut_frame(A, B), end="")
    A += 0.04
    B += 0.02
    time.sleep(0.03)
