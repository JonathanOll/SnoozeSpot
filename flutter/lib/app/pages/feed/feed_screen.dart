import 'package:flutter/material.dart';
import 'package:loading_animation_widget/loading_animation_widget.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/bottom_bar.dart';
import 'package:snoozespot/app/pages/feed/components/feed_element.dart';
import 'package:snoozespot/app/pages/feed/feed_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';

class FeedScreen extends StatefulWidget {
  const FeedScreen({super.key});

  static const routeName = "/feed";

  @override
  State<FeedScreen> createState() => _FeedScreenState();
}

class _FeedScreenState extends State<FeedScreen> {
  final _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((_) {
      final notifier = Provider.of<FeedScreenNotifier>(context, listen: false);
      notifier.loadPosts();
    });

    _scrollController.addListener(_loadMoreItems);
  }

  _loadMoreItems() async {
    final shouldLoadMore  = _scrollController.position.pixels >=
        _scrollController.position.maxScrollExtent - 500;
    if (shouldLoadMore) {
      final notifier = Provider.of<FeedScreenNotifier>(context, listen: false);
      if (!notifier.isLoading) {
        notifier.loadPosts();
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<FeedScreenNotifier>();

    return Scaffold(
      bottomNavigationBar: SnoozeSpotBottomBar(),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final result = await Navigator.of(
            context,
          ).pushNamed(NewPostScreen.routeName);
          if (result != null && result is String && result.isNotEmpty) {
            notifier.createPost(result);
          }
        },
        child: Icon(Icons.add),
      ),
      body: SafeArea(
        child: RefreshIndicator(
          onRefresh: () async {
            notifier.refresh();
          },
          child: ListView.builder(
            controller: _scrollController,
            itemCount: notifier.posts.length + 1,
            itemBuilder: (context, index) {
              if (index < notifier.posts.length) {
                return FeedElement(
                  post: notifier.posts[index],
                  onLike: () {
                    notifier.likePost(notifier.posts[index].id);
                  },
                );
              }

              return Center(
                child: LoadingAnimationWidget.staggeredDotsWave(
                  color: Colors.black,
                  size: 100,
                ),
              );
            },
          ),
        ),
      ),
    );
  }
}
