package com.negset.dothopper

class SoundPlayer {
    companion object {
        val jump = sound("jump.mp3")
        val dead = sound("dead.mp3")
        val clear = sound("clear.mp3")

        fun dispose() {
            jump.dispose()
            dead.dispose()
            clear.dispose()
        }
    }
}
