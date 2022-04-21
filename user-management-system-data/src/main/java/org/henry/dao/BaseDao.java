package org.henry.dao;

import org.henry.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseDao<Entity extends BaseEntity, Repository extends JpaRepository<Entity, Long>> {

    /**
     * @return entity repository
     */
    Repository getRepository();

    /**
     * @return List<Entity>
     */
    default List<Entity> findAll() {
        return getRepository().findAll();
    }

    /**
     * @param id
     * @return Optional<Entity>
     */
    default Optional<Entity> findById(Long id) {
        return getRepository().findById(id);
    }

    /**
     * @param entity
     * @return entity
     */
    default Entity create(Entity entity) {
        return getRepository().save(entity);
    }

    /**
     * @param entity
     * @return entity
     */
    default Entity update(Entity entity) {
        return getRepository().save(entity);
    }

    /**
     * @param id
     */
    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }

}
