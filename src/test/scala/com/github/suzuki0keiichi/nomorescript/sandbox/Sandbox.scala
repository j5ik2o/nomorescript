package com.github.suzuki0keiichi.nomorescript.sandbox

import nomorescriptroot.browser.window._
import nomorescriptroot.enchantjs._
import com.github.suzuki0keiichi.nomorescript.bridge.bridge._

import com.github.suzuki0keiichi.nomorescript.annotation.global

@global object Test3 {
  // forked from phi's "enchant.js - Sprite を画面外に出ないように制御しよう" http://jsdo.it/phi/ooXl
  // forked from phi's "enchant.js - Sprite をフレームアニメーションさせながら移動させてみよう" http://jsdo.it/phi/62N9
  // forked from phi's "enchant.js - Sprite をフレームアニメーションさせてみよう" http://jsdo.it/phi/vFxK
  // forked from phi's "enchant.js - Sprite を移動させてみよう" http://jsdo.it/phi/yXyl
  // forked from phi's "enchant.js - Sprite を表示しよう" http://jsdo.it/phi/kAKa
  // forked from phi's "enchant.js - Entity を生成してSceneに追加しよう" http://jsdo.it/phi/tlgU
  // forked from phi's "enchant.js のテンプレートを用意しよう" http://jsdo.it/phi/isoa
  // おまじない(using namespace enchant)
  enchant()

  val CHARA_IMAGE_NAME = "http://enchantjs.com/assets/images/chara1.gif"

  onload = { window: AnyRef =>
    val game = new Game()

    game.preload(CHARA_IMAGE_NAME) // 画像読み込み

    game.onload = { game: Game =>
      var scene = game.rootScene
      scene.backgroundColor = "black"

      // スプライト生成
      var sprite = new Sprite(32, 32) // スプライト生成
      sprite.moveTo(0, 100) // 移動させる
      sprite.image = game.assets(CHARA_IMAGE_NAME) // 画像をセット
      scene.addChild(sprite) // シーンに追加

      // フレームアニメーションさせてみよう
      var PLAYER_MOVE_RANGE = game.width - sprite.width
      var frameList = Array(0, 1, 2)
      sprite.frameIndex = 0
      sprite.vx = 4
      sprite.onenterframe = { sprite: Sprite =>
        // 移動させる
        sprite.x += sprite.vx
        // フレームを進める
        if (game.frame % 2 == 0) {
          sprite.frameIndex += 1
          sprite.frameIndex %= frameList.length
          sprite.frame = frameList(sprite.frameIndex)
        }

        // 画面からはみ出しそうになったら位置を補正して進行方向を反転させる
        // スケールを使って向きも反転させる
        if (sprite.x < 0) {
          sprite.vx *= -1
          sprite.x = 0
          sprite.scaleX *= -1
        } else if (sprite.x > PLAYER_MOVE_RANGE) {
          sprite.vx *= -1
          sprite.x = PLAYER_MOVE_RANGE
          sprite.scaleX *= -1
        }
      }
    }

    game.start()
  }

}