import 'package:flutter/material.dart';

import 'app_color.dart';
import 'app_dimens.dart';

class AppTheme {
  //region Text Styles

  static const titleLarge = TextStyle(
    fontSize: 30,
    fontWeight: FontWeight.w500,
  );

  static const titleMedium = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.w500
  );

  static const titleMediumBold = TextStyle(
      fontSize: 16,
      fontWeight: FontWeight.w700
  );

  static const titleLargeBold = TextStyle(
    fontSize: 24,
    fontWeight: FontWeight.w700,
  );

  static const titleSmall = TextStyle(
    fontSize: 18,
    fontWeight: FontWeight.w500,
  );

  static const titleSmallBold = TextStyle(
    fontSize: 18,
    fontWeight: FontWeight.w700,
  );

  static const bodyLargeBold = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.w900,
  );

  static const bodyLargeSemiSemiBold = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.w700,
  );

  static const bodyLargeMedium = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.w500,
  );

  static const bodyLarge = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.w400,
  );

  static const bodyBold = TextStyle(
    fontSize: 14,
    fontWeight: FontWeight.w900,
  );

  static const bodyMediumBold = TextStyle(
    fontSize: 14,
    fontWeight: FontWeight.w700,
  );

  static const bodyMedium = TextStyle(
    fontSize: 14,
    fontWeight: FontWeight.w400,
  );

  static const bodySmallBold = TextStyle(
    fontSize: 12,
    fontWeight: FontWeight.w700,
  );

  static const bodySmall = TextStyle(
    fontSize: 12,
    fontWeight: FontWeight.w500,
  );

  static const bodyTiny = TextStyle(
    fontSize: 12,
    fontWeight: FontWeight.w400,
  );

  static const labelSmall = TextStyle(
    fontSize: 10,
    fontWeight: FontWeight.w500,
  );

  static const scoreBig = TextStyle(
    fontSize: 70,
    fontWeight: FontWeight.w900,
  );

  static const headlineTiny = TextStyle(
      fontSize: 16,
      fontWeight: FontWeight.w700
  );

  static const headlineSmall = TextStyle(
      fontSize: 24,
      fontWeight: FontWeight.w700
  );

  static const headlineLarge = TextStyle(
    fontSize: 32,
    fontWeight: FontWeight.w700
  );

  static const displaySmall = TextStyle(
    fontSize: 36,
    fontWeight: FontWeight.w900,
  );

  static const displayMedium = TextStyle(
    fontSize: 46,
    fontWeight: FontWeight.w900,
  );

  static const scoreMedium = TextStyle(
    fontSize: 58,
    fontWeight: FontWeight.w900,
  );

  static const labelSmallBold = TextStyle(
    fontSize: 10,
    fontWeight: FontWeight.w700,
  );

//endregion

//region ThemeData
  static final themeData = ThemeData(
    useMaterial3: true,
    scaffoldBackgroundColor: AppColor.surface,
    dialogBackgroundColor: Colors.white,
    //fontFamily: 'Montserrat',
    textTheme: const TextTheme(
      titleSmall: titleSmall,
      bodyLarge: bodyLarge,
      bodyMedium: bodyMedium,
      bodySmall: bodySmall,
      labelSmall: labelSmall,
    ).apply(bodyColor: AppColor.darkBlue, displayColor: AppColor.darkBlue),
    appBarTheme: AppBarTheme(
      centerTitle: true,
      iconTheme: const IconThemeData(color: AppColor.darkBlue),
      color: AppColor.lightGrey,
      titleTextStyle: AppTheme.titleLargeBold.copyWith(
        height: 1,
        color: AppColor.darkBlue,
      ),
    ),
    dialogTheme: DialogThemeData(
      backgroundColor: AppColor.darkGrey,
      surfaceTintColor: Colors.transparent,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(AppRadius.xxlarge),
      ),
      titleTextStyle: AppTheme.bodyLargeBold.copyWith(color: Colors.white),
      contentTextStyle: AppTheme.bodyMedium.copyWith(color: Colors.white),
    ),
    tabBarTheme: TabBarThemeData(
      indicator: BoxDecoration(
          border: Border.all(color: AppColor.white, width:5, style: BorderStyle.none),
          borderRadius: BorderRadius.circular(40),
          color: AppColor.purple

      ),
      labelStyle: AppTheme.bodyLargeMedium,
      labelColor: Colors.white,
      unselectedLabelStyle: AppTheme.bodyLargeMedium,
      unselectedLabelColor: AppColor.darkBlue30,
      dividerColor: Colors.transparent,
      indicatorSize: TabBarIndicatorSize.tab,
    ),
    colorScheme: ColorScheme.fromSwatch(
      primarySwatch: AppColor.primary.getMaterialColor(),
      cardColor: Colors.white,
    ),
    filledButtonTheme: FilledButtonThemeData(
      style: FilledButton.styleFrom(
        backgroundColor: Colors.white,
        foregroundColor: AppColor.purple,
      ),
    ),
    outlinedButtonTheme: OutlinedButtonThemeData(
      style: OutlinedButton.styleFrom(
        foregroundColor: Colors.white,
      ),
    ),
    cardTheme: CardThemeData(
      shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.all(Radius.circular(AppRadius.small))),
      surfaceTintColor: Colors.transparent,
      color: AppColor.grey30,
      elevation: 8,
      shadowColor: AppColor.cardShadowColor,
      clipBehavior: Clip.hardEdge,
    ),
    textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(foregroundColor: AppColor.darkBlue)),
    bottomNavigationBarTheme: const BottomNavigationBarThemeData(
      selectedItemColor: AppColor.darkBlue,
      unselectedItemColor: AppColor.darkBlue,
      selectedLabelStyle: AppTheme.labelSmallBold,
      unselectedLabelStyle: AppTheme.labelSmallBold,
      showUnselectedLabels: true,
      showSelectedLabels: true,
    ),
    listTileTheme: const ListTileThemeData(dense: true),
    radioTheme: const RadioThemeData(
        visualDensity: VisualDensity(
            horizontal: VisualDensity.minimumDensity,
            vertical: VisualDensity.minimumDensity),
        materialTapTargetSize: MaterialTapTargetSize.shrinkWrap),
    checkboxTheme: CheckboxThemeData(
      visualDensity: const VisualDensity(
          horizontal: VisualDensity.minimumDensity,
          vertical: VisualDensity.minimumDensity),
      materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
      side: WidgetStateBorderSide.resolveWith(
        (states) => states.contains(WidgetState.selected)
            ? null
            : const BorderSide(width: 1.5, color: AppColor.darkBlue),
      ),
    ),
    switchTheme: SwitchThemeData(
      trackColor: WidgetStateProperty.resolveWith((states) =>
          states.contains(WidgetState.selected)
              ? AppColor.electricGreen
              : AppColor.grey),
      trackOutlineColor: WidgetStateProperty.resolveWith(
        (states) => states.contains(WidgetState.selected)
            ? AppColor.electricGreen
            : AppColor.grey,
      ),
    ),
  );
//endregion
}
