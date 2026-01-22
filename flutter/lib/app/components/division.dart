import 'package:flutter/cupertino.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';

class Division extends StatelessWidget {
  final List<Widget> children;

  const Division({
    super.key,
    required this.children,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: EdgeInsetsGeometry.symmetric(horizontal: AppMargin.medium, vertical: AppMargin.small),
      decoration: BoxDecoration(
          border: Border(
              bottom: BorderSide(color: AppColor.grey)
          )
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: children
      ),
    );
  }
}