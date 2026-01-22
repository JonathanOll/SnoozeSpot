import 'package:flutter/material.dart';

class OutlinedTextField extends StatelessWidget {

  final TextEditingController? controller;
  final String hintText;

  const OutlinedTextField({super.key, required this.controller, required this.hintText});

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: controller,
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
        hintText: hintText,
      ),
    );
  }

}