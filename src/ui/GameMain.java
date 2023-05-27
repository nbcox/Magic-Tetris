package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import api.BasicPlayLevel;
import api.Game;
import api.PlayLevel;
import objects.MagicTetris;

/**
 * Main class for a GUI for a Tetris game sets up a GamePanel instance in a
 * frame.
 * 
 * @author Nick Cox
 * 
 */
public class GameMain {
	/**
	 * Cell size in pixels.
	 */
	public static final int SIZE = 25;

	/**
	 * Font for displaying score.
	 */
	public static final int SCORE_FONT = 24;

	/**
	 * Grid background color.
	 */
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	/**
	 * Media player for playing background music.
	 */
	private static MediaPlayer backgroundMusicPlayer;

	/**
	 * Helper method for instantiating the components. This method should be
	 * executed in the context of the Swing event thread only.
	 */
	private static void create() {
		// EDIT HERE TO CHANGE THE GAME
		Game game = new MagicTetris(12, 20);
		// Game game = new examples.SampleGame();

		// create the two panels for the game
		PlayLevel level = new BasicPlayLevel();
		ScorePanel scorePanel = new ScorePanel();
		GamePanel panel = new GamePanel(game, scorePanel, level);

		// arrange the two panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(scorePanel);
		mainPanel.add(panel);

		// put main panel in a window
		JFrame frame = new JFrame("Magic Tetris");
		frame.getContentPane().add(mainPanel);

		// give panels a nonzero size
		Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);
		panel.setPreferredSize(d);
		d = new Dimension(game.getWidth() * GameMain.SIZE, 3 * GameMain.SIZE);
		scorePanel.setPreferredSize(d);
		frame.pack();

		// we want to shut down the application if the
		// "close" button is pressed on the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// be sure key events get to the panel
		panel.requestFocus();
		
		// Initialize the JavaFX toolkit
		new JFXPanel();
				
		try {
			// Load the music file
			String musicFile = "resources/background_music.mp3"; // Update the file path accordingly
			Media music = new Media(new File(musicFile).toURI().toString());

			// Create a MediaPlayer instance to play the music
			backgroundMusicPlayer = new MediaPlayer(music);

			// Set the music to loop indefinitely
			backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);

			// Start playing the music
			backgroundMusicPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// load existing high score
		scorePanel.loadHighScoreFromFile();
		
		// show the frame
		frame.setVisible(true);
	}

	/**
	 * Entry point. Main thread passed control immediately to the Swing event
	 * thread.
	 * 
	 * @param args	not used
	 */
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				create();
			}
		};
		SwingUtilities.invokeLater(r);
		
		// Add a shutdown hook to stop the background music
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	        if (backgroundMusicPlayer != null) {
	            backgroundMusicPlayer.stop();
	        }
	    }));
	}
}
