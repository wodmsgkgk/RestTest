package com.wodms.lotteontest.model
import java.util.*

data class SearchResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: ArrayList<User>
)
