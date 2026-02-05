import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class AccountDetailsScreenNotifier with ChangeNotifier {
  UserDTO? _account;

  UserDTO? get account => _account;

  void loadAccount(String id) async {
    var result = await userRepository.getUser(id);

    if(result case Success<UserDTO>(data: final account)){
      _account = account;
    } else {
      // TODO: Handle this case.
      throw Exception(result.toString());
    }

    notifyListeners();
  }

  void deletePost(PostDTO post) {
    if (_account == null) return;

    _account = _account!.rebuild(
          (b) => b..posts.removeWhere((p) => p.id == post.id),
    );

    notifyListeners();
  }

}
