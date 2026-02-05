import 'package:built_value/json_object.dart';
import 'package:flutter/widgets.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';

class FeedElementController with ChangeNotifier {
  final BuildContext context;
  PostDTO post;

  FeedElementController(this.context, this.post);

  void togglePostLike() async {
    final result = await postRepository.likePost(post.id);

    if (result case Success<bool>(data: final isLiked)) {
      final updated = post.rebuild((b) {
        b.likedByUser = isLiked;
        b.likeCount = (b.likeCount ?? 0) + (isLiked ? 1 : -1);
      });

      post = updated;
      notifyListeners();
    } else {
      Fluttertoast.showToast(
        msg: "Could not like post",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
    }

  }

  void deletePost(VoidCallback onDelete) async {
    final result = await postRepository.deletePost(post.id);
    if(result case Success<JsonObject>()) {
      onDelete();
    } else {
      Fluttertoast.showToast(
        msg: "Could not delete post",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
    }
  }
}
