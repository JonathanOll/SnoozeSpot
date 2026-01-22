import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/spot_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class SpotDetailsScreenNotifier with ChangeNotifier {
  SpotDTO? _spot;

  SpotDTO? get spot => _spot;

  void loadSpot(int id) async {
    var response = await spotRepository.getSpot(id: id);

    if(response case Success<SpotDTO>(data: final spotResponse)){
      _spot = spotResponse;
      notifyListeners();
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }
  }

  void addComment(int id, String content, double rating) async {
    if(_spot == null) return;
    var response = await spotRepository.createSpotComment(spotId: id, content: content, rating: rating);

    if(response case Success<SpotCommentDTO>(data: final spotCommentResponse)){
      _spot = _spot!.rebuild((p0) {
        p0.comments.add(spotCommentResponse);
        if(p0.rating == null) {
          p0.rating = spotCommentResponse.rating.toDouble();
        } else {
          p0.rating = (p0.rating! * (p0.comments.length - 1) + spotCommentResponse.rating) / p0.comments.length;
        }
      });
      notifyListeners();
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }
  }

}
