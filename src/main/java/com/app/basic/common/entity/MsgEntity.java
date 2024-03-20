package com.app.basic.common.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MsgEntity {

    private StatusEnum message;
    private Object result;

}