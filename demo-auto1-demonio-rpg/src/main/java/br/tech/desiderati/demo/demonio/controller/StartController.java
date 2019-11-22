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

import br.tech.desiderati.demo.demonio.view.Menu;
import br.tech.desiderati.demo.demonio.view.MenuItem;
import br.tech.desiderati.demo.demonio.model.Player;
import br.tech.desiderati.demo.demonio.util.ObjectSerializer;
import br.tech.desiderati.demo.demonio.view.action.ExitMenuActionListener;

public class StartController extends AbstractController {

    private static final String PLAYER_STATICS_MSG = "Game loaded with success! Here is yours stats: \n\t%s";

    Menu configureMenu() {
        return new Menu("Welcome to Demônio RPG! An open world RPG Game where you can play as much as you can!")
            .addNewItem(new MenuItem(1, 'S', "Start a new game", this))
            .addNewItem(new MenuItem(2, 'L', "Load saved game", this))
            .addNewItem(new MenuItem(3, 'X', "Exit game", ExitMenuActionListener.getInstance()));
    }

    @Override
    public void onMenuAction(MenuItem menuItem) {
        switch (menuItem.getOption()) {
            case 'S':
                printStatus("Okay sir, let's the game begins...");
                loadNextController(new PlayerConfigurationController());
                break;

            case 'L':
                Player player = ObjectSerializer.load();
                if (player == null) {
                    printStatus("There is no saved game to load or saved file is corrupted!");
                    printMenu();
                }

                printStatus(PLAYER_STATICS_MSG, player);
                loadNextController(new ExplorationController(player));
                break;

            default:
                printStatus("Invalid menu option!");
                printMenu();
                break;
        }
    }

    public static void main(String[] args) {
        Controller startController = new StartController();
        startController.printMenu();
    }
}
