package com.qastia

import com.qastia.testsqdelight.newInstance
import com.qastia.testsqdelight.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

public interface LocalDb : Transacter {
  public val blobSearchQueries: BlobSearchQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = LocalDb::class.schema

    public operator fun invoke(driver: SqlDriver): LocalDb = LocalDb::class.newInstance(driver)
  }
}
