import 'package:flutter/material.dart';
import 'package:flutter_rating/flutter_rating.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class NewSpotCommentResult {
  final String content;
  final double rating;

  NewSpotCommentResult({
    required this.content,
    required this.rating,
  });
}


class NewSpotCommentScreen extends StatefulWidget {

  const NewSpotCommentScreen({super.key});

  static const routeName = "/spots/add-comment";

  @override
  State<NewSpotCommentScreen> createState() => _NewSpotCommentScreenState();
}

class _NewSpotCommentScreenState extends State<NewSpotCommentScreen> {
  double rating = 3;
  late final TextEditingController contentController;

  @override
  void initState() {
    super.initState();
    contentController = TextEditingController();
  }

  @override
  void dispose() {
    contentController.dispose();
    super.dispose();
  }

  void _setRating(double r) {
    setState(() {
      rating = r;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.of(context).pop(NewSpotCommentResult(content: contentController.text, rating: rating));
        },
        child: Icon(Icons.check),
      ),
      body: SafeArea(
        child: Container(
          padding: EdgeInsets.all(AppMargin.medium),
          width: double.infinity,
          height: double.infinity,
          child: Column(
            children: [
              StarRating(rating: rating, allowHalfRating: false, onRatingChanged: (rating) => _setRating(rating)),
              TextField(
                controller: contentController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.black),
                  ),
                  enabledBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.black),
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.black, width: 2),
                  ),
                  hintText: 'Content',
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
