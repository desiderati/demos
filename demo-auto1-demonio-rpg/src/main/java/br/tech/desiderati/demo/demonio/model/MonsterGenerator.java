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

import java.util.concurrent.ThreadLocalRandom;

public final class MonsterGenerator {

    private String name;
    private Character character;

    private MonsterGenerator() {

    }

    public static MonsterGenerator monster() {
        return new MonsterGenerator();
    }

    public MonsterGenerator withName(String name) {
        this.name = name;
        return this;
    }

    public MonsterGenerator basedOn(Character character) {
        this.character = character;
        return this;
    }

    public Monster generateNewMonster() {
        // It would be better if we could use Java Validation for this!!!
        if (name == null || name.isEmpty()) { // And Apache Commons!!!
            throw new IllegalStateException("Name can not be null or empty.");
        }

        if (character == null) {
            throw new IllegalStateException("Character can not be null.");
        }

        int newMaxHitPoints = computeVariationValue( 10, 20, character.getMaxHitPoints());
        int newMaxDamage = computeVariationValue( 10, 15, character.getMaxDamage());
        int newDefenseFactor = computeVariationValue( 50, 100, character.getDefenseFactor());
        int newCriticalHitChance = computeVariationValue( 10, 20, character.getCriticalHitChance());

        return (Monster) Monster.builder().name(name)
            .currentCity(character.getCurrentCity())
            .maxHitPoints(newMaxHitPoints)
            .maxDamage(newMaxDamage)
            .defenseFactor(newDefenseFactor)
            .criticalHitChance(newCriticalHitChance)
            .build();
    }

    private int computeVariationValue(int minVariation, int maxVariation, int intValue) {
        int variation = ThreadLocalRandom.current().nextInt(minVariation, maxVariation + 1);
        variation = (intValue * variation) / 100;
        int minValue = intValue - variation;
        if (minValue <= 0) {
            minValue = 1;
        }

        int maxValue = intValue + variation;
        return ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
    }
}
