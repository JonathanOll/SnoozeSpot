import 'package:dio/src/response.dart';
import 'package:flutter/foundation.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot/repositories/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:built_value/json_object.dart';
import 'package:built_value/serializer.dart';
import 'package:dio/dio.dart';
import 'package:built_collection/built_collection.dart';

final postRepository = PostRepository();

class PostRepository {

  Future<Result<BuiltList<PostDTO>>> getPosts({int page = 0}) async {
    call() => api.postsGet(page: page);

    return await Result.guardAsync(call);
  }

  Future<Result<PostDTO>> getPost(int id) async {
    call() => api.postsIdGet(id: id);

    return await Result.guardAsync(call);
  }

  Future<Result<PostDTO>> createPost(String content) async {
    call() => api.postsPost(content: content);

    return await Result.guardAsync(call);
  }

  Future<Result<bool>> likePost(int id) async {
    call() => api.postsIdLikePost(id: id);

    return await Result.guardAsync(call);
  }

  Future<Result<PostCommentDTO>> createPostComment(int postId, String content) async {
    call() => api.postsIdCommentPost(
      id: postId,
      createPostCommentRequest: CreatePostCommentRequest((b) {
        b.content = content;
      }),
    );

    return await Result.guardAsync(call);
  }
}
