package com.chimerapps.gradle.webtranslateit.webtranslateit.api.model

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
data class Locale(
        val name: String,
        val code: String
)

data class ProjectFile(
        val id: Long,
        val name: String,
        val created_at: String?,
        val updated_at: String?,
        val hash_file: String?,
        val master_project_file_id: Long?,
        val locale_code: String
)

data class Project(
        val name: String,
        val id: Long,
        val created_at: String?,
        val updated_at: String?,
        val source_locale: Locale,
        val target_locales: List<Locale>,
        val project_files: List<ProjectFile>?
)

data class ProjectResponse(
        val project: Project
)