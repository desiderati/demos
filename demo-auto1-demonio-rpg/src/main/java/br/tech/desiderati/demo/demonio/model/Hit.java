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

public class Hit {

    // If it was possible to use Lombok Project it would be cleaner.
    public static class HitBuilder {

        private Character attackingCharacter;
        private Character defendingCharacter;
        private boolean isCritical;
        private int damageDealt;

        HitBuilder() {
        }

        HitBuilder attackingCharacter(Character attackingCharacter) {
            this.attackingCharacter = attackingCharacter;
            return this;
        }

        HitBuilder defendingCharacter(Character defendingCharacter) {
            this.defendingCharacter = defendingCharacter;
            return this;
        }

        HitBuilder isCritical(boolean isCritical) {
            this.isCritical = isCritical;
            return this;
        }

        HitBuilder amount(int damageDealt) {
            this.damageDealt = damageDealt;
            return this;
        }

        Hit build() {
            // It would be better if we could use Java Validation for this!!!
            if (attackingCharacter == null) {
                throw new IllegalStateException("Attacking Character can not be null.");
            }

            if (defendingCharacter == null) {
                throw new IllegalStateException("Defending Character can not be null.");
            }

            if (damageDealt < 1) {
                throw new IllegalStateException("Damage Dealt should be at least 1.");
            }

            return new Hit(attackingCharacter, defendingCharacter, isCritical, damageDealt);
        }
    }

    private Character attackingCharacter;
    private Character defendingCharacter;
    private boolean isCritical;
    private int damageDealt;

    private Hit(Character attackingCharacter, Character defendingCharacter, boolean isCritical, int damageDealt) {
        this.attackingCharacter = attackingCharacter;
        this.defendingCharacter = defendingCharacter;
        this.isCritical = isCritical;
        this.damageDealt = damageDealt;
    }

    // Used only by Character!
    static HitBuilder builder() {
        return new HitBuilder();
    }

    public Character getAttackingCharacter() {
        return attackingCharacter;
    }

    public Character getDefendingCharacter() {
        return defendingCharacter;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public int getDamageDealt() {
        return damageDealt;
    }
}
