package objects;

import java.awt.Color;
import api.Block;
import api.Cell;
import api.Position;
import api.Shape;

/**
 * Abstract superclass for implementations of the Shape interface.
 * 
 * @author Nick Cox
 *
 */
public abstract class AbstractShape implements Shape {
	/**
	 * Defines position variable for super and sub classes
	 */
	private Position position;

	/**
	 * Defines variable for cell array of blocks in the super and sub classes
	 */
	private Cell[] cells;

	/**
	 * Defines color variable for super and sub classes
	 */
	private Color color;

	/**
	 * Getter method to access the position variable in subclasses
	 * 
	 * @return position
	 */
	protected Position getPosition() {
		return position;
	}

	/**
	 * Setter method to set the position variable in subclasses
	 * 
	 * @param position
	 */
	protected void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Getter method to access the color variable in subclasses
	 * 
	 * @return color
	 */
	protected Color getColor() {
		return color;
	}

	/**
	 * Setter method to set the color variable in subclasses
	 * 
	 * @param color
	 */
	protected void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Setter method to set the cells variable in subclasses
	 * 
	 * @param cells
	 */
	protected void setCells(Cell[] cells) {
		this.cells = cells;
	}

	/**
	 * Setter method to set the individual cell variable in subclasses, separate
	 * from setCells and getCells
	 * 
	 * @param i
	 * @param cell
	 */
	protected void setSingleCell(int i, Cell cell) {
		this.cells[i] = cell;
	}

	/**
	 * Returns a new array of Cell objects representing the blocks in this shape
	 * along with their absolute positions.
	 * 
	 * @return the cells occupied by this shape
	 */
	public Cell[] getCells() {
		Cell[] copy = new Cell[cells.length];
		for (int i = 0; i < cells.length; i++) {
			copy[i] = new Cell(cells[i]);
		}
		return copy;
	}

	/**
	 * Shifts the position of this shape down (increasing the row) by one. No bounds
	 * checking is done.
	 */
	public void shiftDown() {
		position.setRow(position.row() + 1);
		for (int i = 0; i < cells.length; i++) {
			cells[i].setRow(cells[i].getRow() + 1);
		}
	}

	/**
	 * Shifts the position of this shape left (decreasing the column) by one. No
	 * bounds checking is done.
	 */
	public void shiftLeft() {
		position.setCol(position.col() - 1);
		for (int i = 0; i < cells.length; i++) {
			cells[i].setCol(cells[i].getCol() - 1);
		}
	}

	/**
	 * Shifts the position of this shape right (increasing the column) by one. No
	 * bounds checking is done.
	 */
	public void shiftRight() {
		position.setCol(position.col() + 1);
		for (int i = 0; i < cells.length; i++) {
			cells[i].setCol(cells[i].getCol() + 1);
		}
	}

	/**
	 * Rotates the shape counterclockwise. Note: OTetromino and SZTetromino need to
	 * override this method
	 */
	public void transform() {
		for (Cell cells : cells) {
			cells.setRow(cells.getRow() - position.row());
			cells.setCol(cells.getCol() - position.col());

			int temp = cells.getRow();
			cells.setRow(cells.getCol() * -1);
			cells.setCol(temp);

			cells.setRow(cells.getRow() + position.row());
			cells.setCol(cells.getCol() + position.col());
		}
	}

	/**
	 * Cycles the blocks within the cells of this shape. Each block is shifted
	 * forward to the next cell (in the original ordering of the cells). The last
	 * block wraps around to the first cell.
	 */
	public void cycle() {
		for (int i = 0; i < cells.length; i++) {
			if (i == cells.length - 1 && cells[i].getBlock().isMagic()) {
				cells[0].setBlock(new Block(color, true));
				cells[i].setBlock(new Block(color, false));
			} else if (i != cells.length - 1 && cells[i].getBlock().isMagic()) {
				cells[i].setBlock(new Block(color, false));
				cells[i + 1].setBlock(new Block(color, true));
				return;
			}
		}
	}

	/**
	 * Returns a deep copy of this object having the correct runtime type.
	 * 
	 * @return a deep copy of this object
	 */
	@Override
	public Shape clone() {
		try {
			// call the Object clone() method to create a shallow copy
			AbstractShape s = (AbstractShape) super.clone();

			// then make it into a deep copy
			s.position = new Position(position);
			s.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; i++) {
				s.cells[i] = new Cell(cells[i]);
			}
			return s;
		} catch (CloneNotSupportedException e) {
			// can't happen
			return null;
		}
	}
}