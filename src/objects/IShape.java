package objects;

import java.awt.Color;
import api.Block;
import api.Cell;
import api.Position;

/**
 * IShape implementation.
 * 
 * @author Nick Cox
 *
 */
public class IShape extends AbstractShape {
	/**
	 * Constructs an IShape with the given position and magic state.
	 * 
	 * @param position	position of this shape's center of rotation
	 * @param magic		true if the this shape's first cell should be magic
	 */
	public IShape(Position givenPosition, boolean magic) {
		this.setPosition(givenPosition);
		this.setColor(Color.CYAN);
		this.setCells(new Cell[3]);
		this.setSingleCell(0, new Cell(new Block(this.getColor(), magic), this.getPosition()));
		Position position1 = new Position(this.getPosition().row() + 1, this.getPosition().col());
		this.setSingleCell(1, new Cell(new Block(this.getColor(), false), position1));
		Position position2 = new Position(this.getPosition().row() + 2, this.getPosition().col());
		this.setSingleCell(2, new Cell(new Block(this.getColor(), false), position2));
	}
}