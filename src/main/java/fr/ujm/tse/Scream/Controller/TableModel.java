package fr.ujm.tse.Scream.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String[]> data = new ArrayList<String[]>();
 
    String  title[] = {"Id","Titre", "Auteur","Lu" ,"Marque page","Note","Commentaire"};
 
    public TableModel() throws SQLException {
        super();
        data=Database.getlibrary();
    }
 
    public int getRowCount() {
        return data.size();
    }
 
    public int getColumnCount() {
        return title.length;
    }
 
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return data.get(rowIndex)[0];
            case 1:
                return data.get(rowIndex)[1];
            case 2:
                return data.get(rowIndex)[2];
            case 3:
            	return data.get(rowIndex)[3];
            case 4:
            	return data.get(rowIndex)[4];
            case 5:
            	return data.get(rowIndex)[5];
            case 6:
            	return data.get(rowIndex)[6];
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addData(String[] str) {
        data.add(str);
 
        fireTableRowsInserted(data.size() -1, data.size() -1);
    }
 
    public void removeData(int rowIndex) {
        data.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}