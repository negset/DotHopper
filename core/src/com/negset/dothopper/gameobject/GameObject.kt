package com.negset.dothopper.gameobject

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class GameObject {
    var x = 0f
    var y = 0f
    var w = 0f
    var h = 0f
    val left
        get() = x + w
    val bottom
        get() = y - h

    open fun draw(batch: SpriteBatch) {}

    open fun update(delta: Float) {}

    open fun dispose() {}
}
