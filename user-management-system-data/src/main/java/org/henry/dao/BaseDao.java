package org.henry.dao;

import org.henry.dto.common.SortingBy;
import org.henry.dto.common.SortingDirection;
import org.henry.dto.request.PaginationRequest;
import org.henry.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * @param paginationRequest
     * @return Page<Entity>
     */
    default Page<Entity> findAllUsersPaginatedRequest(PaginationRequest paginationRequest) {
        //subtract 1 from page number because library start from page 0
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPageNumber() - 1, paginationRequest.getPageSize(), buildSort(paginationRequest));
        return getRepository().findAll(pageRequest);
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

    /**
     * @param paginationRequest
     * @return Sort
     */
    default Sort buildSort(PaginationRequest paginationRequest) {
        return Sort.by(paginationRequest.getSortingByList().stream().map(sortingBy
                -> sortingBy.getIsNumber() ? new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC, sortingBy.getFieldName())
                : new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC, sortingBy.getFieldName()).ignoreCase()).collect(Collectors.toList()));
    }

    default <T extends Class<Entity>> Sort buildSort(PaginationRequest paginationRequest, T entityCls) {
        List<SortingBy> sortingByList = paginationRequest.getSortingByList();
        if (sortingByList == null) {
            sortingByList = new ArrayList<>();
            sortingByList.add(new SortingBy("id", SortingDirection.ASC));
        }
        return Sort.by(sortingByList.stream().map(sortingBy -> {
            Field orderField = ReflectionUtils.findField(entityCls, sortingBy.getFieldName());
            if (orderField == null) throw new NullPointerException("MALFORMED_JSON_REQUEST");
            if (orderField.getType().equals(String.class))
                return new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortingBy.getFieldName()).ignoreCase();
            return new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortingBy.getFieldName());
        }).collect(Collectors.toList()));
    }
}
