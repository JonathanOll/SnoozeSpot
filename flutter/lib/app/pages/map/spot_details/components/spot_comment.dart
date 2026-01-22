import 'package:flutter/cupertino.dart';
import 'package:flutter_rating/flutter_rating.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/app/components/user_profile_picture.dart';
import 'package:snoozespot/app/pages/feed/account_details/account_details_screen.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';

class SpotComment extends StatelessWidget {
  final SpotCommentDTO comment;

  const SpotComment({super.key, required this.comment});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: EdgeInsetsGeometry.all(AppMargin.medium),
      decoration: BoxDecoration(
        border: Border(bottom: BorderSide(color: AppColor.grey)),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              GestureDetector(
                onTap: () {
                  Navigator.of(context).pushNamed(
                    AccountDetailsScreen.routeName,
                    arguments: comment.user.uuid,
                  );
                },
                child: Row(
                  children: [
                    UserProfilePicture(user: comment.user, size: AppImageSize.xlarge),
                    SizedBox(width: AppMargin.small),
                    Text(comment.user.username, style: AppTheme.titleMedium),
                  ],
                ),
              ),
              StarRating(rating: comment.rating.toDouble())
            ],
          ),
          Text(comment.description),
        ],
      ),
    );
  }
}
