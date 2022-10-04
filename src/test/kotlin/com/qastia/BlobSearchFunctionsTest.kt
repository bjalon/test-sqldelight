package com.qastia

import com.qastia.platon.back.service.datalayer.sources.localdb.blob.initAndGetLocalDB
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlobDBEnrichmentSearchFunctionsTest {

    lateinit var localDB: LocalDb

    @BeforeEach
    internal fun setUp() {
        localDB = initAndGetLocalDB()
    }

    @Test
    internal fun shouldFindNoDocumentIfSearchEmpty() {
        val documents = localDB.searchBlob("")
        assertEquals("[]", documents.toString())
    }
}
