import 'package:dio/dio.dart';
import 'package:snoozespot/storage/auth_store.dart';

class AuthInterceptor extends InterceptorsWrapper{
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    if (authStore.getJWT() != null) {
      options.headers["Authorization"] = "Bearer ${authStore.getJWT()}";
    }

    return super.onRequest(options, handler);
  }
}