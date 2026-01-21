import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];

  List<PostDTO> get posts => _posts.toList();

  int page = 0;

  void loadPosts() async {
    var response = await postRepository.getPosts(page: page++);
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

  void likePost(int id) async {
    final response = await postRepository.likePost(id);

    if (response != null) {
      final index = _posts.indexWhere((el) => el.id == id);

      if (index != -1) {
        final updated = _posts[index].rebuild((b) {
          b.likedByUser = response;
          b.likeCount = (b.likeCount ?? 0) + (response ? 1 : -1);
        });

        _posts[index] = updated;
        notifyListeners();
      }
    }
  }

  void refresh() async {
    _posts.clear();
    page = 0;
    loadPosts();
  }
}
