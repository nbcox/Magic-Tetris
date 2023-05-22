package objects;

import api.Generator;
import api.Position;
import api.Shape;

import java.util.Random;

/**
 * Generator for Shape objects in MagicTetris. All six shapes are equally
 * likely, and the generated shape is magic with 20% probability.
 * 
 * @author Nick Cox
 *
 */
public class BasicGenerator implements Generator {
	/**
	 * Returns a new Shape instance according to this generator's strategy.
	 * 
	 * @param width	the width of the game grid
	 * @return 		a new Shape
	 */
	@Override
	public Shape getNext(int width) {
		int mid = width / 2;
		Random rand = new Random();
		int value = rand.nextInt(61);
		int prob = rand.nextInt(101);
		if (value < 10) {
			if (prob < 20) {
				return new LShape(new Position(-1, mid + 1), true);
			}
			return new LShape(new Position(-1, mid + 1), false);
		} else if (value < 20) {
			if (prob < 20) {
				return new JShape(new Position(-1, mid), true);
			}
			return new JShape(new Position(-1, mid), false);
		} else if (value < 30) {
			if (prob < 20) {
				return new IShape(new Position(-2, mid), true);
			}
			return new IShape(new Position(-2, mid), false);
		} else if (value < 40) {
			if (prob < 20) {
				return new OShape(new Position(-1, mid), true);
			}
			return new OShape(new Position(-1, mid), false);
		} else if (value < 50) {
			if (prob < 20) {
				return new TShape(new Position(0, mid), true);
			}
			return new TShape(new Position(0, mid), false);
		}
		if (prob < 20) {
			return new SZShape(new Position(-1, mid), true);
		}
		return new SZShape(new Position(-1, mid), false);
	}
}