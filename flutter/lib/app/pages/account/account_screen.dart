import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/bottom_bar.dart';
import 'package:snoozespot/app/pages/account/account_screen_notifier.dart';
import 'package:snoozespot/app/pages/account/components/account_element.dart';
import 'package:snoozespot/app/pages/account/login/login_screen.dart';
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
      bottomNavigationBar: SnoozeSpotBottomBar(),
      body: SafeArea(
        child: SingleChildScrollView(
          child: ConstrainedBox(
            constraints: BoxConstraints(
              minHeight: MediaQuery.of(context).size.height,
            ),
            child: authStore.getUser() == null
                ? Center(
                    // centré uniquement pour le login
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        ElevatedButton(
                          onPressed: () {
                            Navigator.of(
                              context,
                            ).pushNamed(LoginScreen.routeName);
                          },
                          child: Text("login"),
                        ),
                      ],
                    ),
                  )
                : Column(
                    // affichage normal quand connecté
                    children: [
                      AccountElement(user: authStore.getUser()!),
                      ElevatedButton(
                        onPressed: () {
                          notifier.logout();
                        },
                        child: Text("logout"),
                      ),
                    ],
                  ),
          ),
        ),
      ),
    );
  }
}
