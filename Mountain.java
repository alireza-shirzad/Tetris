
import java.awt.*;
import java.util.ArrayList;

public class Mountain extends Tetromino {
    public Mountain(Vector2D CentreTop) {
        color = Color.magenta;
        squares = GetSquares(CentreTop , RotationState.Zero);
        Type = Constants.TetType.Mountain;
    }

    @Override
    protected ArrayList<Square> GetSquares(Vector2D BasePosition, RotationState rotationState) {
        int X = BasePosition.getX();
        int Y = BasePosition.getY();
        ArrayList<Square> squares = new ArrayList<>();
        switch (rotationState) {
            case Zero:
                this.rotationState = RotationState.Zero;
                squares.add(new Square(new Vector2D(X, Y), color)); // Top Centre
                squares.add(new Square(new Vector2D(X, Y + 1), color));
                squares.add(new Square(new Vector2D(X - 1, Y + 1), color));
                squares.add(new Square(new Vector2D(X + 1, Y + 1), color));
                break;
            case Ninety:
                this.rotationState = RotationState.Ninety;
                squares.add(new Square(new Vector2D(X, Y), color)); // Right Centre
                squares.add(new Square(new Vector2D(X-1, Y ), color));
                squares.add(new Square(new Vector2D(X - 1, Y + 1), color));
                squares.add(new Square(new Vector2D(X - 1, Y - 1), color));
                break;
            case HundredEighty:
                this.rotationState = RotationState.HundredEighty;
                squares.add(new Square(new Vector2D(X, Y), color)); // Down Centre
                squares.add(new Square(new Vector2D(X, Y-1 ), color));
                squares.add(new Square(new Vector2D(X - 1, Y - 1), color));
                squares.add(new Square(new Vector2D(X + 1, Y - 1), color));
                break;
            case TwoHundredSeventy:
                this.rotationState = RotationState.TwoHundredSeventy;
                squares.add(new Square(new Vector2D(X, Y), color)); // Left Centre
                squares.add(new Square(new Vector2D(X+1, Y ), color));
                squares.add(new Square(new Vector2D(X + 1, Y - 1), color));
                squares.add(new Square(new Vector2D(X + 1, Y + 1), color));
                break;
        }
        return squares;
    }


}
