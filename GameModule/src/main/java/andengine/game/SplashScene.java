package andengine.game;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.util.adt.color.Color;

public class SplashScene extends BaseScene {
    @Override
    void onCreateScene() {
        this.setBackground(new Background(Color.WHITE));

//        final Sprite sprite = new Sprite(400, 240, this.mResourcesManager.mLoaderTexture, this.mVertexBufferObjectManager);
//
//        LoopEntityModifier loopEntityModifier = new LoopEntityModifier(new RotationModifier(1, 0, 360));
//        sprite.registerEntityModifier(loopEntityModifier);
//        attachChild(sprite);
    }

    @Override
    void onDestroyScene() {

    }

}
