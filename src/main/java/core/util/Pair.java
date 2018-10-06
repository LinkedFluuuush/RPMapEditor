/*
   Copyright 2018 Jean-Baptiste Louvet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package core.util;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class Pair<T> {
    private T p1, p2;

    public Pair(T p1, T p2){
        this.setP1(p1);
        this.setP2(p2);
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

    @Override
    public String toString() {
        return "Pair{" +
                "p1=" + this.getP1() +
                ", p2=" + this.getP2() +
                '}';
    }

    @SuppressWarnings("unused")
    @JsonValue
    public String toJson(){
        return this.getP1()+"-"+this.getP2();
    }
}
