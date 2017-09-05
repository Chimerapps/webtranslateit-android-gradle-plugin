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