package logic2magic.com.googlemapapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText et_location;
    Button button_search, button_zoomIn, button_zoomOut;
    ToggleButton toggleButton_ChangeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        et_location = (EditText) findViewById(R.id.edittxt_loc);
        button_search = (Button) findViewById(R.id.btn_search);
        toggleButton_ChangeView = (ToggleButton) findViewById(R.id.btn_changeView);
        button_zoomIn = (Button) findViewById(R.id.btn_zoomIn);
        button_zoomOut = (Button) findViewById(R.id.btn_zoomOut);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    List<Address> addresslist = null;

    public void onSearch(View view)
    {
        mMap.clear();
        String location = et_location.getText().toString();
        try
        {
            if (location != null || !location.equals(""))
            {
                Geocoder geocoder = new Geocoder(this);
                try
                {
                    addresslist = geocoder.getFromLocationName(location,1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                Address address = addresslist.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(MapsActivity.this, "Error! " + e, Toast.LENGTH_LONG).show();
        }
    }

    public void changeView (View view)
    {
        if(toggleButton_ChangeView.isChecked() == true)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            toggleButton_ChangeView.setBackgroundResource(R.drawable.satellite_img2);
            Toast.makeText(MapsActivity.this, "Normal View Active", Toast.LENGTH_SHORT).show();
        }
        else if(toggleButton_ChangeView.isChecked() == false)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            toggleButton_ChangeView.setBackgroundResource(R.drawable.map_img2);
            Toast.makeText(MapsActivity.this, "Satellite View Active", Toast.LENGTH_SHORT).show();
        }
    }



    public void zoomIn (View view)
        {
            try
            {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());

            }
            catch (Exception e)
            {
                Toast.makeText(MapsActivity.this, "Error! " + e, Toast.LENGTH_LONG).show();
            }
        }

    public void zoomOut(View view)
        {
            try
            {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
            catch (Exception e)
            {
                Toast.makeText(MapsActivity.this, "Error! " + e, Toast.LENGTH_LONG).show();
            }

        }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try
        {
            // Add a marker in Sydney and move the camera
            LatLng uaf = new LatLng(31.4339, 73.0649);
            mMap.addMarker(new MarkerOptions().position(uaf).title("UAF"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(uaf));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);

        }
        catch (Exception e)
        {
            Toast.makeText(MapsActivity.this, "Error! " + e, Toast.LENGTH_LONG).show();
        }


    }
}
