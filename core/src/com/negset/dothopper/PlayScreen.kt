package com.negset.dothopper

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.negset.dothopper.gameobject.Enemy
import com.negset.dothopper.gameobject.Foothold
import com.negset.dothopper.gameobject.Player
import ktx.app.KtxScreen
import ktx.graphics.use

class PlayScreen(
    private val game: DotHopper,
    private val stageNum: Int
) : KtxScreen {
    private val batch = SpriteBatch()
    private val player = Player()
    private val footholds: List<Foothold>
    private val enemies: List<Enemy>
    private val collision: Collision

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, WIDTH, HEIGHT)
    }
    private val uiCamera = OrthographicCamera().apply {
        setToOrtho(false, WIDTH, HEIGHT)
    }

    private val gameOver = Texture("game_over.png")
    private val gameOverMsg = Texture("game_over_msg.png")
    private val stageClear = Texture("stage_clear.png")
    private val stageClearMsg = Texture("stage_clear_msg.png")
    private val allClear = Texture("all_clear.png")
    private val allClearMsg = Texture("all_clear_msg.png")

    init {
        StageData(stageNum).let {
            footholds = it.footholds
            enemies = it.enemies
        }
        collision = Collision(player, footholds, enemies)

        updateCamera()
    }

    override fun render(delta: Float) {
        draw()
        update(delta)
    }

    private fun draw() {
        batch.use {
            it.projectionMatrix = camera.combined
            player.draw(it)
            footholds.forEach { f -> f.draw(it) }
            enemies.forEach { e -> e.draw(it) }

            when {
                collision.isGameOver -> {
                    it.projectionMatrix = uiCamera.combined
                    it.drawCentered(gameOver, WIDTH / 2, 500f)
                    it.drawCentered(gameOverMsg, WIDTH / 2, 150f)
                }
                collision.isStageClear -> {
                    it.projectionMatrix = uiCamera.combined
                    if (stageNum < STAGE_NUM_MAX) {
                        it.drawCentered(stageClear, WIDTH / 2, 500f)
                        it.drawCentered(stageClearMsg, WIDTH / 2, 150f)
                    } else {
                        it.drawCentered(allClear, WIDTH / 2, 500f)
                        it.drawCentered(allClearMsg, WIDTH / 2, 150f)
                    }
                }
            }
        }
    }

    private fun update(delta: Float) {
        when {
            // ゲームオーバー時
            collision.isGameOver ->
                if (isKeyPressed(Input.Keys.SPACE)) {
                    // 再プレイ
                    game.run {
                        removeScreen<PlayScreen>()
                        addScreen(PlayScreen(this, stageNum))
                        setScreen<PlayScreen>()
                    }
                    dispose()
                }
            // ステージクリア時
            collision.isStageClear ->
                if (stageNum < STAGE_NUM_MAX && isKeyPressed(Input.Keys.SPACE)) {
                    // 次のステージへ
                    game.run {
                        removeScreen<PlayScreen>()
                        addScreen(PlayScreen(this, stageNum + 1))
                        setScreen<PlayScreen>()
                    }
                    dispose()
                }
            // プレイ中
            else -> {
                player.update(delta)
                enemies.forEach { it.update(delta) }
                collision.update()
            }
        }

        // タイトルに戻る
        if (isKeyPressed(Input.Keys.ESCAPE)) {
            game.run {
                removeScreen<PlayScreen>()
                setScreen<TitleScreen>()
            }
            dispose()
        }

        updateCamera()
    }

    private fun updateCamera() {
        camera.run {
            position.y = player.y + 120f
            update()
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
        player.dispose()
        footholds.forEach { it.dispose() }
        enemies.forEach { it.dispose() }
        gameOver.dispose()
        gameOverMsg.dispose()
        stageClear.dispose()
        stageClearMsg.dispose()
        allClear.dispose()
        allClearMsg.dispose()
    }
}
