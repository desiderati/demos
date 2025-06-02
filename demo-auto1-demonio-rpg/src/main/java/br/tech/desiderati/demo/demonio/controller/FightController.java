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

import br.tech.desiderati.demo.demonio.view.MenuItem;
import br.tech.desiderati.demo.demonio.model.Monster;
import br.tech.desiderati.demo.demonio.model.Player;
import br.tech.desiderati.demo.demonio.view.Menu;
import br.tech.desiderati.demo.demonio.view.action.ExitMenuActionListener;
import br.tech.desiderati.demo.demonio.view.observer.HitObserver;

public class FightController extends AbstractController {

    private static final String PLAYER_STATICS_MSG = "Here is yours stats: \n\t%s";

    private static final String MONSTER_STATICS_MSG = "You're fighting: \n\t%s";

    private final Player player;
    private final Monster monster;
    private final Controller nextController;

    FightController(Controller nextController, Player player, Monster monster) {
        super();
        this.nextController = nextController;
        this.player = player;
        this.monster = monster;
    }

    Menu configureMenu() {
        return new Menu()
            .addNewItem(new MenuItem(1, 'F', "Fight monster", this))
            .addNewItem(new MenuItem(2, 'R', "Run for your soul, your fool", this))
            .addNewItem(new MenuItem(3, 'X', "Exit game", ExitMenuActionListener.getInstance()));
    }

    @Override
    public void printMenu() {
        // Before loading the controller, we must update the information being printed.
        String formattedDescription = String.format(PLAYER_STATICS_MSG, player)
            + "\n" + String.format(MONSTER_STATICS_MSG, monster);
        getMenu().setDescription(formattedDescription);
        super.printMenu();
    }

    /**
     * FIXME I should have used the Functional Command Pattern. It would be better.
     */
    @Override
    public void onMenuAction(MenuItem menuItem) {
        switch (menuItem.getOption()) {
            case 'F':
                HitObserver observer = new HitObserver();
                if (player.countObservers() == 0) {
                    player.addObserver(observer);
                }
                if (monster.countObservers() == 0) {
                    monster.addObserver(observer);
                }

                // I could have used the Hit class returned by the attack() method here and print the information.
                // But I've decided to demonstrate how to update a View when the Model state changes.
                player.attack(monster);
                if (monster.isAlive()) {
                    monster.attack(player);
                }

                if (!player.isAlive()) {
                    printStatus("Your lifeless body hits the floor. GAME OVER");
                    System.exit(1);
                }

                if (monster.isAlive()) {
                    printStatus("You should not stand, damn monster!");
                    printMenu();
                } else {
                    player.levelUp();
                    printStatus("The monster has been defeated!");
                    loadNextController(nextController);
                }
                break;

            case 'R':
                player.levelDown();
                player.getCurrentCity().avoidMonster();
                monster.heal();
                printStatus("You received a penalty for running like a coward! " +
                    "You Maximum Hit Points are now: %s", player.getMaxHitPoints());
                loadNextController(nextController);
                break;

            default:
                printStatus("Invalid menu option!");
                printMenu();
                break;
        }
    }
}
