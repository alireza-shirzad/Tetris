
import java.awt.*;
import java.util.Objects;

public class Square {
    private Vector2D CentrePosition; // Discrete
    private Vector2D CornerPosition;
    private Color color;
    private int Size = Constants.SquareSize;



    public Square(Vector2D centrePosition, Color color) {
        CentrePosition = centrePosition;
        this.color = color;
    }

    public void MoveRight(){
        CentrePosition.AddX(1);
    }
    public void MoveLeft(){
        CentrePosition.AddX(-1);
    }
    public void MoveUp(){
        CentrePosition.AddY(-1);
    }
    public void MoveDown(){CentrePosition.AddY(1);}


    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getSize() {
        return Size;
    }
    public void setSize(int size) {
        Size = size;
    }
    public Vector2D getCentrePosition() {
        return CentrePosition;
    }
    public void setCentrePosition(Vector2D centrePosition) {
        CentrePosition = centrePosition;
    }
    public Vector2D getCornerPosition() {
        CornerPosition = new Vector2D(CentrePosition.getX(),CentrePosition.getY());
        CornerPosition.Add(new Vector2D(CentrePosition.getX()*Size,CentrePosition.getY()*Size));
        return CornerPosition;
    }
    public void setCornerPosition(Vector2D cornerPosition) {
        CornerPosition = cornerPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return CentrePosition.equals(square.CentrePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CentrePosition);
    }

    @Override
    public String toString() {
        return "Square{" +
                "CentrePosition=" + CentrePosition +
                '}';
    }
}
