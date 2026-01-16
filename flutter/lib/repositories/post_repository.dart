import 'package:flutter/foundation.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

final postRepository = PostRepository();

class PostRepository {

  Future<List<PostDTO>> getPosts() async {
    final response = await api.postsGet();

    return (response.data ?? <PostDTO>[]).toList();
  }

  Future<PostDTO?> getPost(int id) async {
    final response = await api.postsIdGet(id: id);

    return response.data;
  }

  Future<PostDTO?> createPost(String content) async {
    final response = await api.postsPost(content: content);

    return response.data;
  }

}
