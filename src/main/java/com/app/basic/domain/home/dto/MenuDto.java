package com.app.basic.domain.home.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuDto {

    private int menuSeq;
    private String menuName;
}
