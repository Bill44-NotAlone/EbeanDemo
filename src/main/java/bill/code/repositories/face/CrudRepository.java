package bill.code.repositories.face;

import java.io.Serializable;
import java.util.List;

public interface CrudRepository<EntityType, IdType extends Serializable> {

    EntityType save(EntityType entity);

    EntityType findById(IdType id);

    List<EntityType> findAll();

    void delete(IdType id);
}
