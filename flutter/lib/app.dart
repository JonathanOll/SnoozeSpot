import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/app/pages/feed/feed_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';
import 'package:snoozespot/resources/app_theme.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (context) => FeedScreenNotifier())
      ],
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: AppTheme.themeData,
        routes: {
          FeedScreen.routeName: (context) => FeedScreen(),
          SpotDetailsScreen.routeName: (context) => SpotDetailsScreen(),
        },
        initialRoute: FeedScreen.routeName,
      ),
    );
  }
}