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
package br.tech.desiderati.demo.demonio.view.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @see PrinterManager for deatiled description about Singleton Pattern.
 */
public class InputReaderManager implements InputReader {

    private static final Logger logger = LoggerFactory.getLogger(InputReaderManager.class.getName());

    private static final InputReaderManager instance = new InputReaderManager();

    private Scanner scanner;

    private Printer printer;

    private InputReaderManager() {
        scanner = new Scanner(System.in);
        printer = PrinterManager.getPrinter();
    }

    public static InputReaderManager getInputReader() {
        return instance;
    }

    @Override
    public char readOption() {
        boolean optionIsInvalid;
        String option;

        do {
            try {
                option = scanner.next();
                if (option.length() != 1 || !option.matches("[a-zA-Z0-9]")) {
                    throw new IllegalArgumentException("Invalid option [" + option + "]. " +
                        "Only alphanumeric characters are allowed!");
                }
            } catch (Exception ex) {
                // I'm using a logging framework just to make easier futures debugs.
                logger.error("Error while reading user option.", ex);

                printer.printColored(Printer.Color.RED, "Not a valid option!");
                option = null;
            }

            optionIsInvalid = (option == null);
        } while (optionIsInvalid);

        return Character.toUpperCase(option.charAt(0));
    }
}
