
import java.awt.*;
import java.util.ArrayList;

public class Stick extends Tetromino {
    public Stick(Vector2D LeftyLeft) {
        super();
        color = Color.CYAN;
        squares = GetSquares(LeftyLeft,RotationState.Zero);
        Type = Constants.TetType.Stick;
    }

    @Override
    protected ArrayList<Square> GetSquares(Vector2D BasePosition,RotationState rotationState) {
        int X = BasePosition.getX();
        int Y = BasePosition.getY();
        ArrayList<Square> squares = new ArrayList<>();
        switch (rotationState) {
            case Zero:
                this.rotationState = RotationState.Zero;
                squares.add(new Square(new Vector2D(X, Y), color));
                squares.add(new Square(new Vector2D(X + 1, Y), color));
                squares.add(new Square(new Vector2D(X + 2, Y), color));
                squares.add(new Square(new Vector2D(X + 3, Y), color));
            break;
            case HundredEighty:
                this.rotationState = RotationState.HundredEighty;
                squares.add(new Square(new Vector2D(X, Y), color));
                squares.add(new Square(new Vector2D(X , Y+ 1), color));
                squares.add(new Square(new Vector2D(X , Y+ 2), color));
                squares.add(new Square(new Vector2D(X , Y+ 3), color));
        }
        return squares;
    }

    @Override
    public void Rotate() {
        switch (rotationState){
            case Zero:
                squares=GetSquares(squares.get(0).getCentrePosition(), RotationState.HundredEighty);
                break;
            case HundredEighty:
                squares=GetSquares(squares.get(3).getCentrePosition(),RotationState.Zero);
                break;
        }
    }

}
