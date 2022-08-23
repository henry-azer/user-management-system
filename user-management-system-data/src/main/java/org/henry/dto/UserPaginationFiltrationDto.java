package org.henry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaginationFiltrationDto {
    private String name;

    public String getName() {
        if (this.name == null) return "";

        return this.name;
    }
}
