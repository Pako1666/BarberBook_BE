package barberbook.mysqlconnection.mysqlconnector.model;

import lombok.Data;

@Data
public class QueryModel {
    private String idQuery;
    private String sql;

    @Override
    public boolean equals(Object o){
        return (o instanceof QueryModel && (this.idQuery.equals(((QueryModel) o).idQuery)));
    }
}
