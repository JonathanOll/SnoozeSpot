import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class AccountDetailsScreenNotifier with ChangeNotifier {
  UserDTO? _account;

  UserDTO? get account => _account;

  void loadAccount(String id) async {
    var response = await userRepository.getUser(id);
    _account = response;

    notifyListeners();
  }

  void likePost(int id) async {
    if(_account == null) {
      return;
    }
    final response = await postRepository.likePost(id);

    if (response != null) {
      final index = _account!.posts.indexWhere((el) => el.id == id);

      if (index != -1) {
        final updated = _account!.rebuild((b) {
          b.posts[index] = b.posts[index].rebuild((b) {
            b.likedByUser = response;
            b.likeCount = (b.likeCount ?? 0) + (response ? 1: -1);
          });
        });

        _account = updated;
        notifyListeners();
      }
    }
  }
}
