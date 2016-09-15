package andengine.game;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TreeSprite extends Sprite {
    private static final String TAG = "TreeSprite";

    private static final float MAX_TIME = 6.0f;

    private final float pX;
    private final float pY;

    private final float pDelay;

    private final float moveByX;
    private final float moveByY;

    private float mTimeDelayElapsed;
    private float mTimeElapsed;

    public TreeSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, float pDelay, float pToX, float pToY) {
        super(pX, pY, 350, 450, pTextureRegion, pVertexBufferObjectManager);
        this.pX = pX;
        this.pY = pY;
        this.pDelay = pDelay;

        this.moveByX = (pX - pToX);
        this.moveByY = (pY - pToY);

        setScaleCenter(.5f, 0.0f);
        setScale(0f);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        if (this.mTimeDelayElapsed >= this.pDelay * 2) {
            this.mTimeElapsed += pSecondsElapsed;

            float dx = (float) Math.pow(this.mTimeElapsed / MAX_TIME, 2.5);
            setPosition(pX - dx * moveByX, pY - dx * moveByY);
            setScale(dx);

            if (this.mTimeElapsed >= MAX_TIME) {
                setScale(0);
                setPosition(pX, pY);
                this.mTimeElapsed = 0;
                //setZIndex(index++);
            }
        } else {
            this.mTimeDelayElapsed += pSecondsElapsed;
        }
    }
}
