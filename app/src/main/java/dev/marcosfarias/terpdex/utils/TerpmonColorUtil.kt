package dev.marcosfarias.terpdex.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dev.marcosfarias.terpdex.R

class TerpmonColorUtil(var context: Context) {

    @ColorInt
    fun getTerpmonColor(typeOfTerpmon: List<String>?): Int {
        val type = typeOfTerpmon?.getOrNull(0)
        val color = when (type?.toLowerCase()) {
            "grass", "bug" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> R.color.lightBlue
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}
