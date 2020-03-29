

public class CollisionDetector {
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private GameState gameState = GameState.getInstance();

    public boolean canGoLeft (Tetromino tetromino){
        for (Square square : tetromino.squares) {
            if(square.getCentrePosition().getX()==0) return false;
        }
        Tetromino tetrominoToBe = tetromino.DeepCopy();
        tetrominoToBe.MoveLeft();
        if(CollidwithFixed(tetrominoToBe)) return false;
        return true;
    }
    public boolean canGoRigt (Tetromino tetromino){
        for (Square square : tetromino.squares) {
            if(square.getCentrePosition().getX()== Constants.NumOfWidthSquares-1) return false;
        }
        Tetromino tetrominoToBe = tetromino.DeepCopy();
        tetrominoToBe.MoveRight();
        if(CollidwithFixed(tetrominoToBe)) return false;
        return true;
    }
    public boolean canRotate (Tetromino tetromino) {
        Tetromino tetrominoToBe = tetromino.DeepCopy();
        tetrominoToBe.Rotate();
        for (Square square : tetrominoToBe.squares) {
            if(square.getCentrePosition().getX()>= Constants.NumOfWidthSquares-1
                    | square.getCentrePosition().getX()<=0) return false;
        }
        if(CollidwithFixed(tetrominoToBe)) return false;
        return true;
    }
    public boolean canMoveDown (Tetromino tetromino) {
        for (Square square : tetromino.squares) {
            if (square.getCentrePosition().getY()==Constants.NumOfHeightSquares-1) return false;
        }
        Tetromino tetrominoToBe = tetromino.DeepCopy();
        tetrominoToBe.MoveDown();
        if(CollidwithFixed(tetrominoToBe)) return false;
        return true;
    }

    public boolean CollidwithFixed(Tetromino tetrominoToBe){
        Square[][] GameSquares = gameState.getFixedSquares();
        for (Square square : tetrominoToBe.getSquares()) {
            for (int i=0; i<Constants.NumOfHeightSquares; i++){
                for (int j = 0; j< Constants.NumOfWidthSquares; j++){
                    if (square.equals(GameSquares[i][j])) return true;
                }
            }
        }
        return false;
    }



}
