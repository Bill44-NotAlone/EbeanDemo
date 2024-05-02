package bill.code.cases;

import bill.code.entites.company.CommonCompany;
import bill.code.entites.company.CommonDeveloperTeam;
import bill.code.entites.company.query.QCommonCompany;
import bill.code.entites.company.query.QCommonDeveloperTeam;
import io.ebean.Database;
import io.ebean.Transaction;
import io.ebean.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionCase implements ICase {

    private static final Logger log = LoggerFactory.getLogger(TransactionCase.class);
    private final String anyComm = "AnyComm";
    private final String teamName = "Team1";
    private final Database database;

    public TransactionCase(Database database) {
        this.database = database;
    }

    @Override
    public void doCase() {
        transactionAnnotationSaveExe();
        transactionSave();
    }

    private void transactionAnnotationSaveExe() {
        try {
            transactionAnnotationSave();
        } catch (RuntimeException e) {
            CommonCompany company = new QCommonCompany().name.eq(anyComm).findOne();
            CommonDeveloperTeam team = new QCommonDeveloperTeam().name.eq(teamName).findOne();

            log.info("Transaction in transactionAnnSave is rollback: AnyComm and Team1 not saved." +
              " AnyComm - {}, Team1 - {}", company, team);
        }
    }

    @Transactional
    private void transactionAnnotationSave() {
        CommonCompany company = new CommonCompany();
        company.setName(anyComm);
        database.save(company);
        log.info("Save {}", company);

        CommonDeveloperTeam team = new CommonDeveloperTeam();
        team.setName(teamName);
        team.setCommonCompanyId(company.getId());
        database.save(team);
        log.info("Save {}", team);

        throw new RuntimeException("Ruined save method");
    }

    private void transactionSave() {
        try (Transaction tx = database.beginTransaction()) {
            CommonCompany company = new CommonCompany();
            company.setName(anyComm);
            database.save(company);
            log.info("Save {}", company);

            CommonDeveloperTeam team = new CommonDeveloperTeam();
            team.setName(teamName);
            team.setCommonCompanyId(company.getId());
            database.save(team);
            log.info("Save {}", team);

            throw new RuntimeException("Ruined save method");
        }
        catch (RuntimeException ex) {
            CommonCompany company = new QCommonCompany().name.eq(anyComm).findOne();
            CommonDeveloperTeam team = new QCommonDeveloperTeam().name.eq(teamName).findOne();

            log.info("Transaction in transactionAnnSave is rollback: AnyComm and Team1 not saved." +
              " AnyComm - {}, Team1 - {}", company, team);
        }
    }
}
