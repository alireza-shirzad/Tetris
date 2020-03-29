
import java.util.Objects;

public class Vector2D {
    private int X,Y;


    public Vector2D(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
    public void Add(Vector2D vector2D){
        this.X += vector2D.getX();
        this.Y += vector2D.getY();
    }

    public void AddX(int x){
        this.X += x;
    }

    public void AddY(int y){
        this.Y += y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return X == vector2D.X &&
                Y == vector2D.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
