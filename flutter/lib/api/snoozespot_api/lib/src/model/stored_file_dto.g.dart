// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'stored_file_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_UNKNOWN =
    const StoredFileDTOTypeEnum._('UNKNOWN');
const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_IMAGE =
    const StoredFileDTOTypeEnum._('IMAGE');
const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_VIDEO =
    const StoredFileDTOTypeEnum._('VIDEO');
const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_AUDIO =
    const StoredFileDTOTypeEnum._('AUDIO');
const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_DOCUMENT =
    const StoredFileDTOTypeEnum._('DOCUMENT');
const StoredFileDTOTypeEnum _$storedFileDTOTypeEnum_OTHER =
    const StoredFileDTOTypeEnum._('OTHER');

StoredFileDTOTypeEnum _$storedFileDTOTypeEnumValueOf(String name) {
  switch (name) {
    case 'UNKNOWN':
      return _$storedFileDTOTypeEnum_UNKNOWN;
    case 'IMAGE':
      return _$storedFileDTOTypeEnum_IMAGE;
    case 'VIDEO':
      return _$storedFileDTOTypeEnum_VIDEO;
    case 'AUDIO':
      return _$storedFileDTOTypeEnum_AUDIO;
    case 'DOCUMENT':
      return _$storedFileDTOTypeEnum_DOCUMENT;
    case 'OTHER':
      return _$storedFileDTOTypeEnum_OTHER;
    default:
      throw ArgumentError(name);
  }
}

final BuiltSet<StoredFileDTOTypeEnum> _$storedFileDTOTypeEnumValues =
    BuiltSet<StoredFileDTOTypeEnum>(const <StoredFileDTOTypeEnum>[
  _$storedFileDTOTypeEnum_UNKNOWN,
  _$storedFileDTOTypeEnum_IMAGE,
  _$storedFileDTOTypeEnum_VIDEO,
  _$storedFileDTOTypeEnum_AUDIO,
  _$storedFileDTOTypeEnum_DOCUMENT,
  _$storedFileDTOTypeEnum_OTHER,
]);

const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_UNKNOW =
    const StoredFileDTOUsageEnum._('UNKNOW');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_SPOT_ATTRIBUTES_ICON =
    const StoredFileDTOUsageEnum._('SPOT_ATTRIBUTES_ICON');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_PROFILE_PICTURE =
    const StoredFileDTOUsageEnum._('PROFILE_PICTURE');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_SPOT_MEDIA =
    const StoredFileDTOUsageEnum._('SPOT_MEDIA');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_POST_MEDIA =
    const StoredFileDTOUsageEnum._('POST_MEDIA');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_COMMENT_MEDIA =
    const StoredFileDTOUsageEnum._('COMMENT_MEDIA');
const StoredFileDTOUsageEnum _$storedFileDTOUsageEnum_OTHER =
    const StoredFileDTOUsageEnum._('OTHER');

StoredFileDTOUsageEnum _$storedFileDTOUsageEnumValueOf(String name) {
  switch (name) {
    case 'UNKNOW':
      return _$storedFileDTOUsageEnum_UNKNOW;
    case 'SPOT_ATTRIBUTES_ICON':
      return _$storedFileDTOUsageEnum_SPOT_ATTRIBUTES_ICON;
    case 'PROFILE_PICTURE':
      return _$storedFileDTOUsageEnum_PROFILE_PICTURE;
    case 'SPOT_MEDIA':
      return _$storedFileDTOUsageEnum_SPOT_MEDIA;
    case 'POST_MEDIA':
      return _$storedFileDTOUsageEnum_POST_MEDIA;
    case 'COMMENT_MEDIA':
      return _$storedFileDTOUsageEnum_COMMENT_MEDIA;
    case 'OTHER':
      return _$storedFileDTOUsageEnum_OTHER;
    default:
      throw ArgumentError(name);
  }
}

final BuiltSet<StoredFileDTOUsageEnum> _$storedFileDTOUsageEnumValues =
    BuiltSet<StoredFileDTOUsageEnum>(const <StoredFileDTOUsageEnum>[
  _$storedFileDTOUsageEnum_UNKNOW,
  _$storedFileDTOUsageEnum_SPOT_ATTRIBUTES_ICON,
  _$storedFileDTOUsageEnum_PROFILE_PICTURE,
  _$storedFileDTOUsageEnum_SPOT_MEDIA,
  _$storedFileDTOUsageEnum_POST_MEDIA,
  _$storedFileDTOUsageEnum_COMMENT_MEDIA,
  _$storedFileDTOUsageEnum_OTHER,
]);

Serializer<StoredFileDTOTypeEnum> _$storedFileDTOTypeEnumSerializer =
    _$StoredFileDTOTypeEnumSerializer();
Serializer<StoredFileDTOUsageEnum> _$storedFileDTOUsageEnumSerializer =
    _$StoredFileDTOUsageEnumSerializer();

class _$StoredFileDTOTypeEnumSerializer
    implements PrimitiveSerializer<StoredFileDTOTypeEnum> {
  static const Map<String, Object> _toWire = const <String, Object>{
    'UNKNOWN': 'UNKNOWN',
    'IMAGE': 'IMAGE',
    'VIDEO': 'VIDEO',
    'AUDIO': 'AUDIO',
    'DOCUMENT': 'DOCUMENT',
    'OTHER': 'OTHER',
  };
  static const Map<Object, String> _fromWire = const <Object, String>{
    'UNKNOWN': 'UNKNOWN',
    'IMAGE': 'IMAGE',
    'VIDEO': 'VIDEO',
    'AUDIO': 'AUDIO',
    'DOCUMENT': 'DOCUMENT',
    'OTHER': 'OTHER',
  };

  @override
  final Iterable<Type> types = const <Type>[StoredFileDTOTypeEnum];
  @override
  final String wireName = 'StoredFileDTOTypeEnum';

  @override
  Object serialize(Serializers serializers, StoredFileDTOTypeEnum object,
          {FullType specifiedType = FullType.unspecified}) =>
      _toWire[object.name] ?? object.name;

  @override
  StoredFileDTOTypeEnum deserialize(Serializers serializers, Object serialized,
          {FullType specifiedType = FullType.unspecified}) =>
      StoredFileDTOTypeEnum.valueOf(
          _fromWire[serialized] ?? (serialized is String ? serialized : ''));
}

class _$StoredFileDTOUsageEnumSerializer
    implements PrimitiveSerializer<StoredFileDTOUsageEnum> {
  static const Map<String, Object> _toWire = const <String, Object>{
    'UNKNOW': 'UNKNOW',
    'SPOT_ATTRIBUTES_ICON': 'SPOT_ATTRIBUTES_ICON',
    'PROFILE_PICTURE': 'PROFILE_PICTURE',
    'SPOT_MEDIA': 'SPOT_MEDIA',
    'POST_MEDIA': 'POST_MEDIA',
    'COMMENT_MEDIA': 'COMMENT_MEDIA',
    'OTHER': 'OTHER',
  };
  static const Map<Object, String> _fromWire = const <Object, String>{
    'UNKNOW': 'UNKNOW',
    'SPOT_ATTRIBUTES_ICON': 'SPOT_ATTRIBUTES_ICON',
    'PROFILE_PICTURE': 'PROFILE_PICTURE',
    'SPOT_MEDIA': 'SPOT_MEDIA',
    'POST_MEDIA': 'POST_MEDIA',
    'COMMENT_MEDIA': 'COMMENT_MEDIA',
    'OTHER': 'OTHER',
  };

  @override
  final Iterable<Type> types = const <Type>[StoredFileDTOUsageEnum];
  @override
  final String wireName = 'StoredFileDTOUsageEnum';

  @override
  Object serialize(Serializers serializers, StoredFileDTOUsageEnum object,
          {FullType specifiedType = FullType.unspecified}) =>
      _toWire[object.name] ?? object.name;

  @override
  StoredFileDTOUsageEnum deserialize(Serializers serializers, Object serialized,
          {FullType specifiedType = FullType.unspecified}) =>
      StoredFileDTOUsageEnum.valueOf(
          _fromWire[serialized] ?? (serialized is String ? serialized : ''));
}

class _$StoredFileDTO extends StoredFileDTO {
  @override
  final String uuid;
  @override
  final String description;
  @override
  final String path;
  @override
  final StoredFileDTOTypeEnum type;
  @override
  final StoredFileDTOUsageEnum usage;
  @override
  final DateTime? createdAt;

  factory _$StoredFileDTO([void Function(StoredFileDTOBuilder)? updates]) =>
      (StoredFileDTOBuilder()..update(updates))._build();

  _$StoredFileDTO._(
      {required this.uuid,
      required this.description,
      required this.path,
      required this.type,
      required this.usage,
      this.createdAt})
      : super._();
  @override
  StoredFileDTO rebuild(void Function(StoredFileDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  StoredFileDTOBuilder toBuilder() => StoredFileDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is StoredFileDTO &&
        uuid == other.uuid &&
        description == other.description &&
        path == other.path &&
        type == other.type &&
        usage == other.usage &&
        createdAt == other.createdAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, uuid.hashCode);
    _$hash = $jc(_$hash, description.hashCode);
    _$hash = $jc(_$hash, path.hashCode);
    _$hash = $jc(_$hash, type.hashCode);
    _$hash = $jc(_$hash, usage.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'StoredFileDTO')
          ..add('uuid', uuid)
          ..add('description', description)
          ..add('path', path)
          ..add('type', type)
          ..add('usage', usage)
          ..add('createdAt', createdAt))
        .toString();
  }
}

class StoredFileDTOBuilder
    implements Builder<StoredFileDTO, StoredFileDTOBuilder> {
  _$StoredFileDTO? _$v;

  String? _uuid;
  String? get uuid => _$this._uuid;
  set uuid(String? uuid) => _$this._uuid = uuid;

  String? _description;
  String? get description => _$this._description;
  set description(String? description) => _$this._description = description;

  String? _path;
  String? get path => _$this._path;
  set path(String? path) => _$this._path = path;

  StoredFileDTOTypeEnum? _type;
  StoredFileDTOTypeEnum? get type => _$this._type;
  set type(StoredFileDTOTypeEnum? type) => _$this._type = type;

  StoredFileDTOUsageEnum? _usage;
  StoredFileDTOUsageEnum? get usage => _$this._usage;
  set usage(StoredFileDTOUsageEnum? usage) => _$this._usage = usage;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  StoredFileDTOBuilder() {
    StoredFileDTO._defaults(this);
  }

  StoredFileDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _uuid = $v.uuid;
      _description = $v.description;
      _path = $v.path;
      _type = $v.type;
      _usage = $v.usage;
      _createdAt = $v.createdAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(StoredFileDTO other) {
    _$v = other as _$StoredFileDTO;
  }

  @override
  void update(void Function(StoredFileDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  StoredFileDTO build() => _build();

  _$StoredFileDTO _build() {
    final _$result = _$v ??
        _$StoredFileDTO._(
          uuid: BuiltValueNullFieldError.checkNotNull(
              uuid, r'StoredFileDTO', 'uuid'),
          description: BuiltValueNullFieldError.checkNotNull(
              description, r'StoredFileDTO', 'description'),
          path: BuiltValueNullFieldError.checkNotNull(
              path, r'StoredFileDTO', 'path'),
          type: BuiltValueNullFieldError.checkNotNull(
              type, r'StoredFileDTO', 'type'),
          usage: BuiltValueNullFieldError.checkNotNull(
              usage, r'StoredFileDTO', 'usage'),
          createdAt: createdAt,
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
