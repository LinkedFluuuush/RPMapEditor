package core;

import core.util.Pair;

import java.util.*;

public class RPMap {
    private Map<Pair<Integer>, Tile> mapTiles;

    public RPMap(Map<Pair<Integer>, Tile> mapTiles) {
        this.mapTiles = mapTiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RPMap rpMap = (RPMap) o;
        return Objects.equals(getMapTiles(), rpMap.getMapTiles());
    }

/*    @Override
    public int hashCode() {

        return Objects.hash(getMapTiles());
    }*/

    public RPMap (){
        this.mapTiles = new HashMap<>();
    }

    public RPMap setTile(int p1, int p2, Tile tile){
        mapTiles.put(new Pair<>(p1, p2), tile);

        return this;
    }

    public Map<Pair<Integer>, Tile> getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(Map<Pair<Integer>, Tile> mapTiles) {
        this.mapTiles = mapTiles;
    }

    public Pair<Integer> getUpLeftCorner(){
        Pair<Integer> corner = null;

        if(!this.mapTiles.isEmpty()){
            Set<Pair<Integer>> tileCoords = this.mapTiles.keySet();

            for(Pair<Integer> aCoord : tileCoords){
                if(corner == null){
                    corner = new Pair<>(aCoord.getP1(), aCoord.getP2());
                } else {
                    if (aCoord.getP1() < corner.getP1()){
                        corner.setP1(aCoord.getP1());
                    }

                    if (aCoord.getP2() < corner.getP2()){
                        corner.setP2(aCoord.getP2());
                    }
                }
            }
        }

        return corner;
    }

    public Pair<Integer> getMapSize() {
        Pair<Integer> mapSize = new Pair<>(0, 0);
        Pair<Integer> upLeftCorner = this.getUpLeftCorner();

        if(upLeftCorner != null){
            Pair<Integer> downRightCorner = this.getDownRightCorner();

            mapSize.setP1(downRightCorner.getP1() - upLeftCorner.getP1() + 1);
            mapSize.setP2(downRightCorner.getP2() - upLeftCorner.getP2() + 1);
        }

        return mapSize;
    }

    private Pair<Integer> getDownRightCorner() {
        Pair<Integer> corner = null;

        if(!this.mapTiles.isEmpty()){
            Set<Pair<Integer>> tileCoords = this.mapTiles.keySet();

            for(Pair<Integer> aCoord : tileCoords){
                if(corner == null){
                    corner = new Pair<>(aCoord.getP1(), aCoord.getP2());
                } else {
                    if (aCoord.getP1() > corner.getP1()){
                        corner.setP1(aCoord.getP1());
                    }

                    if (aCoord.getP2() > corner.getP2()){
                        corner.setP2(aCoord.getP2());
                    }
                }
            }
        }

        return corner;
    }
}
