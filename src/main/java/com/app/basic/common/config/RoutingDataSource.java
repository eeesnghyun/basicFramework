package com.app.basic.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Configuration
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // @Transactional ReadOnly 설정이 걸린 메소드는 reader 데이터 소스를 사용하도록 한다.
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "reader" : "writer";
    }
}