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
      bottomRightLongitude: bottomright.longitude,
    );

    return await Result.guardAsync(call);
  }

  Future<Result<SpotDTO>> getSpot({required int id}) async {
    call() => api.spotsIdGet(id: id);

    return await Result.guardAsync(call);
  }

  Future<Result<SpotCommentDTO>> createSpotComment({
    required int spotId,
    required String content,
    required double rating,
  }) async {
    call() => api.spotsIdCommentPost(
      id: spotId,
      createSpotCommentRequest: CreateSpotCommentRequest((b) {
        b
          ..rating = rating.toInt()
          ..content = content;
      }),
    );

    return await Result.guardAsync(call);
  }

  Future<Result<SpotDTO>> createSpot({
    required String name,
    required String description,
    required double latitude,
    required double longitude,
  }) async {
    call() => api.spotsPost(
      name: name,
      description: description,
      latitude: latitude,
      longitude: longitude,
    );

    return await Result.guardAsync(call);
  }
}
