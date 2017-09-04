package com.chimerapps.gradle.webtranslateit.webtranslateit.api

import com.chimerapps.gradle.webtranslateit.webtranslateit.api.model.Project
import com.chimerapps.gradle.webtranslateit.webtranslateit.api.model.ProjectResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Nicola Verbeeck
 * @date 04/09/2017.
 */
interface WebTranslateItApi {

    companion object {
        const val API_BASE = "https://webtranslateit.com/api/"
    }

    @GET("projects/{projectToken}.json")
    fun getProject(@Path("projectToken") projectToken: String): Call<ProjectResponse>

    @GET("projects/{projectToken}/files/{id}/locales/{localeCode}")
    fun getTranslationFile(@Path("projectToken") projectToken: String,
                           @Path("id") id: Long,
                           @Path("localeCode") localeCode: String): Call<ResponseBody>

}