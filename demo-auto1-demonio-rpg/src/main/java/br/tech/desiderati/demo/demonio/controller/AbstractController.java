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
import br.tech.desiderati.demo.demonio.view.StatusBar;
import br.tech.desiderati.demo.demonio.view.action.MenuActionListener;

/**
 * All controllers should be a {@link MenuActionListener}.
 * <p>
 * It will be composed by a {@link Menu}, {@link StatusBar} and the next {@link Controller}.
 */
abstract class AbstractController implements Controller, MenuActionListener {

    private Menu menu = null;
    private StatusBar statusBar = null;
    private Controller nextController = null;

    AbstractController() {
        this.menu = configureMenu();
        this.statusBar = new StatusBar();
    }

    public Menu getMenu() {
        return menu;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    abstract Menu configureMenu();

    /**
     * It loads the next {@link Controller} on the chain.
     */
    void loadNextController(Controller nextController) {
        this.nextController = nextController;
        this.nextController.printMenu();
    }

    public Controller getNextController() {
        return nextController;
    }

    @Override
    public void printMenu() {
        menu.print();
    }

    public void printStatus(String status, Object... params) {
        statusBar.print(String.format(status, params));
    }
}


