import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/big_button.dart';
import 'package:snoozespot/app/components/outlined_textfield.dart';
import 'package:snoozespot/app/pages/account/account_screen.dart';
import 'package:snoozespot/app/pages/account/login/login_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/signup/signup_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';

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
      body: SafeArea(
        child: Container(
          padding: EdgeInsets.all(AppMargin.medium),
          width: double.infinity,
          height: double.infinity,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                "Login",
                style: AppTheme.titleLargeBold.apply(color: AppColor.purple),
              ),
              SizedBox(height: AppMargin.xlarge),
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
                  if (await notifier.login(
                    usernameController.text,
                    passwordController.text,
                  )) {
                    Navigator.of(
                      context,
                    ).pushReplacementNamed(AccountScreen.routeName);
                  }
                },
                textColor: AppColor.white,
                backColor: AppColor.purple,
                child: Text("Login"),
              ),
              SizedBox(height: AppMargin.medium),
              BigButton(
                onPressed: () {},
                textColor: Colors.black,
                backColor: AppColor.white,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Image.asset(
                      Assets.imagesGoogle,
                      height: AppImageSize.small,
                    ),
                    SizedBox(width: AppMargin.small),
                    Text("Continue with Google"),
                  ],
                ),
              ),
              TextButton(
                onPressed: () {
                  Navigator.of(
                    context,
                  ).pushReplacementNamed(SignupScreen.routeName);
                },
                child: Text(
                  "No account? Sign up instead",
                  style: TextStyle(color: AppColor.purple),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
