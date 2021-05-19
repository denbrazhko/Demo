package com.rexbot.bitrtix.bot.ui.common

enum class Themes {
    NIGHT,
    LIGHT;

    companion object {
        fun getThemeFromString(theme: String?): Themes {
            theme ?: return NIGHT
            return if (theme == NIGHT.toString())
                NIGHT
            else LIGHT
        }
    }
}