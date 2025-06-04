/*
 * Copyright (c) 2025 - Felipe Desiderati
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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("WeakerAccess")
public class MonsterGeneratorTest {

    @Test
    public void newMonsterShouldThrowInvalidStateExceptionBecauseNullOrEmptyName() {
        assertThatThrownBy(() -> MonsterGenerator.monster().generateNewMonster())
            .hasMessage("Name can not be null or empty.");

        assertThatThrownBy(() -> MonsterGenerator.monster().withName("").generateNewMonster())
            .hasMessage("Name can not be null or empty.");
    }

    @Test
    public void newMonsterShouldThrowInvalidStateExceptionBecauseNullCharacter() {
        assertThatThrownBy(() -> MonsterGenerator.monster().withName("Monster").generateNewMonster())
            .hasMessage("Character can not be null.");
    }

    @Test
    public void newMonsterShouldBeCreateWithSuccess() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(-1)
                .maxDamage(-1)
                .defenseFactor(-1)
                .criticalHitChance(-1)
                .build();

        Monster monster = MonsterGenerator.monster()
            .withName("Monster")
            .basedOn(player)
            .generateNewMonster();

        assertThat(monster.getName()).isEqualTo("Monster");
        assertThat(monster.getCurrentCity()).isEqualTo(currentCity);
        assertThat(monster.getMaxHitPoints()).isGreaterThanOrEqualTo(Character.MIN_HIT_POINTS);
        assertThat(monster.getCurrentHitPoints()).isEqualTo(monster.getMaxHitPoints());
        assertThat(monster.getMinDamage()).isEqualTo(monster.getMaxDamage() / 2);
        assertThat(monster.getMaxDamage()).isGreaterThanOrEqualTo(Character.MIN_DAMAGE);
        assertThat(monster.getDefenseFactor()).isGreaterThanOrEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(monster.getCriticalHitChance()).isGreaterThanOrEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(1000)
                .maxDamage(1000)
                .defenseFactor(1000)
                .criticalHitChance(1000)
                .build();

        monster = MonsterGenerator.monster()
            .withName("Monster")
            .basedOn(player)
            .generateNewMonster();

        assertThat(monster.getName()).isEqualTo("Monster");
        assertThat(monster.getCurrentCity()).isEqualTo(currentCity);
        assertThat(monster.getMaxHitPoints()).isGreaterThanOrEqualTo(Character.MIN_HIT_POINTS);
        assertThat(monster.getCurrentHitPoints()).isEqualTo(monster.getMaxHitPoints());
        assertThat(monster.getMinDamage()).isEqualTo(monster.getMaxDamage() / 2);
        assertThat(monster.getMaxDamage()).isGreaterThanOrEqualTo(Character.MIN_DAMAGE);
        assertThat(monster.getDefenseFactor()).isLessThanOrEqualTo(Character.MAX_DEFENSE_FACTOR);
        assertThat(monster.getCriticalHitChance()).isLessThanOrEqualTo(Character.MAX_CRITICAL_HIT_CHANCE);
    }
}
