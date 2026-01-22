import 'package:built_collection/built_collection.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

final spotRepository = SpotRepository();

class SpotRepository {
  Future<Result<BuiltList<SpotDTO>>> getSpots({
    required LatLng topleft,
    required LatLng bottomright,
  }) async {
    call() => api.spotsZoneGet(
      topLeftLatitude: topleft.latitude,
      topLeftLongitude: topleft.longitude,
      bottomRightLatitude: bottomright.latitude,
      bottomRightLongitude: bottomright.longitude
    );

    return await Result.guardAsync(call);
  }

  Future<Result<SpotDTO>> getSpot({required int id}) async {
    call() => api.spotsIdGet(id: id);

    return await Result.guardAsync(call);
  }

}
