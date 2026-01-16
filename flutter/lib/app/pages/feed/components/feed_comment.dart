import 'package:flutter/material.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/generated/assets.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:snoozespot/app/components/rounded.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';

class FeedComment extends StatelessWidget {
  final PostCommentDTO comment;

  const FeedComment({super.key, required this.comment});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: AppColor.white,
        border: Border(bottom: BorderSide(color: AppColor.grey, width: 1)),
      ),
      padding: EdgeInsets.symmetric(horizontal: 32, vertical: 12),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            // profil
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Row(
                children: [
                  Rounded(
                    child: Image(
                      image: comment.user.profilePicture?.path != null
                          ? NetworkImage(comment.user.profilePicture!.path)
                          : AssetImage(Assets.imagesLobster),
                      width: AppImageSize.xxlarge,
                      height: AppImageSize.xxlarge,
                    ),
                  ),
                  SizedBox(width: AppMargin.small),
                  Text(comment.user.username, style: AppTheme.titleMedium),
                ],
              ),
              Icon(Icons.more_vert),
            ],
          ),

          SizedBox(height: AppMargin.tiny),
          Text(comment.content),
        ],
      ),
    );
  }
}
