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
    EUR("EUR",1L),
    SEK("SEK",2L),
    GBP("GBP",3L),
    USD("USD",4L),
    OTHER("OTHER",0L);

    private String name;
    private Long code;

    public static CurrencyEnum findByCode(Long code) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(c -> Objects.equals(c.code, code))
                .findFirst()
                .orElse(CurrencyEnum.OTHER);
    }

}
