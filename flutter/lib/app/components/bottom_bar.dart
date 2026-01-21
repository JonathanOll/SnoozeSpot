import 'package:flutter/material.dart';
import 'package:snoozespot/app.dart';
import 'package:snoozespot/app/pages/account/account_screen.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/resources/app_color.dart';

class SnoozeSpotBottomBar extends StatelessWidget {
  const SnoozeSpotBottomBar({super.key});

  @override
  Widget build(BuildContext context) {
    return SnoozeSpotBottomBarState();
  }
}

class SnoozeSpotBottomBarState extends StatefulWidget {
  const SnoozeSpotBottomBarState({super.key});

  @override
  State<SnoozeSpotBottomBarState> createState() => _SnoozeSpotBottomBarStateState();
}

class BottomBarElement {

  final IconData icon;
  final String route;

  const BottomBarElement({ required this.icon, required this.route });

}

class _SnoozeSpotBottomBarStateState extends State<SnoozeSpotBottomBarState> with RouteAware {
  int _selectedIndex = 0;

  static const List<BottomBarElement> _widgetOptions = <BottomBarElement>[
    BottomBarElement(icon: Icons.mode_comment, route: FeedScreen.routeName),
    BottomBarElement(icon: Icons.pin_drop, route: FeedScreen.routeName),
    BottomBarElement(icon: Icons.people, route: FeedScreen.routeName),
    BottomBarElement(icon: Icons.account_circle_outlined, route: AccountScreen.routeName),
  ];

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    routeObserver.subscribe(this, ModalRoute.of(context)!);
  }

  @override
  void dispose() {
    routeObserver.unsubscribe(this);
    super.dispose();
  }

  @override
  void didPush() {
    _updateIndex();
  }

  void _updateIndex() {
    final route = ModalRoute.of(context)!.settings.name;
    final index = _widgetOptions.indexWhere((e) => e.route == route);
    if (index != -1) {
      setState(() => _selectedIndex = index);
    }
  }


  void _onItemTapped(int index) {
    Navigator.of(context).pushReplacementNamed(_widgetOptions[index].route);
  }

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
        items: _widgetOptions.map((el) =>
          BottomNavigationBarItem(icon: Icon(el.icon), label: ''),
        ).toList(),
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.amber[800],
        onTap: _onItemTapped,
        backgroundColor: AppColor.secondary,
        showSelectedLabels: false,
        showUnselectedLabels: false,
      );
  }
}