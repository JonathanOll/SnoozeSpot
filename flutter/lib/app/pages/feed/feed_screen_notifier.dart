import 'package:built_collection/built_collection.dart';
import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedScreenNotifier with ChangeNotifier {
  final List<PostDTO> _posts = <PostDTO>[];
  List<PostDTO> get posts => _posts.toList();

  bool _isLoading = false;
  bool get isLoading => _isLoading;

  int _page = 0;

  void loadPosts() async {
    if (_isLoading) return;

    _isLoading = true;
    notifyListeners();

    var result = await postRepository.getPosts(page: _page++);

    if (result case Success<BuiltList<PostDTO>>(data: final posts)) {
      _posts.addAll(posts);
    } else {
      Fluttertoast.showToast(
        msg: "Could not fetch data",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
      // TODO: remplacer le toast par le composant dédié une fois fait.
    }

    _isLoading = false;
    notifyListeners();
  }

  void createPost(String content) async {
    var result = await postRepository.createPost(content);

    if (result case Success<PostDTO>(data: final post)) {
      _posts.insert(0, post);
      notifyListeners();
    } else {
      Fluttertoast.showToast(
        msg: "Could not create post",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
      );
    }
  }

  void refresh() async {
    _posts.clear();
    _page = 0;
    loadPosts();
  }

  void deletePost(int index) {
    _posts.removeAt(index);
    notifyListeners();
  }
}
