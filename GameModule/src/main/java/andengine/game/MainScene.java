package andengine.game;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;

public class MainScene extends BaseScene {
    private Sprite mStoneSprite;

    @Override
    void onCreateScene() {
        this.setBackground(new Background(Color.BLACK));

        this.mStoneSprite = new Sprite(200, 200, this.mResourcesManager.mStoneTexture, this.mVertexBufferObjectManager);
        this.attachChild(this.mStoneSprite);
    }

    @Override
    void onDestroyScene() {
        this.detachChild(this.mStoneSprite);
    }
}
