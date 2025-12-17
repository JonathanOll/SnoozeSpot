import 'package:flutter/material.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';

class FeedScreen extends StatelessWidget {
  const FeedScreen({super.key});

  static const routeName = "/feed";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Column(
            children: [
              Text("todo"),
              ElevatedButton(
                  onPressed: () { Navigator.of(context).pushNamed(SpotDetailsScreen.routeName); },
                  child: Text("autre page"),
              )
            ],
          )
      )
    );
  }
}