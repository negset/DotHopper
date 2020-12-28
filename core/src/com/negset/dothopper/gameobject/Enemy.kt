package com.negset.dothopper.gameobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import com.negset.dothopper.Segment
import ktx.math.vec2
import kotlin.random.Random

class Enemy(x: Float, y: Float, w: Float) : GameObject() {
    private val texture = Texture("enemy.png")

    // TODO: player幅のベタ書きをやめる
    val segment = Segment(vec2(x - 10, y), vec2(w + 20, 0f))

    // アクション用
    private val itp: Interpolation = Interpolation.fade
    private var elapsed = Random.nextFloat() * 4
    private val startX = x

    init {
        this.x = x
        this.y = y
        this.w = w
        h = 5f
    }

    override fun draw(batch: SpriteBatch) {
        texture.let {
            batch.draw(it, x, y - h, w, h)
        }
    }

    override fun update(delta: Float) {
        elapsed += delta
        if (elapsed >= 4) elapsed -= 4
        val progress = (if (elapsed < 2) elapsed else 4 - elapsed) / 2f

        x = startX + itp.apply(progress) * 250

        segment.run {
            s.x = x
            s.y = y
        }
    }

    override fun dispose() {
        texture.dispose()
    }
}
