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

  Future<PostCommentDTO?> createPostComment(int postId, String content) async {
    final response = await api.postsIdCommentPost(
      id: postId,
      createPostCommentRequest: CreatePostCommentRequest((b) {
        b.content = content;
      }),
    );

    return response.data;
  }
}
