package core.util;

import java.util.Objects;

public class Pair<T> {
    T p1, p2;

    public Pair(T p1, T p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public T getP1() {
        return p1;
    }

    public void setP1(T p1) {
        this.p1 = p1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(getP1(), pair.getP1()) &&
                Objects.equals(getP2(), pair.getP2());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getP1(), getP2());
    }

    public T getP2() {

        return p2;
    }

    public void setP2(T p2) {
        this.p2 = p2;
    }
}
