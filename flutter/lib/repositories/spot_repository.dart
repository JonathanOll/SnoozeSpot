import 'package:flutter/foundation.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

final spotRepository = SpotRepository();

class SpotRepository {
  Future<List<SpotDTO>> getSpots({
    required LatLng topleft,
    required LatLng bottomright,
  }) async {
    final response = await api.spotsZoneGet(
      topLeftLatitude: topleft.latitude,
      topLeftLongitude: topleft.longitude,
      bottomRightLatitude: bottomright.latitude,
      bottomRightLongitude: bottomright.longitude
    );

    return (response.data ?? <SpotDTO>[]).toList();
  }
}
