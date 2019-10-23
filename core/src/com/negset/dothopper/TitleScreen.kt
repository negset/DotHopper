package com.negset.dothopper

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.graphics.use

class TitleScreen(private val game: DotHopper) : KtxScreen {
    private val batch = SpriteBatch()
    private val title = Texture("title.png")
    private val titleMsg = Texture("title_msg.png")
    private val copyright = Texture("copyright.png")

    override fun render(delta: Float) {
        batch.use {
            it.drawCentered(title, WIDTH / 2, 500f)
            it.drawCentered(titleMsg, WIDTH / 2, 200f)
            it.drawCentered(copyright, WIDTH / 2, 50f)
        }

        if (isKeyPressed(Input.Keys.SPACE)) {
            game.run {
                addScreen(PlayScreen(this, 1))
                setScreen<PlayScreen>()
            }
        }
    }

    override fun dispose() {
        title.dispose()
        titleMsg.dispose()
        copyright.dispose()
    }
}
