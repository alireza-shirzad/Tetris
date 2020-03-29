
import java.util.Random;

public class TetrominoFactory {

    Tetromino NextTetromino;
    public Tetromino MakeNewTetromino(){

        GameState.setPrevious_gameState(GameState.getInstance());
        Random random = new Random(System.nanoTime());
        int RAND = random.nextInt(7);
        Tetromino tetromino;
        switch (RAND){
            case 0: tetromino = MakeBlock(false);
            break;
            case 1: tetromino = MakeMountain(false);
            break;
            case 2: tetromino = MakeStick(false);
            break;
            case 3: tetromino = MakeRightGun(false);
            break;
            case 4: tetromino = MakeRightSnake(false);
            break;
            case 5: tetromino = MakeLeftSnake(false);
            break;
            case 6: tetromino = MakeLeftGun(false);
            break;
            default:
                System.out.println("Error");
                tetromino = null;
        }
        Tetromino tmp = NextTetromino;
        NextTetromino = tetromino;
        return tmp;
    }
    public Tetromino MakeFirstTetromino(){

        GameState.setPrevious_gameState(GameState.getInstance());
        Random random = new Random(System.nanoTime());
        int RAND = random.nextInt(7);
        Tetromino tetromino;
        switch (RAND){
            case 0: tetromino = MakeBlock(false);
                break;
            case 1: tetromino = MakeMountain(false);
                break;
            case 2: tetromino = MakeStick(false);
                break;
            case 3: tetromino = MakeRightGun(false);
                break;
            case 4: tetromino = MakeRightSnake(false);
                break;
            case 5: tetromino = MakeLeftSnake(false);
                break;
            case 6: tetromino = MakeLeftGun(false);
                break;
            default:
                System.out.println("Error");
                tetromino = null;
        }
            return tetromino;
    }

    public Block MakeBlock(boolean Next){
        Random random = new Random(System.nanoTime());
        Vector2D LeftyTop;
        if (Next) {LeftyTop = new Vector2D(Constants.NumOfWidthSquares+2,18); }
        else {LeftyTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-2),0);}
        Block newBlock = new Block(LeftyTop);
        return newBlock;
    }
    public Mountain MakeMountain(boolean Next){
        Random random = new Random(System.nanoTime());
        Vector2D CentreTop;
        if(Next){ CentreTop = new Vector2D(Constants.NumOfWidthSquares+3,18);}
        else {CentreTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-3),0); }
        Mountain newMountain = new Mountain(CentreTop);
        return newMountain;
    }
    public Stick MakeStick(boolean Next){
        Vector2D LeftyLeft;
        Random random = new Random(System.nanoTime());
        if (Next){LeftyLeft = new Vector2D(Constants.NumOfWidthSquares+1,18);}
        else{LeftyLeft = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-4),0);}
        Stick newStick = new Stick(LeftyLeft);
        return newStick;
    }
    public RightGun MakeRightGun(boolean Next){
        Random random = new Random(System.nanoTime());
        Vector2D RightyTop;
        if(Next){RightyTop = new Vector2D(Constants.NumOfWidthSquares+4,19);}
        else{RightyTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-3)+2,0);}
        RightGun newRightGun = new RightGun(RightyTop);
        return newRightGun;
    }
    public RightSnake MakeRightSnake(boolean Next){
        Vector2D RightyTop;
        Random random = new Random(System.nanoTime());
        if(Next){RightyTop = new Vector2D(Constants.NumOfWidthSquares+3,18);}
        else {RightyTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-3)+2,0);}
        RightSnake newRightSnake = new RightSnake(RightyTop);
        return newRightSnake;
    }
    public LeftSnake MakeLeftSnake(boolean Next){
        Vector2D LeftyTop;
        Random random = new Random(System.nanoTime());
        if(Next){LeftyTop = new Vector2D(Constants.NumOfWidthSquares+2,18);}
        else{LeftyTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-3),0);}
        LeftSnake newLeftSnake = new LeftSnake(LeftyTop);
        return newLeftSnake;
    }
    public LeftGun MakeLeftGun(boolean Next){
        Vector2D LeftyTop;
        Random random = new Random(System.nanoTime());
        if(Next){ LeftyTop = new Vector2D(Constants.NumOfWidthSquares+2,18);}
        else{LeftyTop = new Vector2D(random.nextInt(Constants.NumOfWidthSquares-4),0);}
        LeftGun newLeftGun = new LeftGun(LeftyTop);
        return newLeftGun;
    }

    public Tetromino getNextTetromino() {
        return NextTetromino;
    }

    public void setNextTetromino(Tetromino nextTetromino) {
        NextTetromino = nextTetromino;
    }
}
