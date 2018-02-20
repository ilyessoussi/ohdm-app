package com.ilyeshtw.ohdm.ohdmapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.ilyeshtw.ohdm.ohdmapp.R;

public class MainFragment extends Fragment {
    private static final String MAP_FILE = "berlin.map";
    //private MapView myOpenMapView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //AndroidGraphicFactory.createInstance(getActivity().getApplication());
        View myView =  inflater.inflate(R.layout.fragment_main,container,false);
        //myOpenMapView = new MapView(this.getContext());
        //this.getActivity().setContentView(this.myOpenMapView);

        /*myOpenMapView = (MapView) myView.findViewById(R.id.open_mapview);

        myOpenMapView.setClickable(true);
        myOpenMapView.getMapScaleBar().setVisible(true);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setZoomLevelMin((byte) 10);
        myOpenMapView.setZoomLevelMax((byte) 20);

        // create a tile cache of suitable size
        TileCache tileCache = AndroidUtil.createTileCache(this.getActivity(), "mapcache",
                myOpenMapView.getModel().displayModel.getTileSize(), 1f,
                myOpenMapView.getModel().frameBufferModel.getOverdrawFactor());

        // tile renderer layer using internal render theme
        MapDataStore mapDataStore = new MapFile(new File(this.getActivity().getFilesDir().toString() + File.separator + "res" + File.separator + "raw" + File.separator +   MAP_FILE));
        //MapDataStore mapDataStore = new MapFile(getResources().openRawResource(R.raw.berlin));
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,
                myOpenMapView.getModel().mapViewPosition, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.DEFAULT);

        // only once a layer is associated with a mapView the rendering starts
        myOpenMapView.getLayerManager().getLayers().add(tileRendererLayer);

        myOpenMapView.setCenter(new LatLong(52.517037, 13.38886));
        myOpenMapView.setZoomLevel((byte) 12);*/
        return myView;
    }

    @Override
    public void onDestroy() {
        //myOpenMapView.destroyAll();
        //AndroidGraphicFactory.clearResourceMemoryCache();
        super.onDestroy();
    }
}
