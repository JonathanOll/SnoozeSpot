import 'package:flutter/material.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class BigButton extends StatelessWidget {

  final Widget child;
  final VoidCallback onPressed;
  final Color textColor;
  final Color backColor;

  const BigButton({super.key, required this.child, required this.onPressed, required this.textColor, required this.backColor});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: FilledButton(
        style: ButtonStyle(
          backgroundColor: MaterialStateProperty.all(backColor),
          foregroundColor: MaterialStateProperty.all(textColor),
          shape: MaterialStateProperty.all(
            RoundedRectangleBorder( borderRadius: BorderRadius.circular(AppRadius.small) )
          ),
          padding: MaterialStateProperty.all(
            const EdgeInsets.symmetric(vertical: AppMargin.medium)
          )
        ),
        onPressed: onPressed,
        child: child,
      ),
    );
  }

}