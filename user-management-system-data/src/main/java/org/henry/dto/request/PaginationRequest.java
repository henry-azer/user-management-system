package org.henry.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.henry.dto.common.Page;
import org.henry.dto.common.SortingBy;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationRequest extends Page {
    private List<SortingBy> sortingByList;
}
