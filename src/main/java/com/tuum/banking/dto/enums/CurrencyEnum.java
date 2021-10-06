package com.tuum.banking.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CurrencyEnum {
    EUR(1L),
    SEK(2L),
    GBP(3L),
    USD(4L),
    OTHER(0L);

    private Long code;

    public static CurrencyEnum of(String name) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(c -> Objects.equals(c.name(), name))
                .findFirst()
                .orElse(CurrencyEnum.OTHER);
    }

    public static CurrencyEnum findByCode(Long code) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(c -> Objects.equals(c.code, code))
                .findFirst()
                .orElse(CurrencyEnum.OTHER);
    }

}
