import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:snoozespot/repositories/result.dart';

class PostDetailsScreenNotifier with ChangeNotifier {
  PostDTO? _post;

  PostDTO? get post => _post;

  void loadPost(int id) async {
    var result = await postRepository.getPost(id);

    if(result case Success<PostDTO>(data: final post)){
      _post = post;
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }

    notifyListeners();
  }

  void createComment(String content) async {
    if(_post == null) {
      return;
    }

    var result = await postRepository.createPostComment(_post!.id, content);

    if(result case Success<PostCommentDTO>(data: final comment)){
      _post = _post!.rebuild((b) {
        b.comments.add(comment);
      });
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }

    notifyListeners();
  }

  void likePost() async {
    if(_post == null) {
      return;
    }

    final result = await postRepository.likePost(_post!.id);

    if(result case Success<bool>(data: final isLiked)){
      final updated = _post!.rebuild((b) {
        b.likedByUser = isLiked;
        b.likeCount = (b.likeCount ?? 0) + (isLiked ? 1: -1);
      });

      _post = updated;
    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }

    notifyListeners();
  }
}
