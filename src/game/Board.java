package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * This Board class stores all the information about the
 * current "game of life and death". 
 * 
 * @author AvoQueen
 * @version 0.1
 */

public class Board {
	private int mRows = 10;
	private int mCols = 10;
	private double mTileWidth = 10;
	private double mTileHeight = 10;
	private int mWidth = 100;
	private int mHeight = 100;

	private Cell[][] cells;

	private static GraphicsContext ctxRef;

	/**
	 * 
	 * Constructor for the Board class that creates a new 
	 * Board-object with a minimum dimension / size of 3x3.
	 * @param rows 	the amount of rows that the board has
	 * @param cols	the amount of columns that the board has 
	 */
	public Board(int rows, int cols) {
		//set rows and columns with a minimum size of 3
		mRows = rows > 2 ? rows : 3;
		mCols = cols > 2 ? cols : 3;

		//create the 2 dimensional array to store the cells
		cells = new Cell[mRows][mCols];

		//fill the array with (inactive / dead) cells
		for (int i = 0; i < mRows; i++) {
			for (int j = 0; j < mCols; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}

	/**
	 * 
	 * @param ctx 	reference to the GraphicsContext in order to create graphics
	 */
	public static void initCTX(GraphicsContext ctx) {
		Board.ctxRef = ctx;
	}

	/**
	 * 
	 * @param width		width, in pixels, of the board
	 * @param height	height, in pixels, of the board
	 */
	public void setDimensions(int width, int height) {
		this.mWidth = width;
		this.mHeight = height;

		this.mTileWidth = (double) width / mCols;
		this.mTileHeight = (double) height / mRows;
	}

	/**
	 * Draws the board-pattern and renders the individual cells.
	 */
	public void render() {
		ctxRef.clearRect(0, 0, mWidth, mHeight);
		ctxRef.setStroke(Color.BLACK);

		//draw the board in a checkerboard-pattern
		for (int i = 0; i <= mRows; i++) {
			ctxRef.strokeLine(0, i * mTileHeight, mWidth, i * mTileHeight);
		}
		for (int i = 0; i <= mCols; i++) {
			ctxRef.setStroke(Color.BLACK);
			ctxRef.strokeLine(i * mTileWidth, 0, i * mTileWidth, mHeight);
		}

		//render the individual cells
		for (Cell[] c2 : cells) {
			for (Cell c1 : c2) {
				c1.render(mTileWidth, mTileHeight);
			}
		}
	}

	/**
	 * Updates the cells (Can be considered as 'one iteration').
	 */
	public void updateCells() {
		//update the cells status
		for (Cell[] c2 : cells) {
			for (Cell c1 : c2) {
				c1.update();
			}
		}
		
		//calculate the neighbor-count of each cell
		for (Cell[] c2 : cells) {
			for (Cell c1 : c2) {
				if(c1.isActive())
					updateNeighbors(c1);
			}
		}
	}

	/**
	 * Updates the neighboring cells neighbor-count 
	 * when one cell changed its status from active
	 * to inactive and vice versa.
	 * @param c 	cell that had its state changed
	 */
	public void updateNeighbors(Cell c) {
		final int row = c.getRowIndex();
		final int col = c.getColIndex();
		
		//update all 8 neighbors neighbor-counts (if possible)
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((i != 0 || j != 0) && col + j >= 0 && col + j < mCols && row + i >= 0 && row + i < mRows) {
					if (c.isActive()) {
						cells[row + i][col + j].addNeighbor();
					}
					else {
						cells[row + i][col + j].removeNeighbor();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param x				x-coordinate of the mouse
	 * @param y				y-coordinate of the mouse
	 * @param leftclick		was a left-click
	 */
	public void handleMouseClick(double x, double y, boolean leftclick) {
		int newX = (int) (x / mTileWidth);
		int newY = (int) (y / mTileHeight);

		if (cells[newY][newX] != null) {
			
			if(!leftclick && cells[newY][newX].isActive()) //kill the cell if the right mousebutton was clicked
			{

				cells[newY][newX].setActive(false);
				updateNeighbors(cells[newY][newX]);
				
			} else if(leftclick && !cells[newY][newX].isActive()){ //revive a cell if the left mousebutton was clicked

				cells[newY][newX].setActive(true);
				updateNeighbors(cells[newY][newX]);
				
			}			
		}
	}

}
