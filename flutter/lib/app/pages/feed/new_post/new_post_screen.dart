import 'package:flutter/material.dart';
import 'package:snoozespot/app/components/outlined_textfield.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class NewPostScreen extends StatelessWidget {
  const NewPostScreen({super.key});

  static const routeName = "/feed/new";

  @override
  Widget build(BuildContext context) {
    final TextEditingController contentController = TextEditingController();

    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.of(context).pop(contentController.text);
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
              OutlinedTextField(controller: contentController, hintText: 'Content')
            ],
          ),
        ),
      ),
    );
  }
}
