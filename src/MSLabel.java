import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MSLabel extends JLabel  {
	private MSCell cell;
	private ArrayList<BombListener> listeners = new ArrayList<>();

	public MSLabel() {
		cell = new MSCell();
		setPreferredSize(new Dimension(30, 30));
		setIcon(new ImageIcon("MineSweeperIcons/Empty.png"));
		addMouseListener(new MouseClick());
	}

	protected void setAsBomb(){ cell.setAsBomb(); }

	protected boolean isBomb(){ return cell.isBomb(); }

	protected void setBombsNear(int n){ cell.setBombsNear(n); }

	private int getBombsNear(){ return cell.getBombsNear(); }

	private void reveal(){ cell.reveal(); }

	protected void addBombListener(BombListener b){ listeners.add(b); }

	protected void revealBomb(){
		if (isBomb()){ setIcon(new ImageIcon("MineSweeperIcons/Bomb.png")); }
	}


	//
	//protected void removeBombListener(BombListener b){ listeners.remove(b); }

	private void notifyListeners(){
		BombEvent b = new BombEvent(this);
		for (BombListener l: listeners){
			l.update(b); }
	}

	private class MouseClick implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.isMetaDown() && !cell.isFlagged() && cell.isHidden()) {
				cell.toggleFlagged();
				setIcon(new ImageIcon("MineSweeperIcons/Flag.png")); }
			else if(e.isMetaDown() && cell.isHidden()){
				cell.toggleFlagged();
				setIcon(new ImageIcon("MineSweeperIcons/Empty.png")); }
			else{
				reveal();
				if (isBomb()){
					setIcon(new ImageIcon("MineSweeperIcons/Bomb.png"));
				}
				else{ setIcon(new ImageIcon("MineSweeperIcons/" + getBombsNear() + ".png")); }
				notifyListeners();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}





}

