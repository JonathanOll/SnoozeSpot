import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/app/pages/feed/feed_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';
import 'package:snoozespot/resources/app_theme.dart';

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
      ],
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: AppTheme.themeData,
        routes: {
          FeedScreen.routeName: (context) => FeedScreen(),
          SpotDetailsScreen.routeName: (context) => SpotDetailsScreen(),
          NewPostScreen.routeName: (context) => NewPostScreen(),
          PostDetailsScreen.routeName: (context) => PostDetailsScreen(),
        },
        initialRoute: FeedScreen.routeName,
      ),
    );
  }
}
