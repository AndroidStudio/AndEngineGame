package andengine.game;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

public class SceneManager {

    private static SceneManager sceneManager = null;
    private BaseScene mCurrentScene = null;

    public Engine mEngine;

    public static synchronized SceneManager getInstance() {
        if (SceneManager.sceneManager == null) {
            SceneManager.sceneManager = new SceneManager();
        }
        return SceneManager.sceneManager;
    }

    public Scene onCreateSplashScene() {
        final SplashScene splashScene = new SplashScene();
        this.mCurrentScene = splashScene;
        return splashScene;
    }

    public void onCreateMainScene() {
        final MainScene mainScene = new MainScene();
        this.setCurrentScene(mainScene);
    }

    public void setCurrentScene(BaseScene scene) {
        this.onDestroyCurrentScene();

        this.mCurrentScene = scene;
        this.mEngine.setScene(scene);
    }

    public void onDestroyCurrentScene() {
        if (this.mCurrentScene != null) {
            this.mCurrentScene.onDestroyScene();
        }
    }

    public void setEngine(Engine engine) {
        this.mEngine = engine;
    }
}
