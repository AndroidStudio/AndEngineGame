package andengine.game;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;

import java.util.ArrayList;
import java.util.List;

public class ResourcesManager {
    private static final String GFX_DIR = "gfx/";

    private static ResourcesManager resourcesManager = null;

    private TexturePackTextureRegionLibrary mTextureRegionLibrary;
    private List<ITextureRegion> mTextureList = new ArrayList<>();
    private TexturePack mTexturePack;
    private Engine mEngine;

    public GameActivity mContext;

    public ITextureRegion mLoaderTexture;
    public ITextureRegion mTreeTexture_01;
    public ITextureRegion mTreeTexture_02;

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
        this.mLoaderTexture = createTexture("loader_texture.png");
        this.mTreeTexture_01 = createTexture("tree_texture_01.png");
        this.mTreeTexture_02 = createTexture("tree_texture_02.png");

        this.mTextureList.add(this.mLoaderTexture);
        this.mTextureList.add(this.mTreeTexture_01);
        this.mTextureList.add(this.mTreeTexture_02);



    }

    private ITextureRegion createTexture(String textureName) {
        final AssetBitmapTextureAtlasSource assetBitmapTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.mContext.getAssets(), BitmapTextureAtlasTextureRegionFactory.getAssetBasePath() + textureName);
        final BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(this.mEngine.getTextureManager(), assetBitmapTextureAtlasSource.getTextureWidth(), assetBitmapTextureAtlasSource.getTextureHeight(), TextureOptions.BILINEAR_PREMULTIPLYALPHA);
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
        for (ITextureRegion iTextureRegion : this.mTextureList) {
            iTextureRegion.getTexture().unload();
        }

        if (this.mTexturePack != null) {
            this.mTexturePack.unloadTexture();
        }
    }

    public void setContext(GameActivity context) {
        this.mContext = context;
    }
}
