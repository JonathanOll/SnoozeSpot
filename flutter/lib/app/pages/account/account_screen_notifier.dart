import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:built_collection/built_collection.dart';

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
      throw UnimplementedError();
    }

    notifyListeners();
  }

  void createPost(String content) async {
    var result = await postRepository.createPost(content);

    if(result case Success<PostDTO>(data: final post)){
      _posts.insert(0, post);
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }

    notifyListeners();
  }

  void logout() {
    authStore.setJWT(null);
    authStore.setUser(null);

    notifyListeners();
  }
}
