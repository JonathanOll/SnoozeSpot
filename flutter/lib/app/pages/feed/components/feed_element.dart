import 'package:flutter/material.dart';
import 'package:snoozespot/app/components/user_profile_picture.dart';
import 'package:snoozespot/app/pages/feed/account_details/account_details_screen.dart';
import 'package:snoozespot/app/pages/feed/post_details/post_details_screen.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';
import 'package:snoozespot_api/snoozespot_api.dart';

class FeedElement extends StatelessWidget {
  final PostDTO post;
  final VoidCallback onLike;

  const FeedElement({super.key, required this.post, required this.onLike});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () { Navigator.of(context).pushNamed(PostDetailsScreen.routeName, arguments: post.id); },
      child: Container(
        decoration: BoxDecoration(
          color: AppColor.white,
          border: Border(bottom: BorderSide(color: AppColor.grey, width: 1)),
        ),
        padding: EdgeInsets.symmetric(horizontal: 16, vertical: 12),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              // profil
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                GestureDetector(
                  onTap: () { Navigator.of(context).pushNamed(AccountDetailsScreen.routeName, arguments: post.user.uuid); },
                  child: Row(
                    children: [
                      UserProfilePicture(user: post.user),
                      SizedBox(width: AppMargin.small),
                      Text(post.user.username, style: AppTheme.titleMedium),
                    ],
                  ),
                ),
                Icon(Icons.more_vert),
              ],
            ),
      
            SizedBox(height: AppMargin.small),
            Column(
              // contenu
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(post.content),
                SizedBox(height: AppMargin.tiny),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(
                    children: [
                      ...post.pictures.map(
                        (pic) => Padding(
                          padding: EdgeInsets.only(right: AppMargin.tiny),
                          child: Image(
                            image: NetworkImage(pic.path),
                            width: 150,
                            height: 150,
                            fit: BoxFit.cover,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
      
            SizedBox(height: AppMargin.small),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                OutlinedButton(
                  onPressed: () {},
                  child: Row(
                    children: [
                      Icon(Icons.comment_outlined),
                      SizedBox(width: AppMargin.small),
                      Text("Commentaires"),
                    ],
                  ),
                ),
                OutlinedButton(
                  style: AppTheme.themeData.outlinedButtonTheme.style,
                  onPressed: onLike,
                  child: Row(
                    children: [
                      Text(post.likeCount.toString()),
                      SizedBox(width: AppMargin.small),
                      Icon(
                        post.likedByUser ? Icons.favorite : Icons.favorite_border,
                        color: post.likedByUser ? Colors.red : null,
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
