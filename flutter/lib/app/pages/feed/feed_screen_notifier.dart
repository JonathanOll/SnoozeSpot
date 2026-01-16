import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];

  List<PostDTO> get posts =>
      _posts.toList(); // O(N), makes a new copy each time.

  void load() async {
    var response = await postRepository.getPosts();
    _posts.addAll(response);

    notifyListeners();
  }
}
