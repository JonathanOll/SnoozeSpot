import 'package:built_collection/built_collection.dart';
import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];
  List<PostDTO> get posts => _posts.toList();

  int page = 0;
  bool _isLoading = false;
  bool get isLoading => _isLoading;

  void loadPosts() async {
    if (_isLoading) return;

    _isLoading = true;
    notifyListeners();

    try {
      var result = await postRepository.getPosts(page: page++);

      if(result case Success<BuiltList<PostDTO>>(data: final posts)){
        _posts.addAll(posts);
      } else {
        // TODO: Handle this case.
        throw UnimplementedError();
      }

    } finally {
      _isLoading = false;
      notifyListeners();
    }
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

  void likePost(int id) async {
    final result = await postRepository.likePost(id);

    if(result case Success<bool>(data: final isLiked)){
      final index = _posts.indexWhere((el) => el.id == id);

      if (index != -1) {
        final updated = _posts[index].rebuild((b) {
          b.likedByUser = isLiked;
          b.likeCount = (b.likeCount ?? 0) + (isLiked ? 1 : -1);
        });

        _posts[index] = updated;
      }

    } else {
      // TODO: Handle this case.
      throw UnimplementedError();
    }

    notifyListeners();
  }

  void refresh() async {
    _posts.clear();
    page = 0;
    loadPosts();
  }
}
