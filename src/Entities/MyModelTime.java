/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sara AbstractTableModel
 */
public class MyModelTime extends AbstractTableModel{
public final int ID = 0 ;
public final int OPENTIME = 1;
public final int CLOSETIME = 2;
public final int ASSIGNED = 3;


public final String columns[] = {"ID", "TIME OPEN", "TIME CLOSE", "ASSIGNED"};
List<Tickets> data; 

public MyModelTime(List<Tickets> data){
    this.data = data;
}
    @Override
    public int getRowCount() {
        return data.size(); //return number of rows.
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        //retorna o tipo de dado, para cada coluna
        switch (columnIndex) {
        case ID:
            return int.class;
        case OPENTIME:
            return String.class;
        case CLOSETIME:
            return String.class;
         case ASSIGNED:
            return String.class;
       
     default:
            throw new IndexOutOfBoundsException("Not found!!!");
        }
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;//return number of columns
    }
      public String getColumnName(int num){
        return this.columns[num];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Tickets tickets= data.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return tickets.getId();
            case OPENTIME: 
                    return tickets.getOpentime();
            case CLOSETIME:
                    return tickets.getClosetime(); 
            case ASSIGNED: 
                    return tickets.getAssigned();
            default:
                throw new IndexOutOfBoundsException("Column not found!");
        }
        //https://solutionbto.wordpress.com/2013/11/16/criando-seu-modelo-de-tabela-abstracttablemodel/
    } 
}

    
    
    
    
    
    
    
    
    
    
    