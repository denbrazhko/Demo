package com.rexbot.bitrtix.bot.network

data class Resource<out T>(
    val status: RequestStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(RequestStatus.SUCCESS, data, null)

        fun <T> error(data: T?, message: String?): Resource<T> =
            Resource(RequestStatus.ERROR, data, message)

        fun <T> loading(data: T?): Resource<T> = Resource(RequestStatus.LOADING, data, null)
    }
}
