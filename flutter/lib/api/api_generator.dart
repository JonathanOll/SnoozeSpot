// Openapi Generator last run: : 2026-02-05T08:55:48.068009
import 'package:openapi_generator_annotations/openapi_generator_annotations.dart';
import 'package:snoozespot/api/auth_interceptor.dart';
import 'package:snoozespot/api/snoozespot_api/lib/snoozespot_api.dart';

// pour générer : dart run build_runner build --delete-conflicting-outputs
// générer les .g : dart run build_runner build

const apiAdress = "https://localhost:8080";
final api = SnoozespotApi(basePathOverride: apiAdress, interceptors: [AuthInterceptor()]).getDefaultApi();

@Openapi(
  additionalProperties:
  DioProperties(pubName: 'snoozespot_api', pubAuthor: 'Faury Ollivier'),
  inputSpec: InputSpec(path: 'documentation.yaml'),
  generatorName: Generator.dio,
  runSourceGenOnOutput: true,
  outputDirectory: 'lib/api/snoozespot_api',
)
class ApiGenerator {}