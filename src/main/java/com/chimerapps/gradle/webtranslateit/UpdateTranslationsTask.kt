/*
 * Copyright 2017 - Chimerapps BVBA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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