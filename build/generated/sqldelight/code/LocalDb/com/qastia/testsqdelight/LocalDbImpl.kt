package com.qastia.testsqdelight

import com.qastia.BlobSearchQueries
import com.qastia.LocalDb
import com.qastia.NoteFts
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<LocalDb>.schema: SqlDriver.Schema
  get() = LocalDbImpl.Schema

internal fun KClass<LocalDb>.newInstance(driver: SqlDriver): LocalDb = LocalDbImpl(driver)

private class LocalDbImpl(
  driver: SqlDriver
) : TransacterImpl(driver), LocalDb {
  public override val blobSearchQueries: BlobSearchQueriesImpl = BlobSearchQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE VIRTUAL TABLE NoteFts USING fts5 (
          |  title, description
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class BlobSearchQueriesImpl(
  private val database: LocalDbImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), BlobSearchQueries {
  internal val searchBlob: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> searchBlob(searchText: String, mapper: (title: String,
      description: String) -> T): Query<T> = SearchBlobQuery(searchText) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!
    )
  }

  public override fun searchBlob(searchText: String): Query<NoteFts> = searchBlob(searchText) {
      title, description ->
    NoteFts(
      title,
      description
    )
  }

  private inner class SearchBlobQuery<out T : Any>(
    public val searchText: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(searchBlob, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-1523032548,
        """SELECT * FROM NoteFts WHERE NoteFts MATCH ?""", 1) {
      bindString(1, searchText)
    }

    public override fun toString(): String = "blobSearch.sq:searchBlob"
  }
}
