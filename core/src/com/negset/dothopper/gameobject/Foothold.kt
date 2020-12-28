package com.negset.dothopper.gameobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.negset.dothopper.Segment
import ktx.math.vec2

class Foothold(
    x: Float, y: Float, w: Float,
    val isGoal: Boolean = false
) : GameObject() {
    private val texture = Texture("foothold.png")
    private val goalFlag: Texture?

    // TODO: player幅のベタ書きをやめる
    val segment = Segment(vec2(x - 10, y), vec2(w + 20, 0f))

    init {
        this.x = x
        this.y = y
        this.w = w
        h = 5f
        goalFlag = if (isGoal) Texture("goal_flag.png") else null
    }

    override fun draw(batch: SpriteBatch) {
        texture.let {
            batch.draw(it, x, y - h, w, h)
        }
        goalFlag?.let {
            batch.draw(it, x, y)
        }
    }

    override fun dispose() {
        texture.dispose()
        goalFlag?.dispose()
    }
}
