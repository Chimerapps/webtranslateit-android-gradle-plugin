package com.chimerapps.gradle.webtranslateit.webtranslateit.api.model

import com.chimerapps.moshigenerator.GenerateMoshi
import com.chimerapps.moshigenerator.GenerateMoshiFactory
import com.squareup.moshi.Json

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
@GenerateMoshi
data class Locale(
        val name: String,
        val code: String
)

@GenerateMoshi
data class ProjectFile(
        val id: Long,
        val name: String,
        @Json(name = "created_at") val createdAt: String?,
        @Json(name = "updated_at") val updatedAt: String?,
        @Json(name = "hash_file") val fileHash: String?,
        @Json(name = "master_project_file_id") val masterProjectFileId: Long?,
        @Json(name = "locale_code") val localeCode: String
)

@GenerateMoshi
data class Project(
        val name: String,
        val id: Long,
        @Json(name = "created_at") val createdAt: String?,
        @Json(name = "updated_at") val updatedAt: String?,
        @Json(name = "source_locale") val sourceLocale: Locale,
        @Json(name = "target_locales") val targetLocales: List<Locale>,
        @Json(name = "project_files") val projectFiles: List<ProjectFile>?
)

@GenerateMoshi
data class ProjectResponse(
        val project: Project
)

@GenerateMoshiFactory(Locale::class, ProjectFile::class, Project::class, ProjectResponse::class)
interface ModelFactoryHolder