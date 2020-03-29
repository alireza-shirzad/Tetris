
import java.awt.*;
import java.util.ArrayList;


public class Block extends Tetromino {
    public Block(Vector2D LeftyTop) {
        color = Color.YELLOW;
        squares = GetSquares(LeftyTop,RotationState.Zero);
        Type = Constants.TetType.Block;
    }

    @Override
    protected ArrayList<Square> GetSquares(Vector2D LeftyTop ,RotationState rotationState) {
        int X = LeftyTop.getX();
        int Y = LeftyTop.getY();
        ArrayList<Square> squares = new ArrayList<>();
        squares.add(new Square(new Vector2D(X,Y),color)) ;
        squares.add(new Square(new Vector2D(X,Y+1),color)) ;
        squares.add(new Square(new Vector2D(X+1,Y),color)) ;
        squares.add(new Square(new Vector2D(X+1,Y+1),color)) ;
        return squares;
    }

    @Override
    public void Rotate() {}

}
