
import java.awt.*;
import java.util.ArrayList;

public class RightGun extends Tetromino {
    public RightGun(Vector2D RightyTop) {
        color = Color.orange;
        squares = GetSquares(RightyTop, RotationState.Zero);
        Type = Constants.TetType.RightGun;
    }

    @Override
    protected ArrayList<Square> GetSquares(Vector2D BasePosition, RotationState rotationState) {
        int X = BasePosition.getX();
        int Y = BasePosition.getY();
        ArrayList<Square> squares = new ArrayList<>();

        switch (rotationState) {
            case Zero:
                this.rotationState = RotationState.Zero;
                squares.add(new Square(new Vector2D(X,Y),color)) ; // Right Top
                squares.add(new Square(new Vector2D(X,Y+1),color)) ;
                squares.add(new Square(new Vector2D(X-1,Y+1),color)) ;
                squares.add(new Square(new Vector2D(X-2,Y+1),color)) ;
                break;
            case Ninety:
                this.rotationState = RotationState.Ninety;
                squares.add(new Square(new Vector2D(X, Y), color)); // Right Down
                squares.add(new Square(new Vector2D(X - 1, Y ), color));
                squares.add(new Square(new Vector2D(X - 1, Y - 1), color));
                squares.add(new Square(new Vector2D(X - 1, Y - 2 ), color));
                break;
            case HundredEighty:
                this.rotationState = RotationState.HundredEighty;
                squares.add(new Square(new Vector2D(X, Y), color)); // Left Down
                squares.add(new Square(new Vector2D(X, Y - 1 ), color));
                squares.add(new Square(new Vector2D(X + 1, Y - 1), color));
                squares.add(new Square(new Vector2D(X + 2, Y - 1), color));
                break;
            case TwoHundredSeventy:
                this.rotationState = RotationState.TwoHundredSeventy;
                squares.add(new Square(new Vector2D(X, Y), color)); // Left Up
                squares.add(new Square(new Vector2D(X + 1, Y), color));
                squares.add(new Square(new Vector2D(X + 1, Y + 1), color));
                squares.add(new Square(new Vector2D(X + 1, Y + 2), color));
                break;
        }
        return squares;
    }

}
