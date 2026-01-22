import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';

class SpotMap extends StatelessWidget {
  final SpotDTO spot;

  const SpotMap({
    super.key,
    required this.spot,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      height: 300,
      child: GoogleMap(
        initialCameraPosition: CameraPosition(
          target: LatLng(spot.latitude, spot.longitude),
          zoom: 14,
        ),
        markers: {
          Marker(
            markerId: MarkerId(spot.id.toString()),
            position: LatLng(spot.latitude, spot.longitude),
          ),
        },
        scrollGesturesEnabled: false,
        zoomGesturesEnabled: false,
        rotateGesturesEnabled: false,
        tiltGesturesEnabled: false,
        zoomControlsEnabled: false,
        myLocationButtonEnabled: false,
        compassEnabled: false,
      ),
    );
  }
}
