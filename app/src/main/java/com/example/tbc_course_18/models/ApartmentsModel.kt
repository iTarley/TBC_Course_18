package com.example.tbc_course_18.models

data class ApartmentsModel(
    val content: List<Content>
) {
    data class Content(
        val id: String?,
        val descriptionEN: String?,
        val descriptionKA: String?,
        val descriptionRU: String?,
        val titleEN: String?,
        val titleKA: String?,
        val titleRU: String?,
        val published: Int?,
        val publish_date: String?,
        val created_at: Long?,
        val updated_at: Long?,
        val category: String?,
        val cover: String?,
        val isLast: Boolean?
    )
}