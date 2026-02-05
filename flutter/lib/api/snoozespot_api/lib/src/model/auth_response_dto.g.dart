// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auth_response_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$AuthResponseDTO extends AuthResponseDTO {
  @override
  final String accessToken;
  @override
  final int expiresIn;
  @override
  final UserDTO user;

  factory _$AuthResponseDTO([void Function(AuthResponseDTOBuilder)? updates]) =>
      (AuthResponseDTOBuilder()..update(updates))._build();

  _$AuthResponseDTO._({
    required this.accessToken,
    required this.expiresIn,
    required this.user,
  }) : super._();
  @override
  AuthResponseDTO rebuild(void Function(AuthResponseDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  AuthResponseDTOBuilder toBuilder() => AuthResponseDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is AuthResponseDTO &&
        accessToken == other.accessToken &&
        expiresIn == other.expiresIn &&
        user == other.user;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, accessToken.hashCode);
    _$hash = $jc(_$hash, expiresIn.hashCode);
    _$hash = $jc(_$hash, user.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'AuthResponseDTO')
          ..add('accessToken', accessToken)
          ..add('expiresIn', expiresIn)
          ..add('user', user))
        .toString();
  }
}

class AuthResponseDTOBuilder
    implements Builder<AuthResponseDTO, AuthResponseDTOBuilder> {
  _$AuthResponseDTO? _$v;

  String? _accessToken;
  String? get accessToken => _$this._accessToken;
  set accessToken(String? accessToken) => _$this._accessToken = accessToken;

  int? _expiresIn;
  int? get expiresIn => _$this._expiresIn;
  set expiresIn(int? expiresIn) => _$this._expiresIn = expiresIn;

  UserDTOBuilder? _user;
  UserDTOBuilder get user => _$this._user ??= UserDTOBuilder();
  set user(UserDTOBuilder? user) => _$this._user = user;

  AuthResponseDTOBuilder() {
    AuthResponseDTO._defaults(this);
  }

  AuthResponseDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _accessToken = $v.accessToken;
      _expiresIn = $v.expiresIn;
      _user = $v.user.toBuilder();
      _$v = null;
    }
    return this;
  }

  @override
  void replace(AuthResponseDTO other) {
    _$v = other as _$AuthResponseDTO;
  }

  @override
  void update(void Function(AuthResponseDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  AuthResponseDTO build() => _build();

  _$AuthResponseDTO _build() {
    _$AuthResponseDTO _$result;
    try {
      _$result =
          _$v ??
          _$AuthResponseDTO._(
            accessToken: BuiltValueNullFieldError.checkNotNull(
              accessToken,
              r'AuthResponseDTO',
              'accessToken',
            ),
            expiresIn: BuiltValueNullFieldError.checkNotNull(
              expiresIn,
              r'AuthResponseDTO',
              'expiresIn',
            ),
            user: user.build(),
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'user';
        user.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
          r'AuthResponseDTO',
          _$failedField,
          e.toString(),
        );
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
