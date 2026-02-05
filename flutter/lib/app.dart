import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/account/account_screen.dart';
import 'package:snoozespot/app/pages/account/account_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/login/login_screen.dart';
import 'package:snoozespot/app/pages/account/login/login_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/signup/signup_screen.dart';
import 'package:snoozespot/app/pages/account/signup/signup_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/account_details/account_details_screen.dart';
import 'package:snoozespot/app/pages/feed/account_details/account_details_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/app/pages/feed/feed_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/map_screen.dart';
import 'package:snoozespot/app/pages/map/map_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/new_spot/new_spot_screen.dart';
import 'package:snoozespot/app/pages/map/new_spot/new_spot_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/new_spot_comment/new_spot_comment_screen.dart';
import 'package:snoozespot/app/pages/map/spot_details/spot_details_screen.dart';
import 'package:snoozespot/app/pages/map/spot_details/spot_details_screen_notifier.dart';
import 'package:snoozespot/app/pages/splashscreen/splashscreen_screen.dart';
import 'package:snoozespot/app/pages/splashscreen/splashscreen_screen_notifier.dart';
import 'package:snoozespot/resources/app_theme.dart';

final RouteObserver<ModalRoute<void>> routeObserver =
    RouteObserver<ModalRoute<void>>();

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (context) => FeedScreenNotifier()),
        ChangeNotifierProvider(
          create: (context) => PostDetailsScreenNotifier(),
        ),
        ChangeNotifierProvider(create: (context) => LoginScreenNotifier()),
        ChangeNotifierProvider(create: (context) => SignupScreenNotifier()),
        ChangeNotifierProvider(create: (context) => AccountScreenNotifier()),
        ChangeNotifierProvider(
          create: (context) => AccountDetailsScreenNotifier(),
        ),
        ChangeNotifierProvider(create: (context) => MapScreenNotifier()),
        ChangeNotifierProvider(create: (context) => SpotDetailsScreenNotifier()),
        ChangeNotifierProvider(create: (context) => NewSpotScreenNotifier()),
        ChangeNotifierProvider(create: (context) => SplashScreenScreenNotifier()),
      ],
      child: MaterialApp(
        title: 'SnoozeSpot',
        theme: AppTheme.themeData,
        routes: {
          FeedScreen.routeName: (context) => FeedScreen(),
          SpotDetailsScreen.routeName: (context) => SpotDetailsScreen(),
          NewPostScreen.routeName: (context) => NewPostScreen(),
          PostDetailsScreen.routeName: (context) => PostDetailsScreen(),
          LoginScreen.routeName: (context) => LoginScreen(),
          SignupScreen.routeName: (context) => SignupScreen(),
          AccountScreen.routeName: (context) => AccountScreen(),
          AccountDetailsScreen.routeName: (context) => AccountDetailsScreen(),
          MapScreen.routeName: (context) => MapScreen(),
          NewSpotCommentScreen.routeName: (context) => NewSpotCommentScreen(),
          NewSpotScreen.routeName: (context) => NewSpotScreen(),
          SplashScreenScreen.routeName: (context) => SplashScreenScreen(),
        },
        initialRoute: SplashScreenScreen.routeName,
        navigatorObservers: [routeObserver],
      ),
    );
  }
}
