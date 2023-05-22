package api;

/**
 * Status values for a Tetris-like game. The meaning of each values is as
 * follows:
 * <dl>
 * <dt>NEW_SHAPE
 * <dd>There is a new current block group at the top of the grid
 * <dt>FALLING
 * <dd>The current block group was successfully shifted down one cell.
 * <dt>STOPPED
 * <dd>The current block group could not be shifted down, but did not complete a
 * collapsible set (it still may be possible to move the polynomial
 * horizontally)
 * <dt>COLLAPSING
 * <dd>There is at least one collapsible set, and the next invocation of step()
 * will collapse them
 * <dt>GAME_OVER
 * <dd>A new block group cannot be placed at the top of the grid without
 * colliding with occupied cells
 * </dl>
 * 
 * @author Nick Cox
 * 
 */
public enum GameStatus {
	NEW_SHAPE, FALLING, STOPPED, COLLAPSING, GAME_OVER
}
