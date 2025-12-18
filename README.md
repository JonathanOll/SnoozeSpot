# SnoozeSpot  
Where naps meet the map.

L'ancien repo (avec l'historique des PRs qui ne peut pas être migré) : 
https://github.com/Cortard/SnoozeSpot/pulls?q=is%3Apr+is%3Aclosed

---

# ⚙️ Api Setup Guide (`/api`)

This guide explains how to run the mobile api located in the `api` subdirectory of the repository.  

#### Run the API:

`cd api` -> `./gradlew run`

#### Default stored file path settings:

- STORED_FILE_PATH = ".\\src\\main\\resources\\static\\files" **Must use \\\ as delimiter.**
- STORED_FILE_REMOTE_PATH = "/files" **Must use / as delimiter.**
- 
*Optional: override these environment variables for production.*

#### Default JWT settings:

- JWT_SECRET = "dev-secret-to-change"
- JWT_ISSUER = "public-snooze-spot-api"
- JWT_EXPIRATION_SECONDS = 3600

*Optional: override these environment variables for production.*

#### Default SSL auto-signed certificate settings:

- SSL_CERTIFICATE_ALIAS = "certificateAlias"
- SSL_CERTIFICATE_PASSWORD = "my-best-password"
- SSL_CERTIFICATE_FILE_PASSWORD = "my-second-best-password"

*Optional: override these environment variables for production.*

---

# 📱 Mobile App Setup Guide (`/app`)

This guide explains how to run the mobile app with android studio located in the `app` subdirectory of the repository.  
Before launching the app, you **must run the `openApiGenerate` Gradle task** to generate the required API client code from the OpenAPI specification.  
You can do this either by running the command manually or by configuring Android Studio to run it automatically before each app start.

#### Configure Android Studio

1. Open the `app` subdirectory at the root of the repository in Android Studio.

2. Edit the automatically detected run configuration.

3. In the **Before launch** section, click **Add**, then select **Run Gradle task**.

4. Select the Gradle project `app:app` and enter `preBuild` in the Tasks field.

5. Put the created task before the build

6. At the root of the `app` directory, create a file called secret.properties in which you include your Google Map API Key (format: `MAPS_API_KEY = my_key`)

7. Launch the app.

*In debug mode, the API uses a self-signed certificate. This certificate is copied into the Android res/raw directory so the app can explicitly trust it. If the API is restarted, run the copyCertificate Gradle task and then restart the app — this task is automatically included in the preBuild step.*

---

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
