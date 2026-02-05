import 'package:flutter/widgets.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';

class FeedElementController extends ChangeNotifier  {
  final BuildContext context;
  PostDTO post;

  FeedElementController(this.context, this.post);

  Future<void> togglePostLike() async {
    final result = await postRepository.likePost(post.id);

    if(result case Success<bool>(data: final isLiked)){

    final updated = post.rebuild((b) {
      b.likedByUser = isLiked;
      b.likeCount = (b.likeCount ?? 0) + (isLiked ? 1 : -1);
    });

    post = updated;

    } else {
      Fluttertoast.showToast(
          msg: "Could not like post",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.BOTTOM
      );
    }

    notifyListeners();
  }
}

