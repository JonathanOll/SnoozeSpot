import 'package:flutter/cupertino.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/app/components/rounded.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class UserProfilePicture extends StatelessWidget {
  final UserDTO user;
  final double? size;

  const UserProfilePicture({
    super.key,
    required this.user,
    this.size,
  });

  @override
  Widget build(BuildContext context) {
    return Rounded(
      child: Image(
        image: user.profilePicture?.path != null
            ? NetworkImage(user.profilePicture!.path)
            : AssetImage(Assets.imagesLobster),
        width: size ?? AppImageSize.xxlarge,
        height: size ?? AppImageSize.xxlarge,
      ),
    );
  }
}