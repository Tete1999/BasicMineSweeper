public class MSCell {

    private int bombsNear;
    private boolean isBomb, isFlagged, isHidden;

    public MSCell(){
        bombsNear = 0;
        isBomb = false;
        isFlagged = false;
        isHidden = true;

    }

    protected void setBombsNear(int bombsNear) { this.bombsNear = bombsNear; }

    protected void setAsBomb(){ isBomb = true; }

    protected void toggleFlagged(){
        if (isFlagged){ isFlagged = false;}
        else {isFlagged = true;}
    }

    protected void reveal(){ isHidden = false; }

    protected int getBombsNear() { return bombsNear; }


    protected boolean isBomb() { return isBomb; }

    protected boolean isFlagged() { return isFlagged; }

    protected boolean isHidden() { return isHidden; }

}