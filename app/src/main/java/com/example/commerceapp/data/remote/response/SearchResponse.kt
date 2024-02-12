package com.example.commerceapp.data.remote.response

import com.example.commerceapp.data.remote.model.Item
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "brand") val display: Int, // 한번에 표시할 아이템 개수
    @Json(name = "items") val items: List<Item>,
    @Json(name = "lastBuildDate") val lastBuildDate: String, // 검색 결과 생성한 시간
    @Json(name = "start") val start: Int, // 검색 시작 위치
    @Json(name = "total") val total: Int // 총 개수
)