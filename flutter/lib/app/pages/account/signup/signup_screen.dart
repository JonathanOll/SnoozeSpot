import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/big_button.dart';
import 'package:snoozespot/app/components/outlined_textfield.dart';
import 'package:snoozespot/app/pages/account/account_screen.dart';
import 'package:snoozespot/app/pages/account/login/login_screen.dart';
import 'package:snoozespot/app/pages/account/signup/signup_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';

class SignupScreen extends StatefulWidget {
  const SignupScreen({super.key});

  static const routeName = "/account/signup";

  @override
  State<SignupScreen> createState() => _SignupScreenState();
}

class _SignupScreenState extends State<SignupScreen> {
  final TextEditingController emailController = TextEditingController();
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<SignupScreenNotifier>();

    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          padding: EdgeInsets.all(AppMargin.medium),
          child: ConstrainedBox(
            constraints: BoxConstraints(
              minHeight: MediaQuery.of(context).size.height,
            ),
            child: IntrinsicHeight(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Sign up",
                    style: AppTheme.titleLargeBold.apply(color: AppColor.purple),
                  ),
                  SizedBox(height: AppMargin.xlarge),
                  OutlinedTextField(
                    controller: emailController,
                    hintText: 'Email address',
                  ),
                  SizedBox(height: AppMargin.large),
                  OutlinedTextField(
                    controller: usernameController,
                    hintText: 'Username',
                  ),
                  SizedBox(height: AppMargin.large),
                  OutlinedTextField(
                    controller: passwordController,
                    hintText: 'Password',
                  ),
                  SizedBox(height: AppMargin.large),
                  BigButton(
                    onPressed: () async {
                      if (await notifier.signup(
                        usernameController.text,
                        passwordController.text,
                        emailController.text,
                      )) {
                        Navigator.of(context)
                            .pushReplacementNamed(AccountScreen.routeName);
                      }
                    },
                    textColor: AppColor.white,
                    backColor: AppColor.purple,
                    child: Text("Sign up"),
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.of(context)
                          .pushReplacementNamed(LoginScreen.routeName);
                    },
                    child: Text(
                      "Already got an account? Login instead",
                      style: TextStyle(color: AppColor.purple),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

