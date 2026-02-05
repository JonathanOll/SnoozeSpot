import 'package:flutter/material.dart';
import 'package:snoozespot/app.dart';
import 'package:snoozespot/app/pages/account/account_screen.dart';
import 'package:snoozespot/app/pages/feed/feed_screen.dart';
import 'package:snoozespot/app/pages/map/map_screen.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/utils/map_indexed.dart';

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
  State<SnoozeSpotBottomBarState> createState() =>
      _SnoozeSpotBottomBarStateState();
}

class BottomBarElement {
  final IconData icon;
  final IconData selectedIcon;
  final String route;

  const BottomBarElement({
    required this.icon,
    required this.selectedIcon,
    required this.route,
  });
}

class _SnoozeSpotBottomBarStateState extends State<SnoozeSpotBottomBarState>
    with RouteAware {
  int _selectedIndex = 0;

  static const List<BottomBarElement> _widgetOptions = <BottomBarElement>[
    BottomBarElement(
      icon: Icons.mode_comment_outlined,
      selectedIcon: Icons.mode_comment,
      route: FeedScreen.routeName,
    ),
    BottomBarElement(
      icon: Icons.pin_drop_outlined,
      selectedIcon: Icons.pin_drop,
      route: MapScreen.routeName,
    ),
    BottomBarElement(
      icon: Icons.people_outline,
      selectedIcon: Icons.people,
      route: FeedScreen.routeName,
    ),
    BottomBarElement(
      icon: Icons.account_circle_outlined,
      selectedIcon: Icons.account_circle_outlined,
      route: AccountScreen.routeName,
    ),
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
      items: _widgetOptions
          .mapIndexed(
            (index, el) => BottomNavigationBarItem(
              icon: Stack(
                alignment: Alignment.center,
                children: [
                  ?_selectedIndex == index
                      ? Image.asset(Assets.imagesSelected, width: 60)
                      : null,
                  Icon(_selectedIndex == index ? el.selectedIcon : el.icon),
                ],
              ),
              label: '',
            ),
          )
          .toList(),
      currentIndex: _selectedIndex,
      selectedIconTheme: IconThemeData(),
      selectedItemColor: Colors.black,
      onTap: _onItemTapped,
      backgroundColor: AppColor.secondary,
      showSelectedLabels: false,
      showUnselectedLabels: false,
      unselectedIconTheme: IconThemeData(
        size: AppImageSize.medium
      ),
      type: BottomNavigationBarType.fixed,
    );
  }
}
