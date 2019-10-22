package com.negset.dothopper.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.graphics.Color
import com.negset.dothopper.DotHopper

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            width = 480
            height = 720
            resizable = false
            initialBackgroundColor = Color.WHITE
            title = "DOT HOPPER"
        }
        LwjglApplication(DotHopper(), config)
    }
}
