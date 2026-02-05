import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot/utils/result.dart';

class LoginScreenNotifier with ChangeNotifier {
  Future<bool> login(String username, String password) async {
    var result = await userRepository.login(username, password);

    if(result case Success<AuthResponseDTO>(data: final authResponse)){
      authStore.setUser(authResponse.user);
      authStore.setJWT(authResponse.accessToken);
      return true;
    } else {
      Fluttertoast.showToast(
          msg: "Could not log in",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.BOTTOM
      );
    }

    return false;
  }
}
