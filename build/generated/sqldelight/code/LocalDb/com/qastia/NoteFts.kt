package com.qastia

import kotlin.String

public data class NoteFts(
  public val title: String,
  public val description: String
) {
  public override fun toString(): String = """
  |NoteFts [
  |  title: $title
  |  description: $description
  |]
  """.trimMargin()
}
