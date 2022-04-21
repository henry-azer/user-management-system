package org.henry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDto {

    private LocalDateTime creationDate;

    private LocalDateTime lastModificationDate;
}
