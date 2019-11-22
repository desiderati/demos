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

public class Player extends Character {

    private static final int MAX_LEVEL = 100;

    // If it was possible to use Lombok Project it would be cleaner.
    public static final class PlayerInnerBuilder extends CharacterInnerBuilder<Player> {

        private PlayerInnerBuilder() {

        }

        @Override
        Player doBuild(String name, City currentCity, int maxHitPoints, int maxDamage,
                       int defenseFactor, int criticalHitChance) {
            return new Player(name, currentCity, maxHitPoints, maxDamage, defenseFactor, criticalHitChance);
        }
    }

    private int level;

    private Player(String name, City currentCity, int maxHitPoints, int maxDamage, int defenseFactor,
                   int criticalHitChance) {
        super(name, currentCity, maxHitPoints, maxDamage, defenseFactor, criticalHitChance);
        level = 1;
    }

    public static PlayerInnerBuilder builder() {
        return new PlayerInnerBuilder();
    }

    public void levelUp() {
        level++;
        if (level <= MAX_LEVEL) {
            int xp = (int) Math.log(level * 5.0f); // Log Factor = 5

            increaseMaxHitPoints(xp * 10);
            increaseMaxDamage(xp);

            boolean isEvenLevel = (level % 2 == 0);
            increaseCriticalHitChance(isEvenLevel? 1 : 0);

            increaseDefenseFactorEvery20Levels();
        }
        heal();
    }

    private void increaseDefenseFactorEvery20Levels() {
        int newDefenseFactor = (level / 20) + 1;
        if (newDefenseFactor > getDefenseFactor()) {
            increaseDefenseFactor(1);
        }
    }

    // TODO Improve level down algorithm???
    public void levelDown() {
        int xp = (int) Math.log(level * 5.0f); // Log Factor = 5
        decreaseMaxHitPoints(xp * 10);
        heal();
    }

    public City goNorth() {
        setCurrentCity(getCurrentCity().getNorthCityNeighbor());
        return getCurrentCity();
    }

    public City goSouth() {
        setCurrentCity(getCurrentCity().getSouthCityNeighbor());
        return getCurrentCity();
    }

    public City goEast() {
        setCurrentCity(getCurrentCity().getEastCityNeighbor());
        return getCurrentCity();
    }

    public City goWest() {
        setCurrentCity(getCurrentCity().getWestCityNeighbor());
        return getCurrentCity();
    }
}
