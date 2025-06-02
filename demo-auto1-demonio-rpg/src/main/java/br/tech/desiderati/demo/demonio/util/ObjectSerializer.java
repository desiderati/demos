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
package br.tech.desiderati.demo.demonio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;

public class ObjectSerializer {

    private static final Logger logger = LoggerFactory.getLogger(ObjectSerializer.class.getName());

    private static final String DEFAULT_FILE = "./.saved/auto1-demonio-rpg.ser";

    private ObjectSerializer() {

    }

    public static <T> boolean save(T object) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(createNewFile()))) {
            objectOutputStream.writeObject(object);
            return true;
        } catch (Exception ex) {
            logger.error("Unbale to serialize object: {}", object, ex);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(createNewFile()))) {
            return (T) objectInputStream.readObject();
        } catch (Exception ex) {
            logger.error("Unbale to deserialize object.", ex);
            return null;
        }
    }

    private static File createNewFile() throws IOException {
        File file = Paths.get(DEFAULT_FILE).toAbsolutePath().toFile();
        if (file.exists()) {
            return file;
        }

        if (!file.getParentFile().mkdirs()) {
            throw new IOException("Could not create parent directories for file: " + file.getName());
        }

        if (!file.createNewFile()) {
            throw new IOException("Could not create file: " + file.getName());
        }
        return file;
    }
}
