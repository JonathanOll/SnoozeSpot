import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/account/components/account_element.dart';
import 'package:snoozespot/app/pages/feed/account_details/account_details_screen_notifier.dart';
import 'package:snoozespot/app/pages/feed/components/feed_element.dart';

class AccountDetailsScreen extends StatefulWidget {
  const AccountDetailsScreen({super.key});

  static const routeName = "/feed/accountdetails";

  @override
  State<AccountDetailsScreen> createState() => _AccountDetailsScreenState();
}

class _AccountDetailsScreenState extends State<AccountDetailsScreen> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      final uuid = ModalRoute.of(context)!.settings.arguments as String;
      final notifier = Provider.of<AccountDetailsScreenNotifier>(
        context,
        listen: false,
      );
      notifier.loadAccount(uuid);
    });
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<AccountDetailsScreenNotifier>();

    return Scaffold(
      body: SafeArea(
        child: notifier.account == null
            ? Center(child: Text("loading..."))
            : RefreshIndicator(
                onRefresh: () async {
                  final uuid =
                      ModalRoute.of(context)!.settings.arguments as String;
                  notifier.loadAccount(uuid);
                },
                child: ListView.builder(
                  itemCount: notifier.account!.posts.length + 1,
                  itemBuilder: (context, index) {
                    if (index == 0) {
                      return AccountElement(user: notifier.account!);
                    }

                    final post = notifier.account!.posts[index - 1];

                    return FeedElement(
                      post: post,
                      onLike: () {
                        notifier.likePost(post.id);
                      },
                    );
                  },
                ),
              ),
      ),
    );
  }
}
