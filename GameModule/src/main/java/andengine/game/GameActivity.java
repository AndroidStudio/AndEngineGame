package andengine.game;

import android.util.DisplayMetrics;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

public class GameActivity extends BaseGameActivity {
    private ResourcesManager mResourceManager;
    private SceneManager mSceneManager;

    private int mCameraHeight;
    private int mCameraWidth;

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {
        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        this.mSceneManager = SceneManager.getInstance();
        this.mSceneManager.setEngine(this.mEngine);

        this.mResourceManager = ResourcesManager.getInstance();
        this.mResourceManager.setEngine(this.mEngine);
        this.mResourceManager.setContext(this);
        this.mResourceManager.loadResources();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        pOnCreateSceneCallback.onCreateSceneFinished(this.mSceneManager.onCreateSplashScene());
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
        this.mEngine.registerUpdateHandler(this.mStartMainSceneTimer);
    }

    private final TimerHandler mStartMainSceneTimer = new TimerHandler(2, new ITimerCallback() {

        @Override
        public void onTimePassed(TimerHandler pTimerHandler) {
            mSceneManager.onCreateMainScene();
        }
    });

    public EngineOptions onCreateEngineOptions() {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final float widthPixels = displayMetrics.widthPixels;
        final float heightPixels = displayMetrics.heightPixels;
        final float device_ratio = widthPixels / heightPixels;

        this.mCameraWidth = 800;
        this.mCameraHeight = (int) (800 / device_ratio);

        final BoundCamera boundCamera = new BoundCamera(0, 0, this.mCameraWidth, this.mCameraHeight);
        boundCamera.setBoundsEnabled(true);

        final RatioResolutionPolicy ratioResolutionPolicy = new RatioResolutionPolicy(this.mCameraWidth, this.mCameraHeight);
        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, ratioResolutionPolicy, boundCamera);
        engineOptions.getRenderOptions().setDithering(true);
        engineOptions.getTouchOptions().setNeedsMultiTouch(false);
        engineOptions.getAudioOptions().setNeedsSound(true);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        return engineOptions;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mSceneManager != null) {
            this.mSceneManager.onDestroyCurrentScene();
        }
        if (this.mEngine != null) {
            this.mEngine.unregisterUpdateHandler(this.mStartMainSceneTimer);
        }
        if (this.mResourceManager != null) {
            this.mResourceManager.unloadResources();
        }
    }
}
