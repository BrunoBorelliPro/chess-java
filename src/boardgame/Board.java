package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			String msg = "Error creating board: there must be at least 1 row and 1 column";
			throw new BoardException(msg);
		}

		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}


	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			String msg = "Position not on the board";
			throw new BoardException(msg);
		}

		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			String msg = "Position not on the board";
			throw new BoardException(msg);
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			String msg = "There is already a piece on position " + position;
			throw new BoardException(msg);
		}
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			String msg = "Position not on the board";
			throw new BoardException(msg);
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		this.pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
	}

	public boolean positionExists(Position position) {
		return this.positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			String msg = "Position not on the board";
			throw new BoardException(msg);
		}
		return piece(position) != null;
	}

}
