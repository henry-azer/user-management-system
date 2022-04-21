package org.henry.service;

import org.henry.dao.BaseDao;
import org.henry.dto.BaseDto;
import org.henry.entity.BaseEntity;
import org.henry.transformer.BaseTransformer;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BaseService<Entity extends BaseEntity, Dto extends BaseDto,
        Dao extends BaseDao<Entity, ?>, Transformer extends BaseTransformer<Entity, Dto, ?>> {

    Dao getDao();

    Transformer getTransformer();

    default List<Dto> findAll() {
        return getTransformer().transformEntityToDto(getDao().findAll());
    }

    default Dto findById(Long id) {
        Optional<Entity> entity = getDao().findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        return getTransformer().transformEntityToDto(entity.get());
    }

    default Dto create(Dto dto) {
        Entity transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        Entity savedEntity = getDao().create(transformedDtoToEntity);

        return getTransformer().transformEntityToDto(savedEntity);
    }

    default List<Dto> create(List<Dto> dtos) {
        return dtos.stream().map(this::create).collect(Collectors.toList());
    }

    default Dto update(Dto dto, Long id) {
        Optional<Entity> entity = getDao().findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        Entity transformedDtoToEntity = entity.get();
        getTransformer().updateEntity(dto, transformedDtoToEntity);
        Entity updatedEntity = getDao().update(transformedDtoToEntity);

        return getTransformer().transformEntityToDto(updatedEntity);
    }

    default void deleteById(Long id) {
        Optional<Entity> entity = getDao().findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        getDao().deleteById(id);
    }

}
