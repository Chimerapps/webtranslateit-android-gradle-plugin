package com.chimerapps.gradle.webtranslateit

import com.chimerapps.gradle.webtranslateit.webtranslateit.TranslationDownloader
import com.chimerapps.gradle.webtranslateit.webtranslateit.api.WebTranslateItApi
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
open class UpdateTranslationsTask : DefaultTask() {

    private val api: WebTranslateItApi by lazy { project.plugins.findPlugin(DownloadTranslationsPlugin::class.java).webTranslateItApi }
    lateinit var configuration: TranslationConfiguration

    @TaskAction
    fun updateTranslations() {
        logger.debug("Update translations task running for config ${configuration.name}")

        val extension = project.extensions.findByType(DownloadTranslationsExtension::class.java)
        val projectKey = extension.apiKey ?: throw IllegalArgumentException("No api key provided for webtranslateit")

        val targetDir = project.file(extension.sourceRoot)
        logger.debug("Saving translations to $targetDir")
        TranslationDownloader(api, logger).download(projectKey, targetDir, extension.fileName)
    }


}