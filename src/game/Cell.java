package game;

/**
 * 
 * This Cell class stores all the information about a
 * single cell on the GOLAD-board.
 * 
 * @author AvoQueen
 * @version 0.1
 */

public class Cell {
	
	private boolean bActive = false;
	private int mRowIndex;
	private int mColIndex;
	private int mNeighbors = 0;

	
	/**
	 * 
	 * @param mRowIndex		indirect pointer to the cells position in the cells array of a board
	 * @param mColIndex		indirect pointer to the cells position in the cells array of a board
	 */
	public Cell(int mRowIndex, int mColIndex) {
		this.mRowIndex = mRowIndex;
		this.mColIndex = mColIndex;
	}

	/**
	 * Check if the cell is active or inactive
	 * @return is the cell active or inactive
	 */
	public boolean isActive() {
		return bActive;
	}

	/**
	 * Set the state of the cell
	 * @param state		state of the cell (active or inactive)
	 */
	public void setActive(boolean state) {
		bActive = state;
	}

	/**
	 * Renders the cell (draws the textures) 
	 * @param width		width of the cell
	 * @param height	height of the cell
	 */
	public void render(double width, double height) {
		if (bActive) {
			if (mNeighbors > 3 || mNeighbors < 2) {
				GameTextures.drawDying(mColIndex * width, mRowIndex * height, width, height);
			} else {
				GameTextures.drawActive(mColIndex * width, mRowIndex * height, width, height);
			}

		} else if (mNeighbors == 3) {
			GameTextures.drawReviving(mColIndex * width, mRowIndex * height, width, height);
		}
	}

	/**
	 * Updates the cell by the GOLAD rules
	 */
	public void update() {
		if ((mNeighbors < 2 || mNeighbors > 3) && bActive) {
			bActive = false;
		} else if (!bActive && mNeighbors == 3) {
			bActive = true;
		}
		mNeighbors = 0;
	}

	/**
	 * Set the amount of neighbors that the cell has.
	 * @param mNeighbors 	amount of neighbors
	 */
	public void setNeighbors(int mNeighbors) {
		this.mNeighbors = mNeighbors;
	}

	/**
	 * increases the neighbor-count by one
	 */
	public void addNeighbor() {
		mNeighbors++;
	}

	/**
	 * decreases the neighbor-count by one
	 */
	public void removeNeighbor() {
		mNeighbors--;
	}

	/**
	 * 
	 * @return the neighbor-count
	 */
	public int getNeighborCount() {
		return mNeighbors;
	}

	/**
	 * 
	 * @return indirect pointer to the cells position in the cells array of a board
	 */
	public int getRowIndex() {
		return mRowIndex;
	}

	/**
	 * 
	 * @param mRowIndex		indirect pointer to the cells position in the cells array of a board
	 */
	public void setRowIndex(int mRowIndex) {
		this.mRowIndex = mRowIndex;
	}

	/**
	 * 
	 * @return indirect pointer to the cells position in the cells array of a board
	 */
	public int getColIndex() {
		return mColIndex;
	}

	/**
	 * 
	 * @param mColIndex		indirect pointer to the cells position in the cells array of a board
	 */
	public void setColIndex(int mColIndex) {
		this.mColIndex = mColIndex;
	}

}
