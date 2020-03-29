
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Tetromino implements Cloneable{
    protected final int NumOfSquares = Constants.NumOfTetrominoSquares;
    protected ArrayList<Square> squares;
    protected Color color;
    protected RotationState rotationState;
    protected abstract ArrayList<Square> GetSquares(Vector2D vector2D,RotationState rotationState);
    protected Tetromino (){rotationState = RotationState.Zero;}
    protected Constants.TetType Type;

    public void Rotate() {
        switch (rotationState){
            case Zero:
                squares=GetSquares(squares.get(0).getCentrePosition(), RotationState.Ninety);
                break;
            case Ninety:
                squares=GetSquares(squares.get(0).getCentrePosition(), RotationState.HundredEighty);
                break;
            case HundredEighty:
                squares=GetSquares(squares.get(0).getCentrePosition(), RotationState.TwoHundredSeventy);
                break;
            case TwoHundredSeventy:
                squares=GetSquares(squares.get(0).getCentrePosition(), RotationState.Zero);
                break;
        }
    }


    public void MoveRight(){
        for (Square square : squares) {
            square.MoveRight();
        }
    }
    public void MoveLeft(){
        for (Square square : squares) {
            square.MoveLeft();
        }
    }
    public void MoveUp(){
        for (Square square : squares) {
            square.MoveUp();
        }
    }
    public void MoveDown(){
        for (Square square : squares) {
            square.MoveDown();
        }
    }


    public int getNumOfSquares() {
        return NumOfSquares;
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }
    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String str = "";
        for (Square square : squares) {
            str += square.toString();
        }
    return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetromino tetromino = (Tetromino) o;
        for (Square square : this.squares) {
            if(tetromino.squares.contains(square)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }


    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Tetromino DeepCopy(){
        Tetromino tetrominoToBe = (Tetromino) this.clone();
        ArrayList<Square> squaresTobe = new ArrayList<>();
        for (int i=0; i<Constants.NumOfTetrominoSquares; i++)
            squaresTobe.add(new Square(new Vector2D(this.squares.get(i).getCentrePosition().getX()
                    ,this.squares.get(i).getCentrePosition().getY()) , Color.BLUE));
        tetrominoToBe.setSquares(squaresTobe);
        return tetrominoToBe;
    }

        protected enum RotationState {
            HundredEighty, Ninety, TwoHundredSeventy, Zero;
        }

    public Constants.TetType getType() {
        return Type;
    }

    public void setType(Constants.TetType type) {
        Type = type;
    }
}