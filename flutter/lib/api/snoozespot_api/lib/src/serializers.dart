//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_import

import 'package:one_of_serializer/any_of_serializer.dart';
import 'package:one_of_serializer/one_of_serializer.dart';
import 'package:built_collection/built_collection.dart';
import 'package:built_value/json_object.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';
import 'package:built_value/iso_8601_date_time_serializer.dart';
import 'package:snoozespot_api/src/date_serializer.dart';
import 'package:snoozespot_api/src/model/date.dart';

import 'package:snoozespot_api/src/model/auth_response_dto.dart';
import 'package:snoozespot_api/src/model/create_post_comment_request.dart';
import 'package:snoozespot_api/src/model/create_spot_comment_request.dart';
import 'package:snoozespot_api/src/model/post_comment_dto.dart';
import 'package:snoozespot_api/src/model/post_dto.dart';
import 'package:snoozespot_api/src/model/spot_attribute_dto.dart';
import 'package:snoozespot_api/src/model/spot_comment_dto.dart';
import 'package:snoozespot_api/src/model/spot_dto.dart';
import 'package:snoozespot_api/src/model/stored_file_dto.dart';
import 'package:snoozespot_api/src/model/user_auth_request.dart';
import 'package:snoozespot_api/src/model/user_dto.dart';

part 'serializers.g.dart';

@SerializersFor([
  AuthResponseDTO,
  CreatePostCommentRequest,
  CreateSpotCommentRequest,
  PostCommentDTO,
  PostDTO,
  SpotAttributeDTO,
  SpotCommentDTO,
  SpotDTO,
  StoredFileDTO,
  UserAuthRequest,
  UserDTO,
])
Serializers serializers = (_$serializers.toBuilder()
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(SpotDTO)]),
        () => ListBuilder<SpotDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(PostDTO)]),
        () => ListBuilder<PostDTO>(),
      )
      ..addBuilderFactory(
        const FullType(BuiltList, [FullType(UserDTO)]),
        () => ListBuilder<UserDTO>(),
      )
      ..add(const OneOfSerializer())
      ..add(const AnyOfSerializer())
      ..add(const DateSerializer())
      ..add(Iso8601DateTimeSerializer()))
    .build();

Serializers standardSerializers =
    (serializers.toBuilder()..addPlugin(StandardJsonPlugin())).build();
