import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/feed/feed_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/components/feed_element.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';

class FeedScreen extends StatefulWidget {
  const FeedScreen({super.key});

  static const routeName = "/feed";

  @override
  State<FeedScreen> createState() => _FeedScreenState();
}

class _FeedScreenState extends State<FeedScreen> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      final notifier = Provider.of<FeedScreenNotifier>(context, listen: false);
      notifier.loadPosts();
    });
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<FeedScreenNotifier>();

    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final result = await Navigator.of(context).pushNamed(NewPostScreen.routeName);
          if (result != null && result is String && result.isNotEmpty) {
            notifier.createPost(result);
          }
        },
        child: Icon(Icons.add),
      ),
      body: SafeArea(
        child: SizedBox(
          width: double.infinity,
          child: SingleChildScrollView(
            scrollDirection: Axis.vertical,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              children: [
                ...notifier.posts.map((post) => FeedElement(post: post)),
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(
                      context,
                    ).pushNamed(SpotDetailsScreen.routeName);
                  },
                  child: Text("autre page"),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
