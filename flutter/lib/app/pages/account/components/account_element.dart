import 'package:flutter/material.dart';
import 'package:snoozespot/app/components/user_profile_picture.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class AccountElement extends StatelessWidget {
  final UserDTO user;

  const AccountElement({super.key, required this.user});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: AppColor.white,
        border: Border(bottom: BorderSide(color: AppColor.grey, width: 1)),
      ),
      padding: EdgeInsets.all(AppMargin.large),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [Icon(Icons.settings_outlined)],
          ),
          Center(
            child: Column(
              children: [
                UserProfilePicture(user: user, size: AppImageSize.xxxxlarge),
                SizedBox(width: AppMargin.small),
                Text(user.username, style: AppTheme.titleLargeBold),
                SizedBox(width: AppMargin.small),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    FilledButton(
                      onPressed: null,
                      child: Row(
                        children: [Icon(Icons.share_outlined), Text("Partager")],
                      ),
                    ),
                    SizedBox(width: AppMargin.tiny),
                    FilledButton(
                      onPressed: null,
                      child: Row(
                        children: [Icon(Icons.person_add), Text("Suivre")],
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
