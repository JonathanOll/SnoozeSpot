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

  void likePost(int id) async {
    if (_account == null) {
      return;
    }

    final result = await postRepository.likePost(id);

    if(result case Success<bool>(data: final isLiked)){
      final index = _account!.posts.indexWhere((el) => el.id == id);

      if (index != -1) {
        final updated = _account!.rebuild((b) {
          b.posts[index] = b.posts[index].rebuild((b) {
            b.likedByUser = isLiked;
            b.likeCount = (b.likeCount ?? 0) + (isLiked ? 1 : -1);
          });
        });

        _account = updated;
      }

    } else {
      // TODO: Handle this case.
      throw Exception(result.toString());
    }

    notifyListeners();
  }
}
