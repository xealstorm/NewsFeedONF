package com.onefootball.model

data class News(
    val title: String?,
    val imageUri: String?,
    val resourceName: String?,
    val resourceUrl: String?,
    val newsLink: String?
) {
    fun isValid() = title.isNullOrEmpty().not() &&
            imageUri.isNullOrEmpty().not() &&
            resourceName.isNullOrEmpty().not() &&
            resourceUrl.isNullOrEmpty().not() &&
            newsLink.isNullOrEmpty().not()
}