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

import java.io.PrintStream;

/**
 * I decided to use the PrinterManager as the only Printer available.
 * But as I've declared the Printer interface, it would be possible to implement different print strategies
 * in the future and the PrinterManager, instead of being a simple Singleton, it would be responsible
 * for loading each strategy available and to decide each one to use.
 */
public class PrinterManager implements Printer {

    // I've used early initialization just to avoid the double checked locking pattern.
    // See more: https://www.baeldung.com/java-singleton-double-checked-locking
    private static final PrinterManager instance = new PrinterManager();

    private PrintStream out;

    private PrinterManager() {
        out = System.out;
    }

    public static PrinterManager getPrinter() {
        return instance;
    }

    @Override
    public void print(String message) {
        out.println(message);
    }

    @Override
    public void printColored(Color color, String message) {
        out.println(color + message + Color.RESET);
    }
}
