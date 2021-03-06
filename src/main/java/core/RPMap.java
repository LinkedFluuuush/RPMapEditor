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
package core;

import core.util.Pair;

import java.util.*;


public class RPMap {
    private final Map<Pair<Integer>, Tile> mapTiles;

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

    public void setTile(int p1, int p2, Tile tile){
        mapTiles.put(new Pair<>(p1, p2), tile);

    }

    public Map<Pair<Integer>, Tile> getMapTiles() {
        return mapTiles;
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

    public void cleanMap(){
        Tile tile;
        Set<Pair<Integer>> allTileKeys = this.getMapTiles().keySet();
        List<Pair<Integer>> removingTiles = new ArrayList<>();

        for(Pair<Integer> key : allTileKeys){
            tile = this.getMapTiles().get(key);

            if(!tile.isRoom() && tile.getType() == Tile.TileType.EMPTY){
                removingTiles.add(key);
            }
        }

        for(Pair<Integer> key : removingTiles){
            this.getMapTiles().remove(key);
        }
    }
}
