package com.negset.dothopper

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

fun constrain(x: Float, min: Float, max: Float): Float =
    when {
        x < min -> min
        x > max -> max
        else -> x
    }

fun isKeyPressed(key: Int) =
    Gdx.input.isKeyPressed(key)

fun isKeyJustPressed(key: Int) =
    Gdx.input.isKeyJustPressed(key)

fun SpriteBatch.drawCentered(texture: Texture, x: Float, y: Float) =
    draw(texture, x - texture.width / 2, y - texture.height / 2)

fun sound(file: String): Sound =
    Gdx.audio.newSound(Gdx.files.internal(file))
