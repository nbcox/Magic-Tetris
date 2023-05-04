package hw4;

import java.awt.Color;
import api.Block;
import api.Cell;
import api.Position;

/**
 * @author Nick Cox
 *
 */
public class SZShape extends AbstractShape {
	/**
	 * Constructs an SZShape with the given position and magic state.
	 * 
	 * @param position
	 *            - position of this shape's upper left block
	 * @param magic
	 *            - true if the this shape's first cell should be magic
	 */
	public SZShape(Position givenPosition, boolean magic) {
		this.setPosition(givenPosition);
		this.setColor(Color.GREEN);
		this.setCells(new Cell[4]);
		this.setSingleCell(0, new Cell(new Block(this.getColor(), magic), this.getPosition()));
		Position position1 = new Position(this.getPosition().row() + 1, this.getPosition().col());
		this.setSingleCell(1, new Cell(new Block(this.getColor(), false), position1));
		Position position2 = new Position(this.getPosition().row() + 1, this.getPosition().col() + 1);
		this.setSingleCell(2, new Cell(new Block(this.getColor(), false), position2));
		Position position3 = new Position(this.getPosition().row() + 2, this.getPosition().col() + 1);
		this.setSingleCell(3, new Cell(new Block(this.getColor(), false), position3));
	}

	@Override
	public void transform() {
		if (this.getCells()[0].getRow() == this.getPosition().row()
				&& this.getCells()[0].getCol() == this.getPosition().col()) {
			this.setColor(Color.RED);
			Position position0 = new Position(this.getCells()[0].getRow(), this.getPosition().col() + 1);
			this.setSingleCell(0,
					new Cell(new Block(this.getColor(), this.getCells()[0].getBlock().isMagic()), position0));
			Position position1 = new Position(this.getCells()[1].getRow(), this.getPosition().col() + 1);
			this.setSingleCell(1,
					new Cell(new Block(this.getColor(), this.getCells()[1].getBlock().isMagic()), position1));
			Position position2 = new Position(this.getCells()[2].getRow(), this.getPosition().col());
			this.setSingleCell(2,
					new Cell(new Block(this.getColor(), this.getCells()[2].getBlock().isMagic()), position2));
			Position position3 = new Position(this.getCells()[3].getRow(), this.getPosition().col());
			this.setSingleCell(3,
					new Cell(new Block(this.getColor(), this.getCells()[3].getBlock().isMagic()), position3));

		} else {
			this.setColor(Color.GREEN);
			Position position0 = new Position(this.getCells()[0].getRow(), this.getPosition().col());
			this.setSingleCell(0,
					new Cell(new Block(this.getColor(), this.getCells()[0].getBlock().isMagic()), position0));
			Position position1 = new Position(this.getCells()[1].getRow(), this.getPosition().col());
			this.setSingleCell(1,
					new Cell(new Block(this.getColor(), this.getCells()[1].getBlock().isMagic()), position1));
			Position position2 = new Position(this.getCells()[2].getRow(), this.getPosition().col() + 1);
			this.setSingleCell(2,
					new Cell(new Block(this.getColor(), this.getCells()[2].getBlock().isMagic()), position2));
			Position position3 = new Position(this.getCells()[3].getRow(), this.getPosition().col() + 1);
			this.setSingleCell(3,
					new Cell(new Block(this.getColor(), this.getCells()[3].getBlock().isMagic()), position3));

		}
	}
}