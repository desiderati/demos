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

import br.tech.desiderati.demo.demonio.view.util.InputReader;
import br.tech.desiderati.demo.demonio.view.util.InputReaderManager;
import br.tech.desiderati.demo.demonio.view.util.Printer;
import br.tech.desiderati.demo.demonio.view.util.PrinterManager;

import java.util.Set;
import java.util.TreeSet;

public class Menu {

    private String description;
    private Set<MenuItem> items;

    private Printer printer = PrinterManager.getPrinter();
    private InputReader reader = InputReaderManager.getInputReader();

    public Menu() {
        this("");
    }

    public Menu(String description) {
        this.description = description;
        this.items = new TreeSet<>();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Using fluent language. I could use a Builder here, but as
     * the Class is so simple, I didn't see the need for it.
     * Example:
     *    Menu.monster().id(1).name("Menu Name")
     *      .newItem(itemA)
     *      .newItem(itemB)
     *      .build()
     */
    public Menu addNewItem(MenuItem item) {
        this.items.add(item);
        return this;
    }

    public void print() {
        printer.printColored(Printer.Color.GREEN, description);
        printer.print("Please select an option below:");
        for (MenuItem item : items) {
            item.print();
        }

        boolean optionIsInvalid;
        MenuItem chosenMenuItem;
        do {
            char option = reader.readOption();
            chosenMenuItem = chooseMenuItem(option);
            optionIsInvalid = chosenMenuItem == null;
            if (optionIsInvalid) {
                printer.printColored(Printer.Color.RED, "Not a valid option!");
            }
        } while (optionIsInvalid);

        chosenMenuItem.select();
    }

    private MenuItem chooseMenuItem(char option) {
        return items.stream().filter(item -> option == item.getOption()).findFirst().orElse(null);
    }
}
