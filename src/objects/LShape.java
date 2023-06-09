package objects;

import java.awt.Color;
import api.Block;
import api.Cell;
import api.Position;

/**
 * LShape implementation.
 * 
 * @author Nick Cox
 *
 */
public class LShape extends AbstractShape {
	/**
	 * Constructs an LShape with the given position and magic state.
	 * 
	 * @param position	position of this shape's center of rotation
	 * @param magic		true if the this shape's first cell should be magic
	 */
	public LShape(Position givenPosition, boolean magic) {
		this.setPosition(givenPosition);
		this.setColor(Color.ORANGE);
		this.setCells(new Cell[4]);
		this.setSingleCell(0, new Cell(new Block(this.getColor(), magic), this.getPosition()));
		Position position1 = new Position(this.getPosition().row() + 1, this.getPosition().col() - 2);
		this.setSingleCell(1, new Cell(new Block(this.getColor(), false), position1));
		Position position2 = new Position(this.getPosition().row() + 1, this.getPosition().col() - 1);
		this.setSingleCell(2, new Cell(new Block(this.getColor(), false), position2));
		Position position3 = new Position(this.getPosition().row() + 1, this.getPosition().col());
		this.setSingleCell(3, new Cell(new Block(this.getColor(), false), position3));
	}
}