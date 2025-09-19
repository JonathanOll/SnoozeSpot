# SnoozeSpot  
Where naps meet the map.

---

# 📱 Mobile App Setup Guide (`/app`)

This guide explains how to run the mobile app located in the `app` subdirectory of the repository.  
Before launching the app, you **must run the `openApiGenerate` Gradle task** to generate the required API client code from the OpenAPI specification.  
You can do this either by running the command manually or by configuring Android Studio to run it automatically before each app start.

#### Configure Android Studio

1. Open the `app` subdirectory at the root of the repository in Android Studio.

2. Edit the automatically detected run configuration.

3. In the **Before launch** section, click **Add**, then select **Run Gradle task**.

4. Select the Gradle project `app:app` and enter `openApiGenerate` in the Tasks field.

5. Launch the app.


# Internal Notes
app de note de lieux de sieste

feed à la tweeter
->poster ça sieste

carte interactive
->point interêt avec catégorie
->note générale, bruit, exposition au vents, commentaire divers, photo

traqueur de sieste avec stats
-> détécter bruit environnement avec IA

un système de prestige de sieste

evennement sieste party

traduction ?

api maps