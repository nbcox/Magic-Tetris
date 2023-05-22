package objects;

import java.util.ArrayList;
import java.util.List;
import api.AbstractGame;
import api.Position;

/**
 * MagicTetris implementation.
 * 
 * @author Nick Cox
 *
 */
public class MagicTetris extends AbstractGame {

	private boolean gravity;

	private int score;

	/**
	 * Constructs a game with the given width (columns) and height (rows). This game
	 * will use a new instance of BasicGenerator to generate new shapes.
	 * 
	 * @param width
	 *            - width of the game grid (number of columns)
	 * @param height
	 *            - height of the game grid (number of rows)
	 */
	public MagicTetris(int width, int height) {
		super(width, height, new BasicGenerator());
	}

	/**
	 * Returns a list of locations for all cells that form part of a collapsible
	 * set. This list may contain duplicates.
	 */
	@Override
	public List<Position> determinePositionsToCollapse() {
		List<Position> positions = new ArrayList<>();
		if (gravity) {
			for (int j = 0; j < getWidth(); j++) {
				boolean turn = true;
				for (int i = 0; i < getHeight(); i++) {
					if (getBlock(i, j) != null && turn) {
						turn = false;
					} else if (getBlock(i, j) == null && !turn) {
						Position position0 = new Position(i, j);
						positions.add(position0);
					}
				}
			}
			gravity = false;
		} else {
			for (int i = 0; i < getHeight(); i++) {
				List<Position> temp = new ArrayList<>();
				int countMagic = 0;
				for (int j = 0; j < getWidth(); j++) {
					if (getBlock(i, j) == null) {
						temp.clear();
						countMagic = 0;
						break;
					}
					if (getBlock(i, j).isMagic())
						countMagic++;
					Position tempPosition = new Position(i, j);
					temp.add(tempPosition);
					if (!temp.isEmpty())
						score += Math.pow(2, countMagic);
					positions.addAll(temp);
					if (countMagic >= 3) {
						gravity = true;
					}
				}
			}
		}
		return positions;
	}

	/**
	 * Returns the current score.
	 * 
	 * @return the current score
	 */
	@Override
	public int determineScore() {
		return score;
	}
}