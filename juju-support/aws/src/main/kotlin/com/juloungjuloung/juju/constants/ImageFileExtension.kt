package com.juloungjuloung.juju.constants

enum class ImageFileExtension(
    val extension: String,
    val contentType: String
) {
    JPG(".jpg", "image/jpg"),
    JPEG(".jpeg", "image/jpeg"),
    PNG(".png", "image/png")
}