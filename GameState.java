
import java.io.IOException;
import java.util.ArrayList;

public class GameState {
    private Square[][] FixedSquares;
    private Tetromino MovingTetromino;
    private static GameState Previous_gameState;
    private static GameState gameState;
    private boolean Pause;
    private boolean GameOver;
    private boolean Exit;
    private int Score;
    public GameState() {
        Exit=false;
        GameOver = false;
        Pause = true;
        Score = 0;
        FixedSquares = new Square[Constants.NumOfHeightSquares][Constants.NumOfWidthSquares];
    }
    public static void RestartState(){
        gameState = new GameState();
        for (int i = 0; i < Constants.NumOfHeightSquares ; i++) {
            for (int j = 0; j <Constants.NumOfWidthSquares ; j++) {
                gameState.FixedSquares[i][j]=null;
            }
        }
    }
    public void Undo(){
        //if (getPrevious_gameState()!=null) {
            gameState = Previous_gameState;
            setPrevious_gameState(null);
        //}
    }

    public static GameState getInstance(){
        if(gameState==null) { gameState = new GameState();}
        return gameState;
    }
    public static GameState getPrevious_Instance(){
        if(Previous_gameState==null) { Previous_gameState = new GameState();}
        return Previous_gameState;
    }

    public static GameState getPrevious_gameState() {
        return Previous_gameState;
    }

    public static void setPrevious_gameState(GameState previous_gameState) {
        Previous_gameState = previous_gameState;
    }

    public boolean isGameOver() {
        return GameOver;
    }

    public void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    public boolean isExit() {
        return Exit;
    }

    public void setExit(boolean exit) {
        Exit = exit;
    }

    public Square[][] getFixedSquares() {
        return FixedSquares;
    }

    public void setFixedSquares(Square[][] fixedSquares) {
        FixedSquares = fixedSquares;
    }

    public Tetromino getMovingTetromino() {
        return MovingTetromino;
    }
    public void setMovingTetromino(Tetromino movingTetromino) {
        MovingTetromino = movingTetromino;
    }
    public boolean isPause() {
        return Pause;
    }
    public void setPause(boolean pause) {
        Pause = pause;
    }
    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
        try {
            ScoreBoard.getInstance().UpdateScores(gameState.getScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
