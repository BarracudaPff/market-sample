/**
* Marketplace
* Marketplace - учебный проект курса \"Backend разработка на Kotlin\"
*
* The version of the OpenAPI document: 0.0.1
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package ru.otus.otuskotlin.marketplace.kmp.transport.models


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Структура с описанием отладочных параметров запроса
 * @param mode 
 */
@Serializable
data class BaseDebugRequest (
    @SerialName(value = "mode") val mode: BaseDebugRequest.Mode? = null
) {

    /**
     * 
     * Values: PROD,TEST,STUB
     */
    @Serializable
    enum class Mode(val value: kotlin.String) {
        @SerialName(value = "prod") PROD("prod"),
        @SerialName(value = "test") TEST("test"),
        @SerialName(value = "stub") STUB("stub");
    }
}
