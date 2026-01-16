import 'package:flutter/material.dart';
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
