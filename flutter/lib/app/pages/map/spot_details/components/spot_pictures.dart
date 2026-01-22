import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/cupertino.dart';
import 'package:snoozespot/api/api_generator.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';
import 'package:snoozespot/generated/assets.dart';

class SpotPictures extends StatelessWidget {
  final SpotDTO spot;

  const SpotPictures({super.key, required this.spot});

  @override
  Widget build(BuildContext context) {
    return CarouselSlider(
      items: spot.pictures.isNotEmpty
          ? spot.pictures
                .map(
                  (p) => Builder(
                    builder: (BuildContext context) => Image(
                      width: double.infinity,
                      fit: BoxFit.cover,
                      image: NetworkImage(apiAdress + p.path),
                    ),
                  ),
                )
                .toList()
          : [
              Builder(
                builder: (BuildContext context) => Image(
                  width: double.infinity,
                  fit: BoxFit.cover,
                  image: AssetImage(Assets.imagesLobster),
                ),
              ),
            ],
      options: CarouselOptions(
        height: 300,
        viewportFraction: 1,
        enableInfiniteScroll: false,
      ),
    );
  }
}
