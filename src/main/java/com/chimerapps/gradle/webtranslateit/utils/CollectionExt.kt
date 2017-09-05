package com.chimerapps.gradle.webtranslateit.utils

/**
 * @author Nicola Verbeeck
 * @date 05/09/2017.
 */
inline fun <T> Collection<T>.exists(function: (T) -> Boolean): Boolean {
    return find(function) != null
}