package hw4;

import java.awt.Color;
import api.Block;
import api.Cell;
import api.Position;

/**
 * @author Nick Cox
 *
 */
public class JShape extends AbstractShape {
	/**
	 * Constructs a JShape with the given position and magic state.
	 * 
	 * @param position
	 *            - position of this shape's center of rotation
	 * @param magic
	 *            - true if the this shape's first cell should be magic
	 */
	public JShape(Position givenPosition, boolean magic) {
		this.setPosition(givenPosition);
		this.setColor(Color.BLUE);
		this.setCells(new Cell[4]);
		Position position0 = new Position(this.getPosition().row(), this.getPosition().col() - 1);
		this.setSingleCell(0, new Cell(new Block(this.getColor(), magic), position0));
		Position position1 = new Position(this.getPosition().row() + 1, this.getPosition().col() - 1);
		this.setSingleCell(1, new Cell(new Block(this.getColor(), false), position1));
		Position position2 = new Position(this.getPosition().row() + 1, this.getPosition().col());
		this.setSingleCell(2, new Cell(new Block(this.getColor(), false), position2));
		Position position3 = new Position(this.getPosition().row() + 1, this.getPosition().col() + 1);
		this.setSingleCell(3, new Cell(new Block(this.getColor(), false), position3));
	}
}