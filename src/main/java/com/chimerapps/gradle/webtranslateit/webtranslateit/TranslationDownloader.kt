package com.chimerapps.gradle.webtranslateit.webtranslateit

import com.chimerapps.gradle.webtranslateit.webtranslateit.api.WebTranslateItApi
import org.gradle.api.logging.Logger
import java.io.File
import java.io.FileOutputStream

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
class TranslationDownloader(private val webTranslateItApi: WebTranslateItApi, private val logger: Logger) {

    fun download(projectKey: String, outputRoot: File, stringsFileName: String) {
        outputRoot.mkdirs()
        logger.debug("Downloading translations for project ($projectKey)")

        val projectResponse = webTranslateItApi.getProject(projectKey).execute()
        logger.debug("Project download result: ${projectResponse.message()} ${projectResponse.body()}")

        val project = projectResponse.body()?.project ?: throw IllegalArgumentException("Failed to fetch project")
        logger.debug("Got ${project.project_files?.size} project files, checking which we need to download")
        project.project_files?.forEach {
            logger.debug("Checking: ${it.id}")
            val masterId = it.master_project_file_id ?: it.id
            logger.debug("Downloading file: ${it.id} - ${it.locale_code}")

            val translationFile = webTranslateItApi.getTranslationFile(projectKey, masterId, it.locale_code).execute()
            val body = translationFile.body()
            if (body == null) {
                logger.warn("Failed to download file for locale ${it.locale_code}: ${translationFile.message()}")
                return@forEach
            }
            val folderName = if (it.master_project_file_id == null) "values" else "values-${it.locale_code}"

            val dir = File(outputRoot, folderName)
            dir.mkdirs()
            FileOutputStream(File(dir, stringsFileName)).apply {
                body.byteStream().copyTo(this)
            }
        }
    }

}