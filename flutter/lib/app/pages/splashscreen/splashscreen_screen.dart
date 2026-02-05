import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/app/pages/splashscreen/splashscreen_screen_notifier.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot/resources/app_color.dart';

class SplashScreenScreen extends StatefulWidget {
  const SplashScreenScreen({super.key});

  static const routeName = "/splashscreen";

  @override
  State<SplashScreenScreen> createState() => _SplashScreenScreenState();
}

class _SplashScreenScreenState extends State<SplashScreenScreen> {
  @override
  void initState() {
    WidgetsBinding.instance.addPostFrameCallback((_) {
      context.read<SplashScreenScreenNotifier>().updateUserInfo();
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<SplashScreenScreenNotifier>();

    if (notifier.isReady) {
      WidgetsBinding.instance.addPostFrameCallback((_) {
        Navigator.of(context).pushNamed(FeedScreen.routeName);
      });
    }

    return Scaffold(
      body: ColoredBox(
        color: AppColor.secondary,
        child: SizedBox(
          width: double.infinity,
          height: double.infinity,

          child: Stack(
            alignment: Alignment.center,
            children: [
              Image.asset(
                Assets.imagesSplashScreenGradient,
                width: double.infinity,
                height: double.infinity,
                fit: BoxFit.fill,
              ),
              Lottie.asset(Assets.animationsSplashScreen),
              FractionallySizedBox(
                widthFactor: 0.7,
                child: Image.asset(Assets.imagesIconShadow),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
