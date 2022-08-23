package org.henry.service;

import org.henry.dao.BaseDao;
import org.henry.dto.BaseDto;
import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.dto.request.PaginationRequest;
import org.henry.dto.response.PaginationResponse;
import org.henry.entity.BaseEntity;
import org.henry.transformer.BaseTransformer;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BaseService<Entity extends BaseEntity, Dto extends BaseDto, Dao extends BaseDao, Transformer extends BaseTransformer> {

    Dao getDao();

    Transformer getTransformer();

    default List<Dto> findAll() {
        return getTransformer().transformEntityToDto(getDao().findAll());
    }

    default Dto findById(Long id) {
        Optional<Entity> entity = getDao().findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        return (Dto) getTransformer().transformEntityToDto(entity.get());
    }

    default Dto create(Dto dto) {
        Entity transformedDtoToEntity = (Entity) getTransformer().transformDtoToEntity(dto);
        Entity savedEntity = (Entity) getDao().create(transformedDtoToEntity);

        return (Dto) getTransformer().transformEntityToDto(savedEntity);
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
        Entity updatedEntity = (Entity) getDao().update(transformedDtoToEntity);

        return (Dto) getTransformer().transformEntityToDto(updatedEntity);
    }

    default void deleteById(Long id) {
        Optional<Entity> entity = getDao().findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        getDao().deleteById(id);
    }

    default PaginationResponse<Dto> findAllUsersPaginatedRequest(PaginationRequest paginationRequest) {
        Page<Entity> entities = getDao().findAllUsersPaginatedRequest(paginationRequest);
        return buildPaginationResponse(entities);
    }

    default PaginationResponse<Dto> buildPaginationResponse(Page<Entity> entities) {
        return PaginationResponse.builder().pageNumber(entities.getNumber() + 1).pageSize(entities.getSize())
                .isFirst(entities.isFirst()).isLast(entities.isLast()).
                result(getTransformer().transformEntityToDto(entities.getContent()))
                .totalNumberOfElements(entities.getTotalElements()).totalNumberOfPages(entities.getTotalPages()).build();
    }

}
