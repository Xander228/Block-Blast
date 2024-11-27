
public class PieceBag
{
    private int[] pieceBag;
    private int bagIndex;
    
    public PieceBag()
    {
        pieceBag = new int[Block.getBlockCount()];
        bagIndex = 0;
        for (int i = 0; i < Block.getBlockCount(); i++) pieceBag[i] = i;
        shuffleBag();
    }

    public int pullNewPiece() {
        int newPiece = pieceBag[bagIndex];
        bagIndex++;
        if (bagIndex >= Block.getBlockCount()) {
            shuffleBag();
            bagIndex = 0;
        }
        return newPiece;
    }
    
    private void shuffleBag() {
        for (int i = 0; i < 30; i++) {
            int buffer = 0;
            int index1 = (int)(Math.random() * (Block.getBlockCount() - 1));
            int index2 = (int)(Math.random() * (Block.getBlockCount() - 1));
            buffer = pieceBag[index1];
            pieceBag[index1] = pieceBag[index2];
            pieceBag[index2] = buffer;
        }
    }
}
