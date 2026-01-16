import 'package:flutter/cupertino.dart';
import 'package:snoozespot/repositories/post_repository.dart';
import 'package:snoozespot/repositories/user_repository.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class AccountDetailsScreenNotifier with ChangeNotifier {
  UserDTO? _account;

  UserDTO? get account => _account;

  void loadAccount(String id) async {
    var response = await userRepository.getUser(id);
    _account = response;

    notifyListeners();
  }
}
