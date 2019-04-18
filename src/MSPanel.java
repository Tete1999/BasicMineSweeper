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
				squares[i][j].addBombListener(this);
				add(squares[i][j]); } }
	}

	private void displayAllBombs(MSLabel[][] squares) {
		for (int i = 0; i != rows; i++) {
			for (int j = 0; j != cols; j++) {
				squares[i][j].revealBomb(); } }
	}


	private void setBombs() {
		int a, b, count = 0;
		while (count != bombsToSet) {
			a = (int) (Math.random() * rows);
			b = (int) (Math.random() * cols);
			if (!squares[a][b].isBomb()) {
				squares[a][b].setAsBomb();
				count += 1; } }
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
			if (squares[a][b].isBomb()) { count += 1; } }
		catch (IndexOutOfBoundsException e) { }
		return count;
	}


	private boolean gameWin() {
		return (numShowing == (rows * cols) - bombsToSet);
	}


	@Override
	public void update(BombEvent b) {
		MSLabel m = (MSLabel) b.getSource();
		if (m.isBomb()) {
			displayAllBombs(squares);
			JOptionPane.showMessageDialog(null, "Oops, You Lost");
			System.exit(0); }
		else { numShowing ++;
			if (gameWin()) {
				displayAllBombs(squares);
				JOptionPane.showMessageDialog(null, "Congratulations. You win !");
			    System.exit(0); } }
		}
}









