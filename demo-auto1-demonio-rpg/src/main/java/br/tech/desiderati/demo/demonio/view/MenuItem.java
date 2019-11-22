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
package br.tech.desiderati.demo.demonio.view;

import br.tech.desiderati.demo.demonio.view.action.MenuActionListener;
import br.tech.desiderati.demo.demonio.view.util.Printer;
import br.tech.desiderati.demo.demonio.view.util.PrinterManager;

import java.util.Objects;

public class MenuItem implements Comparable<MenuItem> {

    private int id;
    private char option;
    private String description;
    private MenuActionListener menuActionListener;

    private Printer printer = PrinterManager.getPrinter();

    public MenuItem(int id, char option, String description, MenuActionListener menuActionListener) {
        this.id = id;
        this.option = option;
        this.description = description;
        this.menuActionListener = menuActionListener;
    }

    @SuppressWarnings("unused")
    public int getId() {
        return id;
    }

    public char getOption() {
        return option;
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    void select() {
        menuActionListener.onMenuAction(this);
    }

    void print() {
        printer.print("\t" + Printer.Color.CYAN + option + Printer.Color.RESET + ": " + description);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        MenuItem menuItem = (MenuItem) other;
        return option == menuItem.option;
    }

    @Override
    public int hashCode() {
        return Objects.hash(option);
    }


    @Override
    public int compareTo(MenuItem other) {
        return id - other.id;
    }
}
