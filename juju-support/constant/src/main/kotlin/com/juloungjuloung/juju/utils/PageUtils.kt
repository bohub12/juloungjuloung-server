package com.juloungjuloung.juju.utils

fun getTotalPageCount(
    totalElementCount: Long,
    pageSize: Int,
    maximumPageCount: Int = 10
): Int {
    if (totalElementCount % pageSize != 0L) {
        return (totalElementCount / pageSize + 1).toInt()
    }

    if (totalElementCount == 0L) return 1
    if (totalElementCount / pageSize >= maximumPageCount) return maximumPageCount

    return (totalElementCount / pageSize).toInt()
}