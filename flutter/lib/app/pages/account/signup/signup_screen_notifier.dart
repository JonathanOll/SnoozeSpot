import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';

class SignupScreenNotifier with ChangeNotifier {
  Future<bool> signup(String username, String password, String email) async {
    var response = await userRepository.signup(username, password, email);

    if (response != null) {
      authStore.setUser(response.user);
      authStore.setJWT(response.accessToken);
      return true;
    }
    return false;
  }
}
