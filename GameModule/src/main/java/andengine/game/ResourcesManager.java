package andengine.game;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;

public class ResourcesManager {
    private static final String GFX_DIR = "gfx/";

    private static ResourcesManager resourcesManager = null;

    private TexturePackTextureRegionLibrary mTextureRegionLibrary;
    private TexturePack mTexturePack;
    private Engine mEngine;
    private GameActivity mContext;

    public ITextureRegion mStoneTexture;
    public ITextureRegion mLoaderTexture;

    public static synchronized ResourcesManager getInstance() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(GFX_DIR);
        if (ResourcesManager.resourcesManager == null) {
            ResourcesManager.resourcesManager = new ResourcesManager();
        }
        return ResourcesManager.resourcesManager;
    }

    public void setEngine(Engine engine) {
        this.mEngine = engine;
    }

    public void loadResources() {
        this.mStoneTexture = createTexture("stone.png", TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mLoaderTexture = createTexture("pobrane.png", TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.loadSpriteSheet();
    }

    private ITextureRegion createTexture(String string, TextureOptions bilinear) {
        final AssetBitmapTextureAtlasSource assetBitmapTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.mContext.getAssets(), BitmapTextureAtlasTextureRegionFactory.getAssetBasePath() + string);
        final BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(this.mEngine.getTextureManager(), assetBitmapTextureAtlasSource.getTextureWidth(), assetBitmapTextureAtlasSource.getTextureHeight(), bilinear);
        final TextureRegion region = new TextureRegion(bitmapTextureAtlas, 0.0F, 0.0F, assetBitmapTextureAtlasSource.getTextureWidth(), assetBitmapTextureAtlasSource.getTextureHeight(), false);
        bitmapTextureAtlas.addTextureAtlasSource(assetBitmapTextureAtlasSource, 0, 0);
        bitmapTextureAtlas.load();
        return region;
    }

    public void loadSpriteSheet() {
        try {
            this.mTexturePack = new TexturePackLoader(this.mEngine.getTextureManager(), GFX_DIR).loadFromAsset(this.mContext.getAssets(), "sprite.xml");
            this.mTextureRegionLibrary = this.mTexturePack.getTexturePackTextureRegionLibrary();
            this.mTexturePack.getTexture().load();
        } catch (TexturePackParseException ex) {
            Log.e("Factory", ex.getMessage(), ex);
        }
    }

    public TextureRegion getRegion(int id) {
        return this.mTextureRegionLibrary.get(id);
    }

    public void unloadResources() {
        this.mTexturePack.unloadTexture();
        this.mStoneTexture.getTexture().unload();
    }

    public void setContext(GameActivity context) {
        this.mContext = context;
    }
}
