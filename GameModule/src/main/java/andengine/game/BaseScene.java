package andengine.game;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BaseScene extends Scene {

    protected SceneManager mSceneManager = SceneManager.getInstance();
    protected ResourcesManager mResourcesManager = ResourcesManager.getInstance();

    protected VertexBufferObjectManager mVertexBufferObjectManager = mSceneManager.mEngine.getVertexBufferObjectManager();
    protected Engine mEngine = mSceneManager.mEngine;

    public BaseScene() {
        onCreateScene();
    }

    abstract void onCreateScene();

    abstract void onDestroyScene();

}
