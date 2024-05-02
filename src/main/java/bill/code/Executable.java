package bill.code;

import bill.code.cases.ICase;
import bill.code.cases.QueryBeanCase;
import bill.code.cases.SimpleCrud;
import bill.code.cases.TransactionCase;
import bill.code.configs.DataSourceConfiguration;
import bill.code.repositories.face.CompanyRepository;
import bill.code.repositories.impl.CompanyRepositoryImpl;
import io.ebean.Database;

import java.util.Arrays;
import java.util.List;

public class Executable {

    public static void main(String[] args) {
        DataSourceConfiguration dsc = DataSourceConfiguration.of();
        Database database = dsc.getDatabase();
        CompanyRepository companyRepository = new CompanyRepositoryImpl(database);
        List<ICase> cases = Arrays.asList(
          new SimpleCrud(companyRepository),
          new QueryBeanCase(database),
          new TransactionCase(database)
        );
        cases.forEach(ICase::doCase);
    }
}
