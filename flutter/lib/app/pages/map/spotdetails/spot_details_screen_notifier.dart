import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/spot_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class SpotDetailsScreenNotifier with ChangeNotifier {
  SpotDTO? _spot;

  SpotDTO? get spot => _spot;

  void loadSpot(int id) async {
    var result = await spotRepository.getSpot(id: id);

    if(result case Success<SpotDTO>(data: final spotResponse)){
      _spot = spotResponse;
      notifyListeners();
    } else {
      // TODO: Handle this case.
      throw Exception(result.toString());
    }
  }

}
