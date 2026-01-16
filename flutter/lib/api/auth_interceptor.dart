import 'package:dio/dio.dart';

class AuthInterceptor extends InterceptorsWrapper{
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    options.headers["Authorization"] = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwdWJsaWMtc25vb3plLXNwb3QtYXBpIiwic3ViIjoiMzM2ZjBiMzMtOTA0Mi00ZTEzLWIxMzAtZjk0NmRiNWIzNjVmIiwiaWF0IjoxNzY4NTczMjAzLCJleHAiOjE3Njg1NzY4MDN9.jkTH6Ya0MTE3Vva3-mml-9TutiKgqdiLl97ghORhl8E";

    return super.onRequest(options, handler);
  }
}