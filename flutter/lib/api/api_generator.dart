// Openapi Generator last run: : 2026-01-16T10:23:00.529992
import 'package:openapi_generator_annotations/openapi_generator_annotations.dart';

// pour générer : dart run build_runner build --delete-conflicting-outputs

@Openapi(
  additionalProperties:
  DioProperties(pubName: 'snoozespot_api', pubAuthor: 'Faury Ollivier'),
  inputSpec: InputSpec(path: 'documentation.yaml'),
  generatorName: Generator.dio,
  runSourceGenOnOutput: true,
  outputDirectory: 'lib/api/snoozespot_api',
)
class ApiGenerator {}