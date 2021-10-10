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
    IN(1L),
    OUT(2L),
    OTHER(0L);

    private Long code;

    public static DirectionEnum of(String name) {
        return Arrays.stream(DirectionEnum.values())
                .filter(c -> Objects.equals(c.name(), name))
                .findFirst()
                .orElse(DirectionEnum.OTHER);
    }

    public static DirectionEnum findByCode(Long code) {
        return Arrays.stream(DirectionEnum.values())
                .filter(c -> Objects.equals(c.code, code))
                .findFirst()
                .orElse(DirectionEnum.OTHER);
    }

}
