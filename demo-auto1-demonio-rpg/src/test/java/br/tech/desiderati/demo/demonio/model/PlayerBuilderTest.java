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

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
public class PlayerBuilderTest {

    @Test
    public void newPlayerShouldThrowInvalidStateExceptionBecauseNullOrEmptyName() {
        assertThatThrownBy(() -> Player.builder().build())
            .hasMessage("Name can not be null or empty.");

        assertThatThrownBy(() -> Player.builder().name("").build())
            .hasMessage("Name can not be null or empty.");
    }

    @Test
    public void newPlayerShouldThrowInvalidStateExceptionBecauseNullCity() {
        assertThatThrownBy(() -> Player.builder().name("Felipe").build())
            .hasMessage("Current City can not be null.");
    }

    @Test
    public void newPlayerShouldBeCreatedEvenWithoutMaxHitPoints() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(Character.MIN_HIT_POINTS);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(Character.MIN_DAMAGE);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(Character.MIN_HIT_POINTS - 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(Character.MIN_HIT_POINTS);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(Character.MIN_DAMAGE);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);
    }

    @Test
    public void newPlayerShouldBeCreatedEvenWithoutMaxDamage() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(Character.MIN_DAMAGE);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(Character.MIN_DAMAGE - 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(Character.MIN_DAMAGE);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);
    }

    @Test
    public void newPlayerShouldBeCreatedEvenWithoutDefenseFactor() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(Character.MIN_DEFENSE_FACTOR - 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MIN_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(Character.MAX_DEFENSE_FACTOR + 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MAX_DEFENSE_FACTOR);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);
    }

    @Test
    public void newPlayerShouldBeCreatedEvenWithoutCriticalHit() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(1);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(1)
                .criticalHitChance(Character.MIN_CRITICAL_HIT_CHANCE - 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(1);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MIN_CRITICAL_HIT_CHANCE);

        player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(1)
                .criticalHitChance(Character.MAX_CRITICAL_HIT_CHANCE + 1)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(1);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MAX_CRITICAL_HIT_CHANCE);
    }

    @Test
    public void newPlayerShouldBeCreatedWithSuccess() {
        City currentCity = CityGenerator.generateNewCity();
        Player player =
            Player.builder()
                .name("Felipe")
                .currentCity(currentCity)
                .maxHitPoints(100)
                .maxDamage(20)
                .defenseFactor(1)
                .criticalHitChance(10)
                .build();

        assertThat(player.getName()).isEqualTo("Felipe");
        assertThat(player.getCurrentCity()).isEqualTo(currentCity);
        assertThat(player.getMaxHitPoints()).isEqualTo(100);
        assertThat(player.getCurrentHitPoints()).isEqualTo(player.getMaxHitPoints());
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
        assertThat(player.getMaxDamage()).isEqualTo(20);
        assertThat(player.getDefenseFactor()).isEqualTo(1);
        assertThat(player.getCriticalHitChance()).isEqualTo(10);
    }
}
