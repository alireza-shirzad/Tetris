
import java.io.IOException;

public class Updater {
    private ScoreBoard scoreBoard;
    private GameState gameState;
    private TetrominoFactory tetrominoFactory;
    CollisionDetector collisionDetector;
    private static Updater updater;

    private Updater(GameState gameState) throws IOException {
        this.gameState = gameState;
        collisionDetector = new CollisionDetector();
        tetrominoFactory = new TetrominoFactory();
        tetrominoFactory.setNextTetromino(tetrominoFactory.MakeFirstTetromino());
        gameState.setMovingTetromino(tetrominoFactory.MakeNewTetromino());
        scoreBoard = ScoreBoard.getInstance();
    }

    public static Updater getInstance() throws IOException {
        if(updater==null) { updater = new Updater(GameState.getInstance());}
        return updater;
    }

    public boolean Regularpdate() {
        if (collisionDetector.canMoveDown(gameState.getMovingTetromino())){
            gameState.getMovingTetromino().MoveDown();
            return true;
        }else{
            return false;
        }
    }

    public void LeftKeyboard() {
        if (collisionDetector.canGoLeft(gameState.getMovingTetromino())) gameState.getMovingTetromino().MoveLeft();
    }

    public void RightKeyboard() {
        if (collisionDetector.canGoRigt(gameState.getMovingTetromino())) gameState.getMovingTetromino().MoveRight();
    }

    public void SpaceKeyboard(){
        if (collisionDetector.canRotate(gameState.getMovingTetromino())) gameState.getMovingTetromino().Rotate();
    }

    public void UpdateTetrominos(boolean result) throws IOException {
            if (!result) {
                FixTetromino(gameState.getMovingTetromino());
                gameState.setScore(gameState.getScore()+1);
                gameState.setMovingTetromino(tetrominoFactory.MakeNewTetromino());
            }
    }

    private void FixTetromino(Tetromino tetromino) throws IOException {
        checkIfLost();
        int X,Y;
        Square[][] tmpFixedSquares = gameState.getFixedSquares();
        for (Square square : tetromino.getSquares()) {
            X = square.getCentrePosition().getX();
            Y = square.getCentrePosition().getY();
            tmpFixedSquares[Y][X] = square;
        }
        int Num;
        for (int i = Constants.NumOfHeightSquares-1; i >= 0 ; i--) {
            Num=0;
            for (int j = 0; j < Constants.NumOfWidthSquares ; j++) {
                if (tmpFixedSquares[i][j]==null) break;
                else Num++;
            }
            if (Num== Constants.NumOfWidthSquares){
                tmpFixedSquares = Collapse(tmpFixedSquares,i);
                i=i+1;
            }
        }
        gameState.setFixedSquares(tmpFixedSquares);
    }

    private Square[][] Collapse(Square[][] squares,int rowNum){
        FileOperation.PlaySound("Correct.wav",false);
        gameState.setScore(gameState.getScore()+10);
        for (int j = 0; j < Constants.NumOfWidthSquares; j++) {
            squares[0][j] = null;
        }
        for (int j = 0; j < Constants.NumOfWidthSquares; j++) {
            squares[rowNum][j] = null;
        }
        for (int i = rowNum-1; i > 0 ; i--) {
            for (int j = 0; j < Constants.NumOfWidthSquares ; j++) {
                squares[i+1][j] = squares[i][j];
                if(squares[i+1][j]!=null) squares[i+1][j].setCentrePosition(new Vector2D(j,i+1));
            }
        }
        return squares;
    }
    public void checkIfLost() throws IOException {
        Square[][] squares = gameState.getFixedSquares();
        int Num=0;
        for (int j = 0; j < Constants.NumOfWidthSquares; j++) {
            if(squares[0][j]!=null){
                gameState.setGameOver(true);
                Canvas.getInstance().getPauseButton().setText("Start");
            }
        }
    }
    public void Restart() throws IOException {
        GameState.RestartState();
        this.gameState = GameState.getInstance();
        gameState.setMovingTetromino(tetrominoFactory.MakeNewTetromino());
        Canvas.getInstance().setGameState(GameState.getInstance());
        collisionDetector.setGameState(gameState);
        Canvas.getInstance().getPauseButton().setText("Start");
    }

    public void Pause(){
        gameState.setPause(true);
    }
    public void UnPause(){
        gameState.setPause(false);
    }
    public void Exit(){
        gameState.setExit(true);
    }
    public void Undo() throws IOException {
        gameState.Undo();
        gameState=GameState.getInstance();
        Canvas.getInstance().setGameState(gameState);
        collisionDetector.setGameState(GameState.getInstance());
    }

    public TetrominoFactory getTetrominoFactory() {
        return tetrominoFactory;
    }

    public void setTetrominoFactory(TetrominoFactory tetrominoFactory) {
        this.tetrominoFactory = tetrominoFactory;
    }
}
