package com.disu.disme;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mapbox.mapboxsdk.utils.ColorUtils;


/**
 * 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class ProfileFragment extends Fragment implements OnMapReadyCallback {

    private View view;
    private TextView textView;
    private com.mapbox.mapboxsdk.maps.MapView mapView = null;
    private static final String ID_ICON_AIRPORT = "airport";
    MapboxMap mapboxMap;
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_MAP = 2;
    private LocationRequest locationRequest;

    SecretData user = new SecretData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), getString(R.string.mapbox_access_token));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        setPhoneCall();
        setSendEmail();
        setDirectLink();
        setVersion();
        setMap();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = getView().findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    private void setPhoneCall() {

        view.findViewById(R.id.button_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
    }

    private void call() {
        if (checkPermissions()) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            callIntent.setData(Uri.parse("tel:" + user.getHp()));
            getActivity().startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                Toast.makeText(getContext(), "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_MAP) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (GPS()) {
                    map();
                }
            } else {
                turnOnGPS();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                map();
            }
        }
    }

    private void setSendEmail() {
        view.findViewById(R.id.button_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"deainesia223@gmail.com"});
                email.setData(Uri.parse("mailto:"));
                getActivity().startActivity(email);
            }
        });
    }

    private void setDirectLink() {
        view.findViewById(R.id.button_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(Intent.ACTION_VIEW);
                link.setData(Uri.parse("https://github.com/BANZAITESLA"));
                getActivity().startActivity(link);
            }
        });
    }

    private void setVersion() {
        Button buttonVersion = view.findViewById(R.id.button_version);

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        buttonVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textDialog = (TextView) dialog.findViewById(R.id.app_version);
                Button buttonDialog = (Button) dialog.findViewById(R.id.button_version);

                textDialog.setText(BuildConfig.VERSION_NAME);

                dialog.show();

                buttonDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void setMap() {
        Button button_map = view.findViewById(R.id.button_map);

        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map();
            }
        });
    }

    private void map() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            if (GPS()) {
                LocationServices.getFusedLocationProviderClient(getContext()).requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);

                        LocationServices.getFusedLocationProviderClient(getContext()).removeLocationUpdates(this);

                        if (locationResult != null && locationResult.getLocations().size() > 0 ) {
                            int index = locationResult.getLocations().size() - 1;

                            double sourceLatitude = locationResult.getLocations().get(index).getLatitude();
                            double sourceLongitude = locationResult.getLocations().get(index).getLongitude();
                            double destinationLatitude = user.getLat();
                            double destinationLongitude = user.getLoc_long();

                            Intent map = new Intent(Intent.ACTION_VIEW);
                            map.setData(Uri.parse("http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + destinationLatitude + "," + destinationLongitude));
                            getActivity().startActivity(map);
                        }
                    }
                }, Looper.getMainLooper());
            } else {
                turnOnGPS();
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_MAP);
        }
    }

    private boolean GPS() {
        LocationManager locationManager = null;
        boolean isOn = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        isOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOn;
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(getContext(), "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                                resolvableApiException.startResolutionForResult(getActivity(),2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(user.getLat(), user.getLoc_long()))
                .zoom(9)
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new com.mapbox.mapboxsdk.maps.Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull com.mapbox.mapboxsdk.maps.Style style) {
                SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                symbolManager.setIconAllowOverlap(true);
                symbolManager.setTextAllowOverlap(true);

                style.addImage(ID_ICON_AIRPORT,
                        BitmapUtils.getBitmapFromDrawable(getActivity().getResources().getDrawable(R.drawable.ic_loc)),
                        true);

                SymbolOptions symbolOptions = new SymbolOptions()
                        .withLatLng(new LatLng(user.getLat(), user.getLoc_long()))
                        .withIconImage(ID_ICON_AIRPORT)
                        .withIconSize(2.0f)
                        .withIconColor(ColorUtils.colorToRgbaString(Color.rgb(82,91,78)))
                        .withDraggable(true);

                        symbolManager.create(symbolOptions);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }
}