/*
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 *
 * Project Info:  http://www.idrsolutions.com
 * Help section for developers at http://www.idrsolutions.com/java-pdf-library-support/
 *
 * (C) Copyright 1997-2013, IDRsolutions and Contributors.
 *
 * 	This file is part of JPedal
 *
     This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


 *
 * ---------------
 * Vector_Short.java
 * ---------------
 */
package org.jpedal.utils.repositories;

import java.io.Serializable;

/**
 * Provides the functionality/convenience of a Vector for shorts
 * 
 * Much faster because not synchronized and no cast Does not double in size each time
 */
public class Vector_Short implements Serializable {

	private static final long serialVersionUID = -177128610623475512L;
	// how much we resize each time - will be doubled up to 160
	int increment_size = 1000;
	protected int current_item = 0;

	// current max size
	int max_size = 250;

	// holds the data
	private short[] items = new short[this.max_size];

	// default size
	public Vector_Short() {
	}

	protected static int incrementSize(int increment_size) {

		if (increment_size < 8000) increment_size = increment_size * 4;
		else
			if (increment_size < 16000) increment_size = increment_size * 2;
			else increment_size = increment_size + 2000;
		return increment_size;
	}

	// set size
	public Vector_Short(int number) {
		this.max_size = number;
		this.items = new short[this.max_size];
	}

	// /////////////////////////////////
	/**
	 * get element at
	 */
	final public short elementAt(int id) {
		if (id >= this.max_size) return (short) 0;
		else return this.items[id];
	}

	// /////////////////////////////////
	/**
	 * extract underlying data
	 */
	final public short[] get() {
		return this.items;
	}

	// /////////////////////////////////
	/**
	 * set an element
	 */
	final public void setElementAt(short new_name, int id) {
		if (id >= this.max_size) checkSize(id);

		this.items[id] = new_name;
	}

	// /////////////////////////////////
	/**
	 * replace underlying data
	 */
	final public void set(short[] new_items) {
		this.items = new_items;
	}

	// //////////////////////////////////
	// merge together using larger as new value
	final public void keep_larger(short master, short child) {
		if (this.items[master] < this.items[child]) this.items[master] = this.items[child];
	}

	// //////////////////////////////////
	// merge to keep smaller
	final public void keep_smaller(short master, short child) {
		if (this.items[master] > this.items[child]) this.items[master] = this.items[child];
	}

	// /////////////////////////////////
	/**
	 * clear the array
	 */
	final public void clear() {
		// items = null;
		// holds the data
		// items = new short[max_size];
		if (this.current_item > 0) {
			for (int i = 0; i < this.current_item; i++)
				this.items[i] = 0;
		}
		else {
			for (int i = 0; i < this.max_size; i++)
				this.items[i] = 0;
		}
		this.current_item = 0;
	}

	// /////////////////////////////////
	/**
	 * return the size
	 */
	final public int size() {
		return this.current_item + 1;
	}

	// /////////////////////////////////
	/**
	 * remove element at
	 */
	final public void removeElementAt(int id) {
		if (id >= 0) {
			// copy all items back one to over-write
			System.arraycopy(this.items, id + 1, this.items, id, this.current_item - 1 - id);

			// flush last item
			this.items[this.current_item - 1] = 0;
		}
		else this.items[0] = 0;

		// reduce counter
		this.current_item--;
	}

	// //////////////////////////////////
	/**
	 * see if value present
	 */
	final public boolean contains(short value) {
		boolean flag = false;
		for (int i = 0; i < this.current_item; i++) {
			if (this.items[i] == value) {
				i = this.current_item + 1;
				flag = true;
			}
		}
		return flag;
	}

	// ///////////////////////////////////
	/**
	 * pull item from top as in LIFO stack
	 */
	final public short pull() {

		if (this.current_item > 0) this.current_item--;

		return (this.items[this.current_item]);
	}

	// ///////////////////////////////////
	/**
	 * put item at top as in LIFO stack
	 */
	final public void push(short value) {

		checkSize(this.current_item);
		this.items[this.current_item] = value;

		this.current_item++;
	}

	// /////////////////////////////////
	/**
	 * add an item
	 */
	final public void addElement(short value) {
		checkSize(this.current_item);
		this.items[this.current_item] = value;
		this.current_item++;
	}

	// //////////////////////////////////
	// merge together using larger as new value
	final public void add_together(short master, short child) {
		this.items[master] = (short) (this.items[master] + this.items[child]);
	}

	// //////////////////////////////////
	/**
	 * check the size of the array and increase if needed
	 */
	final private void checkSize(int i) {

		if (i >= this.max_size) {
			int old_size = this.max_size;
			this.max_size = this.max_size + this.increment_size;

			// allow for it not creating space
			if (this.max_size <= i) this.max_size = i + this.increment_size + 2;
			short[] temp = this.items;
			this.items = new short[this.max_size];
			System.arraycopy(temp, 0, this.items, 0, old_size);

			// increase size increase for next time
			this.increment_size = incrementSize(this.increment_size);
		}
	}

	public void trim() {

		short[] newItems = new short[this.current_item];

		System.arraycopy(this.items, 0, newItems, 0, this.current_item);

		this.items = newItems;
		this.max_size = this.current_item;
	}

	/** reset pointer used in add to remove items above */
	public void setSize(int currentItem) {
		this.current_item = currentItem;
	}
}
