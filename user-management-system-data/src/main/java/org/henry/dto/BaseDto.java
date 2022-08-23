package org.henry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
}
