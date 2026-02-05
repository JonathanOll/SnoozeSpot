import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class PostDetailsScreenNotifier with ChangeNotifier {
  PostDTO? _post;

  PostDTO? get post => _post;

  void loadPost(int id) async {
    var result = await postRepository.getPost(id);

    if(result case Success<PostDTO>(data: final post)){
      _post = post;
    } else {
      // TODO: Handle this case.
      throw Exception(result.toString());
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
      Fluttertoast.showToast(
          msg: "Could not comment post",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.BOTTOM
      );
    }

    notifyListeners();
  }
}
