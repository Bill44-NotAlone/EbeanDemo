package bill.code.cases;

import bill.code.entites.company.CommonCompany;
import bill.code.entites.company.query.QCommonCompany;
import io.ebean.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class QueryBeanCase implements ICase {

    private static final Logger log = LoggerFactory.getLogger(QueryBeanCase.class);
    private final List<String> companyNamesList = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
    private final Database database;

    public QueryBeanCase(Database database) {
        this.database = database;
    }

    @Override
    public void doCase() {
        saveAll();
        findAllByNameLikeAndOrderByNameDesc("_e%");
        deleteAll();
    }

    private void saveAll() {
        List<CommonCompany> companies = companyNamesList.stream()
          .map(nc -> {
              CommonCompany c = new CommonCompany();
              c.setName(nc);
              return c;
          }).toList();

        companies.forEach(database::save);
        log.info("Saved {} companies: {}", companies.size(), companies);
    }

    private void findAllByNameLikeAndOrderByNameDesc(String regex) {
        List<CommonCompany> companies = new QCommonCompany()
          .name.like(regex)
          .orderBy().name.desc()
          .findList();

        log.info("Saved all from companies: Delta, Beta");
        log.info("Expected result: {}", companies);
    }

    private List<CommonCompany> findAllByNames(List<String> companyNamesList) {
        return new QCommonCompany().name.in(companyNamesList).findList();
    }

    private void deleteAll() {
        List<CommonCompany> listOfDeletedItems = findAllByNames(companyNamesList);
        listOfDeletedItems.forEach(database::delete);
    }
}
