import 'package:flutter/material.dart';
import 'package:flutter_rating/flutter_rating.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/division.dart';
import 'package:snoozespot/app/pages/map/spotdetails/components/spot_comment.dart';
import 'package:snoozespot/app/pages/map/spotdetails/components/spot_map.dart';
import 'package:snoozespot/app/pages/map/spotdetails/components/spot_pictures.dart';
import 'package:snoozespot/app/pages/map/spotdetails/spot_details_screen_notifier.dart';
import 'package:snoozespot/resources/app_theme.dart';

class SpotDetailsScreen extends StatefulWidget {
  static const List<String> gradeCommentaries = [
    "Très Mauvais",
    "Mauvais",
    "Passable",
    "Bon",
    "Très bon",
  ];
  const SpotDetailsScreen({super.key});

  static const routeName = "/spot/details";

  @override
  State<SpotDetailsScreen> createState() => _SpotDetailsScreenState();
}

class _SpotDetailsScreenState extends State<SpotDetailsScreen> {
  late final int id;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      id = ModalRoute.of(context)!.settings.arguments as int;
      final notifier = Provider.of<SpotDetailsScreenNotifier>(
        context,
        listen: false,
      );
      notifier.loadSpot(id);
    });
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<SpotDetailsScreenNotifier>();

    return Scaffold(
      body: SafeArea(
        child: notifier.spot == null
            ? Text("loading...")
            : SingleChildScrollView(
              child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SpotPictures(spot: notifier.spot!),
                    Division(
                      children: [
                        Text(notifier.spot!.name, style: AppTheme.titleLargeBold),
                      ],
                    ),
                    Division(children: [Text(notifier.spot!.description)]),
                    Division(
                      children: [
                        Row(
                          children: [
                            Icon(Icons.location_on_outlined),
                            Text("Emplacement", style: AppTheme.titleMedium),
                          ],
                        ),
                      ],
                    ),
                    SpotMap(spot: notifier.spot!),
                    Division(
                      children: [
                        Row(
                          children: [
                            Icon(Icons.reviews),
                            SizedBox(width: 8),
                            Text(
                              "Avis des dormeurs",
                              style: AppTheme.titleMedium,
                            ),
                          ],
                        ),
                        notifier.spot!.rating == null
                            ? Center(child: Text("Aucun avis"))
                            : Column(
                                children: [
                                  StarRating(rating: notifier.spot!.rating!),
                                  Text("${notifier.spot!.rating!.toStringAsFixed(1)}/5 - ${SpotDetailsScreen.gradeCommentaries[notifier.spot!.rating!.round() - 1]}")
                                ],
                              ),
                      ],
                    ),
                    SizedBox(
                      width: double.infinity,
                      child: Column(
                        children: [
                          ...notifier.spot!.comments.map(
                                  (comment) => SpotComment(comment: comment)
                          ),
                          ElevatedButton(onPressed: () {}, child: Text("Ajouter un commentaire"))
                        ],
                      ),
                    )
                  ],
                ),
            ),
      ),
    );
  }
}
