package com.negset.dothopper

import com.badlogic.gdx.math.Vector2
import com.negset.dothopper.gameobject.Enemy
import com.negset.dothopper.gameobject.Player
import com.negset.dothopper.gameobject.Foothold
import ktx.math.minus
import ktx.math.x

class Collision(private val player: Player,
                private val footholds: List<Foothold>,
                private val enemies: List<Enemy>) {
    var isGameOver = false
    var isStageClear = false

    fun update() {
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
                if (collisionSegments(player.segment, foothold.segment)) {
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
            if (collisionSegments(player.segment, enemy.segment)) {
                player.run { y = if (vy < 0) enemy.y else enemy.bottom - h }
                SoundPlayer.dead.play()
                isGameOver = true
                break
            }
        }
    }

    /**
     * 2つの線分が交差しているか否かを調べる
     */
    private fun collisionSegments(seg1: Segment, seg2: Segment): Boolean {
        val v = seg2.s - seg1.s
        val crsV1V2 = seg1.v x seg2.v

        // 平行
        if (crsV1V2 == 0f)
            return false

        val crsVV1 = v x seg1.v
        val crsVV2 = v x seg2.v

        val t1 = crsVV2 / crsV1V2
        val t2 = crsVV1 / crsV1V2

        // 交差していない
        val eps = 0.00001f
        if (t1 + eps < 0 || t1 - eps > 1 || t2 + eps < 0 || t2 - eps > 1)
            return false

        return true
    }
}

data class Segment(val s: Vector2, val v: Vector2)
