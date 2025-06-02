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

import br.tech.desiderati.demo.demonio.view.observer.Observable;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character extends Observable<Hit> implements Serializable {

    static final int MIN_HIT_POINTS = 50;

    static final int MIN_DAMAGE = 10;

    static final int MIN_DEFENSE_FACTOR = 1;

    static final int MAX_DEFENSE_FACTOR = 5;

    static final int MIN_CRITICAL_HIT_CHANCE = 5;

    static final int MAX_CRITICAL_HIT_CHANCE = 60;

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    // If it was possible to use Lombok Project, it would be cleaner.
    public abstract static class CharacterInnerBuilder<T> {

        private String name;
        private City currentCity;
        private int maxHitPoints;
        private int maxDamage;
        private int defenseFactor;
        private int criticalHitChance;

        CharacterInnerBuilder() {
        }

        public CharacterInnerBuilder<T> name(String name) {
            this.name = name;
            return this;
        }

        public CharacterInnerBuilder<T> currentCity(City currentCity) {
            this.currentCity = currentCity;
            return this;
        }

        public CharacterInnerBuilder<T> maxHitPoints(int maxHitPoints) {
            this.maxHitPoints = maxHitPoints;
            return this;
        }

        public CharacterInnerBuilder<T> maxDamage(int maxDamage) {
            this.maxDamage = maxDamage;
            return this;
        }

        public CharacterInnerBuilder<T> defenseFactor(int defenseFactor) {
            this.defenseFactor = defenseFactor;
            return this;
        }

        public CharacterInnerBuilder<T> criticalHitChance(int criticalHitChance) {
            this.criticalHitChance = criticalHitChance;
            return this;
        }

        public T build() {
            // It would be better if we could use Java Validation for this!!!
            if (name == null || name.isEmpty()) { // And Apache Commons!!!
                throw new IllegalStateException("Name can not be null or empty.");
            }

            if (currentCity == null) {
                throw new IllegalStateException("Current City can not be null.");
            }

            if (maxHitPoints < MIN_HIT_POINTS) {
                maxHitPoints = MIN_HIT_POINTS;
            }

            if (maxDamage < MIN_DAMAGE) {
                maxDamage = MIN_DAMAGE;
            }

            if (defenseFactor < MIN_DEFENSE_FACTOR) {
                defenseFactor = MIN_DEFENSE_FACTOR;
            }

            if (defenseFactor > MAX_DEFENSE_FACTOR) {
                defenseFactor = MAX_DEFENSE_FACTOR;
            }

            if (criticalHitChance < MIN_CRITICAL_HIT_CHANCE) {
                criticalHitChance = MIN_CRITICAL_HIT_CHANCE;
            }

            if (criticalHitChance > MAX_CRITICAL_HIT_CHANCE) {
                criticalHitChance = MAX_CRITICAL_HIT_CHANCE;
            }

            return doBuild(name, currentCity, maxHitPoints, maxDamage, defenseFactor, criticalHitChance);
        }

        abstract T doBuild(String name, City currentCity, int maxHitPoints, int maxDamage, int defenseFactor,
                           int criticalHitChance);
    }

    private final String name;
    private City currentCity;

    /**
     * Health is usually measured in hit points or health points, shortened to HP which lowers
     * by set amounts when the entity is attacked or injured.
     */
    private int maxHitPoints;
    private int currentHitPoints;

    /**
     * Damage that can be done.
     */
    private int minDamage;
    private int maxDamage;

    /**
     * It damage dealt to this monster will be reduced by this defense factor.
     */
    private int defenseFactor;

    /**
     * Critical hits occur randomly when striking enemies. By default, each successful hit on an enemy
     * has a x% chance of being a "critical hit", dealing approximately double the usual damage.
     */
    private int criticalHitChance;

    Character(String name, City currentCity, int maxHitPoints, int maxDamage, int defenseFactor, int criticalHitChance) {
        this.name = name;
        this.currentCity = currentCity;
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
        this.minDamage = maxDamage / 2;
        this.maxDamage = maxDamage;
        this.defenseFactor = defenseFactor;
        this.criticalHitChance = criticalHitChance;
    }

    // Just for testing!!!
    static void setRandom(ThreadLocalRandom random) {
        Character.random = random;
    }

    public String getName() {
        return name;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    void increaseMaxHitPoints(int value) {
        if (value < 0) {
            value = 0;
        }

        maxHitPoints += value;
    }

    void decreaseMaxHitPoints(int value) {
        if (value < 0) {
            value = 0;
        }

        maxHitPoints -= value;
        if (maxHitPoints < MIN_HIT_POINTS) {
            maxHitPoints = MIN_HIT_POINTS;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    @SuppressWarnings("WeakerAccess")
    public int getMinDamage() {
        return minDamage;
    }

    @SuppressWarnings("WeakerAccess")
    public int getMaxDamage() {
        return maxDamage;
    }

    void increaseMaxDamage(int value) {
        if (value < 0) {
            value = 0;
        }

        maxDamage += value;
        minDamage = maxDamage / 2;
    }

    @SuppressWarnings("WeakerAccess")
    public int getDefenseFactor() {
        return defenseFactor;
    }

    void increaseDefenseFactor(int value) {
        if (value < 0) {
            value = 0;
        }

        defenseFactor += value;
        if (defenseFactor > MAX_DEFENSE_FACTOR) {
            defenseFactor = MAX_DEFENSE_FACTOR;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public int getCriticalHitChance() {
        return criticalHitChance;
    }

    void increaseCriticalHitChance(int value) {
        if (value < 0) {
            value = 0;
        }

        criticalHitChance += value;
        if (criticalHitChance > MAX_CRITICAL_HIT_CHANCE) {
            criticalHitChance = MAX_CRITICAL_HIT_CHANCE;
        }
    }

    public boolean isAlive() {
        return currentHitPoints > 0;
    }

    public Hit attack(Character otherCharacter) {
        if (otherCharacter == null) {
            throw new IllegalArgumentException("Character can not be null.");
        }

        int damageToDeal = calculateDamageToDeal();
        boolean isCritical = false;
        if (isCriticalHit()) {
            damageToDeal = damageToDeal * 2;
            isCritical = true;
        }

        int damageDealt = otherCharacter.receiveDamage(damageToDeal);
        Hit hit = Hit.builder()
            .attackingCharacter(this)
            .defendingCharacter(otherCharacter)
            .isCritical(isCritical)
            .amount(damageDealt)
            .build();

        setChanged();
        notifyObservers(hit);
        return hit;
    }

    private int calculateDamageToDeal() {
        return random.nextInt(minDamage, maxDamage + 1);
    }

    private boolean isCriticalHit() {
        int chance = random.nextInt(99) + 1; // 1 to 100
        return chance <= criticalHitChance;
    }

    private int receiveDamage(int damageToReceive) {
        int newDamageToReceive = damageToReceive / defenseFactor;
        currentHitPoints -= newDamageToReceive;
        if (currentHitPoints < 0) {
            currentHitPoints = 0;
        }

        return newDamageToReceive;
    }

    public void heal() {
        currentHitPoints = maxHitPoints;
    }

    public String toString() {
        return this.name + "(maxHitPoints=" + this.maxHitPoints
            + ", currentHitPoints=" + this.currentHitPoints
            + ", minDamage=" + this.minDamage
            + ", maxDamage=" + this.maxDamage
            + ", defenseFactor=" + this.defenseFactor
            + ", criticalHitChance=" + this.criticalHitChance + ")";
    }
}
