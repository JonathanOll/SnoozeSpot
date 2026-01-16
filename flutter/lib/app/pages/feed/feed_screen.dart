import 'package:flutter/material.dart';
import 'package:snoozespot_api/snoozespot_api.dart';
import 'package:snoozespot/app/pages/feed/components/feed_element.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen.dart';

class FeedScreen extends StatelessWidget {
  final List<PostDTO> posts = List.generate(
    10,
    (index) => PostDTO(
      (b) => b
        ..id = 1
        ..user.replace(
          UserDTO(
            (u) => u
              ..username = 'John'
              ..uuid = 'aeae'
              ..karma = 0
              ..createdAt = DateTime.now()
              ..profilePicture.replace(
                StoredFileDTO(
                  (f) => f
                    ..uuid = ""
                    ..description = ""
                    ..type = StoredFileDTOTypeEnum.IMAGE
                    ..usage = StoredFileDTOUsageEnum.POST_MEDIA
                    ..createdAt = DateTime.now()
                    ..path =
                        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzb1_3zjhWSZTAVHPC2NcsivOHXtuMFHMXLtx2wpUWfcBKl_JCcc8tA8BZNHsjNq12ZJwwAv544NNb_dqlBPCp6P-b-2eStssPritslw&s=10',
                ),
              ),
          ),
        )
        ..content = 'Hello world!'
        ..likeCount = 10
        ..likedByUser = false
        ..createdAt = DateTime.now()
        ..pictures.replace([
          StoredFileDTO(
            (f) => f
              ..uuid = ""
              ..description = ""
              ..type = StoredFileDTOTypeEnum.IMAGE
              ..usage = StoredFileDTOUsageEnum.POST_MEDIA
              ..createdAt = DateTime.now()
              ..path =
                  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzb1_3zjhWSZTAVHPC2NcsivOHXtuMFHMXLtx2wpUWfcBKl_JCcc8tA8BZNHsjNq12ZJwwAv544NNb_dqlBPCp6P-b-2eStssPritslw&s=10',
          ),
        ]),
    ),
  );

  FeedScreen({super.key});

  static const routeName = "/feed";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Container(
          width: double.infinity,
          child: SingleChildScrollView(
            scrollDirection: Axis.vertical,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              children: [
                ...posts.map((post) => FeedElement(post: post)),
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(
                      context,
                    ).pushNamed(SpotDetailsScreen.routeName);
                  },
                  child: Text("autre page"),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
