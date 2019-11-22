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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@PrepareForTest(ThreadLocalRandom.class)
@RunWith(PowerMockRunner.class)
public class PlayerTest {

    private Player player;

    @Mock
    private ThreadLocalRandom randomMock;

    @Before
    public void setup() {
        PowerMockito.mockStatic(ThreadLocalRandom.class);
        when(ThreadLocalRandom.current()).thenReturn(randomMock);

        City currentCity = CityGenerator.generateNewCity();
        player = (Player) Player.builder()
            .name("Felipe")
            .currentCity(currentCity)
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(1)
            .criticalHitChance(10)
            .build();
    }

    @Test
    public void playerShouldIncreaseMaxHitPointWithSuccess() {
        int previousMaxHitPoints = player.getMaxHitPoints();
        player.increaseMaxHitPoints(10);
        assertThat(player.getMaxHitPoints()).isEqualTo(previousMaxHitPoints + 10);
    }

    @Test
    public void playerShouldDecreaseMaxHitPointWithSuccess() {
        int previousMaxHitPoints = player.getMaxHitPoints();
        player.decreaseMaxHitPoints(-1);
        assertThat(player.getMaxHitPoints()).isEqualTo(previousMaxHitPoints);

        previousMaxHitPoints = player.getMaxHitPoints();
        player.decreaseMaxHitPoints(0);
        assertThat(player.getMaxHitPoints()).isEqualTo(previousMaxHitPoints);

        previousMaxHitPoints = player.getMaxHitPoints();
        player.decreaseMaxHitPoints(10);
        assertThat(player.getMaxHitPoints()).isEqualTo(previousMaxHitPoints - 10);

        previousMaxHitPoints = player.getMaxHitPoints();
        player.decreaseMaxHitPoints(100);
        assertThat(player.getMaxHitPoints()).isEqualTo(Character.MIN_HIT_POINTS);
    }

    @Test
    public void playerShouldIncreaseMaxDamageWithSuccess() {
        int previousMaxDamage = player.getMaxDamage();
        player.increaseMaxDamage(-1);
        assertThat(player.getMaxDamage()).isEqualTo(previousMaxDamage);
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);

        previousMaxDamage = player.getMaxDamage();
        player.increaseMaxDamage(0);
        assertThat(player.getMaxDamage()).isEqualTo(previousMaxDamage);
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);

        previousMaxDamage = player.getMaxDamage();
        player.increaseMaxDamage(10);
        assertThat(player.getMaxDamage()).isEqualTo(previousMaxDamage + 10);
        assertThat(player.getMinDamage()).isEqualTo(player.getMaxDamage() / 2);
    }

    @Test
    public void playerShouldIncreaseDefenseFactorWithSuccess() {
        int previousDefenseFactor = player.getDefenseFactor();
        player.increaseDefenseFactor(-1);
        assertThat(player.getDefenseFactor()).isEqualTo(previousDefenseFactor);

        previousDefenseFactor = player.getDefenseFactor();
        player.increaseDefenseFactor(0);
        assertThat(player.getDefenseFactor()).isEqualTo(previousDefenseFactor);

        previousDefenseFactor = player.getDefenseFactor();
        player.increaseDefenseFactor(1);
        assertThat(player.getDefenseFactor()).isEqualTo(previousDefenseFactor + 1);

        previousDefenseFactor = player.getDefenseFactor();
        player.increaseDefenseFactor(100);
        assertThat(player.getDefenseFactor()).isEqualTo(Character.MAX_DEFENSE_FACTOR);
    }

    @Test
    public void playerShouldIncreaseCriticalHitChanceWithSuccess() {
        int previousCriticalHitChance = player.getCriticalHitChance();
        player.increaseCriticalHitChance(-1);
        assertThat(player.getCriticalHitChance()).isEqualTo(previousCriticalHitChance);

        previousCriticalHitChance = player.getCriticalHitChance();
        player.increaseCriticalHitChance(0);
        assertThat(player.getCriticalHitChance()).isEqualTo(previousCriticalHitChance);

        previousCriticalHitChance = player.getCriticalHitChance();
        player.increaseCriticalHitChance(10);
        assertThat(player.getCriticalHitChance()).isEqualTo(previousCriticalHitChance + 10);

        previousCriticalHitChance = player.getCriticalHitChance();
        player.increaseCriticalHitChance(100);
        assertThat(player.getCriticalHitChance()).isEqualTo(Character.MAX_CRITICAL_HIT_CHANCE);
    }

    @Test
    public void playerShouldThrowInvalidStateExceptionBecauseNullCharacterWhileAttacking() {
        assertThatThrownBy(() -> {
            player.attack(null);
        }).hasMessage("Character can not be null.");
    }

    @Test
    public void playerShouldAttackWithoutCriticalHitWithoutDefenseFactor() {
        int damageToDeal = player.getMinDamage() + (player.getMaxDamage() - player.getMinDamage()) / 2;
        when(randomMock.nextInt(anyInt(), anyInt())).thenReturn(damageToDeal);

        // It will not be a critical hit!!!
        when(randomMock.nextInt(anyInt())).thenReturn(player.getCriticalHitChance() + 5);

        Monster monster = (Monster) Monster.builder()
            .name("Monster")
            .currentCity(player.getCurrentCity())
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(1)
            .criticalHitChance(10)
            .build();

        Character.setRandom(randomMock);
        Hit hit = player.attack(monster);

        assertThat(hit.getAttackingCharacter()).isEqualTo(player);
        assertThat(player.isAlive()).isTrue();
        assertThat(hit.getDefendingCharacter()).isEqualTo(monster);
        assertThat(monster.isAlive()).isTrue();
        assertThat(hit.isCritical()).isEqualTo(false);
        assertThat(hit.getDamageDealt()).isEqualTo(damageToDeal);
    }

    @Test
    public void playerShouldAttackWithCriticalHitWithoutDefenseFactor() {
        int damageToDeal = player.getMinDamage() + (player.getMaxDamage() - player.getMinDamage()) / 2;
        when(randomMock.nextInt(anyInt(), anyInt())).thenReturn(damageToDeal);

        // It will be a critical hit!!!
        when(randomMock.nextInt(anyInt())).thenReturn(player.getCriticalHitChance() - 5);

        Monster monster = (Monster) Monster.builder()
            .name("Monster")
            .currentCity(player.getCurrentCity())
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(1)
            .criticalHitChance(10)
            .build();

        Character.setRandom(randomMock);
        Hit hit = player.attack(monster);

        assertThat(hit.getAttackingCharacter()).isEqualTo(player);
        assertThat(player.isAlive()).isTrue();
        assertThat(hit.getDefendingCharacter()).isEqualTo(monster);
        assertThat(monster.isAlive()).isTrue();
        assertThat(hit.isCritical()).isEqualTo(true);
        assertThat(hit.getDamageDealt()).isEqualTo(damageToDeal * 2); // Critical!
    }

    @Test
    public void playerShouldAttackWithoutCriticalHitWithDefenseFactor() {
        int damageToDeal = player.getMinDamage() + (player.getMaxDamage() - player.getMinDamage()) / 2;
        when(randomMock.nextInt(anyInt(), anyInt())).thenReturn(damageToDeal);

        // It will not be a critical hit!!!
        when(randomMock.nextInt(anyInt())).thenReturn(player.getCriticalHitChance() + 5);

        Monster monster = (Monster) Monster.builder()
            .name("Monster")
            .currentCity(player.getCurrentCity())
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(3)
            .criticalHitChance(10)
            .build();

        Character.setRandom(randomMock);
        Hit hit = player.attack(monster);

        assertThat(hit.getAttackingCharacter()).isEqualTo(player);
        assertThat(player.isAlive()).isTrue();
        assertThat(hit.getDefendingCharacter()).isEqualTo(monster);
        assertThat(monster.isAlive()).isTrue();
        assertThat(hit.isCritical()).isEqualTo(false);
        assertThat(hit.getDamageDealt()).isEqualTo(damageToDeal / monster.getDefenseFactor());
    }

    @Test
    public void playerShouldAttackWithCriticalHitWithDefenseFactor() {
        int damageToDeal = player.getMinDamage() + (player.getMaxDamage() - player.getMinDamage()) / 2;
        when(randomMock.nextInt(anyInt(), anyInt())).thenReturn(damageToDeal);

        // It will be a critical hit!!!
        when(randomMock.nextInt(anyInt())).thenReturn(player.getCriticalHitChance() - 5);

        Monster monster = (Monster) Monster.builder()
            .name("Monster")
            .currentCity(player.getCurrentCity())
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(3)
            .criticalHitChance(10)
            .build();

        Character.setRandom(randomMock);
        Hit hit = player.attack(monster);

        assertThat(hit.getAttackingCharacter()).isEqualTo(player);
        assertThat(player.isAlive()).isTrue();
        assertThat(hit.getDefendingCharacter()).isEqualTo(monster);
        assertThat(monster.isAlive()).isTrue();
        assertThat(hit.isCritical()).isEqualTo(true);
        assertThat(hit.getDamageDealt()).isEqualTo(damageToDeal * 2 / monster.getDefenseFactor()); // Critical!
    }

    @Test
    public void playerShouldAttackAndKillMonster() {
        int damageToDeal = 150;
        when(randomMock.nextInt(anyInt(), anyInt())).thenReturn(damageToDeal);

        // It will be a critical hit!!!
        when(randomMock.nextInt(anyInt())).thenReturn(player.getCriticalHitChance() - 5);

        Monster monster = (Monster) Monster.builder()
            .name("Monster")
            .currentCity(player.getCurrentCity())
            .maxHitPoints(100)
            .maxDamage(20)
            .defenseFactor(1)
            .criticalHitChance(10)
            .build();

        Character.setRandom(randomMock);
        Hit hit =  player.attack(monster);

        assertThat(hit.getAttackingCharacter()).isEqualTo(player);
        assertThat(player.isAlive()).isTrue();
        assertThat(hit.getDefendingCharacter()).isEqualTo(monster);
        assertThat(!monster.isAlive()).isTrue();
        assertThat(hit.isCritical()).isEqualTo(true);
        assertThat(hit.getDamageDealt()).isEqualTo(damageToDeal * 2); // Critical!
    }
}
