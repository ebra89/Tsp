import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class TSPGene {

    private int x;
    private int y;

    TSPGene(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "( "+this.x + ", " + this.y +" )";
    }

    double distance (final TSPGene other){
        return sqrt(pow(getX() - other.getX(), 2) + pow(getY() - other.getY(), 2));

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSPGene tspGene = (TSPGene) o;
        return x == tspGene.x &&
                y == tspGene.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
