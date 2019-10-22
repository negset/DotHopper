package com.negset.dothopper.gameobject

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.negset.dothopper.*
import ktx.math.vec2

class Player : GameObject() {
    private val texture = Texture("player.png")

    var vx = 0f
    var vy = 0f
    /** 浮いているか否か */
    var isHopping = true
    /** 現在乗っている足場 */
    var foothold: Foothold? = null
    /** 衝突判定用の線分 */
    val segment
        get() = Segment(vec2(x + w / 2, y), vec2(vx, vy))

    init {
        x = 230f
        y = 200f
        w = texture.width.toFloat()
        h = texture.height.toFloat()
    }

    override fun draw(batch: SpriteBatch) {
        batch.draw(texture, x, y)
    }

    override fun update(delta: Float) {
        if (isKeyPressed(Input.Keys.LEFT))
            if (vx > -4f) vx -= 1f
        if (isKeyPressed(Input.Keys.RIGHT))
            if (vx < 4f) vx += 1f
        if (isKeyJustPressed(Input.Keys.UP)) {
            if (!isHopping) {
                vy = 32f
                isHopping = true
                SoundPlayer.jump.play()
            }
        }

        x += vx
        y += vy

        when {
            vx < -0.1f -> vx += 0.1f
            vx > 0.1f -> vx -= 0.1f
            else -> vx = 0f
        }

        if (isHopping) vy -= 1.5f

        // x座標を画面内に収める
        x = constrain(x, 0f, WIDTH - 20)
    }

    /**
     * 着陸処理
     */
    fun land(foothold: Foothold) {
        y = foothold.y
        vy = 0f
        isHopping = false
        this.foothold = foothold
    }

    /**
     * 離陸処理
     */
    fun hop() {
        isHopping = true
        foothold = null
    }

    override fun dispose() {
        texture.dispose()
    }
}
