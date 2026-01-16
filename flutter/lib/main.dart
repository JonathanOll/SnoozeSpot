import 'package:flutter/material.dart';
import 'package:snoozespot/app.dart';
import 'package:snoozespot/storage/auth_store.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await authStore.init();

  runApp(const MyApp());
}
