import 'package:flutter/cupertino.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/repositories/spot_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';
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

    _spots.clear();
    _spots.addAll(response);
    notifyListeners();
  }
}
