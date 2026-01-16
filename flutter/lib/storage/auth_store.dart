import 'dart:convert';

import 'package:hive/hive.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:path_provider/path_provider.dart' as path_provider;

final authStore = AuthStore();

class AuthStore {
  late Box tokenBox;
  late Box userBox;

  Future<void> init() async {
    final appDocumentDir = await path_provider.getApplicationDocumentsDirectory();
    Hive.init(appDocumentDir.path);
    tokenBox = await Hive.openBox('JWT');
    userBox = await Hive.openBox('user');
  }

  UserDTO? getUser() => userBox.get("user") != null ? serializers.deserialize(userBox.get("user")) as UserDTO : null;

  void setUser(UserDTO? user) => userBox.put("user", user != null ? serializers.serialize(user) : null);

  String? getJWT() => tokenBox.get("jwt");

  void setJWT(String? jwt) => tokenBox.put("jwt", jwt);
}
