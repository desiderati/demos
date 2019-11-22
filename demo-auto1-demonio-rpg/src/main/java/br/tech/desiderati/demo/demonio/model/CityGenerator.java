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

import java.util.concurrent.ThreadLocalRandom;

public final class CityGenerator {

    private static final ThreadLocalRandom rand = ThreadLocalRandom.current();

    private static final String[] prefixes = {"Kr", "Ca", "Ra", "Mrok", "Cru",
        "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
        "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
        "Mar", "Luk"};

    private static final String[] radicals = {"air", "ir", "mi", "sor", "mee",
        "clo", "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur",
        "zer", "marac", "zoir", "slamar", "salmar", "urak"};

    private static final String[] suffixes = {"d", "ed", "ark", "arc", "es",
        "er", "der", "tron", "med", "ure", "zur", "cred", "mur"};

    private CityGenerator() {

    }

    public static City generateNewCity() {
        String name = prefixes[rand.nextInt(prefixes.length)] +
            radicals[rand.nextInt(radicals.length)] +
            suffixes[rand.nextInt(suffixes.length)];
        return new City(name + " City");
    }
}
