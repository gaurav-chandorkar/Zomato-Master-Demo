package com.grv.glammtest

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.grv.gauravtest.utils.NetworkConstant

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity grv"
    val REQUEST_CHECK_SETTINGS = 2000
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    lateinit var locationRequest: LocationRequest

    lateinit var locationCallback: LocationCallback

    lateinit var location: Location

    val REQUEST_CODE = 1000

    var isLocationSet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission
                    .ACCESS_FINE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), REQUEST_CODE
            )
        } else {
            buildLocationRequest()
            createLocationDialog()
            buildLocationCallback()
        }

    }

    private fun goToNext() {
        Handler().postDelayed(object : Runnable {
            override fun run() {
                if (isLocationSet)
                    callHomeScreen()
                else {
                    Toast.makeText(this@MainActivity, " Location required", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }, 2000)
    }

    private fun callHomeScreen() {
        startActivity(Intent(this@MainActivity, HomeScreenActivity::class.java))
        finish()
    }

    private fun buildLocationCallback() {

        locationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult?) {
                location = locationResult!!.locations.get(locationResult!!.locations.size - 1)
                Log.e(
                    "lattitude",
                    location.latitude.toString() + "  " + location.longitude.toString()
                )

                NetworkConstant.lattitude = location.latitude.toString()
                NetworkConstant.longitude = location.longitude.toString()
                if (!isLocationSet) {
                    isLocationSet = true
                    callHomeScreen()
                }

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    else
                        Toast.makeText(
                            this@MainActivity,
                            "Permission Required",
                            Toast.LENGTH_SHORT
                        ).show()

                }
            }
        }
    }

    private fun createLocationDialog() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        this@MainActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            data?.extras
            startLocationUpdate()
        }
    }

    override fun onResume() {
        super.onResume()

        Log.e(TAG, "onresume")
        buildLocationRequest()
        buildLocationCallback()
        startLocationUpdate()

    }

    override fun onStop() {
        super.onStop()

        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun startLocationUpdate() {
        //Create FusedProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission
                    .ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(
                    this@MainActivity, Manifest.permission
                        .ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(
                    Manifest.permission
                        .ACCESS_FINE_LOCATION
                ), REQUEST_CODE
            )
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun buildLocationRequest() {

        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 3000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }
}
