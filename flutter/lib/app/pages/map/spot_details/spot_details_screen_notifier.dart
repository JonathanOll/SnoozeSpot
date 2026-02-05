import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
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
      Fluttertoast.showToast(
        msg: "Could not fetch data",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
      // TODO: remplacer le toast par le composant dédié une fois fait.
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
      Fluttertoast.showToast(
        msg: "Could not add comment",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
    }
  }

}
