import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/outlined_textfield.dart';
import 'package:snoozespot/app/pages/account/login/login_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/signup/signup_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  static const routeName = "/account/login";

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<LoginScreenNotifier>();

    final TextEditingController usernameController = TextEditingController();
    final TextEditingController passwordController = TextEditingController();

    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          if (await notifier.login(usernameController.text, passwordController.text)) {
            Navigator.of(context).pushReplacementNamed(PostDetailsScreen.routeName);
          }
        },
        child: Icon(Icons.login),
      ),
      body: SafeArea(
        child: Container(
          padding: EdgeInsets.all(AppMargin.medium),
          width: double.infinity,
          height: double.infinity,
          child: Column(
            children: [
              OutlinedTextField(controller: usernameController, hintText: 'Username'),
              OutlinedTextField(controller: passwordController, hintText: 'Password'),
              FilledButton(
                onPressed: () {
                  Navigator.of(
                    context,
                  ).pushReplacementNamed(SignupScreen.routeName);
                },
                child: Text("Signup instead"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
