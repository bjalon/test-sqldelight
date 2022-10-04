package com.qastia

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String

public interface BlobSearchQueries : Transacter {
  public fun <T : Any> searchBlob(searchText: String, mapper: (title: String,
      description: String) -> T): Query<T>

  public fun searchBlob(searchText: String): Query<NoteFts>
}
