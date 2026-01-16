import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];

  List<PostDTO> get posts =>
      _posts.toList();

  void loadPosts() async {
    var response = await postRepository.getPosts();
    _posts.addAll(response);

    notifyListeners();
  }

  void createPost(String content) async {
    var response = await postRepository.createPost(content);

    if (response != null) {
      _posts.insert(0, response);
      notifyListeners();
    }
  }
}
