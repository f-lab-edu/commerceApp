package com.example.commerceapp.data.remote.request

data class ProductSearchRequest(
    val query: String? = null,
    val display: Int = 10, // 한 번에 표시할 검색 결과 개수
    val start: Int = 1, // 검색 시작 위치
    val sort: String? = null, // 검색 결과 정렬 방법
    val filter: String? = null, // 검색 결과에 포함할 상품 유형
    val exclude: String? = null // 검색 결과에서 제외할 상품 유형
) {
    fun requestToMap(): Map<String, String?> {
        val params = mutableMapOf<String, String?>()
        query.let { params["query"] = it.toString() }
        display.let { params["display"] = it.toString() }
        start.let { params["start"] = it.toString() }
        sort?.let { params["sort"] = it }
        filter?.let { params["filter"] = it }
        exclude?.let { params["exclude"] = it }

        return params.toMap()
    }
}
