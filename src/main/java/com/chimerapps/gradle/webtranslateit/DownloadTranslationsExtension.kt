package com.chimerapps.gradle.webtranslateit

import com.chimerapps.gradle.webtranslateit.utils.exists
import org.gradle.api.Action

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */

open class TranslationConfiguration(open var name: String = "default") {

    open var apiKey: String? = null
    open var fileName: String = "strings.xml"
    open var sourceRoot: String = "src/main/res"

}

open class DownloadTranslationsExtension : TranslationConfiguration() {

    val configurations = arrayListOf<TranslationConfiguration>()

    open fun configuration(name: String, configuration: Action<in TranslationConfiguration>) {
        if (configurations.exists { it.name == name })
            throw IllegalArgumentException("A configuration with the name \"$name\" already exists")
        if (name == "default")
            throw IllegalArgumentException("Creating a configuration with the name \"default\" is not allowed. The outer block is default")

        TranslationConfiguration(name).apply {
            configuration.execute(this)
            configurations.add(this)
        }
    }

}