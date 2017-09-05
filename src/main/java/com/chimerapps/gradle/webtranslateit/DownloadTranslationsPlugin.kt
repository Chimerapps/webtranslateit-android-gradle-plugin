package com.chimerapps.gradle.webtranslateit

import com.chimerapps.gradle.webtranslateit.webtranslateit.api.WebTranslateItApi
import com.chimerapps.gradle.webtranslateit.webtranslateit.api.model.MoshiFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.gradle.api.Plugin
import org.gradle.api.Project
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
open class DownloadTranslationsPlugin : Plugin<Project> {

    private val httpClient = OkHttpClient.Builder()
            .build()
    private val moshi = Moshi.Builder()
            .add(MoshiFactory())
            .build()

    val webTranslateItApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .baseUrl(WebTranslateItApi.API_BASE)
            .build()
            .create(WebTranslateItApi::class.java)

    override fun apply(target: Project) {
        val extension = target.extensions.create("webtranslateit", DownloadTranslationsExtension::class.java)

        target.afterEvaluate {
            target.extensions.getByType(DownloadTranslationsExtension::class.java).configurations.forEach { configuration ->
                target.logger.debug("Creating task update${configuration.name.capitalize()}Translations")
                val task = target.tasks.create("update${configuration.name.capitalize()}Translations", UpdateTranslationsTask::class.java) {
                    it.configuration = configuration
                }
                task.group = "Translations"
            }
        }

        target.logger.debug("Creating task updateTranslations")
        val task = target.tasks.create("updateTranslations", UpdateTranslationsTask::class.java) {
            it.configuration = extension
        }
        task.group = "Translations"
    }

}