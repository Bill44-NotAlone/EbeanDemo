package bill.code.repositories.face;

import bill.code.entites.company.CommonCompany;

public interface CompanyRepository extends CrudRepository<CommonCompany, Integer> {

    CommonCompany findByName(String name);
}
