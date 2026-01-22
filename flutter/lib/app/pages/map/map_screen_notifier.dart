import 'package:built_collection/built_collection.dart';
import 'package:flutter/cupertino.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/repositories/spot_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class MapScreenNotifier with ChangeNotifier {
  final List<SpotDTO> _spots = <SpotDTO>[];

  List<SpotDTO> get spots => _spots.toList();

  void loadSpots(LatLngBounds position) async {
    final LatLng topleft = LatLng(
      position.northeast.latitude,
      position.southwest.longitude,
    );
    final LatLng bottomright = LatLng(
      position.southwest.latitude,
      position.northeast.longitude,
    );

    var response = await spotRepository.getSpots(
      topleft: topleft,
      bottomright: bottomright,
    );

    if(response case Success<BuiltList<SpotDTO>>(data: final spotsResponse)){
      _spots.clear();
      _spots.addAll(spotsResponse);
      notifyListeners();
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }
  }
}
