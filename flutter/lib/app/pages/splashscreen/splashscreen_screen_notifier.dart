import 'package:flutter/cupertino.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot/utils/result.dart';

class SplashScreenScreenNotifier extends ChangeNotifier {

  bool _isReady = false;

  bool get isReady => _isReady;

  void updateUserInfo() async {
    if(authStore.getJWT() != null) {
      var result = await userRepository.getMe();

      if (result case Success<UserDTO>(data: final user)) {
        authStore.setUser(user);
      } else {
        Fluttertoast.showToast(
            msg: "Logged out",
            toastLength: Toast.LENGTH_SHORT,
            gravity: ToastGravity.BOTTOM
        );
        authStore.setUser(null);
        authStore.setJWT(null);
      }
    }

    _isReady = true;
    notifyListeners();
  }
}