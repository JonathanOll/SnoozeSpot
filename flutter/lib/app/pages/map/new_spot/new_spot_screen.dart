import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:snoozespot/app/components/division.dart';
import 'package:snoozespot/app/components/outlined_textfield.dart';
import 'package:snoozespot/resources/app_color.dart';
import 'package:snoozespot/resources/app_dimens.dart';
import 'package:snoozespot/resources/app_theme.dart';

class NewSpotResult {
  late final String name;
  late final String description;
  late final LatLng position;

  NewSpotResult({
    required this.name,
    required this.description,
    required this.position,
  });
}

class NewSpotScreen extends StatefulWidget {
  static const routeName = "/spot/new";

  const NewSpotScreen({super.key});

  @override
  State<NewSpotScreen> createState() => _NewSpotScreenState();
}

class _NewSpotScreenState extends State<NewSpotScreen> {
  final Completer<GoogleMapController> _mapController =
      Completer<GoogleMapController>();

  @override
  Widget build(BuildContext context) {
    final TextEditingController nameController = TextEditingController();
    final TextEditingController descriptionController = TextEditingController();

    return Scaffold(
      appBar: AppBar(
        title: Text("Création de spot"),
        centerTitle: false,
        actions: [
          IconButton(
            onPressed: () async {
              final GoogleMapController mapController =
                  await _mapController.future;
              final LatLngBounds bounds = await mapController
                  .getVisibleRegion();
              final LatLng center = LatLng(
                (bounds.northeast.latitude + bounds.southwest.latitude) / 2,
                (bounds.northeast.longitude + bounds.southwest.longitude) / 2,
              );

              print(context.mounted);

              if (context.mounted) {
                Navigator.of(context).pop(
                  NewSpotResult(
                    name: nameController.text,
                    description: descriptionController.text,
                    position: center,
                  ),
                );
              }
            },
            icon: Icon(Icons.check),
          ),
        ],
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              Division(
                children: [
                  Text("Informations", style: AppTheme.titleLarge),
                  OutlinedTextField(controller: nameController, hintText: "Name"),
                  SizedBox(height: AppMargin.small),
                  OutlinedTextField(
                    controller: descriptionController,
                    hintText: "Description",
                  ),
                ],
              ),
              Division(
                children: [
                  Text("Position", style: AppTheme.titleLarge),
                  SizedBox(
                    width: double.infinity,
                    height: 250,
                    child: Stack(
                      children: [
                        GoogleMap(
                          initialCameraPosition: CameraPosition(
                            target: LatLng(46.2, 5.21),
                            zoom: 14,
                          ),
                          onMapCreated: (controller) {
                            _mapController.complete(controller);
                          },
                        ),
                        Center(
                          child: Container(
                            width: 16,
                            height: 16,
                            decoration: BoxDecoration(
                              color: Colors.red,
                              shape: BoxShape.circle,
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
