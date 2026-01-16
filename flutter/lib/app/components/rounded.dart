import 'package:flutter/cupertino.dart';

class Rounded extends StatelessWidget {
  final double radius;
  final Widget child;

  const Rounded({
    super.key,
    required this.child,
    this.radius = 99999
  });

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(radius),
      child: child,
    );
  }
}