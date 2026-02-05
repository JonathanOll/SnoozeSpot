import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:provider/provider.dart';
import 'package:snoozespot/app/components/bottom_bar.dart';
import 'package:snoozespot/app/pages/map/map_screen_notifier.dart';
import 'package:snoozespot/app/pages/map/new_spot/new_spot_screen.dart';
import 'package:snoozespot/app/pages/map/spot_details/spot_details_screen.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  static const routeName = "/map";

  @override
  State<MapScreen> createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  final Completer<GoogleMapController> _controller =
      Completer<GoogleMapController>();

  @override
  void initState() {
    super.initState();
  }

  Future<void> fetchSpots() async {
    final GoogleMapController controller = await _controller.future;

    final LatLngBounds position = await controller.getVisibleRegion();
    final notifier = Provider.of<MapScreenNotifier>(context, listen: false);

    notifier.loadSpots(position);
  }

  @override
  Widget build(BuildContext context) {
    final notifier = context.watch<MapScreenNotifier>();

    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(onPressed: () async {
            var result = await Navigator.of(context).pushNamed(NewSpotScreen.routeName);
            if(result != null && result is NewSpotResult) {
              notifier.createSpot(result);
            }
          }, icon: const Icon(Icons.add))
        ],
      ),
      bottomNavigationBar: SnoozeSpotBottomBar(),
      body: SafeArea(
        child: SizedBox(
          width: double.infinity,
          height: double.infinity,
          child: GoogleMap(
            initialCameraPosition: CameraPosition(
              target: LatLng(46.2, 5.21),
              zoom: 14,
            ),
            onMapCreated: (GoogleMapController controller) {
              _controller.complete(controller);
            },
            onCameraIdle: () {
              fetchSpots();
            },
            markers: <Marker>{
              ...notifier.spots.map(
                (spot) => Marker(
                  position: LatLng(spot.latitude, spot.longitude),
                  markerId: MarkerId(spot.id.toString()),
                  onTap: () {
                    Navigator.of(context).pushNamed(SpotDetailsScreen.routeName, arguments: spot.id);
                  }
                ),
              ),
            },
          ),
        ),
      ),
    );
  }
}
