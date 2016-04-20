/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

/**
 *
 * @author tifui
 */
public class Title{
       
       private int row;
       private int column;
       private char value;
       public Title(int row,int collumn,char value)
       {
           this.column=collumn;
           this.row=row;
           this.value=value;
       }

    /**
     * Rturneaza linia pe care se afla litera.
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Seteaza linia carcaterului.
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returneaza coloana pe care se afla carcaterul.
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Seteaza coloana pe care se afla caracterul.
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Returneaza valoarea carcaterului.
     * @return the value
     */
    public char getValue() {
        return value;
    }

    /**
     * Seteaza valoarea carcaterului.
     * @param value the value to set
     */
    public void setValue(char value) {
        this.value = value;
    }
    
    @Override
    /**
     * Returneaza valoarea carcaterului ca si String.
     */
    public String toString()
    {
        return ""+value;   
    }
       
       
   }