package com.qastia.platon.back.service.datalayer.sources.localdb.blob

import com.qastia.LocalDb
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

fun initAndGetLocalDB(): LocalDb {
    val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    LocalDb.Schema.create(sqlDriver)
    return LocalDb(sqlDriver)

}