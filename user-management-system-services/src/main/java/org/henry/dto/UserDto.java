package org.henry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto extends BaseDto {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
}