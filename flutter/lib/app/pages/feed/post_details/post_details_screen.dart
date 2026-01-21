import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/feed/components/feed_comment.dart';
import 'package:snoozespot/app/pages/feed/components/feed_element.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen_notifier.dart';

class PostDetailsScreen extends StatefulWidget {
  const PostDetailsScreen({super.key});

  static const routeName = "/feed/details";

  @override
  State<PostDetailsScreen> createState() => _PostDetailsScreenState();
}

class _PostDetailsScreenState extends State<PostDetailsScreen> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      final id = ModalRoute.of(context)!.settings.arguments as int;
      final notifier = Provider.of<PostDetailsScreenNotifier>(
        context,
        listen: false,
      );
      notifier.loadPost(id);
    });
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<PostDetailsScreenNotifier>();

    return Scaffold(
      body: SafeArea(
        child: notifier.post == null
            ? Center(child: Text("loading..."))
            : SingleChildScrollView(
                scrollDirection: Axis.vertical,
                child: Column(
                  children: [
                    FeedElement(
                      post: notifier.post!,
                      onLike: () {
                        notifier.likePost();
                      },
                    ),
                    ...notifier.post!.comments.map(
                      (comment) => FeedComment(comment: comment),
                    ),
                    ElevatedButton(
                      onPressed: () async {
                        final result = await Navigator.of(
                          context,
                        ).pushNamed(NewPostScreen.routeName);
                        if (result != null &&
                            result is String &&
                            result.isNotEmpty) {
                          notifier.createComment(result);
                        }
                      },
                      child: Text("Add comment"),
                    ),
                  ],
                ),
              ),
      ),
    );
  }
}
