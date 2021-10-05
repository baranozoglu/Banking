package com.tuum.banking.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DirectionEnum {
    IN("IN",1L),
    OUT("OUT",2L),
    OTHER("OTHER",0L);

    private String name;
    private Long code;

    public static DirectionEnum findByCode(Long code) {
        return Arrays.stream(DirectionEnum.values())
                .filter(c -> Objects.equals(c.code, code))
                .findFirst()
                .orElse(DirectionEnum.OTHER);
    }

}
