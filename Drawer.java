
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

public class Drawer {
    private Graphics2D graphics2D ;
    private final Square[][] FielddSquares;
    public Drawer(Graphics2D graphics2D) {
        setGraphics2D(graphics2D);
        FielddSquares = new Square[Constants.NumOfHeightSquares][Constants.NumOfWidthSquares];
        for (int i = 0; i < Constants.NumOfHeightSquares ; i++) {
            for (int j = 0; j < Constants.NumOfWidthSquares ; j++) {
                FielddSquares[i][j] = new Square(new Vector2D(j,i),Color.BLACK);
            }
        }
    }

    public void DrawTetromino(Tetromino tetromino){
        for (Square square : tetromino.getSquares()) {
            if (tetromino!=null) DrawSquare(square);
        }
    }

    public void DrawSquare(Square square){
        if (square!=null) {
            graphics2D.setColor(square.getColor());
            graphics2D.fillRect(square.getCornerPosition().getX(), square.getCornerPosition().getY()
                    , square.getSize(), square.getSize());
        }
    }


    public void DrawSidePannel(){
        // Border
        Line2D lin = new Line2D.Float(Constants.FieldWidth+10,0, Constants.FieldWidth+10, Constants.WindowHeight);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(lin);

        // Logo
        Image LogoImage;
        try {
            String Location =new File(".").getCanonicalPath() + "\\src\\com\\company\\Tetris_Logo.jpg";
            LogoImage = new ImageIcon(Location).getImage();
            graphics2D.drawImage(LogoImage, Constants.FieldWidth + 20,10, 150,50,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Name
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 15));
        graphics2D.drawString("Alireza Shirzad", Constants.FieldWidth + 44, 650);
        graphics2D.drawString("95101847", Constants.FieldWidth + 68, 670);
        // Score
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 15));
        graphics2D.drawString("Score:", Constants.FieldWidth + 20, 100);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 40));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString(String.valueOf(GameState.getInstance().getScore()), Constants.FieldWidth + 85, 140);
        try {
            DrawScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DrawField() throws IOException {
        Square[][] Squares = GameState.getInstance().getFixedSquares();
        for (int i = 0; i < Constants.NumOfHeightSquares; i++) {
            for (int j = 0; j < Constants.NumOfWidthSquares; j++) {
                DrawSquare(this.FielddSquares[i][j]);
                DrawSquare(Squares[i][j]);
            }
        }
    }
    public void DrawExit(){
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 100));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("GoodBye!", 30, Constants.WindowHeight/2);
    }
    public void DrawGameOver(){
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 100));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("GameOver!", 30, Constants.WindowHeight/2);

    }
    private void DrawNextTetromino(){
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 15));
        graphics2D.drawString("Next Tetromino:", Constants.FieldWidth+20,Constants.WindowHeight/4+270);
        Tetromino tetromino;
        try {
            switch (Updater.getInstance().getTetrominoFactory().getNextTetromino().getType()){
                case Block: tetromino = Updater.getInstance().getTetrominoFactory().MakeBlock(true);
                    break;
                case Mountain: tetromino = Updater.getInstance().getTetrominoFactory().MakeMountain(true);
                    break;
                case Stick: tetromino = Updater.getInstance().getTetrominoFactory().MakeStick(true);
                    break;
                case LeftGun: tetromino = Updater.getInstance().getTetrominoFactory().MakeLeftGun(true);
                    break;
                case LeftSnake: tetromino = Updater.getInstance().getTetrominoFactory().MakeLeftSnake(true);
                    break;
                case RightGun: tetromino = Updater.getInstance().getTetrominoFactory().MakeRightGun(true);
                    break;
                case RightSnake: tetromino = Updater.getInstance().getTetrominoFactory().MakeRightSnake(true);
                    break;
                default:
                    tetromino = null;
            }
            DrawTetromino(tetromino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }
    private void DrawScores() throws IOException {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 15));
        graphics2D.drawString("HighScores:", Constants.FieldWidth+20,Constants.WindowHeight/4+130);
        int[] Scores = ScoreBoard.getInstance().getScores();
        int[] HighScores = new int[5];
        for (int i = 0; i < 5; i++) {
            HighScores[i]= Scores[i];
        }
        reverse(HighScores);
        for (int i = 0; i < ScoreBoard.getInstance().getMaxNumOfScores() ; i++) {
            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            graphics2D.drawString((i+1)+": "+ HighScores[i] , Constants.FieldWidth+80,Constants.WindowHeight/4+160 + i*20);
        }
        DrawNextTetromino();
    }

    static void reverse(int a[]) {
        int n=5;
        int i, k, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }
    }



}
