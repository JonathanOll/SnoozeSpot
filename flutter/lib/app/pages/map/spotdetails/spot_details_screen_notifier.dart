import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/repositories/spot_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class SpotDetailsScreenNotifier with ChangeNotifier {
  SpotDTO? _spot;

  SpotDTO? get spot => _spot;

  void loadSpot(int id) async {
    var response = await spotRepository.getSpot(id: id);
    if (response != null) {
      _spot = response;
    }

    notifyListeners();
  }

}
