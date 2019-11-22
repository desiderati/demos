/*
 * Copyright (c) 2019 - Felipe Desiderati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package br.tech.desiderati.demo.demonio.model;

import java.io.Serializable;

public class City implements Serializable {

    private String name;

    private City northCityNeighbor = null;
    private City southCityNeighbor = null;
    private City eastCityNeighbor = null;
    private City westCityNeighbor = null;

    private Monster monster;

    private boolean monsterCanBeAvoided = false;

    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public City getNorthCityNeighbor() {
        if (northCityNeighbor == null) {
            northCityNeighbor = CityGenerator.generateNewCity();
        }
        northCityNeighbor.setSouthCityNeighbor(this);
        return northCityNeighbor;
    }

    public City getSouthCityNeighbor() {
        if (southCityNeighbor == null) {
            southCityNeighbor = CityGenerator.generateNewCity();
        }
        southCityNeighbor.setNorthCityNeighbor(this);
        return southCityNeighbor;
    }

    public City getEastCityNeighbor() {
        if (eastCityNeighbor == null) {
            eastCityNeighbor = CityGenerator.generateNewCity();
        }
        eastCityNeighbor.setWestCityNeighbor(this);
        return eastCityNeighbor;
    }

    public City getWestCityNeighbor() {
        if (westCityNeighbor == null) {
            westCityNeighbor = CityGenerator.generateNewCity();
        }
        westCityNeighbor.setEastCityNeighbor(this);
        return westCityNeighbor;
    }

    private void setNorthCityNeighbor(City northCityNeighbor) {
        this.northCityNeighbor = northCityNeighbor;
    }

    private void setSouthCityNeighbor(City southCityNeighbor) {
        this.southCityNeighbor = southCityNeighbor;
    }

    private void setEastCityNeighbor(City eastCityNeighbor) {
        this.eastCityNeighbor = eastCityNeighbor;
    }

    private void setWestCityNeighbor(City westCityNeighbor) {
        this.westCityNeighbor = westCityNeighbor;
    }

    public Monster getMonster() {
        return monster;
    }

    public void addMonster(Monster monster) {
        this.monster = monster;
    }

    public void avoidMonster() {
        monsterCanBeAvoided = true;
    }

    public boolean hasMonster() {
        return monster != null;
    }

    public boolean hasMonsterAlive() {
        return monster != null && monster.isAlive();
    }

    public boolean hasMonsterAliveAndWaitingToFight() {
        return monster != null && monster.isAlive() && !monsterCanBeAvoided;
    }

    @Override
    public String toString() {
        return name;
    }
}
