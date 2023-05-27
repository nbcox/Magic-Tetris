package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * Panel for displaying the score in a simple video game.
 * 
 * @author Nick Cox
 * 
 */
public class ScorePanel extends JPanel {
	/**
	 * Format string for displaying score.
	 */
	private static final String HIGH_SCORE_FORMAT = "High Score: %1d";
	
	/**
	 * Format string for displaying score.
	 */
	private static final String SCORE_FORMAT = "Score: %1d";

	/**
	 * Score to be displayed.
	 */
	private int score;
	
	/**
	 * Stores the current high score.
	 */
	private int highScore = 0;
	
	/**
     * Loads the high score from the file (if it exists).
     */
    public void loadHighScoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/highscore.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                this.highScore = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

	/**
	 * Sets the score to be displayed in this panel.
	 * 
	 * @param newScore	score to be displayed
	 */
	public void updateScore(int newScore) {
		this.score = newScore;
		repaint();
	}
	
	public void updateHighScore(int score) {
	    if (score > this.highScore) {
	        this.highScore = score;
	        repaint();
	        
	        // Save the high score to a file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/highscore.txt", false))) {
	            writer.write(Integer.toString(highScore));
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception as needed
	        }
	    }
	}


	@Override
	public void paintComponent(Graphics g) {
	    Dimension d = getPreferredSize();
	    ((Graphics2D) g).setBackground(Color.WHITE);
	    g.clearRect(0, 0, d.width, d.height);
	    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, GameMain.SCORE_FONT);
	    g.setFont(font);
	    FontMetrics metrics = g.getFontMetrics(font);

	    // Calculate the height of the text
	    int textHeight = metrics.getHeight();

	    // Calculate the y-coordinate for the high score
	    int highScoreY = (d.height - textHeight) / 2;

	    // Calculate the y-coordinate for the score
	    int scoreY = highScoreY + textHeight;

	    // Draw the high score
	    String highScoreText = String.format(HIGH_SCORE_FORMAT, highScore);
	    int highScoreWidth = metrics.stringWidth(highScoreText);
	    int highScoreX = (d.width - highScoreWidth) / 2;
	    g.drawString(highScoreText, highScoreX, highScoreY);

	    // Draw the score
	    String scoreText = String.format(SCORE_FORMAT, score);
	    int scoreWidth = metrics.stringWidth(scoreText);
	    int scoreX = (d.width - scoreWidth) / 2;
	    g.drawString(scoreText, scoreX, scoreY);
	}

}
