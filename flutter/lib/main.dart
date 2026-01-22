import 'dart:io';

import 'package:flutter/material.dart';
import 'package:snoozespot/app.dart';
import 'package:snoozespot/storage/auth_store.dart';
import 'package:snoozespot/utils/http_override.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await authStore.init();
  HttpOverrides.global = MyHttpOverrides();

  runApp(const MyApp());
}
