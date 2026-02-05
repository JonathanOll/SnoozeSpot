import 'package:built_collection/built_collection.dart';
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class AccountScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];

  List<PostDTO> get posts =>
      _posts.toList();

  void loadPosts() async {
    var result = await postRepository.getPosts();

    if(result case Success<BuiltList<PostDTO>>(data: final posts)){
      _posts.addAll(posts);
    } else {
      // TODO: Handle this case.
      throw Exception(result.toString());
    }

    notifyListeners();
  }

  void logout() {
    authStore.setJWT(null);
    authStore.setUser(null);

    notifyListeners();
  }
}
