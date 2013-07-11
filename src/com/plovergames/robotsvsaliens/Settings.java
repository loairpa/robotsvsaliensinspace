package com.plovergames.robotsvsaliens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.plovergames.framework.FileIO;

public class Settings {
	
	// Default Settings values 
    public static boolean soundEnabled = true;
    //public static boolean accelEnable = false;
    //public static boolean levels = true;
    //public static int level = 1;
    public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };
    
    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".superjumper")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            //accelEnable = Boolean.parseBoolean(in.readLine());
            //levels = Boolean.parseBoolean(in.readLine());
            //level = Integer.parseInt(in.readLine());
            for (int i = 0; i < 5; i++) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
    
    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".superjumper")));
            out.write(Boolean.toString(soundEnabled));
            //out.write(Boolean.toString(accelEnable));
            //out.write(Boolean.toString(levels));
            //out.write(Integer.toString(level));
            for (int i = 0; i < 5; i++) {
                out.write(Integer.toString(highscores[i]));
                out.write("\n");
            }

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j--)
                    highscores[j] = highscores[j - 1];
                highscores[i] = score;
                break;
            }
        }
    }
}
