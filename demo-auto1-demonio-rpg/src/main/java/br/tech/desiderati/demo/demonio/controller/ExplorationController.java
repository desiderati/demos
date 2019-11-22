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

import br.tech.desiderati.demo.demonio.model.City;
import br.tech.desiderati.demo.demonio.model.MonsterGenerator;
import br.tech.desiderati.demo.demonio.view.Menu;
import br.tech.desiderati.demo.demonio.view.MenuItem;
import br.tech.desiderati.demo.demonio.model.Monster;
import br.tech.desiderati.demo.demonio.model.Player;
import br.tech.desiderati.demo.demonio.util.ObjectSerializer;
import br.tech.desiderati.demo.demonio.view.action.ExitMenuActionListener;

import java.util.function.Supplier;

public class ExplorationController extends AbstractController {

    private static final String PLAYER_STATICS_MSG =  "Here is yours player stats: \n\t%s";

    private Player player = null;

    public ExplorationController(Player player) {
        super();
        this.player = player;
    }

    Menu configureMenu() {
        return new Menu()
            .addNewItem(new MenuItem(1, 'W', "Go North", this))
            .addNewItem(new MenuItem(2, 'S', "Go South", this))
            .addNewItem(new MenuItem(3, 'D', "Go East", this))
            .addNewItem(new MenuItem(4, 'A', "Go West", this))
            .addNewItem(new MenuItem(5, 'F', "Fight a Monster", this))
            .addNewItem(new MenuItem(6, '1', "Show Menu", this))
            .addNewItem(new MenuItem(7, '2', "Show Player Statics", this))
            .addNewItem(new MenuItem(8, '3', "Save the Game", this))
            .addNewItem(new MenuItem(9, 'X', "Exit game", ExitMenuActionListener.getInstance()));
    }

    @Override
    public void printMenu() {
        // Before loading the controller we must update the information being printed.
        String formattedDescription = null;
        City currentCity = player.getCurrentCity();
        if (currentCity.getMonster().isAlive()) {
            formattedDescription =
                String.format("You're in the %s. Here lives: \n\t%s", currentCity, currentCity.getMonster());
        } else {
            formattedDescription =
                String.format("You're in the %s. There is no monster in this city to fight off!", currentCity);
        }

        getMenu().setDescription(formattedDescription);
        super.printMenu();
    }

    /**
     * FIXME I should have used the Functional Command Pattern. It would be better.
     */
    @Override
    public void onMenuAction(MenuItem menuItem) {
        switch (menuItem.getOption()) {
            case 'W':
                travelToCity(() -> player.goNorth());
                break;

            case 'S':
                travelToCity(() -> player.goSouth());
                break;

            case 'D':
                travelToCity(() -> player.goEast());
                break;

            case 'A':
                travelToCity(() -> player.goWest());
                break;

            case 'F':
                if (!player.getCurrentCity().hasMonsterAlive()) {
                    printStatus("There is no monster in this city to fight off!");
                    printMenu();
                } else {
                    printStatus("Prepare for the battle!");
                    loadNextController(
                        new FightController(this, player, player.getCurrentCity().getMonster()));
                }
                break;

            case '1':
                printStatus("Showing menu options...");
                printMenu();
                break;

            case '2':
                printStatus(PLAYER_STATICS_MSG, player);
                printMenu();
                break;

            case '3':
                if (!ObjectSerializer.save(player)) {
                    printStatus("It wasn't possible to save game!");
                } else {
                    printStatus("Game saved!");
                }
                printMenu();
                break;

            default:
                printStatus("Invalid menu option!");
                printMenu();
                break;
        }
    }

    private void travelToCity(Supplier<City> currentCitySupplier) {
        if (player.getCurrentCity().hasMonsterAliveAndWaitingToFight()) {
            printStatus("You cannot travel to another city, as long as a monster lives in town " +
                "and you didn't figth against him yet.");
        } else {
            City currentCity = currentCitySupplier.get();
            addMonsterToCity(currentCity);
            printStatus("You are traveling to %s", currentCity);
        }
        printMenu();
    }

    private void addMonsterToCity(City currentCity) {
        if (!currentCity.hasMonster()) {
            // TODO Generate a cool monster name???
            Monster monster = MonsterGenerator.monster().withName("Monster").basedOn(player).generateNewMonster();
            currentCity.addMonster(monster);
        }
    }
}
