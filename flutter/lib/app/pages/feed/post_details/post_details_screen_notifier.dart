import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class PostDetailsScreenNotifier with ChangeNotifier {
  PostDTO? _post;

  PostDTO? get post => _post;

  void loadPost(int id) async {
    var response = await postRepository.getPost(id);
    _post = response;

    notifyListeners();
  }

  void createComment(String content) async {
    if(_post == null) {
      return;
    }

    var response = await postRepository.createPostComment(_post!.id, content);
    _post = _post!.rebuild((b) {
      b.comments.add(response!);
    });

    notifyListeners();
  }
}
