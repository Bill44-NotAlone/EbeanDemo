package bill.code.configs;

import io.ebean.Database;
import io.ebean.annotation.Transactional;

public class CreatorDataBase {

    @Transactional
    public void initDdlDataBase(Database database) {
        String sqlQuery = """
          CREATE SCHEMA IF NOT EXISTS ebeanBd;
          SET SCHEMA ebeanBd;
                    
          DROP TABLE IF EXISTS developmentteams;
          DROP TABLE IF EXISTS company;
                    
          CREATE TABLE ebeanBd.company
          (
              Id   INT PRIMARY KEY auto_increment,
              Name VARCHAR(255)
          );
                    
          CREATE TABLE ebeanBd.developmentteams
          (
              Id        INT PRIMARY KEY auto_increment,
              Name      VARCHAR(255),
              CompanyId int REFERENCES company (Id),
              FOREIGN KEY (CompanyId) REFERENCES company (Id)
          );
          """;

        database.sqlUpdate(sqlQuery).execute();
    }
}
