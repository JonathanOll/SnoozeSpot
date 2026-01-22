import 'package:flutter/foundation.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot/utils/result.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:dio/dio.dart';

final userRepository = UserRepository();

class UserRepository {

  Future<Result<UserDTO>> getUser(String uuid) async {
    call() => api.usersUuidGet(uuid: uuid);

    return Result.guardAsync(call);
  }

  Future<Result<AuthResponseDTO>> login(String username, String password) async {
    call() => api.authLoginPost(
      userAuthRequest: UserAuthRequest(
            (b) => b
          ..username = username
          ..password = password,
      ),
    );

    return Result.guardAsync(call);
  }

  Future<Result<AuthResponseDTO>> signup(String username, String password, String email) async {
    call() => api.authSignupPost(
      userAuthRequest: UserAuthRequest(
            (b) => b
          ..username = username
          ..password = password
          ..email = email,
      ),
    );

    return Result.guardAsync(call);
  }
}
