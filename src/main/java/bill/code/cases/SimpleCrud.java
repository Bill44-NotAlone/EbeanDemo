package bill.code.cases;

import bill.code.entites.company.CommonCompany;
import bill.code.repositories.face.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCrud implements ICase {

    private static final Logger log = LoggerFactory.getLogger(SimpleCrud.class);
    private final CompanyRepository companyRepository;
    public final String caseCompanyName = "ComOne";
    public final String editCompanyName = "ComDoo";

    public SimpleCrud(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void doCase() {
        save();
        edit();
        delete();
    }

    private void save() {
        CommonCompany company = new CommonCompany();
        company.setName(caseCompanyName);
        companyRepository.save(company);
        log.info("Saved company: {}", company);
    }

    private void edit() {
        CommonCompany company = companyRepository.findByName(caseCompanyName);
        company.setName(editCompanyName);
        companyRepository.save(company);
        log.info("Edited company: {}", company);
    }

    private void delete() {
        CommonCompany company = companyRepository.findByName(editCompanyName);
        companyRepository.delete(company.getId());
        log.info("Deleted company: {}", company);
    }
}
