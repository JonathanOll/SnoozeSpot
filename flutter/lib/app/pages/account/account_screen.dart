import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/pages/account/account_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/components/account_element.dart';
import 'package:snoozespot/app/pages/account/login/login_screen.dart';
import 'package:snoozespot/app/pages/feed/new_post/new_post_screen.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';
import 'package:snoozespot/storage/auth_store.dart';

class AccountScreen extends StatefulWidget {
  const AccountScreen({super.key});

  static const routeName = "/account";

  @override
  State<AccountScreen> createState() => _AccountScreenState();
}

class _AccountScreenState extends State<AccountScreen> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      final notifier = Provider.of<AccountScreenNotifier>(
        context,
        listen: false,
      );
      notifier.loadPosts();
    });
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<AccountScreenNotifier>();

    return Scaffold(
      body: SafeArea(
        child: SizedBox(
          width: double.infinity,
          child: SingleChildScrollView(
            scrollDirection: Axis.vertical,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              children: [
                ?authStore.getUser() != null
                    ? AccountElement(user: authStore.getUser()!)
                    : null,

                authStore.getUser() != null
                    ? ElevatedButton(
                        onPressed: () {
                          notifier.logout();
                        },
                        child: Text("logout"),
                      )
                    : ElevatedButton(
                        onPressed: () {
                          Navigator.of(
                            context,
                          ).pushNamed(LoginScreen.routeName);
                        },
                        child: Text("login"),
                      ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
