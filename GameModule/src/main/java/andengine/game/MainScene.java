package andengine.game;

import android.util.Log;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXObjectGroup;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends BaseScene {

    private List<TreeSprite> mSpriteList;

    @Override
    void onCreateScene() {
        this.setBackground(new Background(Color.BLACK));

        this.mSpriteList = new ArrayList<TreeSprite>() {
            {
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.1f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.3f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.5f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 0.7f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.9f, -200, 100));

                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.1f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 1.3f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.5f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 1.7f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.9f, -200, 100));

                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.1f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.3f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.5f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 2.7f, -200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.9f, -200, 100));


                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.1f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 0.3f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.5f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.7f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 0.9f, 1200, 100));

                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.1f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 1.3f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.5f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.7f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 1.9f, 1200, 100));

                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_01, mVertexBufferObjectManager, 2.1f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.3f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.5f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.7f, 1200, 100));
                add(new TreeSprite(400, 400, mResourcesManager.mTreeTexture_02, mVertexBufferObjectManager, 2.9f, 1200, 100));
            }
        };

//        for (int i = this.mSpriteList.size() - 1; i >= 0; i--) {
//            this.attachChild(this.mSpriteList.get(i));
//        }

        TMXTiledMap tmxTiledMap = null;

        try {
            final TMXLoader tmxLoader = new TMXLoader(this.mContext.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, mEngine.getVertexBufferObjectManager(), new TMXLoader.ITMXTilePropertiesListener() {
                @Override
                public void onTMXTileWithPropertiesCreated(final TMXTiledMap tmxTiledMap, final TMXLayer tmxLayer, final TMXTile tmxTile, final TMXProperties<TMXTileProperty> tmxTileProperties) {
                    Log.e("TMXLoader", " tmxTile.getTileColumn(): " + tmxTile.getTileColumn());
                    for (TMXTileProperty tmxTileProperty : tmxTileProperties) {
                        Log.e("TMXLoader", "onTMXTileWithPropertiesCreated: " + tmxTileProperty.getName());
                        Log.e("TMXLoader", "onTMXTileWithPropertiesCreated: " + tmxTileProperty.getValue());
                    }
                }
            });

            tmxTiledMap = tmxLoader.loadFromAsset("tmx/test.tmx");
        } catch (final TMXLoadException e) {
            Debug.e(e);
        }

        TMXLayer tmxLayer = tmxTiledMap.getTMXLayers().get(0);
        tmxTiledMap.detachChild(tmxLayer);
        attachChild(tmxLayer);

        Log.e("getTMXObjectGroups", "size: " + tmxTiledMap.getTMXObjectGroups().size());

        for (final TMXObjectGroup group : tmxTiledMap.getTMXObjectGroups()) {
            String name = group.getName();
            Log.e("TMXObjectGroup", "name: " + name);
        }
    }

    @Override
    void onDestroyScene() {
        if (this.mSpriteList != null) {
            for (Sprite sprite : mSpriteList) {
                this.detachChild(sprite);
            }
        }
    }
}
