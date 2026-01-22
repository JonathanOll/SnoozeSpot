import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:test/test.dart';

/// tests for DefaultApi
void main() {
  final instance = SnoozespotApi().getDefaultApi();

  group(DefaultApi, () {
    //
    //
    //Future<AuthResponseDTO> authLoginPost(UserAuthRequest userAuthRequest) async
    test('test authLoginPost', () async {
      // TODO
    });

    //
    //
    //Future<AuthResponseDTO> authSignupPost(UserAuthRequest userAuthRequest) async
    test('test authSignupPost', () async {
      // TODO
    });

    //
    //
    //Future<JsonObject> postsCommentCommentIdDelete(int commentId) async
    test('test postsCommentCommentIdDelete', () async {
      // TODO
    });

    //
    //
    //Future<BuiltList<PostDTO>> postsGet({ int page }) async
    test('test postsGet', () async {
      // TODO
    });

    //
    //
    //Future<PostCommentDTO> postsIdCommentPost(int id, CreatePostCommentRequest createPostCommentRequest) async
    test('test postsIdCommentPost', () async {
      // TODO
    });

    //
    //
    //Future<JsonObject> postsIdDelete(int id) async
    test('test postsIdDelete', () async {
      // TODO
    });

    //
    //
    //Future<PostDTO> postsIdGet(int id) async
    test('test postsIdGet', () async {
      // TODO
    });

    //
    //
    //Future<bool> postsIdLikePost(int id) async
    test('test postsIdLikePost', () async {
      // TODO
    });

    //
    //
    //Future<PostDTO> postsPost() async
    test('test postsPost', () async {
      // TODO
    });

    //
    //
    //Future<BuiltList<SpotDTO>> spotsGet() async
    test('test spotsGet', () async {
      // TODO
    });

    //
    //
    //Future<SpotCommentDTO> spotsIdCommentPost(int id, CreateSpotCommentRequest createSpotCommentRequest) async
    test('test spotsIdCommentPost', () async {
      // TODO
    });

    //
    //
    //Future<SpotDTO> spotsIdGet(int id) async
    test('test spotsIdGet', () async {
      // TODO
    });

    //
    //
    //Future<SpotDTO> spotsPost() async
    test('test spotsPost', () async {
      // TODO
    });

    //
    //
    //Future<BuiltList<SpotDTO>> spotsZoneGet({ num topLeftLatitude, num topLeftLongitude, num bottomRightLatitude, num bottomRightLongitude }) async
    test('test spotsZoneGet', () async {
      // TODO
    });

    //
    //
    //Future<BuiltList<UserDTO>> usersGet() async
    test('test usersGet', () async {
      // TODO
    });

    //
    //
    //Future<UserDTO> usersMeGet() async
    test('test usersMeGet', () async {
      // TODO
    });

    //
    //
    //Future<String> usersProfilePicturePost() async
    test('test usersProfilePicturePost', () async {
      // TODO
    });

    //
    //
    //Future<UserDTO> usersUuidGet(String uuid) async
    test('test usersUuidGet', () async {
      // TODO
    });
  });
}
