package com.negset.dothopper

import com.negset.dothopper.gameobject.Enemy
import com.negset.dothopper.gameobject.Foothold

const val STAGE_NUM_MAX = 3

class StageData(num: Int) {
    val footholds: List<Foothold>
    val enemies: List<Enemy>

    init {
        when (num) {
            1 -> {
                footholds = listOf(
                    Foothold(0f, 0f, WIDTH),
                    Foothold(200f, 300f, 150f),
                    Foothold(300f, 600f, 150f),
                    Foothold(100f, 900f, 150f),
                    Foothold(300f, 1200f, 150f),
                    Foothold(200f, 1500f, 150f),
                    Foothold(200f, 1800f, 100f),
                    Foothold(100f, 2100f, 50f, isGoal = true)
                )
                enemies = listOf(
                    Enemy(50f, 450f, 150f),
                    Enemy(50f, 750f, 150f),
                    Enemy(50f, 1050f, 150f),
                    Enemy(50f, 1350f, 150f),
                    Enemy(50f, 1650f, 150f),
                    Enemy(50f, 1950f, 150f)
                )
            }
            2 -> {
                footholds = listOf(
                    Foothold(0f, 0f, WIDTH),
                    Foothold(100f, 300f, 100f),
                    Foothold(300f, 600f, 100f),
                    Foothold(200f, 900f, 75f),
                    Foothold(100f, 1200f, 75f),
                    Foothold(300f, 1500f, 50f),
                    Foothold(200f, 1800f, 50f),
                    Foothold(100f, 2100f, 50f, isGoal = true)
                )
                enemies = listOf(
                    Enemy(50f, 450f, 150f),
                    Enemy(50f, 750f, 150f),
                    Enemy(50f, 1050f, 150f),
                    Enemy(50f, 1350f, 150f),
                    Enemy(50f, 1650f, 150f),
                    Enemy(50f, 1950f, 150f)
                )
            }
            3 -> {
                footholds = listOf(
                    Foothold(0f, 0f, WIDTH),
                    Foothold(300f, 300f, 100f),
                    Foothold(200f, 600f, 100f),
                    Foothold(100f, 900f, 100f),
                    Foothold(300f, 1200f, 100f),
                    Foothold(100f, 1500f, 100f),
                    Foothold(200f, 1800f, 75f),
                    Foothold(300f, 2100f, 50f, isGoal = true)
                )
                enemies = listOf(
                    Enemy(50f, 420f, 150f),
                    Enemy(50f, 480f, 150f),
                    Enemy(50f, 720f, 150f),
                    Enemy(50f, 780f, 150f),
                    Enemy(50f, 1020f, 150f),
                    Enemy(50f, 1080f, 150f),
                    Enemy(50f, 1320f, 150f),
                    Enemy(50f, 1380f, 150f),
                    Enemy(50f, 1620f, 150f),
                    Enemy(50f, 1680f, 150f),
                    Enemy(50f, 1900f, 150f),
                    Enemy(50f, 1950f, 150f),
                    Enemy(50f, 2000f, 150f)
                )
            }
            else -> {
                footholds = listOf()
                enemies = listOf()
            }
        }
    }
}
