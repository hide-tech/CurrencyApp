package com.yazykov.currencyservice.dialect;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQL10Dialect {

    public JsonPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}
