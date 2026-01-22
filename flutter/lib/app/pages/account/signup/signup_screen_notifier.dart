import 'package:flutter/cupertino.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot/utils/result.dart';

class SignupScreenNotifier with ChangeNotifier {
  Future<bool> signup(String username, String password, String email) async {
    var result = await userRepository.signup(username, password, email);

    if(result case Success<AuthResponseDTO>(data: final authResponse)){
      authStore.setUser(authResponse.user);
      authStore.setJWT(authResponse.accessToken);
      return true;
    }

    return false;
  }
}
