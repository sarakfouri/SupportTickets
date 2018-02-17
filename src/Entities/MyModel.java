/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.List;
import Entities.Users;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sara
 */
public class MyModel extends AbstractTableModel{
    
    public final int ID = 0;
    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    
   public final String columns[]={"Id:","Username:","Password:"};
     List<Users> base;

    
    public MyModel(List<Users> base) {
      
        this.base=base;
    }
    @Override
    public int getRowCount() {
        return base.size(); //return number of rows.
    }
    public String getColumnName(int num){
        return this.columns[num];
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        //retorna o tipo de dado, para cada coluna
        switch (columnIndex) {
        case ID:
            return int.class;
        case USERNAME:
            return String.class;
        case PASSWORD:
            return String.class;
        
        default:
            throw new IndexOutOfBoundsException("Coluna Inválida!!!");
        }
    }
    @Override
    public int getColumnCount() {
        return columns.length;//return number of columns
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Users user= base.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return user.getId();
            case USERNAME: 
                    return user.getUsername();
            case PASSWORD:
                    return user.getPassword();  
            default:
                throw new IndexOutOfBoundsException("Column not found!");
        }
        //https://solutionbto.wordpress.com/2013/11/16/criando-seu-modelo-de-tabela-abstracttablemodel/
    }
   
    }
 
   



//https://solutionbto.wordpress.com/2013/11/16/criando-seu-modelo-de-tabela-abstracttablemodel/