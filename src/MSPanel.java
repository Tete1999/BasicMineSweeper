import javax.swing.*;
import java.awt.*;


public class MSPanel extends JPanel implements BombListener {

	private int rows, cols, numShowing;
	private MSLabel[][] squares;
	private int bombsToSet;


	public MSPanel(int rows, int cols, double difficulty) {
		this.rows = rows;
		this.cols = cols;
		bombsToSet = (int) (difficulty * rows * cols);
		squares = new MSLabel[rows][cols];
		addToPanel(squares);
		setBombs();
		setNumbers();
		setLayout(new GridLayout(rows, cols));
	}

	private void addToPanel(MSLabel[][] squares) {
		for (int i = 0; i != rows; i++) {
			for (int j = 0; j != cols; j++) {
				squares[i][j] = new MSLabel();
				add(squares[i][j]); } }
	}


	private void setBombs() {
		int a, b;
		while (bombsToSet != 0) {
			a = (int) (Math.random() * rows);
			b = (int) (Math.random() * cols);
			if (!squares[a][b].isBomb()) {
				squares[a][b].setAsBomb();
				squares[a][b].addBombListener(this);
				bombsToSet -= 1; } }
	}

	public int getNum(int row, int col) {
		return squares[row][col].getBombsNear();
	}

	private void setNumbers() {
		int count = 0;
		for (int i = 0; i != rows; i++) {
			for (int j = 0; j != cols; j++) {
				count += legality(i, j + 1) + legality(i + 1, j) +
						legality(i - 1, j) + legality(i, j - 1) +
						legality(i + 1, j + 1) + legality(i - 1, j + 1) +
						legality(i - 1, j - 1) + legality(i + 1, j - 1);
				squares[i][j].setBombsNear(count);
				count = 0; } }
	}

	private int legality(int a, int b) {
		int count = 0;
		try {
			if (squares[a][b].isBomb()) { count += 1; }
		} catch (IndexOutOfBoundsException e) { }
		return count;
	}

	@Override
	public void update(BombEvent b) {
		JOptionPane.showMessageDialog(null, "Game End");
		System.exit(0);
	}

}






