package com.matsuu.compassapp.ui.activities.compass

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import com.matsuu.compassapp.R
import com.matsuu.compassapp.data.location.AndroidLocationProvider
import com.matsuu.compassapp.ui.activities.AbstractDefaultActivity
import com.matsuu.compassapp.ui.fragments.compass.usableinterface.Compass
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragment
import com.matsuu.compassapp.ui.fragments.latlonginput.usableinterface.LatLongUserInput
import kotlinx.android.synthetic.main.activity_compass.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class CompassActivity : AbstractDefaultActivity(), CompassActivityContract.View,
    LatLongInputFragment.LatLongInputChangedListener, EasyPermissions.PermissionCallbacks {

    @Inject
    lateinit var presenter: CompassActivityContract.Presenter

    @Inject
    lateinit var locationSettingsRequestBuilder: LocationSettingsRequest.Builder

    @Inject
    lateinit var locationSettingsClient: SettingsClient

    private var compass: Compass? = null
    private var latLongInput: LatLongUserInput? = null

    private var currentlyProvidedLatitude = 0f
    private var currentlyProvidedLongitude = 0f

    companion object {
        const val REQUEST_CHECK_SETTINGS = 1
        const val REQUEST_LOCATION_PERMISSION = 2
    }

    override fun getLayout(): Int = R.layout.activity_compass

    override fun onResume() {
        super.onResume()

        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()

        super.onPause()
    }

    override fun setViews() {
        latLongInput = (fragment_lat_long_user_input as LatLongUserInput).apply {
            setLatLongInputChangedListener(this@CompassActivity)
        }
        compass = fragment_compass as Compass
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CHECK_SETTINGS)
            startNavigating()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        checkLocationSettings()
    }

    override fun onCorrectLatLongProvided(latitude: Float, longitude: Float) {

        currentlyProvidedLatitude = latitude
        currentlyProvidedLongitude = longitude

        checkPermissions()
    }

    private fun checkPermissions() {
        if (EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            checkLocationSettings()
        else
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.request_location_permission_rationale),
                REQUEST_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
    }

    private fun checkLocationSettings() {

        AndroidLocationProvider.createLocationRequest()?.let { request ->
            locationSettingsRequestBuilder.addLocationRequest(request)
        }

        val task: Task<LocationSettingsResponse> =
            locationSettingsClient.checkLocationSettings(locationSettingsRequestBuilder.build())

        task.addOnSuccessListener { locationSettingsResponse ->

            // All location settings are satisfied. Can start navigating
            startNavigating()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        this,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun startNavigating() {
        compass?.startNavigation(currentlyProvidedLatitude, currentlyProvidedLongitude)
    }

    override fun onWrongInputProvided() {
        compass?.stopNavigation()
    }
}
