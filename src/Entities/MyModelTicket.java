/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Entities.Users;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sara
 */
public class MyModelTicket extends  AbstractTableModel{
    public final int ID = 0;
    public final int CLIENTEID = 1;
    public final int DESCRIPTION = 2;
    public final int SEV = 3;
    public final int OPENTIME = 4;
    public final int CLOSETIME = 5;
    public final int ASSIGNED = 6;
    public final int STATUS = 7;
    
    
   public final String columns[]={"Id:","ClientId:", "Description","Sev:", "OpenTime:", "CloseTime:", "Assigned:","Status:"};
     List<Tickets> base;

    
    public MyModelTicket(List<Tickets> base) {
       
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
        case CLIENTEID:
            return String.class;
        case DESCRIPTION:
            return String.class;
         case SEV:
            return String.class;
        case OPENTIME:
            return String.class;
       case CLOSETIME:
            return String.class;
        case ASSIGNED:
            return String.class;
        case STATUS:
            return String.class;
            
        default:
            throw new IndexOutOfBoundsException("not found!!!");
        }
    }
    @Override
    public int getColumnCount() {
        return columns.length;//return number of columns
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Tickets tickets= base.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return tickets.getId();
            case CLIENTEID: 
                    return tickets.getClientID();
            case DESCRIPTION:
                    return tickets.getDescription(); 
             case SEV: 
                return tickets.getSev();
            case OPENTIME: 
                    return tickets.getOpentime();
            case CLOSETIME:
                    return tickets.getClosetime(); 
            case ASSIGNED: 
                    return tickets.getAssigned();
            case STATUS:
                    return tickets.getStatus();   
            default:
                throw new IndexOutOfBoundsException("Column not found!");
        }
        //https://solutionbto.wordpress.com/2013/11/16/criando-seu-modelo-de-tabela-abstracttablemodel/
    } 
}
