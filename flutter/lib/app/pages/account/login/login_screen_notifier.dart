import 'package:flutter/cupertino.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/result.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';

class LoginScreenNotifier with ChangeNotifier {
  Future<bool> login(String username, String password) async {
    var result = await userRepository.login(username, password);

    if(result case Success<AuthResponseDTO>(data: final authResponse)){
      authStore.setUser(authResponse.user);
      authStore.setJWT(authResponse.accessToken);
      return true;
    }

    return false;
  }
}
