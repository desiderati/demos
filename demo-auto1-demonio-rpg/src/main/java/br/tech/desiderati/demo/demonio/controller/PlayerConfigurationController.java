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
package br.tech.desiderati.demo.demonio.controller;

import br.tech.desiderati.demo.demonio.model.*;
import br.tech.desiderati.demo.demonio.view.Menu;
import br.tech.desiderati.demo.demonio.view.MenuItem;
import br.tech.desiderati.demo.demonio.view.action.ExitMenuActionListener;

public class PlayerConfigurationController extends AbstractController {

    private static final String PLAYER_STATICS_MSG = "Well chosen, my lord! Here is yours stats: \n\t%s";

    Menu configureMenu() {
        return new Menu("Helloooo... my fellow player, which monster you would like to play?")
            .addNewItem(
                new MenuItem(1, 'W', "Warrior - A warrior deals damage with impressive, " +
                    "deadly feats of swordplay while weaving in and out of enemies' attacks.",
                    this))
            .addNewItem(
                new MenuItem(2, 'K', "Knight - Knights are deadly from anywhere on the " +
                    "battlefield. They strike, evade, and strike again, playing a game of position few classes " +
                    "can match.",
                    this))
            .addNewItem(
                new MenuItem(3, 'X', "Exit game", ExitMenuActionListener.getInstance()));
    }

    /**
     * FIXME I should have used the Functional Command Pattern. It would be better.
     */
    @Override
    public void onMenuAction(MenuItem menuItem) {
        City city = CityGenerator.generateNewCity();
        Player player = null;
        switch (menuItem.getOption()) {
            case 'W':
                player = (Player)
                    Player.builder().name("Warrior")
                        .currentCity(city)
                        .maxHitPoints(115)
                        .maxDamage(30)
                        .defenseFactor(1)
                        .criticalHitChance(10)
                        .build();
                break;

            case 'K':
                player = (Player)
                    Player.builder().name("Knight")
                        .currentCity(city)
                        .maxHitPoints(105)
                        .maxDamage(20)
                        .defenseFactor(2)
                        .criticalHitChance(20)
                        .build();
                break;

            default:
                printStatus("Invalid menu option!");
                printMenu();
                break;
        }

        // TODO Generate a cool monster name???
        Monster monster = MonsterGenerator.monster().withName("Monster").basedOn(player).generateNewMonster();
        city.addMonster(monster);

        printStatus(PLAYER_STATICS_MSG, player);
        loadNextController(new ExplorationController(player));
    }
}
