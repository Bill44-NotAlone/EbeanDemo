package bill.code.configs;

import io.ebean.DB;
import io.ebean.Database;

public class DataSourceConfiguration {

    private final Database database;

    public DataSourceConfiguration() {
        database = DB.getDefault();
        new CreatorDataBase().initDdlDataBase(database);
    }

    public Database getDatabase() {
        return database;
    }

    public static DataSourceConfiguration of() {
        return new DataSourceConfiguration();
    }
}
