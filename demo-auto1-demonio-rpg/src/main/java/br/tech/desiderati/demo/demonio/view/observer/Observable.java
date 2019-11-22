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
package br.tech.desiderati.demo.demonio.view.observer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Observable<T> {

    private boolean changed = false;

    private List<Observer<T>> observers = new ArrayList<>();

    public synchronized void addObserver(Observer<T> observer) {
        if (observer == null) {
            throw new NullPointerException();
        } else {
            if (!this.observers.contains(observer)) {
                this.observers.add(observer);
            }
        }
    }

    public synchronized void deleteObserver(Observer<T> observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        this.notifyObservers(null);
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public void notifyObservers(T arg) {
        Observer<T>[] arrLocal;
        synchronized (this) {
            if (!this.changed) {
                return;
            }

            arrLocal = this.observers.toArray(new Observer[]{});
            this.clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; --i) {
            arrLocal[i].update(this, arg);
        }
    }

    public synchronized void deleteObservers() {
        this.observers.clear();
    }

    protected synchronized void setChanged() {
        this.changed = true;
    }

    private synchronized void clearChanged() {
        this.changed = false;
    }

    public synchronized boolean hasChanged() {
        return this.changed;
    }

    public synchronized int countObservers() {
        return this.observers.size();
    }
}