package com.negset.dothopper

import com.badlogic.gdx.Screen
import ktx.app.KtxGame
import ktx.app.clearScreen

const val WIDTH = 480f
const val HEIGHT = 720f

class DotHopper : KtxGame<Screen>(clearScreen = false) {
    override fun create() {
        addScreen(TitleScreen(this))
        setScreen<TitleScreen>()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f, 1f)
        super.render()
    }

    override fun dispose() {
        SoundPlayer.dispose()
        super.dispose()
    }
}
