package com.app.basic.domain.home.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class MenuDto implements Serializable {

    private int menuSeq;
    private String menuName;

}
