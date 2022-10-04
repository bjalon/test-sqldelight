package com.qastia

fun LocalDb.searchBlob(searchString: String): List<NoteFts> {
    return blobSearchQueries.searchBlob(searchString).executeAsList()
}
