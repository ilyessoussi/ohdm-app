package com.ilyeshtw.ohdm.ohdmapp;


        import android.app.Activity;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.os.Environment;
        import org.mapsforge.core.model.LatLong;
        import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
        import org.mapsforge.map.android.util.AndroidUtil;
        import org.mapsforge.map.android.view.MapView;
        import org.mapsforge.map.datastore.MapDataStore;
        import org.mapsforge.map.layer.cache.TileCache;
        import org.mapsforge.map.layer.renderer.TileRendererLayer;
        import org.mapsforge.map.reader.MapFile;
        import org.mapsforge.map.rendertheme.InternalRenderTheme;

        import java.io.File;
        import java.io.InputStream;
        import  java.io.OutputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Map;

public class MapActivity extends Activity {
    // name of the map file in the external storage
    private static final String MAP_FILE = "berlin.map";

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidGraphicFactory.createInstance(this.getApplication());

        this.mapView = new MapView(this);
        setContentView(this.mapView);

        this.mapView.setClickable(true);
        this.mapView.getMapScaleBar().setVisible(true);
        this.mapView.setBuiltInZoomControls(true);
        this.mapView.setZoomLevelMin((byte) 10);
        this.mapView.setZoomLevelMax((byte) 20);

        // create a tile cache of suitable size
        TileCache tileCache = AndroidUtil.createTileCache(this, "mapcache",
                mapView.getModel().displayModel.getTileSize(), 1f,
                this.mapView.getModel().frameBufferModel.getOverdrawFactor());

        //InputStream inptStrm = getResources().openRawResource(R.raw.berlin);
        //String  path = "android.resource://" + getPackageName() + File.separator + "res" +File.separator + "raw" + File.separator  + MAP_FILE;

        //Uri path = Uri.parse("android.resource://com.androidbook.samplevideo/" + R.raw.myvideo);

        // tile renderer layer using internal render theme
        //MapDataStore mapDataStore = new MapFile(new File(Environment.getExternalStorageDirectory(), MAP_FILE));
        MapDataStore mapDataStore = new MapFile(getMapFile());//this.getPackageName() + File.separator  + "raw" + File.separator +   MAP_FILE));
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,
                this.mapView.getModel().mapViewPosition, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.DEFAULT);

        // only once a layer is associated with a mapView the rendering starts
        this.mapView.getLayerManager().getLayers().add(tileRendererLayer);

        this.mapView.setCenter(new LatLong(52.517037, 13.38886));
        this.mapView.setZoomLevel((byte) 12);
    }

    @Override
    protected void onDestroy() {
        this.mapView.destroyAll();
        AndroidGraphicFactory.clearResourceMemoryCache();
        super.onDestroy();
    }

    private File copyFileToSdcard(int resid, String filename, String sdcardDirectoryName) {

        //String dirpath = Environment.getExternalStorageDirectory().getPath() + "/" + sdcardDirectoryName;
        String dirpath = Environment.getExternalStorageDirectory().getPath() +  File.separator +sdcardDirectoryName + File.separator + filename;
        File dir = new File(dirpath);
        if (dir.exists() == false) {
            dir.mkdir();
        }

        File file = new File(dirpath, filename);
        try {
            InputStream is = getResources().openRawResource(resid);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (IOException e) {
            return null;
        }
        return file;
    }

    private File getMapFile() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String filepath = Environment.getExternalStorageDirectory().getPath() + "/maps/berlin.map";
        File file = new File(filepath);
        try {
            if (file.exists() == false) {
                file = copyFileToSdcard(R.raw.berlin, "berlin.map", "maps");

            }

        } catch (Exception e) {
            throw e;
        }
        return file;
    }

}