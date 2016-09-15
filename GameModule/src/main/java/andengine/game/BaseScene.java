package andengine.game;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BaseScene extends Scene {

    protected final SceneManager mSceneManager = SceneManager.getInstance();
    protected final ResourcesManager mResourcesManager = ResourcesManager.getInstance();

    protected final VertexBufferObjectManager mVertexBufferObjectManager;
    protected final Engine mEngine;
    protected final GameActivity mContext;

    public BaseScene() {
        this.mEngine = mSceneManager.mEngine;
        this.mVertexBufferObjectManager = mEngine.getVertexBufferObjectManager();
        this.mContext = mResourcesManager.mContext;

        this.onCreateScene();
    }

    abstract void onCreateScene();

    abstract void onDestroyScene();

}
