package barberbook.mysqlconnection.mysqlconnector.model;

import lombok.Data;

import java.util.List;

@Data
public class TableModel {
    private String tableName;
    private List<QueryModel> queries;

    @Override
    public boolean equals(Object o){
        return (o instanceof TableModel && (this.tableName.equals(((TableModel) o).getTableName())));
    }
}
