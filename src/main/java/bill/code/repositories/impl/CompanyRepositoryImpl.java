package bill.code.repositories.impl;

import bill.code.entites.company.CommonCompany;
import bill.code.repositories.face.CompanyRepository;
import io.ebean.Database;

import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private final Database database;

    public CompanyRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public CommonCompany save(CommonCompany entity) {
        database.save(entity);
        return entity;
    }

    @Override
    public CommonCompany findById(Integer id) {
        return database.find(CommonCompany.class).where().idEq(id).findOne();
    }

    @Override
    public List<CommonCompany> findAll() {
        return database.find(CommonCompany.class).findList();
    }

    @Override
    public void delete(Integer id) {
        database.delete(CommonCompany.class, id);
    }

    @Override
    public CommonCompany findByName(String name) {
        return database.find(CommonCompany.class).where().eq("name", name).findOne();
    }
}
