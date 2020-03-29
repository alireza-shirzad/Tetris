
public class Constants {
    public static final int WindowWidth = 550;
    public static final int WindowHeight = 714;
    public static final int FieldWidth = 353;
    public static final int FieldHeight = WindowHeight;
    public static final String GameTitle = "Tetris";
    public static final int TimerDelay = 1000; //milisecond
    public static final int RenderingPeriod = 1000/8; //milisecond
    public static final int NumOfWidthSquares = 14;
    public static final int NumOfHeightSquares = 26;
    public static final int SquareSize = 25;
    public static final int NumOfTetrominoSquares = 4;
    public enum TetType{
        Stick,
        Block,
        Mountain,
        LeftGun,
        LeftSnake,
        RightGun,
        RightSnake;
    }
}
