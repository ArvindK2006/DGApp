package com.ebookfrenzy.tablayoutdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    CustomPolyline customPolylineObj;
    ArrayList<CustomPolyline> customPolylineArrayList = new ArrayList<>();

    private static final String TAG = MapsActivity.class.getSimpleName();
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    public static final int PATTERN_GAP_LENGTH_PX = 10;
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    public static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_POLYLINE_DASHED = Arrays.asList(GAP, DASH);
    private static final double POLYLINE_STROKE_WIDTH_PX = 10.5;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        //@Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.setMinZoomPreference(13.0f);
            mMap.setMaxZoomPreference(16.0f);

            boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
                    .getString(R.string.style_json)));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }

            // Add a marker in Sydney and move the camera
            LatLng middle = new LatLng(42.144, -88.069);
//        LatLng deerGrove = new LatLng(42.14384682495575, -88.06887273075864);
//        LatLng deerGroveEast = new LatLng(42.14572971060655, -88.04575745123859);
//        mMap.addMarker(new MarkerOptions().position(deerGrove).title("Deer Grove Marker"));
//        mMap.addMarker(new MarkerOptions().position(deerGroveEast).title("Deer Grove East Marker"));

            float zoomLevel = 13.0f;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(middle, zoomLevel));

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);


            mMap.getUiSettings().setTiltGesturesEnabled(true);

            final LatLng deerGroveLakeLocation = new LatLng(42.145373, -88.070564);

            final int height = 60;
            final int width = 60;

            Bitmap a = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_water);
            Bitmap smallMarker1 = Bitmap.createScaledBitmap(a, width, height, false);
            BitmapDescriptor smallMarkerIcon1 = BitmapDescriptorFactory.fromBitmap(smallMarker1);

            Marker deerGroveLake = mMap.addMarker(
                    new MarkerOptions()
                            .position(deerGroveLakeLocation)
                            .title("Deer Grove Lake")
                            .icon(smallMarkerIcon1));

            final LatLng sleddingHillLocation = new LatLng(42.146534, -88.074027);

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_sled);
            Bitmap smallMarker2 = Bitmap.createScaledBitmap(b, width, height, false);
            BitmapDescriptor smallMarkerIcon2 = BitmapDescriptorFactory.fromBitmap(smallMarker2);

            Marker sleddingHill = mMap.addMarker(
                    new MarkerOptions()
                            .position(sleddingHillLocation)
                            .title("Sledding Hill")
                            .icon(smallMarkerIcon2));

            Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.139379, -88.034060),
                            new LatLng(42.139515, -88.034126),
                            new LatLng(42.139999, -88.034504)));

            // Set listeners for click events.
            polyline1.setWidth((float) POLYLINE_STROKE_WIDTH_PX);
//        googleMap.setOnPolylineClickListener(this);

            Polyline polyline2 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.142838, -88.033981),
                            new LatLng(42.142716, -88.034069),
                            new LatLng(42.142630, -88.034159),
                            new LatLng(42.142597, -88.034207)));


            // Set listeners for click events.
            polyline2.setWidth((float) POLYLINE_STROKE_WIDTH_PX);


            Polyline polyline3 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.142597, -88.034207),
                            new LatLng(42.142718, -88.034235),
                            new LatLng(42.142827, -88.034280),
                            new LatLng(42.142914, -88.034353),
                            new LatLng(42.142999, -88.034457),
                            new LatLng(42.143048, -88.034553),
                            new LatLng(42.143088, -88.034715),
                            new LatLng(42.143156, -88.035014),
                            new LatLng(42.143214, -88.035263),
                            new LatLng(42.143304, -88.035680),
                            new LatLng(42.143304, -88.035863),
                            new LatLng(42.143290, -88.036214),
                            new LatLng(42.143278, -88.036635),
                            new LatLng(42.143271, -88.036975),
                            new LatLng(42.143264, -88.037340),
                            new LatLng(42.143263, -88.037407),
                            new LatLng(42.143254, -88.038188),
                            new LatLng(42.143251, -88.038520),
                            new LatLng(42.143256, -88.038663),
                            new LatLng(42.143278, -88.038818),
                            new LatLng(42.143312, -88.038925),
                            new LatLng(42.143373, -88.039055),
                            new LatLng(42.143470, -88.039197),
                            new LatLng(42.143690, -88.039451),
                            new LatLng(42.143801, -88.039556),
                            new LatLng(42.144041, -88.039712),
                            new LatLng(42.144288, -88.039830),
                            new LatLng(42.144345, -88.039850),
                            new LatLng(42.144535, -88.039891),
                            new LatLng(42.144915, -88.039953),
                            new LatLng(42.144997, -88.039988),
                            new LatLng(42.145069, -88.040038),
                            new LatLng(42.145124, -88.040100),
                            new LatLng(42.145188, -88.040220),
                            new LatLng(42.145224, -88.040309),
                            new LatLng(42.145252, -88.040402),
                            new LatLng(42.145266, -88.040485),
                            new LatLng(42.145263, -88.040687),
                            new LatLng(42.145231, -88.041416),
                            new LatLng(42.145234, -88.041611),
                            new LatLng(42.145259, -88.041939),
                            new LatLng(42.145286, -88.042181),
                            new LatLng(42.145320, -88.042453),
                            new LatLng(42.145345, -88.042684),
                            new LatLng(42.145387, -88.043060),
                            new LatLng(42.145412, -88.043205),
                            new LatLng(42.145448, -88.043339),
                            new LatLng(42.145533, -88.043523),
                            new LatLng(42.145634, -88.043658),
                            new LatLng(42.145762, -88.043757),
                            new LatLng(42.145884, -88.043813),
                            new LatLng(42.146165, -88.043913),
                            new LatLng(42.146785, -88.044077),
                            new LatLng(42.146895, -88.044123),
                            new LatLng(42.146966, -88.044185),
                            new LatLng(42.147030, -88.044288),
                            new LatLng(42.147063, -88.044412),
                            new LatLng(42.147078, -88.044522),
                            new LatLng(42.147064, -88.044646),
                            new LatLng(42.147015, -88.044862),
                            new LatLng(42.146961, -88.045110),
                            new LatLng(42.146904, -88.045406),
                            new LatLng(42.146821, -88.045676),
                            new LatLng(42.146779, -88.045787),
                            new LatLng(42.146723, -88.045893),
                            new LatLng(42.146661, -88.045989),
                            new LatLng(42.146565, -88.046119),
                            new LatLng(42.146522, -88.046159),
                            new LatLng(42.146345, -88.046324),
                            new LatLng(42.145990, -88.046623),
                            new LatLng(42.145231, -88.047264),
                            new LatLng(42.145136, -88.047344),
                            new LatLng(42.144896, -88.047631),
                            new LatLng(42.144649, -88.047944),
                            new LatLng(42.144407, -88.048252),
                            new LatLng(42.144337, -88.048363),
                            new LatLng(42.144268, -88.048612),
                            new LatLng(42.144259, -88.048718),
                            new LatLng(42.144257, -88.048886),
                            new LatLng(42.144274, -88.049284),
                            new LatLng(42.144296, -88.049854),
                            new LatLng(42.144301, -88.049940),
                            new LatLng(42.144312, -88.050242),
                            new LatLng(42.144305, -88.050342),
                            new LatLng(42.144273, -88.050554),
                            new LatLng(42.144267, -88.050697),
                            new LatLng(42.144307, -88.051607),
                            new LatLng(42.144289, -88.051810),
                            new LatLng(42.144256, -88.051979),
                            new LatLng(42.144218, -88.052140),
                            new LatLng(42.144176, -88.052311),
                            new LatLng(42.144125, -88.052512),
                            new LatLng(42.143990, -88.053006),
                            new LatLng(42.143861, -88.053590),
                            new LatLng(42.143809, -88.053810),
                            new LatLng(42.143781, -88.053923),
                            new LatLng(42.143734, -88.054020),
                            new LatLng(42.143665, -88.054138),
                            new LatLng(42.143155, -88.054922),
                            new LatLng(42.143096, -88.054885),
                            new LatLng(42.142994, -88.054832),
                            new LatLng(42.142929, -88.054822),
                            new LatLng(42.142793, -88.054779),
                            new LatLng(42.142628, -88.054735),
                            new LatLng(42.142540, -88.054712),
                            new LatLng(42.142351, -88.054622),
                            new LatLng(42.142284, -88.054576),
                            new LatLng(42.142226, -88.054546),
                            new LatLng(42.142131, -88.054463),
                            new LatLng(42.142029, -88.054358),
                            new LatLng(42.141939, -88.054246),
                            new LatLng(42.141926, -88.054230),
                            new LatLng(42.141899, -88.054194),
                            new LatLng(42.141871, -88.054153),
                            new LatLng(42.141784, -88.054027),
                            new LatLng(42.141598, -88.053754),
                            new LatLng(42.141465, -88.053527),
                            new LatLng(42.141299, -88.053145),
                            new LatLng(42.141063, -88.052574),
                            new LatLng(42.141005, -88.052389),
                            new LatLng(42.140984, -88.052270),
                            new LatLng(42.140971, -88.051961),
                            new LatLng(42.140985, -88.051834),
                            new LatLng(42.141075, -88.051529),
                            new LatLng(42.141259, -88.051148),
                            new LatLng(42.141308, -88.051049),
                            new LatLng(42.141337, -88.050986),
                            new LatLng(42.141355, -88.050950),
                            new LatLng(42.141523, -88.050578),
                            new LatLng(42.141625, -88.050353),
                            new LatLng(42.141778, -88.049975),
                            new LatLng(42.141929, -88.049590),
                            new LatLng(42.141987, -88.049452),
                            new LatLng(42.142041, -88.049289),
                            new LatLng(42.142070, -88.049145),
                            new LatLng(42.142064, -88.049028),
                            new LatLng(42.142018, -88.048863),
                            new LatLng(42.141926, -88.048566),
                            new LatLng(42.141900, -88.048435)
                    ));
            // Set listeners for click events.
            polyline3.setColor(Color.RED);
            polyline3.setWidth((float) POLYLINE_STROKE_WIDTH_PX);
//        polyline3.setTag("one");
//
//        customPolylineArrayList.add(new CustomPolyline(polyline3, "Red Paved Loop", "2.82 mi"));

            Polyline polyline4 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.142597, -88.034207),
                            new LatLng(42.142469, -88.034197),
                            new LatLng(42.142314, -88.034184),
                            new LatLng(42.142267, -88.034180),
                            new LatLng(42.142160, -88.034187),
                            new LatLng(42.142089, -88.034190),
                            new LatLng(42.141809, -88.034252),
                            new LatLng(42.141662, -88.034285),
                            new LatLng(42.141421, -88.034347),
                            new LatLng(42.141268, -88.034381),
                            new LatLng(42.141225, -88.034391),
                            new LatLng(42.141122, -88.034403),
                            new LatLng(42.140917, -88.034411),
                            new LatLng(42.140379, -88.034362),
                            new LatLng(42.140274, -88.034356),
                            new LatLng(42.140180, -88.034374),
                            new LatLng(42.140142, -88.034394),
                            new LatLng(42.140078, -88.034439),
                            new LatLng(42.139998, -88.034504),
                            new LatLng(42.139934, -88.034592),
                            new LatLng(42.139884, -88.034672),
                            new LatLng(42.139822, -88.034796),
                            new LatLng(42.139769, -88.034959),
                            new LatLng(42.139760, -88.035034),
                            new LatLng(42.139753, -88.035114),
                            new LatLng(42.139755, -88.035193),
                            new LatLng(42.139775, -88.035484),
                            new LatLng(42.139777, -88.035581),
                            new LatLng(42.139768, -88.035652),
                            new LatLng(42.139750, -88.035737),
                            new LatLng(42.139693, -88.035860),
                            new LatLng(42.139589, -88.036025),
                            new LatLng(42.139539, -88.036120),
                            new LatLng(42.139510, -88.036193),
                            new LatLng(42.139475, -88.036315),
                            new LatLng(42.139457, -88.036443),
                            new LatLng(42.139452, -88.036519),
                            new LatLng(42.139446, -88.036644),
                            new LatLng(42.139466, -88.037113),
                            new LatLng(42.139473, -88.038048),
                            new LatLng(42.139464, -88.038366),
                            new LatLng(42.139471, -88.038470),
                            new LatLng(42.139490, -88.038614),
                            new LatLng(42.139505, -88.038879),
                            new LatLng(42.139510, -88.039133),
                            new LatLng(42.139513, -88.039350),
                            new LatLng(42.139510, -88.039535),
                            new LatLng(42.139491, -88.039753),
                            new LatLng(42.139467, -88.040190),
                            new LatLng(42.139461, -88.040429),
                            new LatLng(42.139463, -88.040503),
                            new LatLng(42.139495, -88.040658),
                            new LatLng(42.139517, -88.040749),
                            new LatLng(42.139545, -88.040869),
                            new LatLng(42.139568, -88.040979),
                            new LatLng(42.139621, -88.041154),
                            new LatLng(42.139672, -88.041259),
                            new LatLng(42.139755, -88.041400),
                            new LatLng(42.139913, -88.041464),
                            new LatLng(42.140756, -88.041427),
                            new LatLng(42.140838, -88.041427),
                            new LatLng(42.140928, -88.041426),
                            new LatLng(42.140977, -88.041431),
                            new LatLng(42.141020, -88.041445),
                            new LatLng(42.141073, -88.041473),
                            new LatLng(42.141193, -88.041570),
                            new LatLng(42.141254, -88.041685),
                            new LatLng(42.141273, -88.041737),
                            new LatLng(42.141287, -88.041840),
                            new LatLng(42.141301, -88.042099)
                    ));
            // Set listeners for click events.
            polyline4.setColor(Color.RED);
            polyline4.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline5 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.141301, -88.042099),
                            new LatLng(42.141302, -88.042198),
                            new LatLng(42.141298, -88.042239),
                            new LatLng(42.141301, -88.042348),
                            new LatLng(42.141285, -88.042479),
                            new LatLng(42.141280, -88.042593),
                            new LatLng(42.141270, -88.042709),
                            new LatLng(42.141265, -88.042772),
                            new LatLng(42.141260, -88.042917),
                            new LatLng(42.141282, -88.043050),
                            new LatLng(42.141442, -88.043475),
                            new LatLng(42.141499, -88.043620),
                            new LatLng(42.141600, -88.043933),
                            new LatLng(42.141638, -88.044111),
                            new LatLng(42.141628, -88.044194),
                            new LatLng(42.141594, -88.044329),
                            new LatLng(42.141380, -88.044877),
                            new LatLng(42.141357, -88.045058),
                            new LatLng(42.141366, -88.045233),
                            new LatLng(42.141396, -88.045436),
                            new LatLng(42.141405, -88.045549),
                            new LatLng(42.141395, -88.045698),
                            new LatLng(42.141368, -88.045791),
                            new LatLng(42.141352, -88.046107),
                            new LatLng(42.141357, -88.046384),
                            new LatLng(42.141539, -88.047123),
                            new LatLng(42.141579, -88.047288),
                            new LatLng(42.141612, -88.047424),
                            new LatLng(42.141708, -88.047706),
                            new LatLng(42.141811, -88.048087),
                            new LatLng(42.141860, -88.048265),
                            new LatLng(42.141900, -88.048435)
                    ));
            // Set listeners for click events.
            polyline5.setColor(Color.RED);
            polyline5.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline6 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.143845, -88.061500),
                            new LatLng(42.144076, -88.061455),
                            new LatLng(42.144149, -88.061455),
                            new LatLng(42.144184, -88.061471),
                            new LatLng(42.144202, -88.061488),
                            new LatLng(42.144313, -88.061633),
                            new LatLng(42.144360, -88.061703),
                            new LatLng(42.144377, -88.061766),
                            new LatLng(42.144386, -88.061832),
                            new LatLng(42.144404, -88.061881),
                            new LatLng(42.144427, -88.061920),
                            new LatLng(42.144618, -88.062064),
                            new LatLng(42.144643, -88.062105),
                            new LatLng(42.144723, -88.062262),
                            new LatLng(42.144745, -88.062283),
                            new LatLng(42.144790, -88.062300),
                            new LatLng(42.144836, -88.062303),
                            new LatLng(42.144876, -88.062293),
                            new LatLng(42.144906, -88.062274),
                            new LatLng(42.144938, -88.062244),
                            new LatLng(42.144954, -88.062238),
                            new LatLng(42.144970, -88.062235),
                            new LatLng(42.144992, -88.062239),
                            new LatLng(42.145034, -88.062256),
                            new LatLng(42.145051, -88.062260),
                            new LatLng(42.145081, -88.062259),
                            new LatLng(42.145260, -88.062195),
                            new LatLng(42.145433, -88.062183),
                            new LatLng(42.145514, -88.062206),
                            new LatLng(42.145535, -88.062218),
                            new LatLng(42.145552, -88.062243),
                            new LatLng(42.145612, -88.062396),
                            new LatLng(42.145631, -88.062415),
                            new LatLng(42.145642, -88.062429),
                            new LatLng(42.145658, -88.062437),
                            new LatLng(42.145669, -88.062437),
                            new LatLng(42.145689, -88.062429),
                            new LatLng(42.145797, -88.062325),
                            new LatLng(42.145844, -88.062302),
                            new LatLng(42.145921, -88.062280),
                            new LatLng(42.145943, -88.062279),
                            new LatLng(42.145967, -88.062276),
                            new LatLng(42.146030, -88.062242),
                            new LatLng(42.146049, -88.062208),
                            new LatLng(42.146076, -88.062089),
                            new LatLng(42.146080, -88.062018),
                            new LatLng(42.146096, -88.061993),
                            new LatLng(42.146113, -88.061982),
                            new LatLng(42.146132, -88.061980),
                            new LatLng(42.146175, -88.061962),
                            new LatLng(42.146193, -88.061946),
                            new LatLng(42.146200, -88.061927),
                            new LatLng(42.146206, -88.061893),
                            new LatLng(42.146213, -88.061800),
                            new LatLng(42.146221, -88.061758),
                            new LatLng(42.146242, -88.061697),
                            new LatLng(42.146247, -88.061633),
                            new LatLng(42.146238, -88.061562),
                            new LatLng(42.146183, -88.061421),
                            new LatLng(42.146154, -88.061301),
                            new LatLng(42.146144, -88.061273),
                            new LatLng(42.146129, -88.061254),
                            new LatLng(42.146080, -88.061202),
                            new LatLng(42.145986, -88.061080),
                            new LatLng(42.145986, -88.061055),
                            new LatLng(42.145990, -88.061037),
                            new LatLng(42.146018, -88.060965),
                            new LatLng(42.146021, -88.060941),
                            new LatLng(42.146020, -88.060882),
                            new LatLng(42.146012, -88.060860),
                            new LatLng(42.145954, -88.060811),
                            new LatLng(42.145910, -88.060754),
                            new LatLng(42.145904, -88.060700),
                            new LatLng(42.145918, -88.060627),
                            new LatLng(42.145935, -88.060577),
                            new LatLng(42.145943, -88.060538),
                            new LatLng(42.145995, -88.060380),
                            new LatLng(42.146002, -88.060329),
                            new LatLng(42.146022, -88.060277),
                            new LatLng(42.146034, -88.060256),
                            new LatLng(42.146051, -88.060235),
                            new LatLng(42.146117, -88.060138),
                            new LatLng(42.146122, -88.060113),
                            new LatLng(42.146123, -88.060096),
                            new LatLng(42.146119, -88.060071),
                            new LatLng(42.146096, -88.060036),
                            new LatLng(42.146073, -88.060013),
                            new LatLng(42.146062, -88.060005),
                            new LatLng(42.146039, -88.059981),
                            new LatLng(42.146027, -88.059964),
                            new LatLng(42.146018, -88.059941),
                            new LatLng(42.146031, -88.059912),
                            new LatLng(42.146045, -88.059890),
                            new LatLng(42.146056, -88.059875),
                            new LatLng(42.146073, -88.059854),
                            new LatLng(42.146092, -88.059835),
                            new LatLng(42.146109, -88.059824),
                            new LatLng(42.146136, -88.059808),
                            new LatLng(42.146198, -88.059780),
                            new LatLng(42.146215, -88.059763),
                            new LatLng(42.146232, -88.059748),
                            new LatLng(42.146244, -88.059727),
                            new LatLng(42.146244, -88.059703),
                            new LatLng(42.146242, -88.059673),
                            new LatLng(42.146228, -88.059574),
                            new LatLng(42.146225, -88.059465),
                            new LatLng(42.146195, -88.059357),
                            new LatLng(42.146146, -88.059233),
                            new LatLng(42.146126, -88.059168),
                            new LatLng(42.146098, -88.059148),
                            new LatLng(42.146069, -88.059112),
                            new LatLng(42.146015, -88.059091),
                            new LatLng(42.145988, -88.059074),
                            new LatLng(42.145953, -88.059038),
                            new LatLng(42.145933, -88.059025),
                            new LatLng(42.145889, -88.059001),
                            new LatLng(42.145862, -88.058957),
                            new LatLng(42.145847, -88.058877),
                            new LatLng(42.145811, -88.058824),
                            new LatLng(42.145786, -88.058759),
                            new LatLng(42.145766, -88.058620),
                            new LatLng(42.145747, -88.058537),
                            new LatLng(42.145744, -88.058491),
                            new LatLng(42.145748, -88.058428),
                            new LatLng(42.145756, -88.058377),
                            new LatLng(42.145782, -88.058321),
                            new LatLng(42.145812, -88.058276),
                            new LatLng(42.145819, -88.058258),
                            new LatLng(42.145842, -88.058197),
                            new LatLng(42.145859, -88.058142),
                            new LatLng(42.145896, -88.057969),
                            new LatLng(42.145908, -88.057896),
                            new LatLng(42.145916, -88.057834),
                            new LatLng(42.145920, -88.057725),
                            new LatLng(42.145926, -88.057693),
                            new LatLng(42.145916, -88.057638),
                            new LatLng(42.145899, -88.057591),
                            new LatLng(42.145842, -88.057523),
                            new LatLng(42.145830, -88.057507),
                            new LatLng(42.145801, -88.057474),
                            new LatLng(42.145745, -88.057354),
                            new LatLng(42.145698, -88.057324),
                            new LatLng(42.145645, -88.057304),
                            new LatLng(42.145148, -88.057591)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green)));
            // Set listeners for click events.
            polyline6.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline7 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.140207, -88.067226),
                            new LatLng(42.140441, -88.066485),
                            new LatLng(42.140509, -88.066349),
                            new LatLng(42.140554, -88.066303),
                            new LatLng(42.140643, -88.066236),
                            new LatLng(42.140732, -88.066199),
                            new LatLng(42.140813, -88.066186),
                            new LatLng(42.140893, -88.066199),
                            new LatLng(42.140985, -88.066239),
                            new LatLng(42.141228, -88.066384),
                            new LatLng(42.141295, -88.066403),
                            new LatLng(42.141650, -88.066407),
                            new LatLng(42.141818, -88.066374),
                            new LatLng(42.141976, -88.066296),
                            new LatLng(42.143107, -88.065486),
                            new LatLng(42.143165, -88.065396),
                            new LatLng(42.143221, -88.065277),
                            new LatLng(42.143257, -88.065007),
                            new LatLng(42.143268, -88.064875),
                            new LatLng(42.143251, -88.064645),
                            new LatLng(42.143245, -88.064588),
                            new LatLng(42.143217, -88.064445),
                            new LatLng(42.143187, -88.064256),
                            new LatLng(42.143173, -88.064151),
                            new LatLng(42.143185, -88.063515),
                            new LatLng(42.143113, -88.063053),
                            new LatLng(42.143114, -88.063033),
                            new LatLng(42.143116, -88.063009),
                            new LatLng(42.143122, -88.062988),
                            new LatLng(42.143127, -88.062971),
                            new LatLng(42.143137, -88.062943),
                            new LatLng(42.143147, -88.062896),
                            new LatLng(42.143153, -88.062880),
                            new LatLng(42.143164, -88.062843),
                            new LatLng(42.143170, -88.062825),
                            new LatLng(42.143176, -88.062811),
                            new LatLng(42.143187, -88.062786),
                            new LatLng(42.143198, -88.062767),
                            new LatLng(42.143220, -88.062740),
                            new LatLng(42.143255, -88.062706),
                            new LatLng(42.143326, -88.062652),
                            new LatLng(42.143397, -88.062611),
                            new LatLng(42.143460, -88.062554),
                            new LatLng(42.143526, -88.062491),
                            new LatLng(42.143583, -88.062427),
                            new LatLng(42.143653, -88.062324),
                            new LatLng(42.143705, -88.062227),
                            new LatLng(42.143745, -88.062131),
                            new LatLng(42.143785, -88.062000),
                            new LatLng(42.143819, -88.061868),
                            new LatLng(42.143831, -88.061794),
                            new LatLng(42.143842, -88.061682),
                            new LatLng(42.143847, -88.061580),
                            new LatLng(42.143846, -88.061394),
                            new LatLng(42.143848, -88.061281),
                            new LatLng(42.143853, -88.061216),
                            new LatLng(42.143866, -88.061127),
                            new LatLng(42.143916, -88.060913),
                            new LatLng(42.144279, -88.060130),
                            new LatLng(42.144396, -88.059762),
                            new LatLng(42.144439, -88.059606),
                            new LatLng(42.144527, -88.059414),
                            new LatLng(42.144663, -88.059214),
                            new LatLng(42.144697, -88.059137),
                            new LatLng(42.144732, -88.059056),
                            new LatLng(42.144753, -88.058941),
                            new LatLng(42.144756, -88.058852),
                            new LatLng(42.144743, -88.058775),
                            new LatLng(42.144677, -88.058638),
                            new LatLng(42.144553, -88.058457),
                            new LatLng(42.144495, -88.058400),
                            new LatLng(42.144357, -88.058324),
                            new LatLng(42.144097, -88.058199),
                            new LatLng(42.143984, -88.058122),
                            new LatLng(42.143912, -88.058018),
                            new LatLng(42.143844, -88.057804),
                            new LatLng(42.143817, -88.057412),
                            new LatLng(42.143815, -88.057133),
                            new LatLng(42.143811, -88.056792),
                            new LatLng(42.143807, -88.056668),
                            new LatLng(42.143787, -88.056055),
                            new LatLng(42.143675, -88.055635),
                            new LatLng(42.143551, -88.055354),
                            new LatLng(42.143466, -88.055216),
                            new LatLng(42.143296, -88.055039),
                            new LatLng(42.143155, -88.054922)
                    ));
            polyline7.setColor(Color.BLACK);
            polyline7.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline8 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.142627, -88.054735),
                            new LatLng(42.142589, -88.054802),
                            new LatLng(42.142548, -88.054860),
                            new LatLng(42.142493, -88.054893),
                            new LatLng(42.142435, -88.054916),
                            new LatLng(42.142386, -88.054917),
                            new LatLng(42.142344, -88.054909),
                            new LatLng(42.142226, -88.054837),
                            new LatLng(42.142174, -88.054770),
                            new LatLng(42.142152, -88.054714),
                            new LatLng(42.142139, -88.054690),
                            new LatLng(42.142131, -88.054649),
                            new LatLng(42.142123, -88.054581),
                            new LatLng(42.142119, -88.054517),
                            new LatLng(42.142132, -88.054464)
                    ));

            polyline8.setColor(Color.RED);
            polyline8.setWidth((float) POLYLINE_STROKE_WIDTH_PX);


            Polyline polyline9 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.142931, -88.064331),
                            new LatLng(42.142904, -88.064311),
                            new LatLng(42.142880, -88.064273),
                            new LatLng(42.142875, -88.064232),
                            new LatLng(42.142881, -88.064188),
                            new LatLng(42.142889, -88.064117),
                            new LatLng(42.142885, -88.064087),
                            new LatLng(42.142875, -88.064060),
                            new LatLng(42.142861, -88.064045),
                            new LatLng(42.142831, -88.064023),
                            new LatLng(42.142759, -88.063999),
                            new LatLng(42.142663, -88.063986),
                            new LatLng(42.142546, -88.063982),
                            new LatLng(42.142458, -88.063969),
                            new LatLng(42.142434, -88.063959),
                            new LatLng(42.142418, -88.063947),
                            new LatLng(42.142405, -88.063922),
                            new LatLng(42.142395, -88.063888),
                            new LatLng(42.142394, -88.063811),
                            new LatLng(42.142412, -88.063734),
                            new LatLng(42.142440, -88.063692),
                            new LatLng(42.142492, -88.063593),
                            new LatLng(42.142527, -88.063479),
                            new LatLng(42.142531, -88.063440),
                            new LatLng(42.142527, -88.063402),
                            new LatLng(42.142508, -88.063355),
                            new LatLng(42.142490, -88.063322),
                            new LatLng(42.142454, -88.063271),
                            new LatLng(42.142385, -88.063217),
                            new LatLng(42.142314, -88.063179),
                            new LatLng(42.142278, -88.063133),
                            new LatLng(42.142256, -88.063086),
                            new LatLng(42.142248, -88.063043),
                            new LatLng(42.142244, -88.063004),
                            new LatLng(42.142250, -88.062970)

                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tan)));
            polyline9.setWidth((float) POLYLINE_STROKE_WIDTH_PX);


            Polyline polyline10 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.142251, -88.062970),
                            new LatLng(42.142154, -88.062674),
                            new LatLng(42.142100, -88.062564),
                            new LatLng(42.142060, -88.062493),
                            new LatLng(42.141907, -88.062366),
                            new LatLng(42.141806, -88.062315),
                            new LatLng(42.141778, -88.062272),
                            new LatLng(42.141756, -88.062211),
                            new LatLng(42.141699, -88.062085),
                            new LatLng(42.141683, -88.062025),
                            new LatLng(42.141654, -88.061971),
                            new LatLng(42.141529, -88.061770),
                            new LatLng(42.141412, -88.061503),
                            new LatLng(42.141363, -88.061345),
                            new LatLng(42.141230, -88.061059),
                            new LatLng(42.141086, -88.060786),
                            new LatLng(42.140863, -88.060144),
                            new LatLng(42.140708, -88.059731),
                            new LatLng(42.140665, -88.059651),
                            new LatLng(42.140442, -88.059306),
                            new LatLng(42.140297, -88.059153),
                            new LatLng(42.140120, -88.059022),
                            new LatLng(42.139989, -88.058947),
                            new LatLng(42.139883, -88.058803),
                            new LatLng(42.139817, -88.058688),
                            new LatLng(42.139777, -88.058592),
                            new LatLng(42.139754, -88.058499),
                            new LatLng(42.139779, -88.058322),
                            new LatLng(42.139845, -88.058180),
                            new LatLng(42.139895, -88.058042),
                            new LatLng(42.139955, -88.057824),
                            new LatLng(42.139976, -88.057688),
                            new LatLng(42.140003, -88.057566),
                            new LatLng(42.140009, -88.057447),
                            new LatLng(42.140057, -88.057330),
                            new LatLng(42.140097, -88.057252),
                            new LatLng(42.140112, -88.057166),
                            new LatLng(42.140138, -88.056963),
                            new LatLng(42.140171, -88.056877),
                            new LatLng(42.140220, -88.056808),
                            new LatLng(42.140325, -88.056729)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tan)));
            polyline10.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline11 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.143912, -88.058018),
                            new LatLng(42.143124, -88.058033),
                            new LatLng(42.142926, -88.058087),
                            new LatLng(42.142762, -88.058058),
                            new LatLng(42.142555, -88.058080),
                            new LatLng(42.142377, -88.058084),
                            new LatLng(42.142342, -88.058061),
                            new LatLng(42.142322, -88.058024),
                            new LatLng(42.142304, -88.057963),
                            new LatLng(42.142274, -88.057858),
                            new LatLng(42.142239, -88.057781),
                            new LatLng(42.142193, -88.057735),
                            new LatLng(42.142133, -88.057699),
                            new LatLng(42.142080, -88.057694),
                            new LatLng(42.141994, -88.057696),
                            new LatLng(42.141970, -88.057702),
                            new LatLng(42.141935, -88.057722),
                            new LatLng(42.141919, -88.057739),
                            new LatLng(42.141886, -88.057786),
                            new LatLng(42.141840, -88.057901),
                            new LatLng(42.141824, -88.057934),
                            new LatLng(42.141772, -88.058009),
                            new LatLng(42.141731, -88.058047),
                            new LatLng(42.141558, -88.058084),
                            new LatLng(42.141416, -88.058075),
                            new LatLng(42.141278, -88.058077),
                            new LatLng(42.140583, -88.058032),
                            new LatLng(42.140467, -88.057991),
                            new LatLng(42.140449, -88.057922),
                            new LatLng(42.140446, -88.057801),
                            new LatLng(42.140414, -88.057717),
                            new LatLng(42.140368, -88.057526),
                            new LatLng(42.140295, -88.056230),
                            new LatLng(42.140296, -88.055989),
                            new LatLng(42.140306, -88.055795),
                            new LatLng(42.140288, -88.055642),
                            new LatLng(42.140275, -88.054068),
                            new LatLng(42.140260, -88.053661),
                            new LatLng(42.140271, -88.053332),
                            new LatLng(42.140291, -88.053189),
                            new LatLng(42.140322, -88.053131),
                            new LatLng(42.140396, -88.053088),
                            new LatLng(42.140675, -88.053089),
                            new LatLng(42.140742, -88.053067),
                            new LatLng(42.140763, -88.053039),
                            new LatLng(42.140782, -88.052955),
                            new LatLng(42.140843, -88.052601),
                            new LatLng(42.140865, -88.052374),
                            new LatLng(42.140933, -88.051856),
                            new LatLng(42.141059, -88.051394),
                            new LatLng(42.141231, -88.050973),
                            new LatLng(42.141574, -88.050309),
                            new LatLng(42.141721, -88.050004),
                            new LatLng(42.141959, -88.049320),
                            new LatLng(42.141980, -88.049222),
                            new LatLng(42.141983, -88.049118),
                            new LatLng(42.141971, -88.049018),
                            new LatLng(42.141942, -88.048912),
                            new LatLng(42.141816, -88.048586),
                            new LatLng(42.141645, -88.047784),
                            new LatLng(42.141599, -88.047699),
                            new LatLng(42.141538, -88.047646),
                            new LatLng(42.141472, -88.047571),
                            new LatLng(42.141406, -88.047439),
                            new LatLng(42.141390, -88.047346),
                            new LatLng(42.141364, -88.047296),
                            new LatLng(42.141332, -88.047254),
                            new LatLng(42.141286, -88.047212),
                            new LatLng(42.141135, -88.047132),
                            new LatLng(42.140977, -88.047016),
                            new LatLng(42.140957, -88.046970),
                            new LatLng(42.140923, -88.046915),
                            new LatLng(42.140894, -88.046842),
                            new LatLng(42.140878, -88.046739),
                            new LatLng(42.140872, -88.046656),
                            new LatLng(42.140867, -88.046430),
                            new LatLng(42.140821, -88.045814),
                            new LatLng(42.140743, -88.045406),
                            new LatLng(42.140713, -88.045297),
                            new LatLng(42.140695, -88.045179),
                            new LatLng(42.140695, -88.045081),
                            new LatLng(42.140699, -88.045009),
                            new LatLng(42.140717, -88.044899),
                            new LatLng(42.140782, -88.044636),
                            new LatLng(42.140806, -88.044494),
                            new LatLng(42.140822, -88.044314),
                            new LatLng(42.140818, -88.044149),
                            new LatLng(42.140829, -88.043989),
                            new LatLng(42.140896, -88.043712),
                            new LatLng(42.140863, -88.043504),
                            new LatLng(42.140852, -88.043364),
                            new LatLng(42.140884, -88.043282),
                            new LatLng(42.141013, -88.043113),
                            new LatLng(42.141057, -88.043023),
                            new LatLng(42.141087, -88.042937),
                            new LatLng(42.141090, -88.042762),
                            new LatLng(42.141084, -88.042663),
                            new LatLng(42.141099, -88.042558),
                            new LatLng(42.141124, -88.042480),
                            new LatLng(42.141213, -88.042333),
                            new LatLng(42.141249, -88.042290),
                            new LatLng(42.141300, -88.042238)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.brown)));
            polyline11.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline12 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.143815, -88.057134),
                            new LatLng(42.143675, -88.057120),
                            new LatLng(42.143642, -88.057102),
                            new LatLng(42.143623, -88.057061),
                            new LatLng(42.143611, -88.057022),
                            new LatLng(42.143600, -88.056998),
                            new LatLng(42.143589, -88.056978),
                            new LatLng(42.143559, -88.056945),
                            new LatLng(42.143549, -88.056929),
                            new LatLng(42.143535, -88.056918),
                            new LatLng(42.143507, -88.056905),
                            new LatLng(42.143463, -88.056878),
                            new LatLng(42.143384, -88.056811),
                            new LatLng(42.143364, -88.056801),
                            new LatLng(42.143331, -88.056799),
                            new LatLng(42.143299, -88.056806),
                            new LatLng(42.143284, -88.056812),
                            new LatLng(42.143192, -88.056886),
                            new LatLng(42.143144, -88.056911),
                            new LatLng(42.143076, -88.056912),
                            new LatLng(42.143042, -88.056908),
                            new LatLng(42.143011, -88.056907),
                            new LatLng(42.142986, -88.056911),
                            new LatLng(42.142927, -88.056929),
                            new LatLng(42.142902, -88.056927),
                            new LatLng(42.142832, -88.056959),
                            new LatLng(42.142806, -88.057002),
                            new LatLng(42.142715, -88.057173),
                            new LatLng(42.142671, -88.057233),
                            new LatLng(42.142614, -88.057262),
                            new LatLng(42.142551, -88.057287),
                            new LatLng(42.142503, -88.057315),
                            new LatLng(42.142467, -88.057347),
                            new LatLng(42.142353, -88.057408),
                            new LatLng(42.142290, -88.057392),
                            new LatLng(42.142276, -88.057385),
                            new LatLng(42.142248, -88.057383),
                            new LatLng(42.142221, -88.057393),
                            new LatLng(42.142202, -88.057396),
                            new LatLng(42.142177, -88.057396),
                            new LatLng(42.142134, -88.057377),
                            new LatLng(42.141934, -88.057267),
                            new LatLng(42.141896, -88.057254),
                            new LatLng(42.141859, -88.057260),
                            new LatLng(42.141827, -88.057260),
                            new LatLng(42.141784, -88.057289),
                            new LatLng(42.141766, -88.057375),
                            new LatLng(42.141748, -88.057419),
                            new LatLng(42.141723, -88.057459),
                            new LatLng(42.141705, -88.057523),
                            new LatLng(42.141697, -88.057578),
                            new LatLng(42.141675, -88.057625),
                            new LatLng(42.141652, -88.057653),
                            new LatLng(42.141624, -88.057679),
                            new LatLng(42.141593, -88.057688),
                            new LatLng(42.141581, -88.057701),
                            new LatLng(42.141575, -88.057741),
                            new LatLng(42.141578, -88.057777),
                            new LatLng(42.141576, -88.057795),
                            new LatLng(42.141567, -88.057814),
                            new LatLng(42.141558, -88.057831),
                            new LatLng(42.141467, -88.057962),
                            new LatLng(42.141448, -88.058009),
                            new LatLng(42.141439, -88.058045),
                            new LatLng(42.141417, -88.058076)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.brown)));
            polyline12.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline13 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.141778, -88.057309),
                            new LatLng(42.141702, -88.057179),
                            new LatLng(42.141691, -88.057166),
                            new LatLng(42.141679, -88.057159),
                            new LatLng(42.141546, -88.057182),
                            new LatLng(42.141519, -88.057193),
                            new LatLng(42.141442, -88.057277),
                            new LatLng(42.141422, -88.057351),
                            new LatLng(42.141427, -88.057490),
                            new LatLng(42.141424, -88.057517),
                            new LatLng(42.141410, -88.057536),
                            new LatLng(42.141388, -88.057557),
                            new LatLng(42.141361, -88.057572),
                            new LatLng(42.141344, -88.057569),
                            new LatLng(42.141327, -88.057562),
                            new LatLng(42.141194, -88.057446),
                            new LatLng(42.141162, -88.057432),
                            new LatLng(42.141118, -88.057425),
                            new LatLng(42.140807, -88.057455),
                            new LatLng(42.140771, -88.057440),
                            new LatLng(42.140755, -88.057421),
                            new LatLng(42.140735, -88.057399),
                            new LatLng(42.140706, -88.057342),
                            new LatLng(42.140604, -88.057011),
                            new LatLng(42.140600, -88.056950),
                            new LatLng(42.140623, -88.056846),
                            new LatLng(42.140764, -88.056523),
                            new LatLng(42.140778, -88.056450),
                            new LatLng(42.140784, -88.056271),
                            new LatLng(42.140782, -88.056184),
                            new LatLng(42.140768, -88.056098),
                            new LatLng(42.140744, -88.056017),
                            new LatLng(42.140605, -88.055758),
                            new LatLng(42.140592, -88.055712),
                            new LatLng(42.140584, -88.055643),
                            new LatLng(42.140589, -88.055588),
                            new LatLng(42.140630, -88.055451),
                            new LatLng(42.140636, -88.055385),
                            new LatLng(42.140643, -88.055270),
                            new LatLng(42.140633, -88.054969),
                            new LatLng(42.140568, -88.054761),
                            new LatLng(42.140456, -88.054644),
                            new LatLng(42.140273, -88.054548)
                    ));
            polyline13.setColor(Color.RED);
            polyline13.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline14 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.141294, -88.041955),
                            new LatLng(42.141325, -88.041939),
                            new LatLng(42.141350, -88.041904),
                            new LatLng(42.141373, -88.041843),
                            new LatLng(42.141376, -88.041798),
                            new LatLng(42.141370, -88.041726),
                            new LatLng(42.141352, -88.041657),
                            new LatLng(42.141325, -88.041556),
                            new LatLng(42.141304, -88.041500),
                            new LatLng(42.141227, -88.041239),
                            new LatLng(42.141218, -88.041185),
                            new LatLng(42.141223, -88.041142),
                            new LatLng(42.141244, -88.041082),
                            new LatLng(42.141525, -88.040639),
                            new LatLng(42.141785, -88.040361),
                            new LatLng(42.141952, -88.040229),
                            new LatLng(42.142063, -88.040185),
                            new LatLng(42.142178, -88.040160),
                            new LatLng(42.142533, -88.040154),
                            new LatLng(42.142717, -88.040096),
                            new LatLng(42.143054, -88.039840),
                            new LatLng(42.143170, -88.039783),
                            new LatLng(42.143322, -88.039733),
                            new LatLng(42.143453, -88.039721),
                            new LatLng(42.143547, -88.039732),
                            new LatLng(42.143856, -88.039790),
                            new LatLng(42.144728, -88.040092),
                            new LatLng(42.144964, -88.040136),
                            new LatLng(42.145062, -88.040169),
                            new LatLng(42.145100, -88.040193),
                            new LatLng(42.145129, -88.040250),
                            new LatLng(42.145163, -88.040362),
                            new LatLng(42.145196, -88.040571),
                            new LatLng(42.145194, -88.040701),
                            new LatLng(42.145180, -88.041059),
                            new LatLng(42.145174, -88.041395),
                            new LatLng(42.145174, -88.041637),
                            new LatLng(42.145216, -88.042110),
                            new LatLng(42.145231, -88.042209),
                            new LatLng(42.145342, -88.043219),
                            new LatLng(42.145371, -88.043369),
                            new LatLng(42.145412, -88.043464),
                            new LatLng(42.145512, -88.043625),
                            new LatLng(42.145667, -88.043791),
                            new LatLng(42.145779, -88.043863),
                            new LatLng(42.146358, -88.044085),
                            new LatLng(42.146677, -88.044144),
                            new LatLng(42.146874, -88.044230),
                            new LatLng(42.146959, -88.044337),
                            new LatLng(42.146998, -88.044490),
                            new LatLng(42.146994, -88.044634),
                            new LatLng(42.146812, -88.045459),
                            new LatLng(42.146725, -88.045703),
                            new LatLng(42.146638, -88.045867),
                            new LatLng(42.146533, -88.046030),
                            new LatLng(42.146522, -88.046081),
                            new LatLng(42.146528, -88.046123),
                            new LatLng(42.146528, -88.046151),
                            new LatLng(42.146585, -88.046704),
                            new LatLng(42.146615, -88.046986),
                            new LatLng(42.146663, -88.047214),
                            new LatLng(42.146695, -88.047431),
                            new LatLng(42.146688, -88.047527),
                            new LatLng(42.146657, -88.047660),
                            new LatLng(42.146398, -88.048040),
                            new LatLng(42.146327, -88.048200),
                            new LatLng(42.146263, -88.048291),
                            new LatLng(42.146206, -88.048341),
                            new LatLng(42.146169, -88.048413),
                            new LatLng(42.146112, -88.048490),
                            new LatLng(42.145828, -88.048669),
                            new LatLng(42.145750, -88.048802),
                            new LatLng(42.145730, -88.048889),
                            new LatLng(42.145653, -88.049435),
                            new LatLng(42.145609, -88.050419),
                            new LatLng(42.145622, -88.050777),
                            new LatLng(42.145577, -88.050928),
                            new LatLng(42.145536, -88.051217),
                            new LatLng(42.145442, -88.051546),
                            new LatLng(42.145241, -88.051957),
                            new LatLng(42.145240, -88.052009),
                            new LatLng(42.145260, -88.052097),
                            new LatLng(42.145269, -88.052196),
                            new LatLng(42.145275, -88.052376),
                            new LatLng(42.145300, -88.052563),
                            new LatLng(42.145336, -88.052733),
                            new LatLng(42.145422, -88.052889),
                            new LatLng(42.145481, -88.053013),
                            new LatLng(42.145481, -88.053142),
                            new LatLng(42.145465, -88.053238),
                            new LatLng(42.145453, -88.053266),
                            new LatLng(42.145459, -88.053359),
                            new LatLng(42.145400, -88.053650),
                            new LatLng(42.144952, -88.054513),
                            new LatLng(42.144922, -88.054644),
                            new LatLng(42.144912, -88.054848),
                            new LatLng(42.144924, -88.054948),
                            new LatLng(42.144990, -88.055143),
                            new LatLng(42.145095, -88.055402),
                            new LatLng(42.145177, -88.055547),
                            new LatLng(42.145200, -88.055667),
                            new LatLng(42.145206, -88.055936),
                            new LatLng(42.145197, -88.056032),
                            new LatLng(42.145238, -88.056914),
                            new LatLng(42.145252, -88.057075),
                            new LatLng(42.145248, -88.057218),
                            new LatLng(42.145236, -88.057345),
                            new LatLng(42.145212, -88.057435),
                            new LatLng(42.145120, -88.057661),
                            new LatLng(42.145049, -88.057771),
                            new LatLng(42.145017, -88.057798),
                            new LatLng(42.144900, -88.057808),
                            new LatLng(42.144852, -88.057839),
                            new LatLng(42.144730, -88.057938),
                            new LatLng(42.144695, -88.057989),
                            new LatLng(42.144662, -88.058018),
                            new LatLng(42.144628, -88.058034),
                            new LatLng(42.144588, -88.058008),
                            new LatLng(42.144565, -88.057997),
                            new LatLng(42.144527, -88.057996),
                            new LatLng(42.144492, -88.058018),
                            new LatLng(42.144441, -88.058078),
                            new LatLng(42.144390, -88.058114),
                            new LatLng(42.144351, -88.058125),
                            new LatLng(42.144310, -88.058131),
                            new LatLng(42.144248, -88.058126),
                            new LatLng(42.144196, -88.058110),
                            new LatLng(42.144167, -88.058069),
                            new LatLng(42.144155, -88.058042),
                            new LatLng(42.144133, -88.058006),
                            new LatLng(42.144117, -88.057968),
                            new LatLng(42.144103, -88.057929),
                            new LatLng(42.144050, -88.057868),
                            new LatLng(42.143985, -88.057831),
                            new LatLng(42.143843, -88.057786)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.brown)));
            polyline14.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline15 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146642, -88.082385),
                            new LatLng(42.146653, -88.082129),
                            new LatLng(42.146662, -88.081889),
                            new LatLng(42.146677, -88.081503),
                            new LatLng(42.146647, -88.081233),
                            new LatLng(42.146606, -88.081130),
                            new LatLng(42.146561, -88.080956),
                            new LatLng(42.146553, -88.080660),
                            new LatLng(42.146555, -88.080340),
                            new LatLng(42.146570, -88.079851),
                            new LatLng(42.146590, -88.079728)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green)));
            // Set listeners for click events.
            polyline15.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline16 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146662, -88.081889),
                            new LatLng(42.146217, -88.081955)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green)));
            // Set listeners for click events.
            polyline16.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline17 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146745, -88.092595),
                            new LatLng(42.146582, -88.092454),
                            new LatLng(42.146432, -88.092354),
                            new LatLng(42.146268, -88.092271),
                            new LatLng(42.146092, -88.092251),
                            new LatLng(42.146092, -88.092251),
                            new LatLng(42.145696, -88.092226),
                            new LatLng(42.145575, -88.092161),
                            new LatLng(42.145425, -88.092054)
                    ));
            // Set listeners for click events.
            polyline17.setColor(Color.BLACK);
            polyline17.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline18 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.135114, -88.092979),
                            new LatLng(42.135253, -88.092922),
                            new LatLng(42.135370, -88.092841),
                            new LatLng(42.135468, -88.092754),
                            new LatLng(42.135594, -88.092606),
                            new LatLng(42.135718, -88.092528),
                            new LatLng(42.136055, -88.092414),
                            new LatLng(42.136304, -88.092430),
                            new LatLng(42.136610, -88.092441),
                            new LatLng(42.137118, -88.092532),
                            new LatLng(42.137332, -88.092482),
                            new LatLng(42.137676, -88.092345),
                            new LatLng(42.137936, -88.092137),
                            new LatLng(42.138085, -88.091928),
                            new LatLng(42.138190, -88.091658),
                            new LatLng(42.138255, -88.091427),
                            new LatLng(42.138271, -88.091145),
                            new LatLng(42.138253, -88.090927),
                            new LatLng(42.138103, -88.090233),
                            new LatLng(42.138125, -88.090163),
                            new LatLng(42.138163, -88.089991),
                            new LatLng(42.138295, -88.089569),
                            new LatLng(42.138336, -88.089509),
                            new LatLng(42.138394, -88.089462),
                            new LatLng(42.138494, -88.089430),
                            new LatLng(42.138651, -88.089390),
                            new LatLng(42.138807, -88.089281),
                            new LatLng(42.138930, -88.089204),
                            new LatLng(42.139908, -88.088419),
                            new LatLng(42.140141, -88.088200),
                            new LatLng(42.140310, -88.087906),
                            new LatLng(42.140466, -88.087594),
                            new LatLng(42.140630, -88.087392),
                            new LatLng(42.140858, -88.087069),
                            new LatLng(42.141032, -88.086935),
                            new LatLng(42.141110, -88.086799),
                            new LatLng(42.141159, -88.086648),
                            new LatLng(42.141175, -88.086428),
                            new LatLng(42.141185, -88.086106),
                            new LatLng(42.141228, -88.085945),
                            new LatLng(42.141276, -88.085841),
                            new LatLng(42.141290, -88.085722),
                            new LatLng(42.141286, -88.085511),
                            new LatLng(42.141239, -88.085338),
                            new LatLng(42.141173, -88.085013),
                            new LatLng(42.141080, -88.084497),
                            new LatLng(42.140995, -88.084159),
                            new LatLng(42.140926, -88.083921),
                            new LatLng(42.140919, -88.083719),
                            new LatLng(42.140928, -88.083560),
                            new LatLng(42.140974, -88.083393),
                            new LatLng(42.141113, -88.083222),
                            new LatLng(42.141253, -88.083163),
                            new LatLng(42.141675, -88.083157),
                            new LatLng(42.142006, -88.083034),
                            new LatLng(42.142414, -88.082774),
                            new LatLng(42.142515, -88.082651),
                            new LatLng(42.142560, -88.082471),
                            new LatLng(42.142579, -88.082254),
                            new LatLng(42.142521, -88.081776),
                            new LatLng(42.142522, -88.081628),
                            new LatLng(42.142529, -88.081372),
                            new LatLng(42.142519, -88.081046),
                            new LatLng(42.142520, -88.080778),
                            new LatLng(42.142560, -88.080517),
                            new LatLng(42.142695, -88.080183),
                            new LatLng(42.142853, -88.079583),
                            new LatLng(42.142914, -88.079230),
                            new LatLng(42.142943, -88.078962),
                            new LatLng(42.142976, -88.078809),
                            new LatLng(42.143071, -88.078645),
                            new LatLng(42.143308, -88.078433),
                            new LatLng(42.144644, -88.077659),
                            new LatLng(42.144863, -88.077562),
                            new LatLng(42.145694, -88.077509),
                            new LatLng(42.145933, -88.077521),
                            new LatLng(42.146212, -88.077494),
                            new LatLng(42.146327, -88.077442),
                            new LatLng(42.146419, -88.077325),
                            new LatLng(42.146820, -88.076565),
                            new LatLng(42.146959, -88.076225),
                            new LatLng(42.147094, -88.075944),
                            new LatLng(42.147351, -88.075224),
                            new LatLng(42.147538, -88.074473),
                            new LatLng(42.147581, -88.074228),
                            new LatLng(42.147589, -88.074086)
                    )
                    .color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.orange)));
            // Set listeners for click events.
            polyline18.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline19 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.141104, -88.083233),
                            new LatLng(42.141009, -88.083102),
                            new LatLng(42.140821, -88.082722),
                            new LatLng(42.140745, -88.082542),
                            new LatLng(42.140675, -88.082376),
                            new LatLng(42.140557, -88.082035),
                            new LatLng(42.140319, -88.081334),
                            new LatLng(42.140303, -88.081247),
                            new LatLng(42.140235, -88.080874),
                            new LatLng(42.140232, -88.080825),
                            new LatLng(42.140208, -88.080475),
                            new LatLng(42.140203, -88.080105),
                            new LatLng(42.140210, -88.079883),
                            new LatLng(42.140224, -88.079701),
                            new LatLng(42.140313, -88.079380),
                            new LatLng(42.140516, -88.079006),
                            new LatLng(42.140570, -88.078832),
                            new LatLng(42.140594, -88.078697),
                            new LatLng(42.140625, -88.078369),
                            new LatLng(42.140633, -88.078092),
                            new LatLng(42.140621, -88.077793)
                    ));
            // Set listeners for click events.
            polyline19.setColor(Color.BLACK);
            polyline19.setWidth((float) POLYLINE_STROKE_WIDTH_PX);


            Polyline polyline20 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.136096, -88.079328),
                            new LatLng(42.136223, -88.079042),
                            new LatLng(42.136511, -88.078604),
                            new LatLng(42.136645, -88.078328),
                            new LatLng(42.136820, -88.078036),
                            new LatLng(42.137005, -88.077892),
                            new LatLng(42.137254, -88.077856),
                            new LatLng(42.137509, -88.077875),
                            new LatLng(42.137549, -88.077916),
                            new LatLng(42.137654, -88.078056),
                            new LatLng(42.137736, -88.078071),
                            new LatLng(42.137824, -88.078042),
                            new LatLng(42.137945, -88.077947),
                            new LatLng(42.138139, -88.077837),
                            new LatLng(42.138428, -88.078052),
                            new LatLng(42.138526, -88.078262),
                            new LatLng(42.138540, -88.078685),
                            new LatLng(42.138651, -88.078791),
                            new LatLng(42.139027, -88.078578),
                            new LatLng(42.139509, -88.078384),
                            new LatLng(42.139745, -88.078234),
                            new LatLng(42.139853, -88.078151),
                            new LatLng(42.140417, -88.077834),
                            new LatLng(42.140621, -88.077794),
                            new LatLng(42.140721, -88.077608),
                            new LatLng(42.140812, -88.077363),
                            new LatLng(42.140896, -88.076867),
                            new LatLng(42.140918, -88.076591),
                            new LatLng(42.140902, -88.076207),
                            new LatLng(42.140851, -88.075889),
                            new LatLng(42.140825, -88.075511),
                            new LatLng(42.140813, -88.075027),
                            new LatLng(42.140799, -88.074718),
                            new LatLng(42.140830, -88.074562),
                            new LatLng(42.140798, -88.074428),
                            new LatLng(42.140715, -88.074253),
                            new LatLng(42.140548, -88.073564),
                            new LatLng(42.140410, -88.073206),
                            new LatLng(42.140298, -88.073005),
                            new LatLng(42.140246, -88.072879),
                            new LatLng(42.140224, -88.072607),
                            new LatLng(42.140223, -88.072401),
                            new LatLng(42.140246, -88.072268),
                            new LatLng(42.140327, -88.072114),
                            new LatLng(42.140377, -88.071963),
                            new LatLng(42.140400, -88.071811),
                            new LatLng(42.140394, -88.071441),
                            new LatLng(42.140384, -88.071337),
                            new LatLng(42.140389, -88.071228),
                            new LatLng(42.140405, -88.071104),
                            new LatLng(42.140433, -88.071053),
                            new LatLng(42.140498, -88.071023),
                            new LatLng(42.140593, -88.070994),
                            new LatLng(42.140682, -88.070986),
                            new LatLng(42.140765, -88.070923),
                            new LatLng(42.141004, -88.070695),
                            new LatLng(42.141100, -88.070556),
                            new LatLng(42.141122, -88.070363),
                            new LatLng(42.141088, -88.070247),
                            new LatLng(42.140977, -88.070025),
                            new LatLng(42.140794, -88.069742),
                            new LatLng(42.140642, -88.069538),
                            new LatLng(42.140405, -88.069105),
                            new LatLng(42.140325, -88.068836),
                            new LatLng(42.140289, -88.068634),
                            new LatLng(42.140311, -88.068479),
                            new LatLng(42.140364, -88.068351),
                            new LatLng(42.140451, -88.068236),
                            new LatLng(42.140497, -88.068078),
                            new LatLng(42.140494, -88.067851),
                            new LatLng(42.140470, -88.067767),
                            new LatLng(42.140466, -88.067641),
                            new LatLng(42.140591, -88.067463),
                            new LatLng(42.140614, -88.067387),
                            new LatLng(42.140593, -88.067274),
                            new LatLng(42.140549, -88.067101),
                            new LatLng(42.140448, -88.066859),
                            new LatLng(42.140376, -88.066696),
                            new LatLng(42.140356, -88.066504),
                            new LatLng(42.140373, -88.066415),
                            new LatLng(42.140419, -88.066314),
                            new LatLng(42.140507, -88.066204),
                            new LatLng(42.140611, -88.066128),
                            new LatLng(42.140709, -88.066076),
                            new LatLng(42.140799, -88.066062),
                            new LatLng(42.140913, -88.066091),
                            new LatLng(42.140996, -88.066136),
                            new LatLng(42.141464, -88.066308),
                            new LatLng(42.141744, -88.066290),
                            new LatLng(42.141942, -88.066217),
                            new LatLng(42.142242, -88.065978),
                            new LatLng(42.142540, -88.065832),
                            new LatLng(42.142635, -88.065769),
                            new LatLng(42.142736, -88.065614),
                            new LatLng(42.142793, -88.065561),
                            new LatLng(42.142862, -88.065484),
                            new LatLng(42.142887, -88.065385),
                            new LatLng(42.142914, -88.065169),
                            new LatLng(42.142926, -88.064706),
                            new LatLng(42.142907, -88.064438),
                            new LatLng(42.142923, -88.064349),
                            new LatLng(42.142943, -88.064308),
                            new LatLng(42.142972, -88.064279),
                            new LatLng(42.143183, -88.064234),
                            new LatLng(42.143266, -88.064273),
                            new LatLng(42.143350, -88.064358),
                            new LatLng(42.143475, -88.064462),
                            new LatLng(42.143551, -88.064508),
                            new LatLng(42.143589, -88.064525),
                            new LatLng(42.143622, -88.064530),
                            new LatLng(42.143950, -88.064394),
                            new LatLng(42.144045, -88.064370),
                            new LatLng(42.144423, -88.064384),
                            new LatLng(42.144673, -88.064401),
                            new LatLng(42.144924, -88.064431),
                            new LatLng(42.144958, -88.064443),
                            new LatLng(42.145493, -88.064755),
                            new LatLng(42.146550, -88.065609),
                            new LatLng(42.146845, -88.065798),
                            new LatLng(42.147105, -88.065850),
                            new LatLng(42.147343, -88.065946),
                            new LatLng(42.147649, -88.066188),
                            new LatLng(42.147860, -88.066286),
                            new LatLng(42.148031, -88.066356),
                            new LatLng(42.148218, -88.066395),
                            new LatLng(42.148393, -88.066377),
                            new LatLng(42.148608, -88.066274),
                            new LatLng(42.148788, -88.066091),
                            new LatLng(42.148879, -88.066072),
                            new LatLng(42.149013, -88.066111),
                            new LatLng(42.149254, -88.066223),
                            new LatLng(42.149524, -88.066369),
                            new LatLng(42.149682, -88.066496),
                            new LatLng(42.149863, -88.066811),
                            new LatLng(42.149985, -88.066934),
                            new LatLng(42.150335, -88.067019),
                            new LatLng(42.150593, -88.067144),
                            new LatLng(42.150798, -88.067284),
                            new LatLng(42.150889, -88.067427),
                            new LatLng(42.150945, -88.067705),
                            new LatLng(42.150954, -88.067932),
                            new LatLng(42.150984, -88.068033),
                            new LatLng(42.151088, -88.068113),
                            new LatLng(42.151428, -88.068478),
                            new LatLng(42.151644, -88.068633),
                            new LatLng(42.151836, -88.068716),
                            new LatLng(42.152002, -88.068721),
                            new LatLng(42.152298, -88.068638),
                            new LatLng(42.152500, -88.068595),
                            new LatLng(42.152661, -88.068626),
                            new LatLng(42.152956, -88.068791),
                            new LatLng(42.153309, -88.069205),
                            new LatLng(42.153408, -88.069386),
                            new LatLng(42.153411, -88.069524),
                            new LatLng(42.153344, -88.069666),
                            new LatLng(42.153224, -88.069862),
                            new LatLng(42.153143, -88.069967),
                            new LatLng(42.153095, -88.070090),
                            new LatLng(42.153043, -88.070242),
                            new LatLng(42.152857, -88.070408),
                            new LatLng(42.152821, -88.070511),
                            new LatLng(42.152821, -88.070660),
                            new LatLng(42.152852, -88.070812),
                            new LatLng(42.152825, -88.071018),
                            new LatLng(42.152799, -88.071344),
                            new LatLng(42.152760, -88.071424),
                            new LatLng(42.152817, -88.071647),
                            new LatLng(42.152870, -88.072069),
                            new LatLng(42.152908, -88.072252),
                            new LatLng(42.152853, -88.072361),
                            new LatLng(42.152820, -88.072465),
                            new LatLng(42.152825, -88.072620),
                            new LatLng(42.152867, -88.072805),
                            new LatLng(42.152899, -88.072921),
                            new LatLng(42.152809, -88.073082),
                            new LatLng(42.152688, -88.073213),
                            new LatLng(42.152543, -88.073411),
                            new LatLng(42.152398, -88.073635),
                            new LatLng(42.152277, -88.073764),
                            new LatLng(42.152166, -88.073875),
                            new LatLng(42.151998, -88.074110),
                            new LatLng(42.151792, -88.074443),
                            new LatLng(42.151731, -88.074654),
                            new LatLng(42.151626, -88.074918),
                            new LatLng(42.151492, -88.075049),
                            new LatLng(42.151455, -88.075085),
                            new LatLng(42.151058, -88.075396),
                            new LatLng(42.150990, -88.075472),
                            new LatLng(42.150911, -88.075560),
                            new LatLng(42.150852, -88.075699),
                            new LatLng(42.150740, -88.075807),
                            new LatLng(42.150604, -88.075957),
                            new LatLng(42.150482, -88.076236),
                            new LatLng(42.150352, -88.076324),
                            new LatLng(42.150205, -88.076443),
                            new LatLng(42.150103, -88.076443),
                            new LatLng(42.149926, -88.076374),
                            new LatLng(42.149823, -88.076300),
                            new LatLng(42.149722, -88.076266),
                            new LatLng(42.149631, -88.076317),
                            new LatLng(42.149575, -88.076378),
                            new LatLng(42.149527, -88.076409),
                            new LatLng(42.149489, -88.076433),
                            new LatLng(42.149441, -88.076507),
                            new LatLng(42.149400, -88.076635),
                            new LatLng(42.149357, -88.076838),
                            new LatLng(42.149290, -88.076976),
                            new LatLng(42.149158, -88.077126),
                            new LatLng(42.149028, -88.077185),
                            new LatLng(42.148935, -88.077220),
                            new LatLng(42.148773, -88.077321),
                            new LatLng(42.148660, -88.077457),
                            new LatLng(42.148635, -88.077534),
                            new LatLng(42.148833, -88.078003),
                            new LatLng(42.148858, -88.078275),
                            new LatLng(42.148857, -88.078493),
                            new LatLng(42.148822, -88.078595),
                            new LatLng(42.148749, -88.078646),
                            new LatLng(42.148627, -88.078685),
                            new LatLng(42.148486, -88.078768),
                            new LatLng(42.148212, -88.078897),
                            new LatLng(42.148030, -88.078940),
                            new LatLng(42.147910, -88.078937),
                            new LatLng(42.147796, -88.078898),
                            new LatLng(42.147758, -88.078813),
                            new LatLng(42.147734, -88.078744),
                            new LatLng(42.147679, -88.078725),
                            new LatLng(42.147594, -88.078730),
                            new LatLng(42.147463, -88.078788),
                            new LatLng(42.147310, -88.078845),
                            new LatLng(42.147162, -88.078987),
                            new LatLng(42.147059, -88.079063),
                            new LatLng(42.146860, -88.079145),
                            new LatLng(42.146783, -88.079195),
                            new LatLng(42.146677, -88.079385),
                            new LatLng(42.146608, -88.079613),
                            new LatLng(42.146590, -88.079729),
                            new LatLng(42.146157, -88.080239),
                            new LatLng(42.146076, -88.080396),
                            new LatLng(42.146058, -88.080556),
                            new LatLng(42.146047, -88.080802),
                            new LatLng(42.146055, -88.081114),
                            new LatLng(42.146092, -88.081347),
                            new LatLng(42.146195, -88.081795),
                            new LatLng(42.146216, -88.081948),
                            new LatLng(42.146202, -88.082154),
                            new LatLng(42.146183, -88.082318),
                            new LatLng(42.146162, -88.082466),
                            new LatLng(42.146149, -88.082621),
                            new LatLng(42.146151, -88.082771),
                            new LatLng(42.146165, -88.082925),
                            new LatLng(42.146169, -88.083076),
                            new LatLng(42.146145, -88.083299),
                            new LatLng(42.146051, -88.083893),
                            new LatLng(42.146007, -88.084372),
                            new LatLng(42.146007, -88.084770),
                            new LatLng(42.146018, -88.085065),
                            new LatLng(42.146062, -88.085484),
                            new LatLng(42.146093, -88.086408),
                            new LatLng(42.146090, -88.086662),
                            new LatLng(42.146085, -88.087193),
                            new LatLng(42.146024, -88.088055),
                            new LatLng(42.145962, -88.088850),
                            new LatLng(42.146002, -88.089660),
                            new LatLng(42.145991, -88.090388),
                            new LatLng(42.145881, -88.091155),
                            new LatLng(42.145763, -88.091540),
                            new LatLng(42.145615, -88.091813),
                            new LatLng(42.145425, -88.092054),
                            new LatLng(42.144950, -88.092259),
                            new LatLng(42.144798, -88.092308),
                            new LatLng(42.144591, -88.092315),
                            new LatLng(42.144275, -88.092284),
                            new LatLng(42.143618, -88.092272),
                            new LatLng(42.143441, -88.092291),
                            new LatLng(42.143191, -88.092380),
                            new LatLng(42.142826, -88.092586),
                            new LatLng(42.142352, -88.093069),
                            new LatLng(42.142250, -88.093129),
                            new LatLng(42.142140, -88.093166),
                            new LatLng(42.141251, -88.093341),
                            new LatLng(42.141092, -88.093402),
                            new LatLng(42.140905, -88.093532),
                            new LatLng(42.140201, -88.094219),
                            new LatLng(42.140064, -88.094403),
                            new LatLng(42.139757, -88.094876),
                            new LatLng(42.139657, -88.094986),
                            new LatLng(42.139570, -88.095056),
                            new LatLng(42.139444, -88.095122),
                            new LatLng(42.139228, -88.095181),
                            new LatLng(42.139076, -88.095203),
                            new LatLng(42.138830, -88.095347),
                            new LatLng(42.138523, -88.095577),
                            new LatLng(42.137617, -88.096466),
                            new LatLng(42.137506, -88.096315),
                            new LatLng(42.137187, -88.095922),
                            new LatLng(42.136864, -88.095561),
                            new LatLng(42.136338, -88.095118),
                            new LatLng(42.135928, -88.094675),
                            new LatLng(42.135692, -88.094311),
                            new LatLng(42.135518, -88.093872),
                            new LatLng(42.135114, -88.092979),
                            new LatLng(42.135039, -88.093009),
                            new LatLng(42.134833, -88.092972),
                            new LatLng(42.134387, -88.092689),
                            new LatLng(42.134242, -88.092483),
                            new LatLng(42.134196, -88.092305),
                            new LatLng(42.134185, -88.092101),
                            new LatLng(42.134173, -88.091932),
                            new LatLng(42.134182, -88.091784),
                            new LatLng(42.134227, -88.091561),
                            new LatLng(42.134303, -88.090767),
                            new LatLng(42.134311, -88.090612),
                            new LatLng(42.134314, -88.090561),
                            new LatLng(42.134342, -88.090245),
                            new LatLng(42.134413, -88.090217),
                            new LatLng(42.134453, -88.090178),
                            new LatLng(42.134485, -88.090138),
                            new LatLng(42.134544, -88.090071),
                            new LatLng(42.134563, -88.090025),
                            new LatLng(42.134579, -88.089938),
                            new LatLng(42.134563, -88.089831),
                            new LatLng(42.134565, -88.089767),
                            new LatLng(42.134566, -88.089675),
                            new LatLng(42.134569, -88.089638),
                            new LatLng(42.134581, -88.089592),
                            new LatLng(42.134610, -88.089545),
                            new LatLng(42.134631, -88.089515),
                            new LatLng(42.134735, -88.089401),
                            new LatLng(42.134826, -88.089370),
                            new LatLng(42.134970, -88.089281),
                            new LatLng(42.135067, -88.089196),
                            new LatLng(42.135140, -88.089079),
                            new LatLng(42.135177, -88.088953),
                            new LatLng(42.135185, -88.088865),
                            new LatLng(42.135171, -88.088769),
                            new LatLng(42.134915, -88.088249),
                            new LatLng(42.134901, -88.088176),
                            new LatLng(42.134902, -88.088060),
                            new LatLng(42.134872, -88.087968),
                            new LatLng(42.134845, -88.087834),
                            new LatLng(42.134858, -88.087744),
                            new LatLng(42.134921, -88.087632),
                            new LatLng(42.134954, -88.087584),
                            new LatLng(42.134969, -88.087557),
                            new LatLng(42.134996, -88.087537),
                            new LatLng(42.135015, -88.087531),
                            new LatLng(42.135038, -88.087537),
                            new LatLng(42.135081, -88.087544),
                            new LatLng(42.135124, -88.087554),
                            new LatLng(42.135167, -88.087546),
                            new LatLng(42.135196, -88.087537),
                            new LatLng(42.135245, -88.087505),
                            new LatLng(42.135273, -88.087465),
                            new LatLng(42.135303, -88.087423),
                            new LatLng(42.135320, -88.087371),
                            new LatLng(42.135330, -88.087310),
                            new LatLng(42.135365, -88.087228),
                            new LatLng(42.135384, -88.087174),
                            new LatLng(42.135406, -88.087088),
                            new LatLng(42.135413, -88.086968),
                            new LatLng(42.135406, -88.086927),
                            new LatLng(42.135360, -88.086780),
                            new LatLng(42.135346, -88.086724),
                            new LatLng(42.135223, -88.086387),
                            new LatLng(42.135219, -88.086367),
                            new LatLng(42.135219, -88.086338),
                            new LatLng(42.135225, -88.086292),
                            new LatLng(42.135219, -88.086167),
                            new LatLng(42.135209, -88.086123),
                            new LatLng(42.135198, -88.086085),
                            new LatLng(42.135193, -88.086051),
                            new LatLng(42.135202, -88.086011),
                            new LatLng(42.135217, -88.085991),
                            new LatLng(42.135247, -88.085972),
                            new LatLng(42.135366, -88.085956),
                            new LatLng(42.135427, -88.085979),
                            new LatLng(42.135489, -88.085987),
                            new LatLng(42.135560, -88.086006),
                            new LatLng(42.135609, -88.086026),
                            new LatLng(42.135757, -88.086049),
                            new LatLng(42.135851, -88.086009),
                            new LatLng(42.135878, -88.085980),
                            new LatLng(42.135893, -88.085943),
                            new LatLng(42.135925, -88.085843),
                            new LatLng(42.135972, -88.085774),
                            new LatLng(42.136004, -88.085740),
                            new LatLng(42.136014, -88.085715),
                            new LatLng(42.136032, -88.085611),
                            new LatLng(42.136033, -88.085558),
                            new LatLng(42.136040, -88.085460),
                            new LatLng(42.136056, -88.085388),
                            new LatLng(42.136073, -88.085337),
                            new LatLng(42.136088, -88.085190),
                            new LatLng(42.136068, -88.085010),
                            new LatLng(42.136043, -88.084883),
                            new LatLng(42.135997, -88.084700),
                            new LatLng(42.135992, -88.084574),
                            new LatLng(42.135914, -88.084257),
                            new LatLng(42.135882, -88.084041),
                            new LatLng(42.135919, -88.084021),
                            new LatLng(42.135952, -88.083957),
                            new LatLng(42.135961, -88.083840),
                            new LatLng(42.135960, -88.083362),
                            new LatLng(42.135992, -88.083218),
                            new LatLng(42.136022, -88.082898),
                            new LatLng(42.135926, -88.082737),
                            new LatLng(42.135840, -88.082637),
                            new LatLng(42.135720, -88.082414),
                            new LatLng(42.135613, -88.082006),
                            new LatLng(42.135598, -88.081707),
                            new LatLng(42.135658, -88.081340),
                            new LatLng(42.135720, -88.080876),
                            new LatLng(42.135765, -88.080623),
                            new LatLng(42.135835, -88.080357),
                            new LatLng(42.135906, -88.079956),
                            new LatLng(42.136055, -88.079422),
                            new LatLng(42.136096, -88.079328)
                    ));
            // Set listeners for click events.
            polyline20.setColor(Color.YELLOW);
            polyline20.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline21 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.148635, -88.077534),
                            new LatLng(42.148429, -88.077212),
                            new LatLng(42.148295, -88.077092),
                            new LatLng(42.148206, -88.077107),
                            new LatLng(42.148122, -88.077129),
                            new LatLng(42.148039, -88.077133),
                            new LatLng(42.147847, -88.077167),
                            new LatLng(42.147766, -88.077145),
                            new LatLng(42.147497, -88.077041),
                            new LatLng(42.147319, -88.076903),
                            new LatLng(42.147228, -88.076793),
                            new LatLng(42.147158, -88.076438),
                            new LatLng(42.147116, -88.076080),
                            new LatLng(42.147093, -88.075944)
                    ));
            // Set listeners for click events.
            polyline21.setColor(Color.YELLOW);
            polyline21.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline22 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146783, -88.079195),
                            new LatLng(42.146758, -88.078933),
                            new LatLng(42.146550, -88.078133),
                            new LatLng(42.146395, -88.077732),
                            new LatLng(42.146332, -88.077587),
                            new LatLng(42.146294, -88.077454)
                    ).color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.purple)));
            // Set listeners for click events.
            polyline22.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline23 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146395, -88.077731),
                            new LatLng(42.146378, -88.077754),
                            new LatLng(42.146347, -88.077835),
                            new LatLng(42.146328, -88.077868),
                            new LatLng(42.146288, -88.077908),
                            new LatLng(42.146264, -88.077926),
                            new LatLng(42.146193, -88.077948),
                            new LatLng(42.146088, -88.077935),
                            new LatLng(42.146014, -88.077898),
                            new LatLng(42.145994, -88.077880),
                            new LatLng(42.145953, -88.077798),
                            new LatLng(42.145943, -88.077768),
                            new LatLng(42.145931, -88.077682),
                            new LatLng(42.145932, -88.077519)
                    ).color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.purple)));
            // Set listeners for click events.
            polyline23.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline24 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.146080, -88.080387),
                            new LatLng(42.145928, -88.080449),
                            new LatLng(42.145805, -88.080550),
                            new LatLng(42.145706, -88.080624),
                            new LatLng(42.145672, -88.080624),
                            new LatLng(42.145612, -88.080582),
                            new LatLng(42.145522, -88.080428),
                            new LatLng(42.145246, -88.079838),
                            new LatLng(42.145185, -88.079748),
                            new LatLng(42.145117, -88.079673),
                            new LatLng(42.145037, -88.079592),
                            new LatLng(42.144910, -88.079403),
                            new LatLng(42.144857, -88.079293),
                            new LatLng(42.144850, -88.079262),
                            new LatLng(42.144869, -88.079035),
                            new LatLng(42.144859, -88.078886),
                            new LatLng(42.144803, -88.078583),
                            new LatLng(42.144741, -88.078324),
                            new LatLng(42.144666, -88.078139),
                            new LatLng(42.144442, -88.077770)
                    ));
            // Set listeners for click events.
            polyline24.setColor(Color.YELLOW);
            polyline24.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline25 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.134342, -88.090245),
                            new LatLng(42.134640, -88.087113),
                            new LatLng(42.134858, -88.085937),
                            new LatLng(42.134857, -88.085492),
                            new LatLng(42.134860, -88.085131),
                            new LatLng(42.135018, -88.084255),
                            new LatLng(42.135021, -88.084075),
                            new LatLng(42.135044, -88.083977),
                            new LatLng(42.135163, -88.083841),
                            new LatLng(42.135334, -88.083842),
                            new LatLng(42.135830, -88.084068),
                            new LatLng(42.135881, -88.084041)
                    ));
            // Set listeners for click events.
            polyline25.setColor(Color.BLACK);
            polyline25.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline26 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(42.140207, -88.067226),
                            new LatLng(42.140069, -88.067232),
                            new LatLng(42.139557, -88.067320),
                            new LatLng(42.139376, -88.067319),
                            new LatLng(42.139279, -88.067260),
                            new LatLng(42.139190, -88.067147),
                            new LatLng(42.139128, -88.066989),
                            new LatLng(42.139110, -88.066817),
                            new LatLng(42.139140, -88.066546),
                            new LatLng(42.139166, -88.066356),
                            new LatLng(42.139198, -88.066235),
                            new LatLng(42.139227, -88.065999),
                            new LatLng(42.139180, -88.065763),
                            new LatLng(42.139089, -88.065640),
                            new LatLng(42.138629, -88.065127),
                            new LatLng(42.138443, -88.064974),
                            new LatLng(42.138224, -88.064832),
                            new LatLng(42.137996, -88.064644),
                            new LatLng(42.137879, -88.064510),
                            new LatLng(42.137776, -88.064343),
                            new LatLng(42.137478, -88.063481),
                            new LatLng(42.137400, -88.063291),
                            new LatLng(42.137401, -88.063262),
                            new LatLng(42.137417, -88.063131)
                    ));
            // Set listeners for click events.
            polyline26.setColor(Color.BLACK);
            polyline26.setWidth((float) POLYLINE_STROKE_WIDTH_PX);

            Polyline polyline27 = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .pattern(PATTERN_POLYLINE_DASHED)
                    .add(
                            new LatLng(42.136890, -88.066192),
                            new LatLng(42.136923, -88.066196),
                            new LatLng(42.136960, -88.066208),
                            new LatLng(42.137004, -88.066230),
                            new LatLng(42.137139, -88.066333),
                            new LatLng(42.137171, -88.066345),
                            new LatLng(42.137240, -88.066348),
                            new LatLng(42.137318, -88.066323),
                            new LatLng(42.137386, -88.066295),
                            new LatLng(42.137443, -88.066262),
                            new LatLng(42.137490, -88.066220),
                            new LatLng(42.137528, -88.066152),
                            new LatLng(42.137561, -88.066080),
                            new LatLng(42.137588, -88.066026),
                            new LatLng(42.137595, -88.066000),
                            new LatLng(42.137633, -88.065929),
                            new LatLng(42.137644, -88.065906),
                            new LatLng(42.137687, -88.065862),
                            new LatLng(42.137738, -88.065833),
                            new LatLng(42.137895, -88.065741),
                            new LatLng(42.137922, -88.065732),
                            new LatLng(42.137997, -88.065659),
                            new LatLng(42.138013, -88.065639),
                            new LatLng(42.138039, -88.065618),
                            new LatLng(42.138126, -88.065585),
                            new LatLng(42.138166, -88.065573),
                            new LatLng(42.138205, -88.065557),
                            new LatLng(42.138221, -88.065551),
                            new LatLng(42.138374, -88.065417),
                            new LatLng(42.138408, -88.065374),
                            new LatLng(42.138475, -88.065261),
                            new LatLng(42.138569, -88.065080)

                    ).color(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.blue)));
            // Set listeners for click events.
            polyline27.setWidth((float) POLYLINE_STROKE_WIDTH_PX);
        }

        public void defaultMap(View view){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        public void satelliteMap(View view) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
//    @Override
//    public void onPolylineClick(Polyline polyline) {
//        for (int x=0; x < customPolylineArrayList.size(); x++){
//            if(customPolylineObj.name.equalsIgnoreCase(polyline.getTag().toString())){
//                marker = mMap.addMarker(new MarkerOptions().title(customPolylineObj.name));
//
//            }
//        }
//    }

}