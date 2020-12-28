package com.negset.dothopper

import com.badlogic.gdx.math.Vector2
import com.negset.dothopper.gameobject.Enemy
import com.negset.dothopper.gameobject.Foothold
import com.negset.dothopper.gameobject.Player
import ktx.math.minus
import ktx.math.x

object Collision {
    var isGameOver = false
    var isStageClear = false

    fun update(player: Player, footholds: List<Foothold>, enemies: List<Enemy>) {
        when {
            // 足場からの落下判定
            !player.isHopping -> player.run {
                foothold?.let { f ->
                    if (x !in f.x - w..f.x + f.w)
                        hop()
                }
            }
            // 足場への上陸判定
            player.vy < 0 -> for (foothold in footholds) {
                if (player.segment.intersects(foothold.segment)) {
                    player.land(foothold)
                    if (foothold.isGoal) {
                        isStageClear = true
                        SoundPlayer.clear.play()
                    }
                    break
                }
            }
        }

        for (enemy in enemies) {
            if (player.segment.intersects(enemy.segment)) {
                player.run { y = if (vy < 0) enemy.y else enemy.bottom - h }
                SoundPlayer.dead.play()
                isGameOver = true
                break
            }
        }
    }
}

data class Segment(val s: Vector2, val v: Vector2) {
    fun intersects(seg: Segment): Boolean {
        val sv = seg.s - s
        val crsV1V2 = v x seg.v

        // 平行
        if (crsV1V2 == 0f)
            return false

        val crsVV1 = sv x v
        val crsVV2 = sv x seg.v

        val t1 = crsVV2 / crsV1V2
        val t2 = crsVV1 / crsV1V2

        // 交差していない
        val eps = 0.00001f
        return t1 in 0 - eps..1 + eps && t2 in 0 - eps..1 + eps
    }
}
