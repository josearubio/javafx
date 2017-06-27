/*
 * Copyright (C)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pickadosdesktop.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author drizo
 */
public class OddRow {

    private final StringProperty odd_bookmakers = new SimpleStringProperty();
    private final StringProperty odd_1 = new SimpleStringProperty();
    private final StringProperty odd_x = new SimpleStringProperty();
    private final StringProperty odd_2 = new SimpleStringProperty();
    private final StringProperty over = new SimpleStringProperty();
    private final StringProperty under = new SimpleStringProperty();

    public OddRow() {
    }
    
    public OddRow(String bookie, String odd_1, String odd_x, String odd_2, String over, String under)
    {
        this.odd_bookmakers.set(bookie);
        this.odd_1.set(odd_1);
        this.odd_x.set(odd_x);
        this.odd_2.set(odd_2);
        this.over.set(over);
        this.under.set(under);
    }

    public String getOdd_bookmakers() {
        return odd_bookmakers.get();
    }
    
    public StringProperty odd_bookmakersProperty() {
        return odd_bookmakers;
    }

    public String getOdd_1() {
        return odd_1.get();
    }

    public StringProperty odd_1Property() {
        return odd_1;
    }
    public String getOdd_x() {
        return odd_x.get();
    }
    
    public StringProperty odd_xProperty() {
        return odd_x;
    }

    public String getOdd_2() {
        return odd_2.get();
    }

    public StringProperty odd_2Property() {
        return odd_2;
    }
    public String getOver() {
        return over.get();
    }
    
    public StringProperty overProperty() {
        return over;
    }

    public String getUnder() {
        return under.get();
    }

    public StringProperty underProperty() {
        return under;
    }
    
    @Override
    public String toString() {
	return "" + odd_bookmakers.get() + " - " + "1: " + odd_1.get() + ", x: " + odd_x.get()+ ", 2:  " + odd_2.get() + '}';
    }
    
}
