import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';

class LoginScreenNotifier with ChangeNotifier {
  Future<bool> login(String username, String password) async {
    var response = await userRepository.login(username, password);

    if (response != null) {
      authStore.setUser(response.user);
      authStore.setJWT(response.accessToken);
      return true;
    }
    return false;
  }
}
