import 'package:flutter/foundation.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

final userRepository = UserRepository();

class UserRepository {
  Future<AuthResponseDTO?> login(String username, String password) async {
    final response = await api.authLoginPost(
      userAuthRequest: UserAuthRequest(
        (b) => b
          ..username = username
          ..password = password,
      ),
    );

    return response.data;
  }
}
