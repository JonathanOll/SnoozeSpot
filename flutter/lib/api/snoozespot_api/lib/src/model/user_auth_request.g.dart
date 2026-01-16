// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_auth_request.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$UserAuthRequest extends UserAuthRequest {
  @override
  final String username;
  @override
  final String password;
  @override
  final String? email;

  factory _$UserAuthRequest([void Function(UserAuthRequestBuilder)? updates]) =>
      (UserAuthRequestBuilder()..update(updates))._build();

  _$UserAuthRequest._(
      {required this.username, required this.password, this.email})
      : super._();
  @override
  UserAuthRequest rebuild(void Function(UserAuthRequestBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  UserAuthRequestBuilder toBuilder() => UserAuthRequestBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is UserAuthRequest &&
        username == other.username &&
        password == other.password &&
        email == other.email;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, username.hashCode);
    _$hash = $jc(_$hash, password.hashCode);
    _$hash = $jc(_$hash, email.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'UserAuthRequest')
          ..add('username', username)
          ..add('password', password)
          ..add('email', email))
        .toString();
  }
}

class UserAuthRequestBuilder
    implements Builder<UserAuthRequest, UserAuthRequestBuilder> {
  _$UserAuthRequest? _$v;

  String? _username;
  String? get username => _$this._username;
  set username(String? username) => _$this._username = username;

  String? _password;
  String? get password => _$this._password;
  set password(String? password) => _$this._password = password;

  String? _email;
  String? get email => _$this._email;
  set email(String? email) => _$this._email = email;

  UserAuthRequestBuilder() {
    UserAuthRequest._defaults(this);
  }

  UserAuthRequestBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _username = $v.username;
      _password = $v.password;
      _email = $v.email;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(UserAuthRequest other) {
    _$v = other as _$UserAuthRequest;
  }

  @override
  void update(void Function(UserAuthRequestBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  UserAuthRequest build() => _build();

  _$UserAuthRequest _build() {
    final _$result = _$v ??
        _$UserAuthRequest._(
          username: BuiltValueNullFieldError.checkNotNull(
              username, r'UserAuthRequest', 'username'),
          password: BuiltValueNullFieldError.checkNotNull(
              password, r'UserAuthRequest', 'password'),
          email: email,
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
