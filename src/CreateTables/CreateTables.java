package CreateTables;

import java.sql.SQLException;

/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
public interface CreateTables {

    void createTables() throws SQLException;
    void defaultTablesData() throws SQLException;

}
